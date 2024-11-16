pipeline {
    agent any
    tools {
        jdk 'jdk17'
        maven 'maven3'
    }
    environment {
        DOCKER_IMAGE = 'diouf173/hospital:latest'
    }
    stages {
        stage('Checkout From Git') {
            steps {
                git branch: 'master', url: 'https://github.com/papadiouf13/hospital-api.git'
            }
        }
        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('List Target Directory') {
            steps {
                sh 'ls -la target'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                withCredentials([string(credentialsId: 'docker-hub-credentials', variable: 'DOCKER_HUB_TOKEN')]) {
                    sh '''
                    echo $DOCKER_HUB_TOKEN | docker login -u diouf173 --password-stdin
                    docker push $DOCKER_IMAGE
                    '''
                }
            }
        }
    }
}
