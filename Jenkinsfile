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
//                 sh 'make check'
//                 junit '**/test_runner_xml_file/test_runner.xml'
//             }
//         }
//         stage('Deploy') {
//             steps {
//                 echo 'Deploying....'
//             }
//         }
//     }
// }
Boolean triggeredByDev() {
  return (env.GIT_BRANCH == 'main')
}

String BUILD_BRANCH_NAME ='main'

env.Environment = "stage"
env.ACTIVE_PROFILE = "integration"
env.ACTIVE_PROFILE_CONFIG_SERVICE = "integration"
env.CONFIG_PREFIX = "stage"

String determineRepoName = scm.getUserRemoteConfigs()[0].getUrl().tokenize('/').last().split("\\.")[0]

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

 options {
    ansiColor('xterm')
    skipStagesAfterUnstable()
    disableConcurrentBuilds()
    buildDiscarder(logRotator(numToKeepStr: '5'))
 }

    stage('Test') {
      when {
        anyOf {
          expression { triggeredByDev() }
        }
      }
      steps {
        script {
          for (String xml : getXmlFiles('test_runner_xml_files')) {
            sh "java -Dspring.profiles.active=integration -jar build/libs/ua_readiness-0.1.0.jar test_runner_xml_files/$xml || true"
          }
        }
      }
      post {
          always {
              step([$class: 'Publisher', reportFilenamePattern: 'file:/home/testng-results.xml'])
          }
      }
    }
  }

  post {
    always {
      sh("sendMessage 60585ac798fe9065f52135e7 \"Status:  ${currentBuild.currentResult}\"")
    }
  }
}