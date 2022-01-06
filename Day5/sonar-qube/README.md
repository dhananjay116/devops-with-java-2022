### Creating SonarQube Server instance as a Docker container
```
docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
```

See if the sonarqube container is running
```
docker ps
```

You may now connect to the SonarQube web page from your favourite web browser
```
http://localhost:9000
```

We need to create a new project and generate a token in order to perform static analyzis on our project for coding violations. The token is used to login to sonarqube and upload the sonarqube reports to the SonarQube server.

We need to integrate the below sonar maven plugin in our project to collect the sonar violations.

```
<build>
	<plugins>
		<plugin>
			<groupId>org.sonarsource.scanner.maven</groupId>
			<artifactId>sonar-maven-plugin</artifactId>
			<version>3.4.0.905</version>
		</plugin>
	</plugins>
</build>
```
At this point, you should be able to perform static code analysis on your project and upload the results to sonarqube
```
mvn clean verify sonar:sonar   -Dsonar.projectKey=tektutor   -Dsonar.host.url=http://localhost:9000   -Dsonar.login=1cd17bda2df20b4e5d0e3db5b7209875bb8fd872
```
You need to modify the projectKey and token as per the project key and token you generated in your Sonaqube server.

You may also add the above maven command in Jenkins as part of your CI Build. 
