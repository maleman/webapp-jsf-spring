# Proyecto DEMO spring JSF(PrimeFace)
## :bulb:Tecnologias usadas
<ul>
  <li>Java 17</li>
  <li>Spring boot</li>
  <li>apache myfaces</li>
  <li>primefaces</li>
  <li>h2database</li>
</ul>

## :hammer:Construir el proyecto
> [!TIP]
> Instalar dependencias necesarias
> - [x] Java 17 https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
> - [x] Apache Maven 3.9.6 https://maven.apache.org/download.cgi
> - [x] git 2.42.0
<h4>Clonar el proyecto</h4>
<img src="https://github.com/maleman/webapp-jsf-spring/assets/12504018/a33bf44a-bc60-4b36-8797-033c5a6409d6" />

<h4>Entrar a la carpeta clonada y construir el proyecto con maven</h4>
<img src="https://github.com/maleman/webapp-jsf-spring/assets/12504018/7f0b298f-05db-498d-85c0-ddd96eb83873" />

<h4>Ejecutar la aplicacion con el comando </h4>
<p>mvn spring-boot:run</p>
<img src="https://github.com/maleman/webapp-jsf-spring/assets/12504018/6ea8211a-bd2b-405f-944f-ae66464aadba" />
<br/>
<p>Si no ocurrio ningu problema podemos abrir la siguiente url en el navegador</p>
http://localhost:8080/login.xhtml

> [!TIP]
> Debera aparecer un formulario de login

## :factory:Probar la app
> [!WARNING]
> Usuario por defecto admin

### Crear un nuevo usuario:
1. Click en el boton Nuevo usuario 
![Screenshot 2024-03-14 223332](https://github.com/maleman/webapp-jsf-spring/assets/12504018/8729d84d-4051-4b0d-8476-b9486772e96a)

2. Llenar la informacion solicitada. 
![Screenshot 2024-03-14 223604](https://github.com/maleman/webapp-jsf-spring/assets/12504018/ee5983e1-3f01-4217-b849-f540d1f1b9fa)

> [!NOTE]
> Parametros para la creacion de usuario:
> 1. Nombre de usuario:
>   - Empezar con una letra
>   - Puede contener letras o numeros o (_ -)
>   - Debe tener una longitud de 3 a 10 caracteres
> 2. La contraseña:
>   - Debe tener una letra minúscula, una letra mayúscula.
>   - un número, un carácter (\!@#$%^*?)
>   - Debe tener una longitud minima de 8 caracteres
