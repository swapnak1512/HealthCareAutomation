pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/swapnak1512/HealthCareAutomation.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo "Pipeline is working"
                sh 'mvn test'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
}
