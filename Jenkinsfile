#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('clean') {
            when {
                branch 'master'
            }
            steps {
                bat "java -version"
                bat "./mvnw clean"
            }
        }
        stage('clean-develop') {
            when {
                branch 'develop'
            }
            steps {
                bat "java -version"
                bat "./mvnw clean"
                bat "echo buildeando develop"
            }
        }
        stage('backend tests') {
            steps {
                bat "./mvnw verify"
                bat "echo 'configurar para ejecutar los tests'"
            }
        }
        stage('Install - Master') {
            steps {
                bat "./mvnw site"
                bat "./mvnw pmd:pmd"
                bat "./mvnw pmd:cpd"
            }            
        }
    }
    post {
                success{
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
                always{
                    archiveArtifacts artifacts: '**/target/site/**', fingerprint: true
                    publishHTML([allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/site',
                        reportFiles: 'index.html',
                        reportName: 'Site'
                    ])
                    junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
                    jacoco ( execPattern: 'target/jacoco.exec')
                }
            }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
}