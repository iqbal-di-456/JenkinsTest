return {
    def BRANCH_NAME = 'git rev-parse --abbrev-ref HEAD'.execute().text.trim()
    echo "Current Branch: ${BRANCH_NAME}"
    
    // Conditional check using 'if' statement
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

    // Return a map with the variables
    return [
        GIT_CREDENTIALS_ID: GIT_CREDENTIALS_ID,
        GIT_USERNAME_VARIABLE: GIT_USERNAME_VARIABLE,
        GIT_PASSWORD_VARIABLE: GIT_PASSWORD_VARIABLE,
        GIT_REPO_URL: GIT_REPO_URL,
        DESTINATION_PATH: DESTINATION_PATH
    ]
}()
