FROM tomcat:9.0.73
COPY target/spring-mvc-appl.war /usr/local/tomcat/webapps
COPY tomcatConfig/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY tomcatConfig/web.xml /usr/local/tomcat/conf/web.xml
RUN cp -R /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps
ADD https://dtdg.co/latest-java-tracer /usr/local/tomcat/webapps/dd-java-agent.jar
COPY tomcatConfig/context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
