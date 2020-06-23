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
        sh './gradlew usecases:clean data:testDebugUnitTest --stacktrace'
      }
    }
    stage('Unit Test domain') {
      steps {
        sh './gradlew domain:clean domain:testDebugUnitTest --stacktrace'
      }
    }
    stage('Unit Test data') {
        steps {
         sh './gradlew data:clean domain:testDebugUnitTest --stacktrace'
        }
     }
  }
}
