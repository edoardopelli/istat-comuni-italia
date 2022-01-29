pipeline {
  agent any
  stages {
    stage('compile') {
      steps {
        git(url: 'https://github.com/edoardopelli/istat-comuni-italia.git', branch: 'develop', credentialsId: 'GitHub', changelog: true, poll: true)
      }
    }

  }
}