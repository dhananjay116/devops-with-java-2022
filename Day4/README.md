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

### Passing extra argument to Ansible playbook
In the below command, my-var.yml file has multiple arguments that you could pass to your playbook.
```
cd /home/rps/devops-jan-2022
git pull
cd Day4
ansible-playbook ansible-playbook passing-extra-args-to-playbook.yml -e @my-vars.yml
```
The expected output is 
<pre>
[jegan@tektutor Day4]$ <b>ansible-playbook passing-extra-args-to-playbook.yml -e @my-vars.yml</b>
[WARNING]: provided hosts list is empty, only localhost is available. Note that the implicit
localhost does not match 'all'

PLAY [This playbook will demonstrate passing arguments to your playbook] ****************************

TASK [Gathering Facts] ******************************************************************************
ok: [localhost]

TASK [debug] ****************************************************************************************
ok: [localhost] => {
    <b>"msg": "message --> Hello DevOps; jdk_path --> /usr/lib/jdk1.8/bin; maven home --> /usr/share/maven "</b>
}

PLAY RECAP ******************************************************************************************
localhost                  : ok=2    changed=0    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   
</pre>


# DevOps
- it is a combination of process and tool
- helps you deliver frequent releases to customer in a incremental fashion with confidence
- when we deliver frequent releases in short interval, it tends to more unstable.  But with the help of DevOps tools
  you should be automate test to catch bugs during development cycle so that you can release more stable releases to your customer
 
## What is Continuous Integration?
- a source code that was written 10~20 minutes, has to be integrated with dev branch several times a day
- as and when your code is logically complete even 
- once your source code is committed, Jenkins or similar CI Build server should detect code commit and trigger a build. As part of the build there were automated test cases execution, if there is any bug it would be reported in 15~20 minutes after your code checkin.
- this entire process is automated i.e end-to-end build->test->report.

### What is Continuous Deployment?
- this next level of Continuous Integration
- the release binaries which are tested in the CI process will be deployed automatically to QA or similar environments for further testing ( functionality, stress, load, performance, etc sort of testing )

### What is Continuous Delivery?
- this is next leve of Continuous Deployment
- the release binaries which are tested in QA, pre-prod, staging environments will be delivered to customer's test environment or in some cases if the organization is confident about the CI/CD test process the release can be made live in production automatically.


### Jenkins 
- is a Continuous Integration(CI) Build Server
- opensource product
- it was developed by Josuke Kawaguchi a former Sun Microsystems employee as Hudson
- Hudson is the name of the intial product, but once Oracle acquired Sun Microsystems Oracle had different strategy
  the team that originally developed Hudson open source product they got split into two team. One team which didn't agree with Oracle stategy they came of Oracle and forked Hudson branch as Jenkins.  The other team which decided to stay back at Oracle continues to develop the Hudson product.
Similar products
  - Bamboo
  - TeamCity
  - Hudson
  - Cloudbees - enterprise variant of Jenkins
  - Microsoft Team Foundation Server (TFS)

### Starting Jenkins
```
cd /home/rps/Downloads
java -jar ./jenkins.war
```

You may access Jenkins Dashboard from browser at http://localhost:8080


