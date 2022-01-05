# What is DevOps?
  - a way of project development where developers, qa and operations team attempts
    to automate whatever service they offer
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
