# littlepay
- The REST application is built using Grizzly HTTP server. the config class for it is in `org.example.config.ServerApp`
- To run the application simply run the main method in ServerApp
- Curl Example: `curl --location --request POST 'http://localhost:8080/api/parse' \
  --header 'Content-Type: text/plain' \
  --data-raw '9f2a0804000000000000005f2a0208269f02031234565a08378282246310005f9f030100'`
### Some suggestions:
    - Refactor code by removing magic values 
    - Add more tests for coverage
    - Use Spring boot as the backbone for the project, for ease of configuration, embedded server and dependency management
    - Add a queue or a message broker to handle incoming messages more effectivily