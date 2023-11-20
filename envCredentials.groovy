return {    
    // Conditional check using 'if' statement
    if (env.BRANCH_NAME == 'dev') {
        AWS_ACCESS_KEY_ID = credentials('Jenkins-User')
        AWS_SECRET_ACCESS_KEY = credentials('Jenkins-User')
        AWS_ACCOUNT_ID = "521607829036"
        CLOUDFRONT_DISTRIBUTION_ID = "E2E0961TQEUIGE"
        S3_BUCKET = "di-mobile-app-versions"
    } else if (env.BRANCH_NAME == 'qa') {
        AWS_ACCESS_KEY_ID = credentials('Jenkins-User-QA')
        AWS_SECRET_ACCESS_KEY = credentials('Jenkins-User-QA')
        AWS_ACCOUNT_ID = "239611646522"
        CLOUDFRONT_DISTRIBUTION_ID = "E3KM9Y4YY79SB2"
        S3_BUCKET = "di-mobile-app-versions-verval"
    } else if (env.BRANCH_NAME == 'beta') {
        AWS_ACCESS_KEY_ID = credentials('Jenkins-User-Beta')
        AWS_SECRET_ACCESS_KEY = credentials('Jenkins-User-Beta')
        AWS_ACCOUNT_ID = "003185359767"
        CLOUDFRONT_DISTRIBUTION_ID = "E1TYF7WH9CLEPL" //
        S3_BUCKET = "di-mobile-app-versions-beta"
    } else if (env.BRANCH_NAME == 'master') {
        AWS_ACCESS_KEY_ID = credentials('Jenkins-User-Prod')
        AWS_SECRET_ACCESS_KEY = credentials('Jenkins-User-Prod')
        AWS_ACCOUNT_ID = "139532372547"
        CLOUDFRONT_DISTRIBUTION_ID = "E1CWVAFT75Z1JF"
        S3_BUCKET = "di-mobile-app-versions-prod"
    } else {
        error "Branch '${env.BRANCH_NAME}' not recognized for any specific build action. Exiting..."
        currentBuild.result = 'ABORTED'
        return
    }

    //Declare global variables using def
    def buildForEnvironment = ''
    if (env.BRANCH_NAME == 'master') {
        buildForEnvironment = 'prod'  
    } else {
        buildForEnvironment = "${env.BRANCH_NAME}"
    }

    // Return a map with the variables
    return [
        AWS_ACCESS_KEY_ID : AWS_ACCESS_KEY_ID,
        AWS_SECRET_ACCESS_KEY : AWS_SECRET_ACCESS_KEY,
        AWS_ACCOUNT_ID : AWS_ACCOUNT_ID,
        CLOUDFRONT_DISTRIBUTION_ID : CLOUDFRONT_DISTRIBUTION_ID,
        S3_BUCKET : S3_BUCKET,
        buildForEnvironment: buildForEnvironment
    ]
}()
