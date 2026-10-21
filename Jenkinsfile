pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "arnavsoni2007/simple-maven-app"
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
                bat 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                powershell 'docker build -t "${env:DOCKER_IMAGE}:${env:BUILD_NUMBER}" -t "${env:DOCKER_IMAGE}:latest" .'
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
                        docker push "${env:DOCKER_IMAGE}:${env:BUILD_NUMBER}"
                        docker push "${env:DOCKER_IMAGE}:latest"
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
