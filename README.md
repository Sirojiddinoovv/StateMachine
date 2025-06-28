
# Loan State Machine

This repository demonstrates a Spring Boot application using Spring State Machine to model a loan processing workflow. Each loan application goes through a series of states, events, actions, and guards to simulate real-world processing steps.

## Features

- Model-driven workflow using Spring State Machine  
- Separate state machine factory for per-request instances  
- Configurable states, events, transitions, actions, and guards  
- Error handling via dedicated `ErrorAction` and cancel flow  
- REST endpoint to trigger the workflow  

## Prerequisites

- Java 21
- Maven 3.8+ or Gradle 7+  
- Spring Boot 3.x  
- Reactor Core (for reactive lifecycle)  

## Getting Started

```bash
# Clone the repository
git clone https://github.com/your-username/loan-state-machine.git
cd loan-state-machine

# Build with Maven
mvn clean package

# Or with Gradle
./gradlew build
```


## States & Events

**States** (`RequestState` enum):
- `NEW`  
- `CLIENT_CHECK`  
- `SALARY_SCORING`  
- `ISSUANCE`  
- `FINISHED`  

**Events** (`LoanEvent` enum):
- `CHECK_LOAN`  
- `CHECK_CLIENT`  
- `SCORING_SALARY`  
- `CREDIT_ACCOUNT`  
- `EXIT`  

## Actions & Guards

- `ReservedAction` — reserves funds; on exception, rethrows to trigger `ErrorAction`.  
- `ScoringAction` — performs salary scoring.  
- `IssueAction` — issues credit; rethrows on failure.  
- `CancelAction` — cancels the loan application.  
- `ErrorAction` — logs errors and triggers `CancelAction`.  
- `BureauGuard` — checks credit bureau results before transition.  

## Usage

Start the application and call the REST endpoint:

```bash
curl -X POST http://localhost:8080/api/loans/{personCode}/process
```

Example response:
```
Loan processed by personCode: 1234567890, final state = FINISHED
```

```
Event CREDIT_ACCOUNT not accepted in state SALARY_SCORING
```


## Contributing

Contributions are welcome! Please submit pull requests against the `main` branch and follow the existing code conventions.

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
