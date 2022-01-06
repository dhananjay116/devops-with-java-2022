FROM ubuntu:16.04
MAINTAINER Jeganathan Swaminathan <jegan@tektutor.org>

RUN apt-get update && apt-get install -y default-jdk maven
RUN apt-get install -y git

COPY settings.xml /usr/share/maven/conf/settings.xml
RUN mkdir -p /root/.m2

CMD ["/bin/bash"]
