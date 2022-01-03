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

### Listing maven default life-cycle
```
mvn help:describe -Dcmd=compile
```
