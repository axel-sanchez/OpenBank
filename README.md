OpenBank

Éste es el proyecto prueba de Open Bank, con esta app se puede ver un listado de personajes de Marvel y acceder para ver sus detalles.

Experiencia de usuario
Este proyecto contiene las siguientes características:

La pantalla principal donde se ve un listado de personajes
Una vista con un personaje específico con su descipción completa (se accede seleccionado un personaje del listado de la primer pantalla)

Capturas de pantalla

<img width="317" alt="Captura de Pantalla 2022-03-11 a la(s) 18 42 55" src="https://user-images.githubusercontent.com/51034538/157974617-b907245d-0298-4cf5-bd58-5a7c93595b0f.png">   <img width="315" alt="Captura de Pantalla 2022-03-11 a la(s) 18 43 22" src="https://user-images.githubusercontent.com/51034538/157974627-5a13b8de-ffc6-423c-a63e-02e8120c2694.png">

Guía de implementación
Trabajo con una API externa que me devuelve los personajes, la url es https://developer.marvel.com/docs
Arquitectura
Este proyecto implementa el patrón de arquitectura MVVM y sigue buenas prácticas de Clean Architecture para hacer un código más independiente, mantenible y sencillo.

Capas
Presentation: Fragments, adapters y viewmodels
Data: contiene la implementación del repositorio y el source donde se conecta con la api y a room
Domain: contiene los casos de uso y la definición del repositorio
Este proyecto usa ViewModel para almacenar y manejar datos, así como comunicar cambios hacia la vista.

Administrador de solicitudes: Retrofit
Este proyecto utiliza Retrofit para mostrar los personajes desde una API.

Administrador de base de datos: Room
Este proyecto utiliza Room para almacenar y luego obtener nuevamente los personajes

Inyección de dependencia - Koin
Este proyecto utiliza Koin para gestionar la inyección de dependencia.

Guía de instalación
En caso de no tener instalado Android Studio, descargue la última versión estable. Una vez que tenemos el programa instalado vamos a Get from Version Control y vamos a pegar https://github.com/axel-sanchez/OpenBank.git Una vez hecho eso se va a clonar el proyecto, lo que resta sería conectar un celular y darle al botón verde de Run 'app'
