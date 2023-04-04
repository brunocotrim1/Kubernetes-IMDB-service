@REM First build a docker image with the tag people-micro-service:v2 for example
@REM Then run the following commands
@REM Update the deployment to use the new image
kubectl set image deployment/people-micro-service people-micro-service=people-micro-service:v2
@REM Check the status of the rollout
kubectl rollout status deploy/people-micro-service
@REM Rollback to the previous version
kubectl rollout undo deploy/people-micro-service