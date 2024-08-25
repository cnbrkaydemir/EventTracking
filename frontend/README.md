
# General Information About EventTracking Project  

Front-end side of the EventTracking app is called event-tracking-ui. EventTracking app is an app for managing events, creating new events and managing event-user interaction, so we can say it resembles various event-managment apps like Google calendar. The architecture of the app is SOA(Server Oriented Architecture) so  angular is responsible for the front-end which runs on server x  and Java Spring is responsible for the back-end side and interacting with databases on localhost server y. These two servers work together to make the app run. Also the app uses PostgresSql as RDBMS and contains multiple tables like events,users and authorities and all these tables have relationships between them .
 
 # Front-End Overview

 For front-end I have worked with the js framework angular. Which is the second most popular js framework after obviously react . As you can understand front-end relies heavily on typescript and html, lots of types and tags. For styling,  scss is the default  styling sheet but styling is made thanks to the wonders of bootsrap. General workflow can be simplfied as  The app  makes a request to back-end in order to display data from database , after it receives the data about users or events the app uses various tables to display these data. So http requests are a big part of the front-end design. Another important part of the front-end is interceptors, we use interceptors to provide authentication and authorization. Interceptors also makes sure jwt token is passed to http request header when a request is made to the back end. So another reason why the  project depends heavily on the front-end is securing the application.
 

You can also view the back-end project [EventTracking](https://github.com/cnbrkaydemir/EventTracking).
