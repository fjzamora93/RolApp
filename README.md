# ToDoList App

ToDoList es una aplicación de gestión de tareas que permite a los usuarios crear, actualizar, eliminar y organizar tareas. Este proyecto utiliza Kotlin y Jetpack Compose para la interfaz de usuario, y está estructurado siguiendo el patrón de arquitectura MVVM (Model-View-ViewModel). La inyección de dependencias se maneja con Dagger Hilt.

## Características

- Crear nuevas tareas
- Actualizar tareas existentes
- Eliminar tareas
- Filtrar tareas por prioridad
- Marcar tareas como completadas o incompletas
- Interfaz de usuario moderna utilizando Jetpack Compose

## Requisitos

- Android Studio Bumblebee o superior
- JDK 11 o superior

## Instalación

1. **Clona este repositorio**:
   ```sh
   git clone https://github.com/tuusuario/todolist.git

2. **Abre el proyecto en Android Studio.**
3. **Sincroniza el proyecto con Gradle.**
4. **Ejecuta la aplicación en un emulador o dispositivo físico.**

## Arquitectura del Proyecto

El proyecto está estructurado utilizando la arquitectura MVVM, lo cual facilita la separación de preocupaciones y mejora la testabilidad del código.

### Directorios Principales

- **data**: Contiene los modelos de datos y el acceso a datos locales.
  - **local**: Contiene los modelos de datos y la implementación de la base de datos local (Room).
- **domain**: Contiene los casos de uso que encapsulan la lógica de negocio.
- **presentation**: Contiene las pantallas de la aplicación y sus respectivos ViewModels.

