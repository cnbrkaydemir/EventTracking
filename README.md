# General Information About EventTracking Project  

Back-end side of the EventTracking app is called EventTracking. EventTracking app is an app for managing events, creating new events and managing event-user interaction,
so we can say it resembles various event-managment apps like Google calendar. The architecture of the app is SOA(Server Oriented Architecture) so  angular is responsible
for the front-end which runs on localhost 4200 and Java Spring is responsible for the back-end side and interacting with databases on localhost 8080. These two servers 
work together to make the app run. Also the app uses PostgresSql as RDBMS and depends on multiple tables like events,users and authorities and all these tables 
have relationships between them .
 
 # Back-End Overview

Back-end is all about Java Spring. Specifically Spring Data Jpa , Spring Boot and Spring Security. Spring Data Jpa is responsible for interacting with the databse and 
acquiring desired data. Spring Boot is a must for every spring project. And Spring Security is everything when it comes to security. Let's dive deep into the security 
of the project. In this project the authentication and authorization is accomplished thanks to JWT tokens. When an user enters their credentials and signs in to the page
the backend creates a jwt token which contains information about the user that is logged in. At every request , back-end also confirms if this user has 
the authority to create a new event or add new users to the events, using the information hidden inside Jwt. The project uses bcrypt password encoder  so each password 
is hashed before getting saved to the database. Also note that front-end and back-end work together to accomplish authorization. Interceptor from the front-end and 
back-end work simultaneously to handle authorization. And that is basically how the back-end works.


You can also view the front-end project [event-tracking-ui](https://github.com/cnbrkaydemir/event-tracking-ui).
