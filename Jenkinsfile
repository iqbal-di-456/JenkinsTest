//def credentials = load 'credentials.groovy'

pipeline {
    agent any

    options {
          buildBlocker (useBuildBlocker: true, blockLevel: 'GLOBAL', blockingJobs: 'my-test-jenkins/.*')
    }

    environment {
        def credentials = load 'credentials.groovy'

        GIT_CREDENTIALS_ID = "${credentials.GIT_CREDENTIALS_ID}"
        GIT_USERNAME_VARIABLE = "${credentials.GIT_USERNAME_VARIABLE}"
        GIT_PASSWORD_VARIABLE = "${credentials.GIT_PASSWORD_VARIABLE}"
        GIT_REPO_URL = "${credentials.GIT_REPO_URL}"
                        
        DESTINATION_PATH = "${credentials.DESTINATION_PATH}"
        
        buildForEnvironment = "${credentials.buildForEnvironment}"

        // GIT_CREDENTIALS_ID = 'c17f17fc-1058-4f9a-b0e0-e2ddf272f29c'
        // GIT_USERNAME_VARIABLE = 'miqbal@datainnovations.com'
        // GIT_PASSWORD_VARIABLE = 'Shinigami@456'
        // GIT_REPO_URL = 'https://github.com/iqbal-di-456/JenkinsTest.git'
                        
        // DESTINATION_PATH = 'D:/D drive/Jenkins_File'

        batch_current = "${env.BRANCH_NAME}"
    }

    // Declare global variables using def
    // def buildForEnvironment = ''
    // if (env.BRANCH_NAME == 'master') {
    //     buildForEnvironment = 'prod'   
    // } else {
    //     buildForEnvironment = "${env.BRANCH_NAME}"
    // }  

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
        //             echo "Checking Branch name : ${env.BRANCH_NAME}"
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
        //             echo "Credential_ID in the Credentials stage: ${env.GIT_CREDENTIALS_ID}"
        //             // Set the credential as environment variables for later stages
        //                 env.GIT_CREDENTIALS_ID = GIT_CREDENTIALS_ID
        //                 env.GIT_USERNAME_VARIABLE = GIT_USERNAME_VARIABLE
        //                 env.GIT_PASSWORD_VARIABLE = GIT_PASSWORD_VARIABLE
        //                 env.GIT_REPO_URL = GIT_REPO_URL
        //                 env.DESTINATION_PATH = DESTINATION_PATH
                    
        //             echo "Credential_ID in the Credentials stage after defining as env.: ${env.GIT_CREDENTIALS_ID}"
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
                    echo "Username before withCredentials: ${env.GIT_USERNAME_VARIABLE}"

                    // Clone the repository using credentials
                    withCredentials([usernamePassword(credentialsId: env.GIT_CREDENTIALS_ID, usernameVariable: env.GIT_USERNAME_VARIABLE, passwordVariable: env.GIT_PASSWORD_VARIABLE)]) {
                        checkout([$class: 'GitSCM', branches: [[name: "${env.BRANCH_NAME}"]], userRemoteConfigs: [[url: env.GIT_REPO_URL, credentialsId: env.GIT_CREDENTIALS_ID]]])
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
                echo "Testing bat command"
                script {

                    // Declare global variables using def
                    // def buildForEnvironment = ''
                    // if (env.BRANCH_NAME == 'dev') {
                    //     buildForEnvironment = 'prod'   
                    // } else {
                    //     buildForEnvironment = "${env.BRANCH_NAME}"
                    // } 

                    // def test = env.BRANCH_NAME
                    // echo "echoing this to test : ${test}"
                    
                    // Call the method from the .js file
                    bat """node run-${buildForEnvironment}-script.js
                    """

                    bat '''                    
                    node run-%buildForEnvironment%-script.js
                    '''

                    // sh '''
                    // node run-${batch_current}-script.js
                    // '''
                }
            }
        }

        stage('Install Dependencies, Load Environment Variables and Test') {
            steps {
                script {
                    // Install dotenv package
                    bat 'npm install dotenv-cli'
                    bat 'npx dotenv-cli -e .env --debug'

                    // Load environment variables from .env file
                    bat 'node -e "require(\'dotenv\').config({ path: \'.env\' })"'

                    // Access environment variables
                    //bat 'echo %ENV%'

                    echo "ENV: ${env.ENV}"
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
