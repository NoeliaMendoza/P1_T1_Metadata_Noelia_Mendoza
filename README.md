# Gestor de Inventario

Tarea de la materia de **Programación Avanzada**.

El objetivo es crear un endpoint `GET /api/info` que devuelva la metadata de la aplicación, leyendo los datos desde archivos de configuración de forma tipada y validada.

## Requisitos de la tarea

- Usar `@ConfigurationProperties` para la configuración.
- Crear un controlador y un DTO de respuesta.
- Configurar los perfiles `dev` y `prod`.
- Sincronizar la versión con el `pom.xml`.
- Crear pruebas con `@WebMvcTest` y MockMvc.
- Explorar propiedades de servidor, logging, bases de datos/JSON, Actuator y seguridad.
- Documentar las decisiones y los experimentos en `DECISIONES.pdf`.
- Presentar evidencias del endpoint en ambos perfiles y de las pruebas exitosas.

## Ejecución

```powershell
.\mvnw.cmd spring-boot:run
```

Consultar: http://localhost:8080/api/info

## Pruebas

```powershell
.\mvnw.cmd test
```

## Nombre

Noelia Mendoza
