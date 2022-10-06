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
                sh 'make check || true'
                junit '**/test_runner_xml_file/*.xml'
      }
      post {
          always {
              step([$class: 'Publisher', reportFilenamePattern: 'file:/home/testng-results.xml'])
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