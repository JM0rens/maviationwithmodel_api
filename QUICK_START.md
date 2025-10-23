# Quick Start Guide

## 1. Build and Run

### Option 1: Using Maven Wrapper (Recommended)
```bash
# Clean and build
.\mvnw clean install

# Run the application
.\mvnw spring-boot:run
```

### Option 2: Using your IDE
1. Open the project in IntelliJ IDEA
2. Wait for Maven to download dependencies
3. Run `MaviationApiApplication.java`

The application will start on **http://localhost:8080**

## 2. Test the API

### Step 1: Create an Aircraft
```bash
curl -X POST http://localhost:8080/api/aircraft -H "Content-Type: application/json" -d "{\"name\":\"F-16 Fighting Falcon\",\"status\":\"ACTIVE\",\"type\":\"FIGHTER\",\"model\":\"F-16C\",\"hangarNumber\":1}"
```

**Save the `id` from the response!**

### Step 2: Create a Pilot
```bash
curl -X POST http://localhost:8080/api/pilots -H "Content-Type: application/json" -d "{\"name\":\"Captain John Smith\",\"status\":\"ACTIVE\",\"rank\":\"CAPTAIN\",\"flightHours\":1500,\"specialization\":\"FIGHTER\"}"
```

**Save the `id` from the response!**

### Step 3: Deploy Aircraft with Pilot
```bash
curl -X POST http://localhost:8080/api/deployments -H "Content-Type: application/json" -d "{\"aircraftId\":\"<aircraft-id-here>\",\"pilotId\":\"<pilot-id-here>\",\"mission\":\"Combat Air Patrol\"}"
```

### Step 4: Check Active Deployments
```bash
curl http://localhost:8080/api/deployments/active
```

### Step 5: Get All Aircraft
```bash
curl http://localhost:8080/api/aircraft
```

## 3. Using Postman or Browser

You can also test the GET endpoints directly in your browser:
- http://localhost:8080/api/aircraft
- http://localhost:8080/api/pilots
- http://localhost:8080/api/deployments

For POST, PUT, DELETE operations, use Postman, Insomnia, or any REST client.

## 4. Data Storage

All data is stored in CSV files in the `data/` directory:
- `data/aircraft.csv`
- `data/pilots.csv`
- `data/deployments.csv`

These files are created automatically on first run.

## 5. Common Scenarios

### Scenario 1: Complete Workflow
1. Create multiple aircraft (assign different hangar numbers 1-100)
2. Create multiple pilots
3. Deploy an aircraft with a pilot
4. Check active deployments
5. Complete the deployment
6. Verify aircraft and pilot are back to ACTIVE status

### Scenario 2: Hangar Management
- Try to create two aircraft with the same hangar number (should fail)
- Update an aircraft to a different hangar
- Check available hangars (1-100)

### Scenario 3: Deployment Validation
- Try to deploy an aircraft that's already deployed (should fail)
- Try to deploy with a non-existent pilot (should fail)
- Check available aircraft and pilots before deployment

## 6. Troubleshooting

**Port 8080 already in use?**
- Change the port in `src/main/resources/application.properties`
- Set: `server.port=8081` (or any available port)

**Build errors?**
- Ensure Java 17 is installed: `java -version`
- Clean and rebuild: `.\mvnw clean install`

**Cannot find data files?**
- Files are created automatically on first use
- Check the `data/` directory in the project root

## 7. What's Implemented

✅ **Aircraft CRUD** - Full Create, Read, Update, Delete operations
✅ **Pilot CRUD** - Full Create, Read, Update, Delete operations  
✅ **Hangar Management** - 100 numbered hangars (1-100) with validation
✅ **Deployment System** - Deploy aircraft with pilots on missions
✅ **CSV Persistence** - All data stored in separate CSV files
✅ **Team Inheritance** - Base Team class inherited by Aircraft and Pilot
✅ **MVC Pattern** - Controller → Service → Repository architecture
✅ **SOLID Principles** - Clean, maintainable code structure
✅ **Status Management** - ACTIVE, IN_MISSION, INACTIVE states
✅ **Business Logic** - Hangar occupancy, deployment validation
✅ **REST API** - Standard HTTP methods and JSON responses

## 8. Next Steps

- Add more aircraft and pilots
- Create complex deployment scenarios
- Implement additional business rules
- Add unit tests
- Create a frontend application
- Add authentication/authorization
- Implement search and filtering
- Add pagination for large datasets
