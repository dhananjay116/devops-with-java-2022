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

