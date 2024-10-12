# Currency Converter

![Currency Converter Logo](https://via.placeholder.com/150)

Este proyecto es un conversor de monedas basado en una API externa, desarrollado como parte del curso de AluraLatam y Oracle Next One - Grupo 7. La aplicación permite convertir entre diferentes monedas, mostrar el historial de conversiones y listar las monedas soportadas.

## 🚀 Características

- 💱 Conversión de monedas utilizando la API de [ExchangeRate-API](https://www.exchangerate-api.com/)
- 🔍 Búsqueda inteligente de monedas por nombre parcial
- 📜 Historial de conversiones almacenado localmente
- 📊 Listado completo de monedas soportadas
- 🛠️ Implementación de buenas prácticas en manejo de datos y servicios HTTP
- 📋 Gestión de proyecto utilizando Trello

## 📋 Requisitos

- Java 17
- Clave de API de [ExchangeRate-API](https://www.exchangerate-api.com/)
- Dependencias:
    - `dotenv-java` para gestión de variables de entorno
    - `Gson` para parsing de JSON

## 🛠️ Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/currency-converter.git
   ```

2. Crea un archivo `.env` en el directorio raíz:
   ```
   API_KEY=tu_clave_de_api_aqui
   ```

3. Añade las dependencias a tu `pom.xml`:
   ```xml
   <dependencies>
     <dependency>
       <groupId>io.github.cdimascio</groupId>
       <artifactId>java-dotenv</artifactId>
       <version>5.2.2</version>
     </dependency>
     <dependency>
       <groupId>com.google.code.gson</groupId>
       <artifactId>gson</artifactId>
       <version>2.8.6</version>
     </dependency>
   </dependencies>
   ```

## 🖥️ Uso

Al iniciar la aplicación, se presentará un menú con las siguientes opciones:

1. **Convertir monedas**: Realiza una conversión entre dos monedas.
2. **Ver historial de conversiones**: Muestra las conversiones anteriores.
3. **Mostrar monedas soportadas**: Lista todas las monedas disponibles.
4. **Salir**: Cierra la aplicación.

## 🏗️ Diseño del Proyecto

- **Patrones de diseño**: Implementación del patrón Singleton.
- **Serialización**: Almacenamiento eficiente de códigos de monedas.
- **Manejo de JSON**: Utilización de Gson y records de Java.
- **Gestión de errores**: Manejo robusto de excepciones y casos de error.
- **Historial**: Almacenamiento local de conversiones realizadas.

## 🔧 Funcionalidades Técnicas

### Petición HTTP a la API
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
   .uri(URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency))
   .build();
```

### Lectura de variables de entorno
```java
Dotenv dotenv = Dotenv.load();
String apiKey = dotenv.get("API_KEY");
```

### Mapeo de JSON a objetos Java
```java
public record Currency(String base_code, Map<String, Double> conversion_rates) {}
```

## 👥 Créditos

Este proyecto fue desarrollado como parte del curso de AluraLatam en colaboración con Oracle Next One - Grupo 7.

---

📝 Desarrollado con ❤️ por [Jonatan Atencio]