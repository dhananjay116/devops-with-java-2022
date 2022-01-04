### How people used to dual or multi-boot many OS before the Virtualization Technology was available?
 - Boot Loaders Utlities
    1. LILO ( Linux Loader ) - Open source
    2. GRUB - open source
 - Boot loaders are installed in Sector 0, byte 0 (MBR - Master Boot Record) of your Hard Drive
 - When your system boots, CPU learns from BIOS that it has to load the utility present in MBR
 - Eventually CPU starts running the Boot Loaders installed in MBR
 - Boot Loader then detects if there is any OS installed on your system, it is possible you may multiple OS
 - Boot Loader then gives a menu for you to choose between the OS installed on your system
 - This way, only one OS can be booted at a time

### What is Hypervisor?
 - general term used to refer the Virtualization Technology
 - heavy-weight technology
      each virtual machine (VM) requires dedicated CPU Core, RAM and storage
 - Hypervisor allows you to run multiple OS simultaneously side by side in the same system
 - it is combination of Hardware + Software technology
 - Processor
     AMD 
       - processors that support Virtualization it has AMD-V feature enabled
     Intel
       - processors that support Virtualization it has VT-X feature enabled
  - Hypervisor are of 2 types
      Type 1 - meant for Servers/Workstations ( Bare Metal Hypervisors )
      Type 2 - meant for laptops/Desktops/Workstations
  - Examples
      VMWare 
          - Fusion (Mac) - Type 2
          - Workstation (Windows/Linux/Mac) - Type 2
          - vSphere - Type 1
      Parallels(Mac) - Type 2
      Microsoft
          - Hyper-V - Type 2
      Oracle
          - VirtualBox - Type 2
     
     Datacenter
        - a collection of many many servers ( 1,00,000 Servers put together is called as 1 Datacenter )
     
     Requirement
     I need 1,00,000 RHEL OS running simultaneously
     
     Without Virtualization Technology
      - How many servers you need to deploy 1,00,000 RHEL OS?
         - We need 1,00,000 physical servers to support 1,00,000 RHEL OS simultaneously
           
     With Virtualization Technology
      - How many servers you need to deploy 1,00,000 RHEL OS?
         - Processor with 1024 cores 
  
     
     CRM
      - Oracle DB Server ( Hyderabad - BOA )
      - Apache Kafka ( Message Queue, Streaming ) - (Hyderabad - BOA )
      - Weblogic App Server - ( US - BOA)
      - Nginx Load Balancer - ( Singapore - BOA )
      - Email Server - ( Bangalore - BOA )
   
 Containers - Application virtualization
   - typically one application runs per container
   - container are not Operating System
   - container looks in many ways like an OS but technically they are just application process
   - containers has their shell just like OS has their shells(command prompt, ksh, bash, sh, etc .,)
   - containers has dedicated network stack ( 7 OSI Layers )
   - containers get their IP Address
   - light-weight technolgy
       - all the containers running in a system share the CPU, RAM and storage just like regular application process.
   - even in laptop with Dual cores with 8 GB RAM, you can easily run 50~100 containers

