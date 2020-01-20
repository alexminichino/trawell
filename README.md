
# Trawell Project

## Documentazione [disponibile qui!](https://drive.google.com/drive/folders/1XJY9enjR8BZve7qnEP1GOPHmPSA11GEf?usp=sharing)

## Cos'è TraWell:
La piattaforma TraWell nasce con l’obbiettivo di fornire all’utente una serie di servizi legati al mondo
dei viaggi, con lo scopo di rendere più facile la condivisione delle proprie esperienze di viaggio, la
pianificazione del nuovo viaggio, mettendo a disposizione degli ulteriori servizi che facilitano la
comunicazione e l’organizzazione del viaggio

## Metriche e risultati 
[![Build Status](https://travis-ci.com/alexminichino/trawell.svg?branch=master)](https://travis-ci.com/alexminichino/trawell)

[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=alexminichino_trawell&metric=alert_status)](https://sonarcloud.io/dashboard?id=alexminichino_trawell) 

 [![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=alexminichino_trawell&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=alexminichino_trawell)

 [![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=alexminichino_trawell&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=alexminichino_trawell)

 [![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=alexminichino_trawell&metric=vulnerabilities)](https://sonarcloud.io/component_measures/metric/security_rating/list?id=alexminichino_trawell)

 [![Maintainability](https://api.codeclimate.com/v1/badges/ef0e304631b60709a830/maintainability)](https://codeclimate.com/github/alexminichino/trawell/maintainability)

 [![codecov](https://codecov.io/gh/alexminichino/trawell/branch/master/graph/badge.svg)](https://codecov.io/gh/alexminichino/trawell)

 [![Coverage Status](https://coveralls.io/repos/github/alexminichino/trawell/badge.svg?branch=master)](https://coveralls.io/github/alexminichino/trawell?branch=master)

[![Contributors](https://img.shields.io/github/contributors/alexminichino/trawell)](https://github.com/alexminichino/trawell/graphs/contributors)



[![issue open](https://img.shields.io/github/issues/alexminichino/trawell)](https://github.com/alexminichino/trawell/issues)

[![issue closed](https://img.shields.io/github/issues-closed/alexminichino/trawell)](https://github.com/alexminichino/trawell/issues?q=is%3Aissue+is%3Aclosed)



[![opened pull request](https://img.shields.io/github/issues-pr/alexminichino/trawell)](https://github.com/alexminichino/trawell/pulls)



[![closed pull request](https://img.shields.io/github/issues-pr-closed/alexminichino/trawell)](https://github.com/alexminichino/trawell/pulls?q=is%3Apr+is%3Aclosed)

[![commit](https://img.shields.io/github/commit-activity/m/alexminichino/trawell)](https://github.com/alexminichino/trawell/commits)



[![forks](https://img.shields.io/github/forks/alexminichino/trawell)](https://github.com/alexminichino/trawell/network/members)


## Deploy:

[![Docker](https://img.shields.io/docker/automated/alexminichino/trawell)](https://hub.docker.com/r/alexminichino/trawell)


[![Website](https://img.shields.io/website?up_message=online&url=https%3A%2F%2Ftrawellunisa.herokuapp.com%2F)](https://trawellunisa.herokuapp.com/)



[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/alexminichino/trawell)




## Manuale installazione:


### Come prima cosa modificare il file in /src/main/resources/application.properties

Impostare le variabili d'ambiente 
- DATABASE
- DB_USER
- DB_PASSWORD
- DB_HOST
- DB_PORT

 (aggiungendo alla fine del file ~/.bashrc  `export NOME_VARIABILE=valore`)

per consentire l'accesso al db.

### inizialiazzare il database
Eseguire lo script `database_init.sql`.

Una volta inizializzato il database eseguire `./mvnw compile -f pom.xml`.

Infine, per lanciare l'applicativo difitare `./mvnw spring-boot:run`.


### Si consiglia di utilizzare ubuntu 18.04, inoltre è possibile effettuare il deploy tramite Heroku e scaricare l'immagine aggiornata del sistema da Docker hub.
## Contributors


<a href="https://github.com/alexminichino/trawell/graphs/contributors">
  <img src="https://contributors-img.firebaseapp.com/image?repo=alexminichino/trawell" />
</a>