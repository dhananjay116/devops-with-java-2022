### Volume Mounting - persisting data outside mysql container
```
mkdir -p /tmp/mysql
docker run -d --name db --hostname db -v /tmp/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root mysql:latest
```

See if the mysql container is running
```
docker ps
```

Get inside the mysql container
```
docker exec -it db sh
mysql -u root -p
```
When prompts for password, type 'root' without quotes.

At this point, you would be connect to mysql prompt
```
SHOW DATABASES;
CREATE DATABASE tektutor;
USE tektutor;
CREATE TABLE Training ( id INT, name VARCHAR(45), duration VARCHAR(45) );
INSERT INTO Training VALUES ( 1, "DevOpS", "5 Days" );
INSERT INTO Training VALUES ( 2, "Embedded C++", "10 Days" );
SELECT * FROM Training;
exit
exit
```

Now let's delete the mysql container named db.
```
docker rm -f db
```

Now let's create a new container either with same name or different name. 
```
docker run -d --name db --hostname db -v /tmp/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root mysql:latest
```

As you have noticed above, we are still mounting the same /tmp/mysql host path(VM path) inside the new container.

Let's get inside the mysql container
```
docker exec -it db sh
mysql -u root -p
SHOW DATABASES;
USE tektutor;
SELECT * FROM Training;
```
The expectation is that, you will still see the tektutor and Training records intact even though we deleted the container that inserted those records.  This is made possible as we are using Volume Mounting feature of Docker.
