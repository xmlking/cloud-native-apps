Prerequisites
=============


### Installation 

####  Java 8
1. Install latest 8.x [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (not JRE)
2. Java Cryptography Extension (JCE)

    Download  "Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files" for 8 and install from [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    Copy `local_policy.jar` and `US_export_policy.jar` to the `$JAVA_HOME/jre/lib/security` 
    for Mac: `/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/lib/security/`
    (Note: these jars will be already there so you have to overwrite them)


### Docker
1. Install Docker CLI and Docker Engine (Mac or Linux only)
2. Install OpenShift 3 CLI

### brew
Install HTTPie
```bash
# Mac distribution
$ brew install httpie
# Debian-based distributions such as Ubuntu:
$ apt-get install httpie
# RPM-based distributions:
$ yum install httpie
```
jmeter
```bash
brew install jmeter
```

### SDKMAN
get SDKMAN! from sdkman.io 

> On Windows: install SDKMAN via Git-Bash command prompt.

Install following software 

    sdk i <software> <version>
     groovy 2.4.7
     grails 3.2.0.M2
     kotlin 1.1-M01
     maven 3.3.9
     gradle  2.14.1
     springboot 1.3.5.RELEASE
     spring install org.springframework.cloud:spring-cloud-cli:1.2.0.BUILD-SNAPSHOT

 
#### Infra Setup
Setup [Infrastructure](./infra) software. 

 