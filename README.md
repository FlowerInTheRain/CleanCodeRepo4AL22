# CleanCodeRepo4AL22

Class :
Clean Code + a bit of Software Architecture

Team Board :
https://kelinis.atlassian.net/jira/software/projects/CC/boards/1

Team Members :
Arnaud Jourdain / Sid Bennaceur

App local url :
http://localhost:8090/



To run this project you'll need :

=> Maven ^3.6 (easily installable with Chocolatey)

=> Java 19

=> IntellijUltimate makes life easier for embedded maven usage and launching app localy (Free with student license using your GES mail adress, visit http://jetbrains.com)

=> SonarQube https://www.sonarqube.org/downloads/ (Download free version, extract folder, run startSonar.bat in bin/{YourOs}/startSonar.bat)

=> GitHub VCS (collaborative work/reviews)

Project Purpose :

=> Our aim was to create a multiple modules SpringBoot app using the strength of Maven to manage the dependencies, testing, code analysis and packaging of the app.

=> This app tries to implement as best as it can given the architecture the Clean Code principles. Those principles include :
- Hexagonal Architecture
- SOLID concept
- no external effect

=> Use the strength of Maven for building multimodule app and handling our modules as artifacts. Any module can be a dependency of any module.

Example :
I have 2 projects 
- CleanCode (the rest app). A multimodule app with a rest module, an impl.
- A CleanCodeDB (the DB transaction manager app). A multimodule app with a rest module, an impl module, a client module that creates a client to call it's rest API) and a db impl module that does the transaction stuff.

If I want to use the client package of CleanCodeDB into CleanCode (because I don't want to pair my app with a client that doesn't get packaged with the APP it works with (dup issues if we set the client in the CleanCodeApp, if I create another toplevel app I'll need another client). All I need to do is to add it's dependency and it's version SNAPSHOT (or not SNAPSHOT if released) to use it.


Project Specs :

=> Agile Methodology, Sprint usage with Jira.

=> Constant 80% Code Coverage minimum (UT done using JUNIT and Mockito).

=> SwaggerUI / SwaggerDoc for simple API testing.

=> PR / Pushes trigger a full build with a maven clean package test install

=> Sonar analysis (code coverage, code smells, clarity) during builds

=> PairProgramming

=> Conception + Mutual review > Trial and Error

=> Why make an UI when Swagger exists ?


![image](https://user-images.githubusercontent.com/39587466/197039651-65d45830-6246-4933-ac3e-aada7111c48c.png)

=> IntelliJ application conf (find the before launch task in the modify option under the browser selection window)

![image](https://user-images.githubusercontent.com/39587466/197053718-f91d639b-795d-4e17-ab50-1ae6354e33b0.png)


Project initial architecture :


=> One Pom to rule them all. POM at root project is the dependency big boss.

![image](https://user-images.githubusercontent.com/39587466/196554650-c5e63bbf-f874-4457-8d1a-aee3ec1d9cbe.png)

=> Child get reference using 

![image](https://user-images.githubusercontent.com/39587466/196554737-5c6c4ce7-09c6-4bdb-a98c-5f1782671092.png)

=> Each module (Rest / API / CLIENT / CONF / IMPL / DBIMPL) has it's own POM and uses the dependencies he needs by calling them explicitly in its own POM

![image](https://user-images.githubusercontent.com/39587466/196554940-eb93fc9a-b2a5-4d6a-a428-4da1cf4325b0.png)

=> Module Rest Exposes EntryPoints

=> Module API contains Beans used for data exchange, mapper...

=> Module Client is a client of the app. I put it in the project so that any change to the app will impact the client, securize it's configuration so that we don't expose it elsewhere, using the client simply require to create a dependency using the submodule POM version (SNAPSHOT for dev / without for releases). Useful for microservices purpose.

=> Module IMPL contains functional code. Business logic.

=> Module DBIMPL handles connexions to the embedded DB (H2)

After cloning the project, and before using it, you'll need to do at least a Maven Clean Install from root POM in Intellij or using CLI

![image](https://user-images.githubusercontent.com/39587466/196553314-efa3b514-b1ec-4d46-ab1c-7a3bec59cfed.png)


To code in this project you'll need :

=> Decent knowledge of the Maven tool

=> Motivation to do rigorous dev. A ton of UT/IT.

=> Architectural and CleanCode expert (wannabe) skills
