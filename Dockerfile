FROM openjdk:8u212-jre-stretch
RUN apt-get update && \
    apt-get install nano && \
    apt-get install htop && \
    apt-get install -y dialog && \
    apt-get install -y debconf && \
    apt-get install -y locales && \
    dpkg-reconfigure locales

RUN echo "en_US.UTF-8 UTF-8" >> /etc/locale.gen && \
    locale-gen en_US

ENV LC_ALL=en_US.UTF-8
ENV LANG=en_US

WORKDIR /data/app

COPY ./target/*.jar ./
