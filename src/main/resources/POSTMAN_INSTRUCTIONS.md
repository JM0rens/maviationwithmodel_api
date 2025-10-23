# Instrucciones para Importar la Colección de Postman

## 📥 Importar la Colección

### Método 1: Importar desde archivo
1. Abre **Postman**
2. Haz clic en **Import** (botón en la esquina superior izquierda)
3. Selecciona **File** o arrastra el archivo
4. Navega a: `src/main/resources/Military_Aviation_API.postman_collection.json`
5. Haz clic en **Import**

### Método 2: Arrastrar y soltar
1. Abre **Postman**
2. Arrastra el archivo `Military_Aviation_API.postman_collection.json` directamente a la ventana de Postman
3. La colección se importará automáticamente

## 🎯 Estructura de la Colección

La colección está organizada en 4 carpetas principales:

### 1. **Aircraft** (9 requests)
- Get All Aircraft
- Get Aircraft by ID
- Get Available Aircraft
- Create Aircraft - F-16
- Create Aircraft - F-35
- Create Aircraft - B-2 Bomber
- Create Aircraft - C-130 Transport
- Update Aircraft
- Delete Aircraft

### 2. **Pilots** (9 requests)
- Get All Pilots
- Get Pilot by ID
- Get Available Pilots
- Create Pilot - Captain Smith
- Create Pilot - Major Rodriguez
- Create Pilot - Colonel Johnson (Bomber)
- Create Pilot - Captain Lee (Transport)
- Update Pilot
- Delete Pilot

### 3. **Deployments** (5 requests)
- Get All Deployments
- Get Active Deployments
- Deploy Aircraft - Combat Air Patrol
- Deploy Aircraft - Reconnaissance Mission
- Deploy Aircraft - Training Exercise
- Complete Deployment

### 4. **Health Check** (1 request)
- API Health Check

## 🔧 Variables de Colección

La colección incluye variables que se actualizan automáticamente:

| Variable | Descripción | Uso |
|----------|-------------|-----|
| `baseUrl` | URL base del API | `http://localhost:8080` |
| `aircraftId` | ID del último aircraft creado | Se guarda automáticamente |
| `pilotId` | ID del último pilot creado | Se guarda automáticamente |
| `deploymentId` | ID del último deployment creado | Se guarda automáticamente |

### Ver/Editar Variables
1. Haz clic derecho en la colección **Military Aviation API**
2. Selecciona **Edit**
3. Ve a la pestaña **Variables**
4. Aquí puedes ver y editar las variables

## 🚀 Flujo de Trabajo Recomendado

### Paso 1: Verificar que el API está corriendo
```bash
.\mvnw spring-boot:run
```

### Paso 2: Health Check
1. Ejecuta: **Health Check → API Health Check**
2. Si recibes un status 200, el API está funcionando

### Paso 3: Crear un Aircraft
1. Ve a: **Aircraft → Create Aircraft - F-16**
2. Haz clic en **Send**
3. El `aircraftId` se guardará automáticamente en las variables

### Paso 4: Crear un Pilot
1. Ve a: **Pilots → Create Pilot - Captain Smith**
2. Haz clic en **Send**
3. El `pilotId` se guardará automáticamente en las variables

### Paso 5: Desplegar el Aircraft
1. Ve a: **Deployments → Deploy Aircraft - Combat Air Patrol**
2. Haz clic en **Send**
3. El `deploymentId` se guardará automáticamente
4. Nota: El aircraft y pilot cambiarán su status a "IN_MISSION"

### Paso 6: Verificar Deployment Activo
1. Ve a: **Deployments → Get Active Deployments**
2. Verás el deployment que acabas de crear

### Paso 7: Completar el Deployment
1. Ve a: **Deployments → Complete Deployment**
2. Haz clic en **Send**
3. El aircraft y pilot volverán a status "ACTIVE"

## 📝 Notas Importantes

### Guardado Automático de IDs
Los siguientes requests tienen scripts que guardan automáticamente los IDs:
- ✅ **Create Aircraft - F-16** → Guarda `aircraftId`
- ✅ **Create Pilot - Captain Smith** → Guarda `pilotId`
- ✅ **Deploy Aircraft - Combat Air Patrol** → Guarda `deploymentId`

### Uso de Variables en Requests
Las variables se usan con la sintaxis `{{variableName}}`:
```json
{
  "aircraftId": "{{aircraftId}}",
  "pilotId": "{{pilotId}}",
  "mission": "Combat Air Patrol"
}
```

### Cambiar el Puerto
Si tu API está en un puerto diferente:
1. Edita la variable `baseUrl`
2. Cambia `http://localhost:8080` por tu URL

