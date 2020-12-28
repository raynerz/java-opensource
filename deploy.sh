mvn clean package -DskipTests=true

# Make docker use the minikube docker daemon
eval $(minikube docker-env)

# Build the Docker images (in minikube)

# Registry
cd registry
docker build -t heroes/registry:v1 .
cd ..

# Camp
cd camp
docker build -t heroes/camp:v1 .
cd ..

# Arena
cd arena
docker build -t heroes/arena:v1 .
cd ..

# Promoter
cd promoter
docker build -t heroes/promoter:v1 .
cd ..

# Frontend
cd frontend
docker build -t heroes/frontend:v1 .
cd ..

# Apply the Kubernetes resources
kubectl apply -f k8s