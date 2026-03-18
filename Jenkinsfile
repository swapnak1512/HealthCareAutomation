pipeline {
    agent any

    tools {
        maven 'Maven'   // must match name in Jenkins config
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/swapnak1512/HealthCareAutomation.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}
