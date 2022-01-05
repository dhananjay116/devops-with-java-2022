# What is DevOps?
  - a way of project development where developers, qa and operations team attempts
    to automate whatever service they offer
  - it involves following certain process and tools to ensure you are able to frequently deliver release with confidence.
  - Process involved
      - Continuous Integration
      - Continuous Deployment
      - Continuous Delivery
  - Tools involved
      - Jenkins/Cloudbees, TeamCity, Bamboo, Microsoft Team Foundation Server (TFS), etc.,
      - Docker( or similar container runtimes ), Virtual Machines, VM Management Tools (vagrant),
      - Configuration Management Tools ( Ansible, Puppet, Chef, SaltStack )
      - Orchestration Tools ( Docker SWARM, Kubernetes, OpenShift, Rancher, etc., )
      - Cloud computing ( AWS, Azure, GCP, Digital Ocean, etc., )
      - Version Control ( Git/GitHub, Bit Bucket, Perforce, ClearCase, etc., )
      - Build Tools ( Ant, Maven, Make, Gradle NPM, etc., )
      
  - Developers
      - primary work involves application development
      - as part of application development, they also do unit testing and integration testing mostly manually
      - DevOps recommends automating unit and intergration testing that developers do ( Unit Testing Framework - Junit, TestNG, Mockito, EasyMock,NUnit, Pytest, Google Test and Google Mock, Jasmine, Karma, Cucumber, Selenium, etc
      - Devops recommends developers automate the process involved in dev environment setup ( Configuration Management Tool )

  - QA 
      - primary work involves testing ( testing is done partly manual and partly automated )
      - DevOps recommends automating all functional testing,  performance, stress, load, API, component, acceptance testing, end-to-end testing
      - DevOps also insists that the automation should be done by writing code
      - DevOps recommends QA team to automate the QA environement setup which mean they need to learn configuration management tools ( ansible, puppet, chef, saltstack )

  - Operations Team
      - System Administrators, DBA, Infra Engineers, DevOps engineers, etc.,
      - primary work involves network administration, system administration, database administration, software installation, ensuring network is secured, etc.,
      - DevOps expects Operations Team to automate all administrative activities by writing code
      - DevOps expects Operations Team to automate environment testing by writing code

# What is Ansible?
  - configuration management tool
  - it is a tool used by typically Administrator to automate sofware installations
  - Domain Specific Language (DSL)
       - YAML ( Yet Another Markup Language - superset of JSON - JavaScript Object Notation )
  - PUSH based architecture

# Puppet/Chef
  - DSL used is Ruby scripting language
  - Setting up Puppet/Chef is very complex
  - Learning curve is also very steep
  - They use proprietary tools in servers where software installation automation is done
  - Pull based architecture
## Configuration Management Tools
  - examples - Ansible, Puppet, Chef and Salt/SaltStack 

### Ansible Node
 - this is the server where software installation automation must be done
 - these servers could be a container, virtual machine on-prem, cloud based machines, physical machines, routers, switches, etc.,
 - Unix/Linux Node
     - python should be installed
     - SSH server should be installed
     - should have the public key of Ansible Controller Machine for key-based login authentication( but not mandatory)
 - Windows Node
    - Powershell (.Net)
    - WinRM enabled and configured

### As rps user, create ssh key-pairs
```
ssh-keygen
```
Accept all defaults by hitting enter key 3 (thrice)

### Creating a custom docker image to use it as a Ansible Node
```
cd /home/rps/devops-jan-2022
git pull
cd Day3/Ansible/ubuntu-ansible
cp /home/rps/.ssh/id_rsa.pub authorized_keys
docker build -t tektutor/ansible-ubuntu .
```

At this point you will have a tektutor/ansible-ubuntu custom image built in your local docker registry.
```
docker images
```
The expected output is
<pre>
[jegan@tektutor ubuntu-ansible]$ <b>docker images</b>
REPOSITORY                                TAG       IMAGE ID       CREATED         SIZE
<b>tektutor/ansible-ubuntu                   latest    fd618dc5cb86   7 minutes ago   220MB</b>
mysql                                     latest    3218b38490ce   2 weeks ago     516MB
docker.bintray.io/jfrog/artifactory-oss   latest    7d5bc86bd887   3 weeks ago     991MB
ubuntu                                    20.04     ba6acccedd29   2 months ago    72.8MB
hello-world                               latest    feb5d9fea6a5   3 months ago    13.3kB
ubuntu                                    16.04     b6f507652425   4 months ago    135MB
</pre>


### Let's clean up by deleting all existing containers
```
docker rm -f $(docker ps -aq)
```
Though this is not necessary, but some containers may conflict with what we are about to do below.

### Let's create couple of containers which we will later use as ansible nodes
```
docker run -d --name ubuntu1 --hostname ubuntu1 -p 2001:22 -p 8001:80 tektutor/ansible-ubuntu 
docker run -d --name ubuntu2 --hostname ubuntu2 -p 2002:22 -p 8002:80 tektutor/ansible-ubuntu 
```
The expected output is
<pre>
[jegan@tektutor ubuntu-ansible]$ <b>docker run -d --name ubuntu1 --hostname ubuntu1 -p 2001:22 -p 8001:80 tektutor/ansible-ubuntu</b>
9a89b26ac9d4efddb094db318605b555cb4137c010e4b9e82a46ecfac52230ef
[jegan@tektutor ubuntu-ansible]$ <b>docker run -d --name ubuntu2 --hostname ubuntu2 -p 2002:22 -p 8002:80 tektutor/ansible-ubuntu</b>
7b38796b00ad34a1aed794a6a0a87526830f961d1c27519382ab70fc94ea22d0
[jegan@tektutor ubuntu-ansible]$ <b>docker ps</b>
CONTAINER ID   IMAGE                     COMMAND                  CREATED          STATUS          PORTS                                                                          NAMES
<b>7b38796b00ad   tektutor/ansible-ubuntu   "/usr/sbin/sshd -D"      4 seconds ago    Up 3 seconds    0.0.0.0:2002->22/tcp, :::2002->22/tcp, 0.0.0.0:8002->80/tcp, :::8002->80/tcp   ubuntu2
9a89b26ac9d4   tektutor/ansible-ubuntu   "/usr/sbin/sshd -D"      30 seconds ago   Up 28 seconds   0.0.0.0:2001->22/tcp, :::2001->22/tcp, 0.0.0.0:8001->80/tcp, :::8001->80/tcp   ubuntu1</b>
0937340d8b5b   mysql:latest              "docker-entrypoint.s…"   3 hours ago      Up 3 hours      3306/tcp, 33060/tcp                                                            db
</pre>

Let's us check if ubuntu1 and ubuntu2 containers are running
```
docker ps
```
Let's us test if we are able to SSH into these ansible nodes(servers)
```
ssh -p 2001 root@localhost
```
The expected output is
<pre>
[jegan@tektutor ubuntu-ansible]$ <b>ssh -p 2001 root@localhost</b>
The authenticity of host '[localhost]:2001 ([::1]:2001)' can't be established.
ECDSA key fingerprint is SHA256:PGuuzt8OaJtk7QpeMep2OILWQ7bjtbcRjbxfSfa/yIs.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added '[localhost]:2001' (ECDSA) to the list of known hosts.
Welcome to Ubuntu 16.04.7 LTS (GNU/Linux 4.18.0-348.el8.x86_64 x86_64)

 * Documentation:  https://help.ubuntu.com
 * Management:     https://landscape.canonical.com
 * Support:        https://ubuntu.com/advantage

The programs included with the Ubuntu system are free software;
the exact distribution terms for each program are described in the
individual files in /usr/share/doc/*/copyright.

Ubuntu comes with ABSOLUTELY NO WARRANTY, to the extent permitted by
applicable law.

root@ubuntu1:~# <b>exit</b>
logout
Connection to localhost closed.
</pre>

Let's repeat the SSH connection test to our other ansible node
```
ssh -p 2002 root@localhost
```
The expected outupt is
<pre>
[jegan@tektutor ubuntu-ansible]$ <b>ssh -p 2002 root@localhost</b>
The authenticity of host '[localhost]:2002 ([::1]:2002)' can't be established.
ECDSA key fingerprint is SHA256:PGuuzt8OaJtk7QpeMep2OILWQ7bjtbcRjbxfSfa/yIs.
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added '[localhost]:2002' (ECDSA) to the list of known hosts.
Welcome to Ubuntu 16.04.7 LTS (GNU/Linux 4.18.0-348.el8.x86_64 x86_64)

 * Documentation:  https://help.ubuntu.com
 * Management:     https://landscape.canonical.com
 * Support:        https://ubuntu.com/advantage

The programs included with the Ubuntu system are free software;
the exact distribution terms for each program are described in the
individual files in /usr/share/doc/*/copyright.

Ubuntu comes with ABSOLUTELY NO WARRANTY, to the extent permitted by
applicable law.

root@ubuntu2:~# <b>exit</b>
logout
Connection to localhost closed.
</pre>

### Ansible ad-hoc command

#### Ansible ping to check if ansible can connect to ansible nodes
```
cd /home/rps/devops-jan-2022
git pull
cd Day3/Ansible
ansible -i inventory all -m ping
```

The expected output is
<pre>
[jegan@tektutor Ansible]$ <b>ansible -i inventory all -m ping</b>
ubuntu2 | SUCCESS => {
    "ansible_facts": {
        "discovered_interpreter_python": "/usr/bin/python3"
    },
    "changed": false,
    "ping": "pong"
}
ubuntu1 | SUCCESS => {
    "ansible_facts": {
        "discovered_interpreter_python": "/usr/bin/python3"
    },
    "changed": false,
    "ping": "pong"
}
</pre>

### What happens when you run ansible ping?
1. Ansible creates a temp directory in ACM and temp directory in Ansible Node
2. Ansible copies the ping.py from ACM Ansible Module folder and puts it in the ACM tmp folder
3. Ansible then transpiles i.e bundles all ansible specific code in the ping.py and creates a ping.py file in the ACM temp folder
4. Using either sftp or scp it then copies the transpiled ping.py to the Ansible Node temp folder
5. Ansible then exectues the ping.py script on the remote node
6. Captures all the responses(ie output), cleans up the temp folder on the ansible node
7. Gives a summary of output in the Ansible Controller Machine

### Running your first ansible playbook
```
cd /home/rps/devops-jan-2022
git pull
cd Day3/Ansible/playbooks
ansible-playbook -i inventory ping.yml
```
The expected output is
<pre>
[jegan@tektutor playbooks]$ <b>ansible-playbook -i inventory ping.yml</b> 

PLAY [This playbook will demonstrate how to invoke ping ansible module] ************

TASK [Gathering Facts] *************************************************************
ok: [ubuntu1]
ok: [ubuntu2]
ok: [centos1]

TASK [Ping the ansible node] *******************************************************
ok: [ubuntu2]
ok: [ubuntu1]
ok: [centos1]

PLAY RECAP *************************************************************************
centos1                    : ok=2    changed=0    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   
ubuntu1                    : ok=2    changed=0    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   
ubuntu2                    : ok=2    changed=0    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   
</pre>

### Executing install nginx playbook
```
cd /home/rps/devops-jan-2022
git pull
cd Day3/ansible/playbooks
ansible-playbook install-nginx-playbook.yml
```
The expected output is
<pre>
[jegan@tektutor playbooks]$ <b>ansible-playbook install-nginx-playbook.yml</b>

PLAY [This playbook will install, configure and deploys custom web page into nginx web server] **********

TASK [Gathering Facts] **********************************************************************************
ok: [ubuntu1]
ok: [ubuntu2]

TASK [Install nginx in Ubuntu] **************************************************************************
[WARNING]: Updating cache and auto-installing missing dependency: python3-apt
changed: [ubuntu1]
changed: [ubuntu2]

PLAY RECAP **********************************************************************************************
ubuntu1                    : ok=2    changed=1    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   
ubuntu2                    : ok=2    changed=1    unreachable=0    failed=0    skipped=0    rescued=0    ignored=0   </pre>
