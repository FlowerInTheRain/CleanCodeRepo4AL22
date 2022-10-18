# CleanCodeRepo4AL22

Class :
Clean Code + a bit of Software Architecture

Team Board :
https://kelinis.atlassian.net/jira/software/projects/CC/boards/1

Team Members :
Arnaud Jourdain / Sid Bennaceur

To run this project you'll need :

=> Maven ^3.6 (easily installable with Chocolatey)

=> Java 19

=> IntellijUltimate makes life easier for embedded maven usage and launching app localy

Project Specs :
=> Agile Methodology, Sprint usage with Jira.

=> Constant 80% Code Coverage minimum (UT done using JUNIT and Mockito).

=> SwaggerUI / SwaggerDoc for simple API testing.

=> PR / Pushes trigger a full build with a maven clean package test install

=> Sonar analysis (code coverage, code smells, clarity) during builds

=> PairProgramming

=> Conception + Mutual review > Trial and Error

Project initial architecture :
=> One Pom to rule them all. POM at root project is the dependency big boss.

![image](https://user-images.githubusercontent.com/39587466/196554650-c5e63bbf-f874-4457-8d1a-aee3ec1d9cbe.png)

=> Child get reference using 
![image](https://user-images.githubusercontent.com/39587466/196554737-5c6c4ce7-09c6-4bdb-a98c-5f1782671092.png)

=> Each module (Rest / API / CLIENT / CONF / IMPL / DBIMPL) has it's own POM and uses the dependencies he needs by calling them explicitly in its own POM
![image](https://user-images.githubusercontent.com/39587466/196554940-eb93fc9a-b2a5-4d6a-a428-4da1cf4325b0.png)

=> Module Rest Exposes EntryPoints

=> Module API contains Beans used for data exchange, mapper...

=> Module Client is a client of the app. I put it in the project so that any change to the app will impact the client, securize it's configuration so that we don't expose it elsewhere, using the client simply require to create a dependency. Useful for microservices purpose.

=> Module IMPL contains functional code. Business logic.

=> Module DBIMPL handles connexions to the embedded DB (H2)

After cloning the project, and before using it, you'll need to do at least a Maven Clean Install from root POM in Intellij or using CLI

![image](https://user-images.githubusercontent.com/39587466/196553314-efa3b514-b1ec-4d46-ab1c-7a3bec59cfed.png)


To code in this project you'll need :

=> Decent knowledge of the Maven tool

=> Motivation to do rigorous dev. A ton of UT/IT.

=> Architectural and CleanCode expert (wannabe) skills
