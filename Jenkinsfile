pipeline {
    agent any
    tools { 
        maven 'maven 3.8.6' 
    
    }
    environment{
        APP_NAME="cafebean-backend"
        RELEASE="0.0.1-SNAPSHOT"
        DOCKER_USER="janmejaym1"
        DOCKER_PASS="09c96828-5bea-44e5-bff8-4fb301fc8ab6"
        IMAGE_NAME="${DOCKER_USER}"+"/"+"{$APP_NAME}"
        IMAGE_TAG="${RELEASE}-${BUILD_NUMBER}"

    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build') {
            steps {
                sh 'pwd'
                
                sh 'mvn clean install -f /Users/janmejaymohapatra/.jenkins/workspace/cafebean-backend-pipeline/Cafe-Bean'
            }
        }

        
        stage ('Docker Build') {
          
      steps {
          script{
            
              sh 'echo start docker'
              sh 'cd /Users/janmejaymohapatra/.jenkins/workspace/cafebean-backend-pipeline/Cafe-Bean'
              docker.withRegistry('https://hub.docker.com/',DOCKER_PASS){
                  docker_image=docker.build "${IMAGE_NAME}"
              }
                docker.withRegistry('https://hub.docker.com/',DOCKER_PASS){
                  docker_image.push ("${IMAGE_TAG}")
                  docker_image.push ("latest")
  
              }

              
          }
           
         }
        }
        
    }
}
