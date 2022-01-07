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

# Creating the Graphana container for setting up Grafana Dashboard for the Jenkins Metrics collected by Prometheus
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

If all goes well, you should be able to view the Grafana visualizations that plots the live Prometheus performance metrics collected from Jenkins.


