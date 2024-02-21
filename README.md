# DataQueueApplication

## Description

DataQueueApplication est une application Java Spring Boot qui gère les files d'attente de données. Elle utilise Apache Camel pour la gestion des routes et Atomikos pour la gestion des transactions.

## Installation

1. Clonez ce dépôt.
2. Assurez-vous que vous avez installé Java et Maven sur votre machine.
3. Exécutez `mvn clean install` pour construire le projet.

## Utilisation

Exécutez `java -jar target/dataqueue-0.0.1-SNAPSHOT.jar` pour démarrer l'application.

## Fonctionnalités

- **MyDataQueueProcessor** : Cette classe reçoit un message et le traite. En cas d'échec, une exception est levée.
- **Jt400RouteBuilder** : Cette classe définit les routes pour le traitement des messages. Elle comprend une politique de nouvelle tentative en cas d'échec du traitement d'un message.
- **AppConfig** : Cette classe configure la gestion des transactions pour l'application.
- **DataQueueApplication** : Il s'agit de la classe principale qui démarre l'application.
