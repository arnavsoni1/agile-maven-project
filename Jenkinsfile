pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "arnavsoni2007:simple-maven-app:latest"
    }

    tools {
        maven 'M3'
    }

    stages {
        stage('Checkout Git') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Build Docker Image') {
            steps {
                powershell 'docker build -t "${env:IMAGE}:${env:BUILD_NUMBER}" -t "${env:IMAGE}:latest" .'
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-creds',
                    usernameVariable: 'DOCKERHUB_USER',
                    passwordVariable: 'DOCKERHUB_TOKEN'
                )]) {
                    powershell '''
                        $env:DOCKERHUB_TOKEN | docker login --username $env:DOCKERHUB_USER --password-stdin
                        docker push "${env:IMAGE}:${env:BUILD_NUMBER}"
                        docker push "${env:IMAGE}:latest"
                    '''
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                powershell '''
                    kubectl apply -f deployment.yaml
                '''
            }
        }
    }
}
