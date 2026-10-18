pipeline {
  agent any
  tools {
    maven 'Maven3'
    jdk 'JDK17'
  }
  environment {
    IMAGE_NAME = 'devops-portfolio'
    // Configure these in Jenkins or replace with your registry details.
    REGISTRY = 'docker.io'
    IMAGE_TAG = "${BUILD_NUMBER}"
  }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build & Test') {
      steps { sh 'mvn -B clean verify' }
      post { always { junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml' } }
    }
    stage('Build Container') {
      steps { sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .' }
    }
    stage('Publish Container') {
      when { expression { return env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master' } }
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
          sh '''
            echo "$DOCKER_PASS" | docker login "$REGISTRY" -u "$DOCKER_USER" --password-stdin
            docker tag "$IMAGE_NAME:$IMAGE_TAG" "$DOCKER_USER/$IMAGE_NAME:$IMAGE_TAG"
            docker tag "$IMAGE_NAME:$IMAGE_TAG" "$DOCKER_USER/$IMAGE_NAME:latest"
            docker push "$DOCKER_USER/$IMAGE_NAME:$IMAGE_TAG"
            docker push "$DOCKER_USER/$IMAGE_NAME:latest"
            docker logout "$REGISTRY"
          '''
        }
      }
    }
  }
  post {
    always { sh 'docker image prune -f || true' }
  }
}
