pipeline {
    agent any

    stages {
         stage('Build') {
            steps {
                // You can add your build steps here
                // For example, if you're using Maven for a Java project
                // sh 'mvn clean install'
                echo 'building the application...'
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

}
