pipeline {
    agent any

    options {
          buildBlocker (useBuildBlocker: true, blockLevel: 'GLOBAL', blockingJobs: 'my-test-jenkins/.*')
    }

    environment {
        // Define Git repository URL and desired destination path
        // Credentials for cloning the repository
        // GIT_CREDENTIALS_ID = ''
        // GIT_USERNAME_VARIABLE = ''
        // GIT_PASSWORD_VARIABLE = ''
        // GIT_REPO_URL = ''

        // DESTINATION_PATH = ''

        when {
                expression { return BRANCH_NAME == 'dev' || BRANCH_NAME == 'qa' }
            }
            steps {
                script {
                    // Select credentials based on the branch
					if (BRANCH_NAME == 'dev') {
                        GIT_CREDENTIALS_ID = 'c17f17fc-1058-4f9a-b0e0-e2ddf272f29c'
                        GIT_USERNAME_VARIABLE = 'miqbal@datainnovations.com'
                        GIT_PASSWORD_VARIABLE = 'Shinigami@456'
                        GIT_REPO_URL = 'https://github.com/iqbal-di-456/JenkinsTest.git'
                        DESTINATION_PATH = 'D:/Test'
					} else if (BRANCH_NAME == 'qa') {
                        GIT_CREDENTIALS_ID = 'c17f17fc-1058-4f9a-b0e0-e2ddf272f29c'
                        GIT_USERNAME_VARIABLE = 'miqbal@datainnovations.com'
                        GIT_PASSWORD_VARIABLE = 'Shinigami@456'
                        GIT_REPO_URL = 'https://github.com/iqbal-di-456/JenkinsTest.git'
                        DESTINATION_PATH = 'D:/D drive/Jenkins_Files'
                    }
                    // Set the credential as environment variables for later stages
                        // env.GIT_CREDENTIALS_ID = GIT_CREDENTIALS_ID
                        // env.GIT_USERNAME_VARIABLE = GIT_USERNAME_VARIABLE
                        // env.GIT_PASSWORD_VARIABLE = GIT_PASSWORD_VARIABLE
                        // env.GIT_REPO_URL = GIT_REPO_URL
                        // env.DESTINATION_PATH = DESTINATION_PATH
                }
		    }

        batch_current = "${env.BRANCH_NAME}"
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
			Defining the credentials to be used based on the current Branch
		**/
		// stage('Credentials') {
        //     when {
        //         expression { return env.BRANCH_NAME == 'dev' || env.BRANCH_NAME == 'qa' }
        //     }
        //     steps {
        //         script {
        //             // Select credentials based on the branch
		// 			if (env.BRANCH_NAME == 'dev') {
        //                 GIT_CREDENTIALS_ID = 'c17f17fc-1058-4f9a-b0e0-e2ddf272f29c'
        //                 GIT_USERNAME_VARIABLE = 'miqbal@datainnovations.com'
        //                 GIT_PASSWORD_VARIABLE = 'Shinigami@456'
        //                 GIT_REPO_URL = 'https://github.com/iqbal-di-456/JenkinsTest.git'
        //                 DESTINATION_PATH = 'D:/Test'
		// 			} else if (env.BRANCH_NAME == 'qa') {
        //                 GIT_CREDENTIALS_ID = 'c17f17fc-1058-4f9a-b0e0-e2ddf272f29c'
        //                 GIT_USERNAME_VARIABLE = 'miqbal@datainnovations.com'
        //                 GIT_PASSWORD_VARIABLE = 'Shinigami@456'
        //                 GIT_REPO_URL = 'https://github.com/iqbal-di-456/JenkinsTest.git'
        //                 DESTINATION_PATH = 'D:/D drive/Jenkins_Files'
        //             }
        //             // Set the credential as environment variables for later stages
        //                 env.GIT_CREDENTIALS_ID = GIT_CREDENTIALS_ID
        //                 env.GIT_USERNAME_VARIABLE = GIT_USERNAME_VARIABLE
        //                 env.GIT_PASSWORD_VARIABLE = GIT_PASSWORD_VARIABLE
        //                 env.GIT_REPO_URL = GIT_REPO_URL
        //                 env.DESTINATION_PATH = DESTINATION_PATH
        //         }
		//     }
        // }

        /**
            Cloning the repository to the local temporary storage
        **/
        stage('Clone Repository') {
            steps {
                script {
                     // Echo to verify the value of GIT_CREDENTIALS_ID
                    echo "Credential_ID before withCredentials: ${env.GIT_CREDENTIALS_ID}"

                    // Use a different variable within the withCredentials block
                    // def credentialsId = env.GIT_CREDENTIALS_ID

                    // Clone the repository using credentials
                    withCredentials([usernamePassword(credentialsId: env.GIT_CREDENTIALS_ID, usernameVariable: env.GIT_USERNAME_VARIABLE, passwordVariable: env.GIT_PASSWORD_VARIABLE)]) {
                        checkout([$class: 'GitSCM', branches: [[name: "${env.BRANCH_NAME}"]], userRemoteConfigs: [[url: env.GIT_REPO_URL, credentialsId: env.GIT_CREDENTIALS_ID]]])
                        {
                            echo "Username: ${env.GIT_USERNAME_VARIABLE}"
                            echo "Password: ${env.GIT_PASSWORD_VARIABLE}"
                        }
                    }
                }
            }
        }

        stage('Copy Files') {
            steps {
                script {
                    // Create the destination directory if it doesn't exist
                    bat "if not exist \"${env.DESTINATION_PATH}\" mkdir \"${env.DESTINATION_PATH}\""

                    // Copy files from source to destination
                    bat "xcopy /S /Y \"${env.WORKSPACE}\" \"${env.DESTINATION_PATH}\""
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
                    if (env.BRANCH_NAME == 'dev' || env.BRANCH_NAME == 'qa') {
                            def myVariable = env.BRANCH_NAME == 'dev' ? 'run-dev-build-script' : 'run-qa-build-script'
                            echo "URL using myVariable: http://3.18.204.118:8000/${myVariable}"
                            echo "URL using BRANCH_NAME: http://3.18.204.118:8000/run-${env.BRANCH_NAME}-build-script"
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
                    bat """node run-${test}-script.js
                    """

                    bat '''                    
                    node run-%batch_current%-script.js
                    '''

                    // sh '''
                    // git rev-parse --abbrev-ref HEAD
                    // set test = git rev-parse --abbrev-ref HEAD
                    // node run-%test%-script.js
                    // '''
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
