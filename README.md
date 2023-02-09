# How to run this app

To run the app just type in `docker compose up` in the root directory, or if you want to do everything manually use the steps bellow

## Manual steps

### Backend

There are two ways to run the backend:  
* Open the project in IntelliJ IDEA, wait for the dependencies to install, and then click `Run 'BackendApplication'`
* Type `gradlew bootRun` in terminal in the backend directory

### Frontend

* First of all install all dependencies by running `npm install` in the frontend folder  
* Then in the same terminal type `npm run start`

The app will be running on `localhost:3000`
