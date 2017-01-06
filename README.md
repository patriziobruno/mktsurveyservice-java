# mktsurveyservice-java
[![license](https://img.shields.io/github/license/patriziobruno/mktsurveyservice-java.svg)](https://raw.githubusercontent.com/patriziobruno/mktsurveyservice-java/master/LICENSE)
[![Build Status](https://travis-ci.org/patriziobruno/mktsurveyservice-java.svg?branch=master)](https://travis-ci.org/patriziobruno/mktsurveyservice-java)
[![Coverage Status](https://coveralls.io/repos/github/patriziobruno/mktsurveyservice-java/badge.svg?branch=master)](https://coveralls.io/github/patriziobruno/mktsurveyservice-java?branch=master)
[![Coverity Scan Build Status](https://img.shields.io/coverity/scan/10664.svg)](https://scan.coverity.com/projects/patriziobruno-mktsurveyservice-java)

A demo Jersey REST service developed to prove some of my Java skills.
It makes use of:
 - Jersey for REST
 - Apache Oltu (https://github.com/apache/oltu) for OAUTH2
 - HK2 for dependency injection
 - JCR and JPA as storage alternatives.
  - JCR storage module can be used to run the service without relying on external dependencies. The project is configured to use Apacke Jackrabbit (https://github.com/apache/jackrabbit) as JCR backend and explicitly depends on it.
  - JPA storage module has been tested only with hibernate-ogm (https://github.com/hibernate/hibernate-ogm) and MongoDB (https://github.com/mongodb/mongo). The project explicitly depends on hibernate-ogm.
  
