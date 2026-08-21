pipeline {

    agent any

    environment {
        IMAGE_NAME = 'manish28/java_program_k8'
        IMAGE_TAG  = 'latest'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/manish2802/Java_Program_K8.git'
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                    docker build \
                    -t ${IMAGE_NAME}:${IMAGE_TAG} \
                    -t ${IMAGE_NAME}:latest .
                '''
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_PASSWORD" | docker login \
                        -u "$DOCKER_USERNAME" \
                        --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh '''
                    docker push ${IMAGE_NAME}:${IMAGE_TAG}
                    docker push ${IMAGE_NAME}:latest
                '''
            }
        }

        stage('Kubernetes Deploy') {
            steps {
               sh '''
            echo "=== Apply Kubernetes Resources ==="
            kubectl apply -f k8s/

            echo "=== Namespace ==="
            kubectl get namespace java-program

            echo "=== Deployments ==="
            kubectl get deployments -n java-program

            echo "=== Pods Before Rollout ==="
            kubectl get pods -n java-program -o wide

            echo "=== Rolling Restart ==="
            kubectl rollout restart deployment/java-program \
                -n java-program

            echo "=== Waiting for Rollout ==="
            kubectl rollout status deployment/java-program \
                -n java-program

            echo "=== Final Pods ==="
            kubectl get pods -n java-program -o wide

            echo "=== Services ==="
            kubectl get svc -n java-program
        '''
            }
        }
    }
}