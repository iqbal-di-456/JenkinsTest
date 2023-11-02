pipeline {
    agent any

    environment {
        // Define Git repository URL and desired destination path
        GIT_REPO_URL = 'https://github.com/iqbal-di-456/JenkinsTest.git'
        DESTINATION_PATH = 'D:/D drive/Jenkins_Files'
    }

    stages {

        stage('Clone Repository') {
            steps {
                script {
                    // Clone the repository
                    checkout([$class: 'GitSCM', userRemoteConfigs: [[url: env.GIT_REPO_URL]]])
                }
            }
        }

        stage('Copy Files') {
            steps {
                script {
                    // Define source path (current workspace)
                    def sourcePath = env.WORKSPACE

                    // Create the destination directory if it doesn't exist
                    bat "if not exist \"${env.DESTINATION_PATH}\" mkdir \"${env.DESTINATION_PATH}\""

                    // Copy files from source to destination
                    bat "xcopy /S /Y \"${sourcePath}\" \"${env.DESTINATION_PATH}\""
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
