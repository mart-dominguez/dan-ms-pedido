#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('clean') {
            steps {
                sh "java -version"
                sh "./mvnw clean"
            }
        }
        stage('backend tests') {
            steps {
                configFileProvider([configFile(fileId: 'SAP_MAVEN_NEXUS', variable: 'MAVEN_SETTINGS')]) {
                    script {
                        try {
                            sh "./mvnw test -s $MAVEN_SETTINGS"
                        } catch (err) {
                            throw err
                        } finally {
                            junit '**/target/surefire-reports/TEST-*.xml'
                        }
                    }
                }

            }
        }
        stage('Install - Master') {
            when {
                branch 'master'
            }
            steps {
                sh "./mvnw site -DskipTests"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
}