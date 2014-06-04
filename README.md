About
-----
This is a String Clustering RESTful web service using Jersey (http://jersey.java.net/).

Requirements
------------
This assumes you have the following already installed:
Java
Maven
Tomcat

Usage
-----
Do the following steps:
1. Create a new maven web-app archetype project either using IDE (like Eclipse) or through command line ( I used command line and later ported to Eclipse). The command is
	mvn archetype:generate -DgroupId=com.refine.clustering -DartifactId=cluster -DarchetypeArtifactId=maven-archetype-webapp

   A cluster folder will be created with the default pom.xml and web.xml files.

2. Modify the pom.xml  and web.xml file

3. Next create the classes
 
4. In the cluster directory run the"mvn package" command.  You will see a lot of output aand towards the end you should see a "Build Succesful" message.

5. A "cluster-x.x.war" file (x.x being the version number) will be created in the cluster/target directory.  Deploy that cluster-x.x.war file to tomcat's webapp directory.

6. Use curl[1] or Google's Advanced Rest Client[2] and use URL "http://localhost:8080/cluster-x.x/cluster/post/". Give a json input and a clustered json output is returned. 

For Curl type at Command line

curl -X POST http://localhost:8080/cluster-1.0/cluster/post --header "Content-Type:application/json" -d "[\"Tom Cruise\", \"John\", \"Cruise,
 Tom\",\"john\"]"

[1]http://curl.haxx.se/download.html
[2]https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo 