### Creating Compile CRM FreeStyle Job
1. Create a FreeStyle Job, give a name "Compile CRM"
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

### Creating Test CRM FreeStyle Job
1. Create a FreeStyle Job, give a name "Test CRM"
2. In the General Section, Select the checkbox "Restrict where this project can be run" and type "devops-jenkins-slave".
3. In the Source Code Management Section, Select Git option box and type "https://github.com/tektutor/devops-jan-2022.git"
4. Under Source Code Management Section --> Branches to Build -> Branch Specifier (blank for 'any') type "*/main"
5. Under Build Triggers Section, select Build after other projects are built and choose "Compile CRM"
6. Under Build Section, Select "Execute Shell" and type the below commands inline
```
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export M2_HOME=/usr/share/maven
export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$PATH

cd Day2/Maven/CRM
mvn test
```
7. Save

### Creating Package CRM FreeStyle Job
1. Create a FreeStyle Job, give a name "Package CRM"
2. In the General Section, Select the checkbox "Restrict where this project can be run" and type "devops-jenkins-slave".
3. In the Source Code Management Section, Select Git option box and type "https://github.com/tektutor/devops-jan-2022.git"
4. Under Source Code Management Section --> Branches to Build -> Branch Specifier (blank for 'any') type "*/main"
5. Under Build Triggers Section, select Build after other projects are built and choose "Test CRM"
6. Under Build Section, Select "Execute Shell" and type the below commands inline
```
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export M2_HOME=/usr/share/maven
export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$PATH

cd Day2/Maven/CRM
mvn package
```
7. Save

### Creating Deploy CRM FreeStyle Job
1. Create a FreeStyle Job, give a name "Deploy CRM"
2. In the General Section, Select the checkbox "Restrict where this project can be run" and type "devops-jenkins-slave".
3. In the Source Code Management Section, Select Git option box and type "https://github.com/tektutor/devops-jan-2022.git"
4. Under Source Code Management Section --> Branches to Build -> Branch Specifier (blank for 'any') type "*/main"
5. Under Build Triggers Section, select Build after other projects are built and choose "Package CRM"
6. Under Build Section, Select "Execute Shell" and type the below commands inline
```
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export M2_HOME=/usr/share/maven
export PATH=$JAVA_HOME/bin:$M2_HOME/bin:$PATH

cd Day2/Maven/CRM
mvn deploy
```
7. Save
