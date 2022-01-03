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
