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
  }
}
