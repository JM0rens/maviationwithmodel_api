# API Usage Examples

## Base URL
```
http://localhost:8080
```

## 1. Create Aircraft

**POST** `/api/aircraft`

```json
{
  "name": "F-16 Fighting Falcon",
  "status": "ACTIVE",
  "type": "FIGHTER",
  "model": "F-16C Block 50",
  "hangarNumber": 15
}
```

**Response:**
```json
{
  "success": true,
  "message": "Aircraft created successfully",
  "data": {
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "name": "F-16 Fighting Falcon",
    "status": "ACTIVE",
    "type": "FIGHTER",
    "model": "F-16C Block 50",
    "hangarNumber": 15,
    "pilotId": null,
    "mission": null
  }
}
```

## 2. Create Pilot

**POST** `/api/pilots`

```json
{
  "name": "Captain John Smith",
  "status": "ACTIVE",
  "rank": "CAPTAIN",
  "flightHours": 1500,
  "specialization": "FIGHTER"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Pilot created successfully",
  "data": {
    "id": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
    "name": "Captain John Smith",
    "status": "ACTIVE",
    "rank": "CAPTAIN",
    "flightHours": 1500,
    "specialization": "FIGHTER"
  }
}
```

## 3. Get All Aircraft

**GET** `/api/aircraft`

**Response:**
```json
{
  "success": true,
  "message": "Aircraft retrieved successfully",
  "data": [
    {
      "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "name": "F-16 Fighting Falcon",
      "status": "ACTIVE",
      "type": "FIGHTER",
      "model": "F-16C Block 50",
      "hangarNumber": 15,
      "pilotId": null,
      "mission": null
    }
  ]
}
```

## 4. Get Available Aircraft

**GET** `/api/aircraft/available`

Returns all aircraft with status "ACTIVE" and not currently deployed.

## 5. Get Available Pilots

**GET** `/api/pilots/available`

Returns all pilots with status "ACTIVE" and not currently deployed.

## 6. Deploy Aircraft

**POST** `/api/deployments`

```json
{
  "aircraftId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "pilotId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
  "mission": "Combat Air Patrol - Sector Alpha"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Aircraft deployed successfully",
  "data": {
    "id": "c3d4e5f6-a7b8-9012-cdef-123456789012",
    "aircraftId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "pilotId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
    "mission": "Combat Air Patrol - Sector Alpha",
    "deploymentDate": "2025-10-22T17:25:00",
    "status": "ACTIVE"
  }
}
```

After deployment:
- Aircraft status changes to "IN_MISSION"
- Pilot status changes to "IN_MISSION"
- Aircraft is assigned to the pilot

## 7. Get Active Deployments

**GET** `/api/deployments/active`

Returns all deployments with status "ACTIVE".

## 8. Complete Deployment

**PUT** `/api/deployments/{deploymentId}/complete`

**Response:**
```json
{
  "success": true,
  "message": "Deployment completed successfully",
  "data": null
}
```

After completion:
- Deployment status changes to "COMPLETED"
- Aircraft status returns to "ACTIVE"
- Pilot status returns to "ACTIVE"
- Aircraft's pilotId and mission are cleared

## 9. Update Aircraft

**PUT** `/api/aircraft/{aircraftId}`

```json
{
  "name": "F-16 Fighting Falcon",
  "status": "ACTIVE",
  "type": "FIGHTER",
  "model": "F-16C Block 52",
  "hangarNumber": 15
}
```

## 10. Delete Aircraft

**DELETE** `/api/aircraft/{aircraftId}`

## 11. Update Pilot

**PUT** `/api/pilots/{pilotId}`

```json
{
  "name": "Captain John Smith",
  "status": "ACTIVE",
  "rank": "MAJOR",
  "flightHours": 2000,
  "specialization": "FIGHTER"
}
```

## 12. Delete Pilot

**DELETE** `/api/pilots/{pilotId}`

## Aircraft Types
- FIGHTER
- BOMBER
- TRANSPORT
- RECONNAISSANCE

## Pilot Ranks
- CAPTAIN
- MAJOR
- LIEUTENANT_COLONEL
- COLONEL

## Status Values
- ACTIVE (available for operations)
- IN_MISSION (currently deployed)
- INACTIVE (out of service)

## Hangar Numbers
Valid range: 1-100 (each hangar can only hold one aircraft)

## Error Responses

**Example - Hangar Already Occupied:**
```json
{
  "success": false,
  "message": "Hangar 15 is already occupied",
  "data": null
}
```

**Example - Aircraft Not Found:**
```json
{
  "success": false,
  "message": "Aircraft not found with id: xyz",
  "data": null
}
```

**Example - Invalid Deployment:**
```json
{
  "success": false,
  "message": "Aircraft is not available for deployment",
  "data": null
}
```

## Testing with cURL

### Create an aircraft:
```bash
curl -X POST http://localhost:8080/api/aircraft \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"F-16 Fighting Falcon\",\"status\":\"ACTIVE\",\"type\":\"FIGHTER\",\"model\":\"F-16C\",\"hangarNumber\":15}"
```

### Create a pilot:
```bash
curl -X POST http://localhost:8080/api/pilots \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Captain John Smith\",\"status\":\"ACTIVE\",\"rank\":\"CAPTAIN\",\"flightHours\":1500,\"specialization\":\"FIGHTER\"}"
```

### Get all aircraft:
```bash
curl http://localhost:8080/api/aircraft
```

### Deploy aircraft:
```bash
curl -X POST http://localhost:8080/api/deployments \
  -H "Content-Type: application/json" \
  -d "{\"aircraftId\":\"<aircraft-id>\",\"pilotId\":\"<pilot-id>\",\"mission\":\"Combat Air Patrol\"}"
```
