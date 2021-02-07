# study
Study is an extremely simple project for revising terms that I put together!  
Check out the one running in [production](https://study.pollo.cc)!

study uses MongoDB for storage, and the webserver is run on port 23000.

## Environment Variables
The following environment variables must be setup before usage:
- STUDY_BASE_URL: base URL where you intend to run on, eg. https://study.pollo.cc
- STUDY_MONGODB_HOST: host for your MongoDB instance
- STUDY_MONGODB_PORT: port for your MongoDB instance
- STUDY_MONGODB_USERNAME: username for your MongoDB instance
- STUDY_MONGODB_PASSWORD: password for your MongoDB instance
- STUDY_MONGODB_DATABASE: database for your MongoDB instance