### Installing git in CentOS 8.x
```
su -
yum install -y git
```
When prompts for password, you may type 'rps@12345' without quotes.


### Cloning TekTutor's DevOps GitHub Repository ( Do this as rps user only the first time )
```
cd ~
git clone https://github.com/tektutor/devops-jan-2022.git
cd /home/rps/devops-jan-2022
cd Day1
cat README.md
```

### For pulling delta changes
```
cd ~
cd /home/rps/devops-jan-2022
git pull
```

### Installing JDK 11
```
su -
yum install -y java-11-openjdk-devel
javac -version
```

### Installing Maven
```
cd /home/rps/Downloads
wget https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz
tar xvfz apache-maven-3.8.4-bin.tar.gz
```

Append the JDK and Maven bath in the /home/rps/.bashrc file as shown below
<pre>
# User specific aliases and functions
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-11.0.13.0.8-4.el8_5.x86_64
export M2_HOME=/home/rps/Downloads/apache-maven-3.8.4
export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$PATH
</pre>

### What is Maven?
- is a build tool used mostly by Java developers
- however it can be used to build C#, C++, python, groovy, scala, any programming languages
- i.e it is language agnostic
- it is a open-source tool from Apache Foundatation
- Maven uses a POM (Project Object Model) xml file to capture your project, its dependencies, etc.,


### Maven Convention over Configuration
- even if you have to name your projects, it has to done in terms of
  maven co-ordinates
- For example, for a hello-world project, the naming convention recommended is
  groupId - org.tektutor
  artifactId - tektutor-helloworld-app
  version - 0.0.1
- Maven has conventions on how your project directory structure should look like
  <pre>
  hello
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── org
    │           └── tektutor
    │               └── App.java
    └── test
        └── java
            └── org
                └── tektutor
                    └── AppTest.java
  </pre>
  
### Maven POM Schema
```
https://maven.apache.org/xsd/maven-4.0.0.xsd
```
### Maven co-ordinates
- 3 co-ordinates
- they are
  1. groupId - reverse domain name of your organization (string)
  2. artifactId - name of jar/war/ear/zip - your binary name
  3. version - your binary version ( x.y.z)
       x - represents major version
       y - represents minor version
       z - represents incremental version
       
### Compiling your first Maven based java application
```
cd ~
cd /home/rps/devops-jan-2022
git pull
cd Day1/hello
mvn compile
```

### Cleaning target folder
```
mvn clean
```
The above command will delete the target folder which has all your project binaries.

### Performing a clean compile ( rebuild )
```
mvn clean compile
```

### Packaging your application as jar/war/ear,etc
```
cd ~
cd devops-jan-2022
git pull
cd Day1/hello
mvn package
```

### Super POM (Project Object Model)
- Super POM has all default Maven configurations
- Its comes with Maven installation
- It has information about life-cycle and plugins that needs to be invoked during specifice life-cycle phase
- This Super POM is inherited by the POM file we define for our projects

### User defined POM file
- every project that we create will have atleast one POM file
- in case of multi-module projects, you will find multiple POM files one per module
- The POM file we write, automatically inherits the default properties from the Super POM

### Effective POM
- each time you run any maven command within a project folder, maven picks all the default properties from
  Super POM and merges with the POM file you wrote and creates an effective as in-memory file
- super POM is the file, Maven refers while performing any build activity in your project

### Creating an effective POM
```
cd ~
cd devops-jan-2022
git pull
cd Day1/hello
mvn help:effective-pom > eff.yml 2>&1
```

### Maven Life Cycle
- life cycle is a chain of commands called one after the other in a pre-defined sequence (top to bottom order)
- Maven supports 3 inbuilt life-cycles
   1. default ( 23 Phases )
   2. clean ( 3 Phases )
   3. site ( 4 Phases )

### Listing maven default life-cycle
```
mvn help:describe -Dcmd=compile
```

### Listing maven clean life-cycle
```
mvn help:describe -Dcmd=clean
```

### Listing maven site life-cycle
```
mvn help:describe -Dcmd=site
```

### Listing Plugin goals
```
mvn help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin:3.1 -Ddetail
```

### Creating a maven project in interactive fashion
```
mvn archetype:generate
```

### Creating a maven web aplication project in batch mode
```
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-webapp -DgroupId=org.tektutor -DartifactId=tektutor-web-app -Dversion=1.0.0 -DinteractiveMode=false
```

### Setting up JFrog Artifactory as a Docker container ( Do this as root user )
```
su -
docker run --name artifactory -d -p 8081-8082:8081-8082 docker.bintray.io/jfrog/artifactory-oss:latest
```
The expected output is
<pre>
[root@tektutor ~]# docker run --name artifactory -d -p 8081:8081 docker.bintray.io/jfrog/artifactory-oss:latest
Unable to find image 'docker.bintray.io/jfrog/artifactory-oss:latest' locally
latest: Pulling from jfrog/artifactory-oss
4f4fb700ef54: Pull complete 
f311cd70d478: Pull complete 
b2da589d92d5: Pull complete 
4fb7164329e5: Pull complete 
7aa8827ab216: Pull complete 
913096d3c8e9: Pull complete 
d80e13affa43: Pull complete 
df547a4d7a28: Pull complete 
9e2b73f22d1d: Pull complete 
ed8c56f5480d: Pull complete 
Digest: sha256:818a555a78b331da16ef4ba528490a56ef6c27e9d0fc9e777039044f7195922b
Status: Downloaded newer image for docker.bintray.io/jfrog/artifactory-oss:latest
653d4d7ffc8a8a04dd6a5b7aebf3c6bdb457531f8179b635fdfc9d044f21b15b
</pre>
You may have to type 'rps@12345' as root password when it prompts.

See if the artifactory is running
```
docker ps
```
The expected ouput is
<pre>
[root@tektutor ~]# docker ps
CONTAINER ID   IMAGE                                            COMMAND                  CREATED          STATUS          PORTS                                       NAMES
653d4d7ffc8a   docker.bintray.io/jfrog/artifactory-oss:latest   "/entrypoint-artifac…"   33 seconds ago   Up 31 seconds   0.0.0.0:8081->8081/tcp, :::8081->8081/tcp   artifactory
</pre>