### Container Orchestration Platform
- You can deploy your applications in a cluster and make it Highly Available(HA)
- Orchestration platforms manages containers
- You can scale up when there is more traffice for your microservice/application
- You can scaled down when the traffice to your website comes down
- You can do rolling update ( upgrading your appliction from one version to other without downtime )
- You can monitor health of your applications and replace with another instance in case your application stops responding
- Examples:
   Docker SWARM ( Docker's native orchestration platform that supports only Docker container Runtime )
   Google Kubernetes ( supports many types of Containers Runtimes including Docker, Podman, LXC, etc.,)
   IBM OpenShift ( supports Podman container Runtime )

### Installing Docker Community Edition in CentOS
```
su -
yum install -y yum-utils
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum install -y docker-ce
systemctl enable docker && systemctl start docker
```

### Docker Image
- is a blueprint of Docker Container
- similar to OS ISO images or VMWare Images
- further broken down into one or more layers

### Docker Container
- is an instance of Docker Image
-

### Listing Docker Images in the Local Docker Registry
```
docker images
```

### Downloading a docker image from Docker Hub(Remote Registry) to Local Docker Registry
```
docker pull hello-world:latest
```

### Creating a container
```
docker run hello-world:latest
```
The expected output is
<pre>
[jegan@tektutor ~]$ <b>docker run hello-world:latest</b>

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
</pre>

### Listing the running containers
```
docker ps
```

### Listing all containers
```
docker ps -a
```
### Creating an ubuntu container in interactive fashion
```
docker run -it --name ubuntu1 --hostname ubuntu1 ubuntu:20.04 /bin/bash
```
The expected output is
<pre>
[jegan@tektutor ~]$ <b>docker run -it --name ubuntu1 --hostname ubuntu1 ubuntu:20.04 /bin/bash</b>
Unable to find image 'ubuntu:20.04' locally
20.04: Pulling from library/ubuntu
7b1a6ab2e44d: Pull complete 
Digest: sha256:626ffe58f6e7566e00254b638eb7e0f3b11d4da9675088f4781a50ae288f3322
Status: Downloaded newer image for ubuntu:20.04
root@ubuntu1:/# ls
bin   dev  home  lib32  libx32  mnt  proc  run   srv  tmp  var
boot  etc  lib   lib64  media   opt  root  sbin  sys  usr
root@ubuntu1:/# hostname
ubuntu1
root@ubuntu1:/# hostname -i
172.17.0.3
root@ubuntu1:/# exit
exit
</pre>

From another terminal, you may list the container
```
docker ps
```

### Creating an ubuntu container in background(daemon mode) fashion
```
docker run -dit --name ubuntu2 --hostname ubuntu2 ubuntu:20.04 /bin/bash
```
The expected output is
<pre>
jegan@tektutor ~]$ <b>docker run -dit --name ubuntu2 --hostname ubuntu2 ubuntu:20.04 /bin/bash</b>
6fffa01cdb8547eb2da3156dc97336c12112547735787039a0bbc67a9e4bbb0a
[jegan@tektutor ~]$ docker ps
CONTAINER ID   IMAGE                                            COMMAND                  CREATED         STATUS         PORTS                                                           NAMES
6fffa01cdb85   ubuntu:20.04                                     "/bin/bash"              6 seconds ago   Up 5 seconds                                                                   ubuntu2
13e24f957b59   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   23 hours ago    Up 3 hours     0.0.0.0:8081-8082->8081-8082/tcp, :::8081-8082->8081-8082/tcp   artifactory
[jegan@tektutor ~]$ docker exec -it ubuntu2 /bin/bash
root@ubuntu2:/# ls
bin   dev  home  lib32  libx32  mnt  proc  run   srv  tmp  var
boot  etc  lib   lib64  media   opt  root  sbin  sys  usr
root@ubuntu2:/# exit
exit
</pre>

### Getting inside a running container
```
docker exec -it ubuntu2 /bin/bash
```

### Finding IP address of a container
```
docker inspect ubuntu2 | grep IPA
```
<pre>
[jegan@tektutor ~]$ <b>docker inspect ubuntu2 | grep IPA</b>
172.17.0.3
</pre>

You may also try this command
```
docker inspect -f {{.NetworkSettings.IPAddress}} ubuntu2
```
The expected output is
<pre>
[jegan@tektutor ~]$ <b>docker inspect -f {{.NetworkSettings.IPAddress}} ubuntu2</b>
172.17.0.3
</pre>

### Let's remove all containers
```
docker rm -f $(docker ps -aq)
```

### Let's create 3 containers
```
docker run -dit --name ubuntu1 --hostname ubuntu1 ubuntu:20.04 /bin/bash
docker run -dit --name ubuntu2 --hostname ubuntu2 ubuntu:20.04 /bin/bash
docker run -dit --name ubuntu3 --hostname ubuntu3 ubuntu:20.04 /bin/bash
```
List the running containers
```
docker ps
```

### Stopping a single running container
```
docker stop ubuntu1
```

### Stopping multiple running containers
```
docker stop ubuntu2 ubuntu3
```

### Starting multiple containers
```
docker start ubuntu1 ubuntu2 ubuntu3
```

### Stopping multiple running containers without calling out their names
```
docker stop $(docker ps -q)
docker rm $(docker ps -aq)
```

### Let's create a single container
```
docker run -dit --name ubuntu1 --hostname ubuntu1 ubuntu:20.04 /bin/bash
```
List the running contianers
```
docker ps
```

### Renaming a container
```
docker ps
docker rename ubuntu1 c1
docker ps
```
