# java-opensource

## Installation

1. Run Eureka Service 
2. Run the rest of the microservices so they registered themselves
## Services

| Service | Port | Description|
| --- | --- | --- |
| Registry | 1111 | Eureka Server|
| Camp | 2222 | Creates Heroes and Parties |
| Arena | 3333 | Creates a battleground for parties |
| Promoter | 4444 | Creates two parties and sends them to the arena |
