### Starting JFrog Artifactory as a Docker container
```
docker run -d --name jfrog --hostname jfrog -p 8081-8082:8081-8082 docker.bintray.io/jfrog/artifactory-oss:latest 
```
You may check if the jfrog artifactory is up and running
```
docker ps
```
If you see the jfrog container up and running, you may try access the jfrog dashboard from a web browser in RPS lab machine.
http://localhost:8081

The default credentials to login JFrog artifactory is
<pre>
Username - admin
Password - password
</pre>
In case it prompts to change the password after the first login, you may change it to Admin@123 or any password of your choice. Just make sure the same password is updated in /home/rps/Downloads/apache-maven-3.8.4/conf/setting.xml server credentials from jfrog.

You also need to create Local Maven Repository with a name "tektutor" without quotes.

### Deploying CRM artifacts to JFrog artifactory
```
cd /home/rps/devops-jan-2022/Day2/Maven/CRM
mvn deploy
```

### Executing ansible playbook that downloads artifacts from JFrog Artifactory
```
cd /home/rps/devops-jan-2022
git pull
cd Day4
ansible-playbook download-artifacts-from-artifactory.yml
```
The expected output is
<pre>[jegan@tektutor Day4]$ <b>ansible-playbook download-artifacts-from-artifactory.yml</b>
[WARNING]: provided hosts list is empty, only localhost is available. Note that the implicit
localhost does not match 'all'

PLAY [This playbook will fetch artifacts(jar) from JFrog Artifactory] *******************************

TASK [Gathering Facts] ******************************************************************************
ok: [localhost]

TASK [Download frontend.jar from JFrog Artifactory to local machine] ********************************
changed: [localhost]

PLAY RECAP ******************************************************************************************
localhost                  : ok=2    changed=1    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   

[jegan@tektutor Day4]$ ls
download-artifacts-from-artifactory.yml  <b>frontend-1.0.0.jar</b>  README.md
</pre>

