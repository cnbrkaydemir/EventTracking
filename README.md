# General Information About EventTracking Project  

Back-end side of the EventTracking app is called EventTracking. EventTracking app is an app for managing events, creating new events and managing event-user interaction,
so we can say it resembles various event-managment apps like Google calendar. The architecture of the app is SOA(Server Oriented Architecture) so  angular is responsible
for the front-end which runs on localhost 4200 and Java Spring is responsible for the back-end side and interacting with databases on localhost 8080. These two servers 
work together to make the app run. Also the app uses PostgresSql as RDBMS and depends on multiple tables like events,users and authorities and all these tables 
have relationships between them .
 
 # Back-End Overview

Back-end is all about Java Spring. Specifically Spring Data Jpa , Spring Boot and Spring Security. Spring Data Jpa is responsible for interacting with the database and 
acquiring desired data. Spring Boot is a must for every spring project. And Spring Security is everything when it comes to security. Let's dive deep into the security 
of the project. In this project the authentication and authorization is accomplished thanks to JWT tokens. When an user enters their credentials and signs in to the page
the back-end creates a jwt token which contains information about the user that is logged in. At every request , back-end also confirms if this user has 
the authority to create a new event or add new users to the events, using the information hidden inside Jwt. The project uses bcrypt password encoder  so each password 
is hashed before getting saved to the database. Also note that front-end and back-end work together to accomplish authorization. Interceptor from the front-end and 
back-end work simultaneously to handle authorization. And that is basically how the back-end works.


# Database Overview
As mentioned before PostgresSql was used as the rdbms for the project. The database called events  has four tables events,users,authorization and participation. Users table contains information about users such as name,surname,email and password. Each user can participate in multiple events. Events table contains information about events such as title,description,start date,end date and created_by. Each event has a creator which is type user. Also events can have multiple users. Authorities is a table for storing user authorization roles like admin or user.  Each user can have multiple authorities or authorization roles. These were the basic tables now let's take a look at relationships between tables and their cardinalities. Users and events have a one-to-one relationship called created_by since only one user could have created an single event.For this relationship we create a foreign key at events table with the id of the user who created this event. Another relationship between users and events is called participation , participation relationship is required since users can participate in multiple events and each event can have multiple users attending it. Therefore we need a table for representing this relationship called participation which holds event id and user id. Each event_id-user_id combination symbolizes a participation of a user to an event. Last relationship is between authorities and users. They have a many-to-one relationship and for that reason we use a foreign key at authorities called user_id.

# Sql Queries to Visualize the Tables

create table events(
event_id serial Primary Key,    
event_title varchar(100),
event_description varchar(500),
event_date date,
event_expired  date,
created_by int,
FOREIGN KEY (created_by) REFERENCES users (user_id),  
);

create table users(
user_id serial Primary Key,
user_name varchar(100),
user_surname varchar(100),
user_email varchar(100),
user_password varchar(500),    
user_role varchar(80)
);


CREATE TABLE authorities (
  id  serial primary key,
  user_id int NOT NULL,
  name varchar(50) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);



create Table participation(
user_id int Not Null,
event_id int  Not Null,
FOREIGN KEY (user_id) REFERENCES users (user_id),
Foreign Key (event_id) REFERENCES events(event_id)    
);





You can also view the front-end project [event-tracking-ui](https://github.com/cnbrkaydemir/event-tracking-ui).

