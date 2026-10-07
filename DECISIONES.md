# Decisiones de diseño — Gestor de Inventario (Endpoint /api/info)

## 1. ¿Por qué se usó @ConfigurationProperties en lugar de @Value?

Los datos de la aplicación forman un solo grupo bajo el prefijo `app.info`, y
`@ConfigurationProperties` los enlaza a un único objeto tipado, `AppInfoProperties`.
Con `@Validated`, las reglas `@NotBlank` y `@Email` se revisan al arrancar, por
lo que un valor incorrecto detiene la aplicación de inmediato. Además, el
enlace relajado permite escribir `developer-name` en el archivo y usar
`developerName` en Java. Con `@Value` cada propiedad se escribiría a mano en
cada lugar de uso y los errores aparecerían tarde. Para comprobarlo se dejó
vacío `app.info.name` y la aplicación no inició.

## 2. ¿Cómo se garantiza que la versión no se desincronice del pom.xml?

Se reemplazó el valor escrito a mano por `app.info.version=@project.version@`.
Maven lo resuelve al construir, porque `spring-boot-starter-parent` activa el
filtrado de recursos en los archivos `application`. Así el `pom.xml` es la
única fuente de la versión. El endpoint devolvió `0.0.1-SNAPSHOT`, y al cambiar
el pom a `0.0.2-SNAPSHOT` y reconstruir, el endpoint mostró el nuevo valor sin
tocar otro archivo.

## 3. ¿Qué pasa si se quita @Component de AppInfoProperties?

La aplicación no arrancó. `@ConfigurationProperties` solo indica cómo enlazar
las propiedades y no registra la clase como bean, por lo que `InfoController`
no encontró qué recibir en su constructor. Al ejecutar las pruebas, 5 de las 6
pasaron y falló `contextLoads` de `P1Proyecto1ApplicationTests`, con resultado
`BUILD FAILURE`. Esa prueba levanta la aplicación completa y se encuentra con
el mismo error. Las pruebas con `@WebMvcTest` siguieron en verde porque cargan
solo la capa web e importan la clase con `@Import`. Esto muestra que una
prueba enfocada puede aprobar mientras la aplicación real está dañada, y que
solo la prueba de contexto completo detecta el problema. Una solución sin
`@Component` es `@ConfigurationPropertiesScan` o `@EnableConfigurationProperties`.

## 4. ¿Qué diferencia hay entre /api/info y /actuator/info?

`/api/info` es el contrato de la aplicación, con campos definidos por el
record `InfoResponse` y datos validados al arranque. `/actuator/info`
pertenece a Actuator, orientado a monitoreo, y su contenido depende de los
contribuyentes de información configurados. Por defecto no muestra datos
propios. Solo mostró contenido al habilitar `management.info.env.enabled` y
definir propiedades `info`.

## Declaración de uso de IA

Se utilizó la herramienta de IA Claude para comprender conceptos, comparar
enfoques y depurar errores. Cada decisión fue verificada en la ejecución real
del proyecto, como ocurrió con la prueba sin `@Component`, donde lo indicado
por la IA no coincidió con el resultado observado y se corrigió documentando
el comportamiento real. Todo el código entregado puede explicarse línea por
línea y cada propiedad se justificó según el contexto del proyecto.