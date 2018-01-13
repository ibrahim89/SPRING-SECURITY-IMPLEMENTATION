# SPRING-SECURITY-IMPLEMENTATION
Annotation Based Spring Security Implementation Using in-memory and database as well

## 1. Database Based Authentication.
# Technologies used.
-----------------
1.Spring 3.2.8.RELEASE
2.Spring Security 3.2.3.RELEASE
3.Spring JDBC 3.2.3.RELEASE
4.Eclipse 4.2
5.JDK 1.8
6.Maven 3.1
7.Tomcat 9 (Servlet 3.x)
8.Postgres version: 42.1.1

----------------------------
## 1. Database authentication, using Spring-JDBC and Postgresql.
## 2. Spring Security, JSP TagLib, sec:authorize access="hasRole('ROLE_USER')
## 3. Customize a 403 access denied page.

-----------------------------

## 4.  Database

To perform database authentication, you have to create tables to store the users and roles detail. Please refer to this [Spring Security user-schema](https://docs.spring.io/spring-security/site/docs/3.2.3.RELEASE/reference/htmlsingle/#user-schema) reference. Here are the Postgres scripts to create users and user_roles tables.

4.1 Create a “users” table.

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled SMALLINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));
  
  4.2 Create a “user_roles” table.
  
  CREATE SEQUENCE user_roles_seq;

CREATE TABLE user_roles (
  user_role_id int NOT NULL DEFAULT NEXTVAL ('user_roles_seq'),
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT uni_username_role UNIQUE (role,username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

  CREATE INDEX fk_username_idx ON user_roles (username);
  
  4.3 Inserts some records for testing.
  
  INSERT INTO users(username,password,enabled)
VALUES ('ibrahim','ibrahim123', 1);
INSERT INTO users(username,password,enabled)
VALUES ('sheeran','sheeran123', 1);

INSERT INTO user_roles (username, role)
VALUES ('ibrahim', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('sheeran', 'ROLE_USER');

## Note:
Username “ibrahim”, with role_user and role_admin.

Username “sheeran”, with role_user.
