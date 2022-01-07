### Creating Compile CRM FreeStyle Job
1. Create a FreeStyle Job
2. In the General Section, Select the checkbox "Restrict where this project can be run" and type "devops-jenkins-slave".
3. In the Source Code Management Section, Select Git option box and type "https://github.com/tektutor/devops-jan-2022.git"
4. Under Source Code Management Section --> Branches to Build -> Branch Specifier (blank for 'any') type "*/main"
5. Under Build Triggers Section, select Build after other projects are built and choose "Build Docker Jenkins Slave Image"
6. Under Build Section, Select "Execute Shell" and type the below commands inline
```
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export M2_HOME=/usr/share/maven
export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$PATH

cd Day2/Maven/CRM
mvn compile
```
7. Save

