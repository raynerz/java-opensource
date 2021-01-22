# java-opensource

## Start application

This project runs on Kubernetes. In order to deploy the application:

Start the cluster, e.g.

```minikube start```

Deploy the application:

```./deploy.sh```

To remove the application from the cluster:

```kubectl delete -f k8s```

## Services

| Service | Port | Description|
| --- | --- | --- |
| Registry | 1111 | Eureka Server|
| Camp | 2222 | Creates Heroes and Parties |
| Arena | 3333 | Creates a battleground for parties |
| Promoter | 4444 | Creates two parties and sends them to the arena |
|Frontend | 8080 | Implements an API Gateway to reach the rest of the services | 
## Additional Ressources

| Resource          | URL                   |
|----------------- |-----------------------| 
|Hystrix Stream    | http://localhost:4444/actuator/hystrix.stream |
|Hystrix Dashboard | http://locahhost:4444/hystrix |

## Issues encountered
### Kubernetes deployment
We couldn't start 3 services, when I tried running ```kubectl get pods``` I got the following:
```
NAME                                   READY   STATUS             RESTARTS   AGE
arena-deployment-746c46859d-x524k      1/1     Running            0          4m56s
camp-deployment-65668f48-tszh8         0/1     ImagePullBackOff   0          4m56s
frontend-deployment-6fb8c9c6-bt6xw     0/1     ImagePullBackOff   0          4m56s
promoter-deployment-868db79664-vqh6l   1/1     Running            0          4m56s
registry-deployment-845d87d74-scmn4    0/1     ImagePullBackOff   0          4m56s
```
#### Solution
the problem was that for camp, frontend and registry, docker was trying to pull the images from the image registry. Normally, K8s deployments contain a `imagePullPolicy` attribute which should be set to `IfNotPresent` so that it can try to look for the images locally before searching on the image registry. I noticed that we had put a version for those 3 services which was different than `1.0`, so when building the images, the `.jar` of those services was mismatching the expected name of their Dockerfile, i.e. we had to change either the Dockerfile to ADD the correct `.jar` or modify the versions. I simply removed the individual version of the submodules so they could inherit it from their parent.

### Oauth2

For securing the API gateway we decided to implement an Oauth2 security configuration. OAuth 2.0 is the industry-standard protocol for authorization. OAuth 2.0 focuses on client developer simplicity while providing specific authorization flows for web applications, desktop applications, mobile phones, and living room devices.
For this task, we have decided to rely on a Oauth2 provider to manage the authorization server and all the user management. The process would be very similar for implementing other similar SSO services, this is because we tried to
build our own authorization server and user management database but the task was a way more complex than we had expected. 

#### Solution 
We decided to use Okta which is a service that provides secure identity management with Single Sign-On, Multi-factor Authentication, Lifecycle Management (Provisioning), and more.
Okta provides the backbone for Oauth2 integration in our project. The steps that we took to do this are described below.

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
The rest of the microservices will be secured by the docker/kubernetes network.

There is a problem with a bad request coming from the okta server, which is due to the fact that the frontend service IP is not fix within the kubernetes cluster, therefore for testing the Oauth2 implementation, one should give the correct ip address of the frontend service to the Okta admin server (through the Okta web portal) and the server will redirect to a login page on the mentioned IP. The credentials needed for accessing this portal are in the kubernetes secret file.  


Source: https://developer.okta.com/blog/2018/02/13/secure-spring-microservices-with-oauth
