# Formulario con validación de los campos

## Versión de Java: 11

## Versión de SpringBoot: 3.1.5

## Los campos son validados con SpringBoot
*** *** Las anotaciones que se usaron son los siguientes:
- @NotBlank
- @Requerido
- @Size
- @NotEmpty
- @Min
- @Max
- @NotNull
- @Past Para las fecha de nacimiento o fechas pasadas
- @DateTimeFormat(pattern = "yyyy-MM-dd")
- @Email(message = "Correo con formato incorrecto")
- @Pattern(regexp="ExpresionRegular")

## Se usó tambien inyección de dependencias

## Para mostrar los datos en los archivos HTML se usó thimeleaf. Para más información, consulte la siguiente liga hthp://www.thimeleaf.org

## También el proyecto cuenta un archivo messages.properties para el manejo de los mensajes de validación del formulario

## Para los estilos de los formularios se usó Bootstrap 4.4




