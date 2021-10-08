
# multiple selected (one-liner)
spring.profiles.active=p1,p2,p3


# Multiple configured in one file

Starting version 2.4, Spring Boot has extended its support for multi-document files for properties files in addition to previously supported YAML. So now, we can specify the dev and production properties in the same application.properties:

my.prop=used-always-in-all-profiles
#---
spring.config.activate.on-profile=dev
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=root
spring.datasource.password=root
#---
spring.config.activate.on-profile=production
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=sa

# multiple profiles active under one GROUP:
Another feature added in Boot 2.4 is Profile Groups. As the name suggests, it allows us to group similar profiles together.

Let's consider a use case where we'd have multiple configuration profiles for the production environment. Say, a proddb for the database and prodquartz for the scheduler in the production environment.

To enable these profiles all at once via our application.properties file, we can specify:

spring.profiles.group.production=proddb,prodquartz
Consequently, activating the production profile will activate proddb and prodquartz as well.

Created by Miroslav Makarov, 1 hour ago

Miroslav Makarov
Add comment
