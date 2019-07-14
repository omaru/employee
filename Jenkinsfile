pipeline{
	agent any
	stages{
		stage('initialization'){
			steps{
				sh 'echo "environment variables:" '
				echo sh(returnStdout: true, script: 'env')
			}
		}
		stage('build'){
			steps{
				sh './gradlew build'
			}
		}
	}
}
