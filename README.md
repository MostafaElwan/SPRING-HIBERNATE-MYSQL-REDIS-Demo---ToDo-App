# TODO-DEMO
### [ SPRING - HIBERNATE - MYSQL - REDIS ]

This repository contains a TODO application that demonistrats the usage of spring, Hibernate on MySQL database and REDIS database.

In this demo, I try to focus on not only how to use, but also the core features and the best practice of some of the important frameworks.

## 1. Design Patterns

In this demo, I have followed the following patterns:

#### 1.1. Delegate Pattern

The launcher of the application will contact a business delegate which is owning the whole logic of the TODO items and it is relation with the users through services.

#### 1.2. DAO Pattern

Accessing data will be through some objects which are resposible of hidding the interaction with data storage.

#### 1.3. Singletone Pattern

A single object that will be responsible of caching both the system configurations and Spring context.

#### 1.4. Factory Pattern

A factory to return the needed DAO.

#### 1.5. Factory of Factories Pattern

Based on a system configuration, a factory will decide which factory will be used to get DAOs.


## 2. Frameworks

#### 2.1. Spring

In this demo, I focus on core, JDBC, transaction and integration with AspectJ using anootations. DAO packages is there to access MySQL database through spring-jdbc, hibrenate or even access a different no sql database.

#### 2.2. Hibernate

Of course, Hibernate is one of the most important frameworks which is used to wrap and ease manupilating database. So, another package contains DAOs that access MySQL database but this time through Hibernate. In this demo, I have used the HQL because it gives the best performance, in addition to second-level of caching through ehcache.

#### 2.3. REDIS

No doubt that NoSQL databases is a trend due to the high performance and the ease of use. so, I have decided to use REDIS as one of the most powerfull noSQL databases, thus, another DAOs package is there to access REDIS.


