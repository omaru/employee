pipeline{
	agent any
	stages{
		stage('initialization'){
			steps{
				sh 'echo "environment variables:" ${env}'
			}
		}
		stage('build'){
			steps{
				sh './gradlew build'
			}
		}
	}
}
