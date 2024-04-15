pipeline {
    agent {
        label 'windows' // Specify the Windows agent label
    }
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }
    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/MariamLaghfiri/JaafRecruit.git'

                // Run Maven
                dir('JaafRecruit') {
                    bat "mvn -Dmaven.test.failure.ignore=true clean package"
                }
            }
        }
        stage('Test') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/MariamLaghfiri/JaafRecruit.git'

                // Run Maven
                dir('JaafRecruit') {
                    bat "mvn test"
                }
            }
        }
    }
}
