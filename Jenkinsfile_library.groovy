def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

pipeline {
    agent any
    parameters {

        stages {
            stage('Build') {
                steps {
                    sh "${mvn} clean verify"
                }
            }
            stage('Run Tests') {
                steps {
                    sh "${mvn} test -Dcucumber.filter.tags=\"${TAG}\""
                }
            }
            stage('Allure Report Generation') {
                steps {
                    allure includeProperties: false,
                            jdk: '',
                            results: [[path: 'target/reports/allure-results']]
                }
            }

        }
    }
}