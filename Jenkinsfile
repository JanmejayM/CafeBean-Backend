pipeline {
    agent any
    tools { 
        maven 'maven 3.8.6' 
    
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
                     sh 'cd /Users/janmejaymohapatra/.jenkins/workspace/cafebean-backend-pipeline/Cafe-Bean'
                     
                }
               
                
            }
        }
        
    }
}
