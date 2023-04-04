kubectl create namespace monitoring
kubectl create -f ./clusterRole.yml
kubectl create -f ./config-map.yml
kubectl create  -f ./deployment.yml