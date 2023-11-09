pipeline {
    agent any

    options {
          buildBlocker (useBuildBlocker: true, blockLevel: 'GLOBAL', blockingJobs: 'my-test-jenkins/.*')
    }

    environment {
        // Define Git repository URL and desired destination path
        // GIT_REPO_URL = 'https://github.com/iqbal-di-456/JenkinsTest.git'
        DESTINATION_PATH = 'D:/D drive/Jenkins_Files'
    }

    stages {

        /**
            Cleaning the workspace by removing the old file from temporary storage
        **/
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        /**
            Cloning the repository to the local temporary storage
        **/
        stage('Clone Repository') {
            steps {
                script {
                    // Clone the repository
                    withCredentials([usernamePassword(credentialsId: 'c17f17fc-1058-4f9a-b0e0-e2ddf272f29c', usernameVariable: 'miqbal@datainnovations.com', passwordVariable: 'Shinigami@456')]) {
                        checkout([$class: 'GitSCM', branches: [[name: "${env.BRANCH_NAME}"]], userRemoteConfigs: [[url: 'https://github.com/iqbal-di-456/JenkinsTest.git', credentialsId: 'c17f17fc-1058-4f9a-b0e0-e2ddf272f29c']]])
                    }
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
                echo "Current branch is: ${env.BRANCH_NAME}  - usign BRANCH_NAME tag"
                echo "Current branch is: ${env.GIT_BRANCH}  - usign GIT_BRANCH tag"

                script {
                    if (env.BRANCH_NAME == 'dev') {
                        echo 'Condition Executed Successfully...'
                        }
                    if (env.BRANCH_NAME == 'qa') {
                        echo 'Condition Executed Successfully...'
                        }
                    else {
                        echo "Skipping Condition for ${env.BRANCH_NAME} env"
                        }
                    }

                script {
                    if (env.BRANCH_NAME == 'dev') {
                        // Set a variable with the desired value
                        def myVariable = 'run-dev-build-script'
                        
                        // Print the variable
                        echo "http://3.18.204.118:8000/${myVariable}"
                        echo "http://3.18.204.118:8000/run-${env.BRANCH_NAME}-build-script"
                    }
                    if (env.BRANCH_NAME == 'qa') {
                        // Set a variable with the desired value
                        def myVariable = 'run-qa-build-script'
                        
                        // Print the variable
                        echo "http://3.18.204.118:8000/${myVariable}"
                        echo "http://3.18.204.118:8000/run-${env.BRANCH_NAME}-build-script"
                    } else {
                        echo "Branch name is not 'dev or qa'."
                    }
                }
            }
		}

        
        stage('Shell Command Check') {
            steps {
                // script {
                //     def currentBranch = env.GIT_BRANCH
                //     sh "echo 'Current branch is: ${currentBranch}'"
                //     sh "echo 'http://3.18.204.118:8000/run-${env.BRANCH_NAME}-build-script)'"
                    
                //     sh "echo 'http://3.18.204.118:8000/run-${currentBranch}-build-script'"
                // }
                echo "Testing bat command"
                script {
                    def test = env.BRANCH_NAME
                    echo "echoing this to test : ${test}"
                    // Call the method from the .js file
                    bat '''node run-"$test"-script.js
                    '''
                }
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
