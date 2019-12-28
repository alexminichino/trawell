
# Trawell Project
[![Build Status](https://travis-ci.com/alexminichino/trawell.svg?branch=master)](https://travis-ci.com/alexminichino/trawell)

[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=alexminichino_trawell&metric=alert_status)](https://sonarcloud.io/dashboard?id=alexminichino_trawell) 

 [![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=alexminichino_trawell&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=alexminichino_trawell)

 [![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=alexminichino_trawell&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=alexminichino_trawell)

 [![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=alexminichino_trawell&metric=vulnerabilities)](https://sonarcloud.io/component_measures/metric/security_rating/list?id=alexminichino_trawell)

 [![Maintainability](https://api.codeclimate.com/v1/badges/ef0e304631b60709a830/maintainability)](https://codeclimate.com/github/alexminichino/trawell/maintainability)

### todo descrizione progetto 
@umbertoRussomando 

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
Effettuare la connessione al db tramite VScode ed eseguire lo script `database_init.sql`.