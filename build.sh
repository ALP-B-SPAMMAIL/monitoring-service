docker build -t team04registry.azurecr.io/monitoring-service:latest .
docker push team04registry.azurecr.io/monitoring-service:latest
kubectl rollout restart deployment monitoring-service