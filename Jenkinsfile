#!/usr/bin/env groovy

pipeline {
    agent any
    stages {
        stage('clean') {
            steps {
                bat "java -version"
                bat "./mvnw clean"
            }
        }
        stage('backend tests') {
            steps {
                //bat "./mvnw test"
                bat "echo 'configurar para ejecutar los tests'"
            }
        }
        stage('Install - Master') {
            steps {
                bat "./mvnw clean install site -DskipTests"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
}