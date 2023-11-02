pipeline {
    agent any

    environment {
        // Define the desired path where you want to copy the files
        TARGET_PATH = 'D:\D drive\Jenkins_Files'
    }

    stages {
        
        stage('Checkout') {
            steps {
                // This step checks out the code from the Git repository
                git(url: 'https://github.com/iqbal-di-456/JenkinsTest.git', branch: "${env.BRANCH_NAME}")
            }
        }

         stage('Copy Files') {
            steps {
                // This step copies the files to the desired path
                script {
                    sh "cp -r * ${env.TARGET_PATH}"
                }
            }
        }

         stage('Build') {
            steps {
                // You can add your build steps here
                // For example, if you're using Maven for a Java project
                // sh 'mvn clean install'
                echo 'building the application...'
                echo "Current branch is: ${env.BRANCH_NAME}"

                script {
                    if (env.BRANCH_NAME == 'dev') {
                        echo 'Condition Executed Successfully...'
                        }
                    else {
                        echo "Skipping Condition for ${env.BRANCH_NAME} env"
                        }
                    }
            }
		}

        stage('Test') {
            steps {
                // You can add your test steps here
                // For example, running unit tests
                // sh 'mvn test'  
              echo 'testing the application...'
            }
        }

        stage('Deploy') {
            steps {
                // You can add your test steps here
                // For example, running unit tests
                // sh 'mvn test'  
              echo 'deploying the application...'
            }
        }
    }

    post {
        success {
            // You can add post-build actions here for success
            echo 'build was success'
        }
        failure {
            // You can add post-build actions here for failure
            echo 'build failed'
        }
    }

}
