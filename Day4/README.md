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

