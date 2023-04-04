mvn -f ./tittles-micro-service/pom.xml clean package
mvn -f ./episode-micro-service/pom.xml clean package
mvn -f ./people-micro-service/pom.xml clean package
mvn -f ./principals-micro-service/pom.xml clean package
docker compose build
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.0.0/deploy/static/provider/cloud/deploy.yaml
kubectl wait --namespace ingress-nginx --for=condition=ready pod --selector=app.kubernetes.io/component=controller --timeout=120s
kubectl apply -f ./k8s/postgres-statefulset.yml
kubectl apply -f ./k8s/spring_k8s.yml
kubectl apply -f ./k8s/ingress.yml

