// pipeline {
//     agent any
//
//     stages {
//         stage('Build') {
//             steps {
//                 echo 'Building..'
//             }
//         }
//         stage('Test') {
//             steps {
//                  archiveArtifacts artifacts: '**/test_runner_xml_file/test_runner.xml', fingerprint: true
//             }
//         }
//         stage('Deploy') {
//             steps {
//                 echo 'Deploying....'
//             }
//         }
//     }
// }
// return true if job is trigger by GitHub push to 'main' branch
Boolean triggeredByDev() {
  return (env.GIT_BRANCH == 'main')
}

List getXmlFiles(String dirName) {
  try {
      fileList = sh(returnStdout: true, script: "cd ${dirName} && ls" ).trim()
  } catch (ex){
      error("Details: ${ex.getMessage()}")
  }
  return fileList.split("\\r?\\n")
}

pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                script {
                     for (String xml : getXmlFiles('test_runner_xml_file')) {
                         sh "java -Dspring.profiles.active=integration -jar test/build/libs/test-0.1.0.jar test_runner_xml_file/$xml || true"
                     }
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}