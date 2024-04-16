pipeline {
    agent any
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "Maven"
    }
    stages {
        stage('Clean') {
            steps {
                // Get code from the main branch of a GitHub repository
                git branch: 'main', url: 'https://github.com/MariamLaghfiri/JaafRecruit/'

                // Run Maven
                bat "cd JaafRecruit && cd skills && mvn clean"
                bat "cd JaafRecruit && cd job-seeker && mvn clean"
                bat "cd JaafRecruit && cd job-posting && mvn clean"
                bat "cd JaafRecruit && cd education && mvn clean"
                bat "cd JaafRecruit && cd company && mvn clean"
                bat "cd JaafRecruit && cd application && mvn clean"
                bat "cd JaafRecruit && cd Experience && mvn clean"
                
            }
        }
        stage('Test') {
            steps {
                // Get code from the main branch of a GitHub repository
                git branch: 'main', url: 'https://github.com/MariamLaghfiri/JaafRecruit/'

                // Run Maven
                bat "cd JaafRecruit && cd skills  && mvn test"
                bat "cd JaafRecruit && cd job-seeker  && mvn test"
                bat "cd JaafRecruit && cd job-posting  && mvn test"
                bat "cd JaafRecruit && cd education  && mvn test"
                bat "cd JaafRecruit && cd company  && mvn test"
                bat "cd JaafRecruit && cd application  && mvn test"
                bat "cd JaafRecruit && cd Experience  && mvn test"
                
            }
        }
        stage('Build') {
            steps {
                // Get code from the main branch of a GitHub repository
                git branch: 'main', url: 'https://github.com/MariamLaghfiri/JaafRecruit/'

                // Run Maven
                bat "cd JaafRecruit && cd skills  && mvn package"
                bat "cd JaafRecruit && cd job-seeker  && mvn package"
                bat "cd JaafRecruit && cd job-posting  && mvn package"
                bat "cd JaafRecruit && cd education  && mvn package"
                bat "cd JaafRecruit && cd company  && mvn package"
                bat "cd JaafRecruit && cd application  && mvn package"
                bat "cd JaafRecruit && cd Experience  && mvn package"
                
            }
        }
    }
}
