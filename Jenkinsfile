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
                junit '**/test_runner_xml_file/*.xml'
//                 archiveArtifacts artifacts: '**/test_runner_xml_file/*.xml', fingerprint: true
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}