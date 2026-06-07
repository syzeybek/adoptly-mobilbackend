pipeline {
    agent any

    environment {
        REGISTRY = 'adoptly-registry'
        IMAGE_NAME = 'adoptly-backend'
        IMAGE_TAG = 'latest'
    }

    stages {
        stage('1. Kodu Çek (Checkout)') {
            steps {
                echo 'Git üzerinden güncel kodlar çekiliyor...'
                checkout scm
            }
        }

        stage('2. Projeyi Paketle (Maven Build)') {
            steps {
                echo 'Spring Boot projesi testler atlanarak paketleniyor...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('3. Docker İmajı Oluştur (Docker Build)') {
            steps {
                echo 'Dockerfile okunuyor ve imaj oluşturuluyor...'
                sh "docker build -t ${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('4. Lokal Dağıtım (Deploy)') {
            steps {
                echo 'Eski container durduruluyor ve yenisi Docker üzerinde 8080 portunda ayağa kaldırılıyor...'
                sh "docker rm -f ${IMAGE_NAME}-container || true"
                sh "docker run -d -p 8080:8080 --name ${IMAGE_NAME}-container ${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }
    }
    
    post {
        success {
            echo 'CI/CD Süreci Başarıyla Tamamlandı! Proje Docker üzerinde canlıda. 🚀'
        }
        failure {
            echo 'CI/CD Süreci Patladı! Logları kontrol et kanka. ❌'
        }
    }
}