# Spring Security Workshop

For securing the API gateway we decided to implement an Oauth2 security configuration. OAuth 2.0 is the industry-standard protocol for authorization. OAuth 2.0 focuses on client developer simplicity while providing specific authorization flows for web applications, desktop applications, mobile phones, and living room devices.
For this task, we have decided to rely on a Oauth2 provider to manage the authorization server and all the user management. The process would be very similar for implementing other similar SSO services.

The following dependencies were added to the frontend-service

```aidl
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security.oauth.boot</groupId>
    <artifactId>spring-security-oauth2-autoconfigure</artifactId>
    <version>2.0.5.RELEASE</version>
</dependency>
```

In order to contact the Okta server, the following was added to the applications.properties

```aidl
security.oauth2.client.client-id=${CLIENT_ID}
security.oauth2.client.client-secret=${CLIENT_SECRET}
security.oauth2.client.access-token-uri=${ACCESS_TOKEN_URI}
security.oauth2.client.user-authorization-uri=${USER_AUTHORIZATION_URI}
security.oauth2.client.scope=openid profile email
security.oauth2.resource.user-info-uri=${USER_INFO_URI}
spring.main.allow-bean-definition-overriding=true

```

The environment variables written above were obtained by creating an application within Okta, the service deals with the authorization server
and user management features in order to enable an easy Oauth2 integration. Our service uses the annotation `@EnableOauth2Sso` and its configured via
`ResourceServerConfig.java` which secures all the endpoints of the service by requesting a Bearer token to each request and contacting the Okta server to validate.
The rest of the microservices will be secured by the docker network within the docker compose file.

Source: https://developer.okta.com/blog/2018/02/13/secure-spring-microservices-with-oauth

