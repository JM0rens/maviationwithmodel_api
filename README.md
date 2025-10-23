# Military Aviation API

REST API for managing military aircraft, pilots, and deployments using Spring Boot.

## Features

- **Aircraft Management**: Full CRUD operations for military aircraft
- **Pilot Management**: Full CRUD operations for pilots
- **Hangar Management**: 100 numbered hangars (1-100) for aircraft storage
- **Deployment System**: Deploy aircraft with pilots on missions
- **CSV Persistence**: Data stored in CSV files
- **Team Inheritance**: Base `Team` class inherited by `Aircraft` and `Pilot`
- **SOLID Principles**: Clean architecture following SOLID, KISS, and DRY principles
- **MVC Pattern**: Controller-Service-Repository architecture

## Technology Stack

- Java 17
- Spring Boot 3.5.6
- Lombok
- Maven

## Project Structure

```
src/main/java/co/edu/umanizales/maviation_api/
├── controller/          # REST Controllers
│   ├── AircraftController.java
│   ├── PilotController.java
│   └── DeploymentController.java
├── service/            # Business Logic
│   ├── AircraftService.java
│   ├── PilotService.java
│   └── DeploymentService.java
├── repository/         # Data Persistence
│   ├── CsvRepository.java
│   ├── AircraftRepository.java
│   ├── PilotRepository.java
│   └── DeploymentRepository.java
├── model/              # Domain Models
│   ├── Team.java
│   ├── Aircraft.java
│   ├── Pilot.java
│   └── Deployment.java
└── dto/                # Data Transfer Objects
    ├── AircraftDTO.java
    ├── PilotDTO.java
    ├── DeploymentDTO.java
    └── ResponseDTO.java
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd maviation_api
```

2. Build the project:
```bash
mvnw clean install
```

3. Run the application:
```bash
mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### Aircraft Endpoints

- `GET /api/aircraft` - Get all aircraft
- `GET /api/aircraft/{id}` - Get aircraft by ID
- `GET /api/aircraft/available` - Get available aircraft (not deployed)
- `POST /api/aircraft` - Create new aircraft
- `PUT /api/aircraft/{id}` - Update aircraft
- `DELETE /api/aircraft/{id}` - Delete aircraft

**Aircraft JSON Example:**
```json
{
  "name": "F-16 Fighting Falcon",
  "status": "ACTIVE",
  "type": "FIGHTER",
  "model": "F-16C",
  "hangarNumber": 15
}
```

### Pilot Endpoints

- `GET /api/pilots` - Get all pilots
- `GET /api/pilots/{id}` - Get pilot by ID
- `GET /api/pilots/available` - Get available pilots (not deployed)
- `POST /api/pilots` - Create new pilot
- `PUT /api/pilots/{id}` - Update pilot
- `DELETE /api/pilots/{id}` - Delete pilot

**Pilot JSON Example:**
```json
{
  "name": "John Smith",
  "status": "ACTIVE",
  "rank": "CAPTAIN",
  "flightHours": 1500,
  "specialization": "FIGHTER"
}
```

### Deployment Endpoints

- `GET /api/deployments` - Get all deployments
- `GET /api/deployments/active` - Get active deployments
- `POST /api/deployments` - Deploy aircraft with pilot
- `PUT /api/deployments/{id}/complete` - Complete a deployment

**Deployment JSON Example:**
```json
{
  "aircraftId": "uuid-aircraft",
  "pilotId": "uuid-pilot",
  "mission": "Combat Air Patrol"
}
```

## Data Models

### Team (Base Class)
- `id`: String
- `name`: String
- `status`: String (ACTIVE, INACTIVE, IN_MISSION)

### Aircraft (extends Team)
- `type`: String (FIGHTER, BOMBER, TRANSPORT, RECONNAISSANCE)
- `model`: String
- `hangarNumber`: Integer (1-100)
- `pilotId`: String (when deployed)
- `mission`: String (when deployed)

### Pilot (extends Team)
- `rank`: String (CAPTAIN, MAJOR, COLONEL, etc.)
- `flightHours`: Integer
- `specialization`: String

### Deployment
- `id`: String
- `aircraftId`: String
- `pilotId`: String
- `mission`: String
- `deploymentDate`: String
- `status`: String (ACTIVE, COMPLETED, ABORTED)

## Data Storage

Data is persisted in CSV files located in the `data/` directory:
- `data/aircraft.csv`
- `data/pilots.csv`
- `data/deployments.csv`

## Business Rules

1. **Hangar Management**:
   - Hangar numbers must be between 1 and 100
   - Each hangar can only hold one aircraft
   - Hangar assignment is validated on aircraft creation/update

2. **Deployment Rules**:
   - Only ACTIVE aircraft can be deployed
   - Only ACTIVE pilots can be deployed
   - Aircraft and pilot status changes to IN_MISSION during deployment
   - Completing a deployment returns aircraft and pilot to ACTIVE status

3. **Data Validation**:
   - Required fields are validated
   - Duplicate hangar assignments are prevented
   - Entity existence is verified before operations

## Design Principles

- **SOLID**: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
- **KISS**: Keep It Simple, Stupid - straightforward, easy-to-understand code
- **DRY**: Don't Repeat Yourself - reusable components and base classes
- **MVC**: Model-View-Controller pattern for clean separation of concerns

## Testing

Run tests with:
```bash
mvnw test
```

## License

This project is for educational purposes.