## 🎨 Ejemplos de Uso

### Ejemplo 1: Crear múltiples Aircraft
1. **Create Aircraft - F-16** (Hangar 1)
2. **Create Aircraft - F-35** (Hangar 2)
3. **Create Aircraft - B-2 Bomber** (Hangar 10)
4. **Create Aircraft - C-130 Transport** (Hangar 20)
5. **Get All Aircraft** → Verás los 4 aircraft

### Ejemplo 2: Crear múltiples Pilots
1. **Create Pilot - Captain Smith** (Fighter)
2. **Create Pilot - Major Rodriguez** (Fighter)
3. **Create Pilot - Colonel Johnson** (Bomber)
4. **Create Pilot - Captain Lee** (Transport)
5. **Get All Pilots** → Verás los 4 pilots

### Ejemplo 3: Workflow Completo de Deployment
1. **Get Available Aircraft** → Verifica aircraft disponibles
2. **Get Available Pilots** → Verifica pilots disponibles
3. **Deploy Aircraft - Combat Air Patrol** → Despliega
4. **Get Active Deployments** → Verifica deployment activo
5. **Get Available Aircraft** → El aircraft ya no aparece
6. **Complete Deployment** → Completa la misión
7. **Get Available Aircraft** → El aircraft vuelve a aparecer

### Ejemplo 4: Validación de Hangar
1. **Create Aircraft - F-16** (Hangar 1)
2. Edita el body de **Create Aircraft - F-35**
3. Cambia `"hangarNumber": 2` a `"hangarNumber": 1`
4. **Send** → Recibirás un error: "Hangar 1 is already occupied"

## 🔍 Verificar Respuestas

Todas las respuestas siguen el formato:
```json
{
  "success": true/false,
  "message": "Mensaje descriptivo",
  "data": { ... }
}
```

### Respuesta Exitosa (200/201)
```json
{
  "success": true,
  "message": "Aircraft created successfully",
  "data": {
    "id": "abc-123",
    "name": "F-16 Fighting Falcon",
    ...
  }
}
```

### Respuesta de Error (400/404/500)
```json
{
  "success": false,
  "message": "Aircraft not found with id: xyz",
  "data": null
}
```

## 🐛 Troubleshooting

### Error: "Could not get response"
- ✅ Verifica que el API está corriendo (`.\mvnw spring-boot:run`)
- ✅ Verifica que el puerto es correcto (8080 por defecto)
- ✅ Revisa la consola del API para errores

### Error: "Hangar is already occupied"
- ✅ Cambia el número de hangar (1-100)
- ✅ Usa **Get All Aircraft** para ver hangares ocupados

### Error: "Aircraft is not available for deployment"
- ✅ El aircraft ya está desplegado
- ✅ Usa **Get Available Aircraft** para ver aircraft disponibles
- ✅ Completa el deployment activo primero

### Error: "Pilot is not available for deployment"
- ✅ El pilot ya está desplegado
- ✅ Usa **Get Available Pilots** para ver pilots disponibles
- ✅ Completa el deployment activo primero

### Error: "Aircraft not found" / "Pilot not found"
- ✅ Verifica que creaste el aircraft/pilot primero
- ✅ Verifica que las variables `aircraftId` y `pilotId` tienen valores
- ✅ Copia el ID manualmente de la respuesta de creación

## 📊 Monitoreo

### Ver Variables Actuales
1. Haz clic en el ícono de ojo 👁️ (esquina superior derecha)
2. Verás todas las variables de entorno y colección
3. Aquí puedes ver los IDs guardados

### Console Log
1. Ve a **View → Show Postman Console** (Ctrl+Alt+C)
2. Los scripts de prueba registran los IDs guardados
3. Ejemplo: `Aircraft ID saved: abc-123`

## ✨ Tips y Trucos

1. **Orden recomendado**: Siempre crea Aircraft y Pilots antes de Deployments
2. **Variables**: Los requests de "Create" con scripts guardan los IDs automáticamente
3. **Cleanup**: Usa los requests "Delete" para limpiar datos de prueba
4. **Múltiples tests**: Crea varios Aircraft y Pilots para probar diferentes escenarios
5. **Persistencia**: Los datos se guardan en archivos CSV en `data/` folder

## 📞 Soporte

Si tienes problemas:
1. Revisa la consola del API para logs
2. Verifica los archivos CSV en `data/` directory
3. Revisa el README.md del proyecto
4. Revisa API_EXAMPLES.md para más ejemplos

---

**¡La colección está lista para usar!** 🚀

Importa, ejecuta el API, y comienza a probar todos los endpoints.
