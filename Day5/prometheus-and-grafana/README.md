# Installing Prometheus Jenkins plugin
```
In Jenkins Dashboard page, go to Manage Jenkins --> Manage Plugins --> Available Tab --> Search for 'Prometheus Metrics'
```
Make sure your Jenkins is restarted after the plugin installation.

You may verify if the Prometheus plugin works as expected at the below REST endpoint URL
```
http://localhost:8080/prometheus
```

# Creating Prometheus container
```
docker run -d --name prometheus-container -e TZ=UTC -p 30090:9090 ubuntu/prometheus:2.28-21.10_beta
```
You may verify if your prometheus-container is running
```
docker ps
```
We need to configure prometheus to collect performance metrics from our Jenkins. Hence we need to copy
the prometheus.yml from the container to configure it and put it back inside the container
```
docker cp prometheus-container:/etc/prometheus/prometheus.yml .
```

You need to edit the prometheus.yml and add the below entry to collect metrics from Jenkins
```
  - job_name: 'jenkins'
    metrics_path: /prometheus
    static_configs:
     - targets: ['192.168.167.129:8080']
```
In the above targets section, you need to update the IP with your RPS Lab machine IP.

Once the prometheus.yml is updated, you need to copy the prometheus.yml back inside the container
```
docker cp prometheus.yml prometheus-container:/etc/prometheus/prometheus.yml
docker restart prometheus-container
```

# Creating the Grafana container for setting up Grafana Dashboard for the Jenkins Metrics collected by Prometheus
```
docker run -d --name grafana -p 3000:3000 grafana/grafana:latest
```
You may access the Grafana Dashboard at URL
```
http://localhost:3000
```

Login credentials are
<pre>
Username - admin
Password - admin
</pre>

You may change the password to "Admin@123" without quotes.

You need to add a Prometheus Datasource, as part of that you need to connect to the Prometheus url
```
http://172.17.0.7:9090
```
You need to find the IP Address of your prometheus container and update the IP address accordingly.
Once the Grafana is able to communicate with Prometheus, you may import Jenkins: Performance and Health Overview with Dashboard ID - 9964.
For more details about the Jenkins Performacne and Health Overview Dashboard, you may check this
```
https://grafana.com/grafana/dashboards/9964
```

If all goes well, you should be able to view the Grafana visualizations that plots the live Prometheus performance metrics collected from Jenkins.


# DevOps Pipeline

1. As soon as as code is committed to TekTutor devops-jan-2022 GitHub repository, the Jenkins job we will create will detect the code change and trigger "Custom Docker Slave Image Build".  The "Custom Docker Slave Image Build" is the Stage1 Jenkins Job.
2. On Success, the "Custom Docker Slave Image Build" will then trigger "Build CRM" Stage2 Jenkins Job.
3. The "Build CRM" Stage2 Jenkins requires a Jenkins slave container with Maven and JDK to perform the CRM project maven build.  But the container will only be created on demand. Hence the "Build CRM" will request the Jenkins Server to create a container mentioning the Container configuration.
4. Jenkins server then picks the Docker container configuration mentioned by the "Build CRM" job and will request the Docker Application Engine for a new container.
5. The Docker Application Engine will create a new container and notifies the Jenkins Server.
6. The Jenkins Server then notifies the "Build CRM" Jenkins Job to make use of the new container that was created on demand.
7. Once the "Build CRM" Jenkins Job is successfuly build, the "Build CRM" job will notify Jenkins Server that it doesn't require the container anymore, hence it will be deleted once build gets completed(SUCCESS/Fail) it then triggers another downstream Job i.e "Deploy CRM artifacts to JFrog Artifactory".
