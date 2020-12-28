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
I couldn't start 3 services, when I tried running ```kubectl get pods``` I got the following:
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
