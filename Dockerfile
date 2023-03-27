FROM tomcat:9.0.73
COPY target/spring-mvc-appl.war /usr/local/tomcat/webapps
RUN cp -R /usr/local/tomcat/webapps.dist/* /usr/local/tomcat/webapps