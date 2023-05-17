Phase 1
To run this project we need to first run the initial_script.bat that will be downloading and unzipping the datasets, after we need to execute the run_script.bat
this script will build the microservices and run our containerized microservices on docker, when the docker compose up command runs the containers will start up
but we need to wait aroung 10-20 min to the service be fully working since the dataset is around 10gb so the load takes a bit of time.
----------------------------------------------------------------------------------------------------------------------------------------------------------------
Execute the run_script in order to maven build the microservices, build the docker images and do some initial setup of the k8s components.
Now we will need to execute the prometheus k8s and monitoring related components, for that we should open a terminal in the folder /k8s/prometheus and execute 
the prometheus script that will take care of this.

To load the postgres database you should open the initial_script file and change the address to the public address of your postgres container, after that execute
the script wait for the password prompt and after that the database will be loaded.

To use the grafana open the adress in the browser, and import the json containing the dashboard that will show us some usefull metrics related to performance and 
network requests: 
1- Add Prometheus as a datasource and point it to http://prometheus-service:9090
2- Go to dashboards and import the json dashboard that is placed in the k8s folder
3- On Refresh each dashboard to show metrics

To execute Admin endpoints Auth0 is used so in order to get an access token you can execute this request:
curl --location 'https://dev-aula3bjod6orbofw.us.auth0.com/oauth/token' \
--header 'Content-Type: application/json' \
--header 'Cookie: did=s%3Av0%3A1b04f820-d0b2-11ed-a37e-87072a67206d.0NMA%2FvN%2F5ifeI%2F4LJqTUvmyD0h0bIu6NyId6OrGD7M0; did_compat=s%3Av0%3A1b04f820-d0b2-11ed-a37e-87072a67206d.0NMA%2FvN%2F5ifeI%2F4LJqTUvmyD0h0bIu6NyId6OrGD7M0' \
--data '{
    "client_id": "6IfdQJ0dWmk3HBdIQ1TtFcHjJD7hpikV",
    "client_secret": "8yhrXJGtfaJh59t93CKvfiGmAhXWVdNv9_kps4wNZtR9WFlpBpRSQbqf3JeozToe",
    "audience": "https://dev-aula3bjod6orbofw.us.auth0.com/api/v2/",
    "grant_type": "client_credentials"
}' 
This request will give you an access token that will provide you admin access to restricted endpoints