pipeline {
  agent any
  stages {
    stage('clean') {
      steps {
        sh './gradlew clean --stacktrace'
      }
    }
    stage('Unit tests usecases') {
      steps {
        sh './gradlew usecases:clean usecases:test --stacktrace'
      }
    }
    stage('Unit Test domain') {
      steps {
        sh './gradlew domain:clean domain:test --stacktrace'
      }
    }
    stage('Unit Test data') {
        steps {
         sh './gradlew data:clean data:test --stacktrace'
        }
     }
    stage('Unit Test Common') {
       steps {
              sh './gradlew common:clean common:test --stacktrace'
        }
     }
    stage('Unit Test TeamDetail') {
        steps {
               sh './gradlew teamdetail:clean teamdetail:test --stacktrace'
         }
      }
    stage('Unit Test Team') {
         steps {
                sh './gradlew team:clean team:test --stacktrace'
          }
    }
    stage('Unit Test League') {
         steps {
                sh './gradlew league:clean league:test --stacktrace'
          }
    }
  }
}
