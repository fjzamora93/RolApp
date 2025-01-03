# Persistencia de datos

A la hora de trabajar con nuestra aplicación, hay muchas estructuras de datos que querremos que persistan a lo largo del ciclo de vida. Para ello, hay distintas opciones:

- ViewModel: que es el que vamos a ver ahora.
- Patrón singleton: una clase con instancia única para toda la aplicación, cuando se necesita que los datos persistan entre distintos componentes de la aplicación y no quieres crear múltiples instancias.
- Repository Pattern: almacenamiento local y remoto de datos (uso de bases de datos, APIS, almacenamiento local o almacenamiento ligero de distinto tipo).

Por lo general, el sistema de persistencia de datos va a ser una combinación de los anteriores, dependiendo de las necesidades existentes en la aplicación (por ejemplo, si hay acceso o no hay acceso a internet, podría hacerse una llamada a la API o tirar del almacenamiento local hasta que haya internet).


### Separación lógica

Antes de comenzar, vamos a trazar la arquitectura de nuestro progama para que haya una separación. El resultaod debería ser algo parecido a este:

```bash

    /miapp
        /models
            Character.kt        <-- Aquí iría tu data class
        /viewmodels
            CharCreatorViewModel.kt  <-- Aquí iría tu ViewModel
        /ui
            CharCreatorScreen.kt   <-- Aquí irían tus pantallas Composables

```
- models: incluye los modelos, es decir, la estructura que van a seguir los datos.
  
- viewmodels: el ViewModel, es el encargado de gestionar el flujo de datos para cuando aparezca en pantalla. Posteriomrente, también hará las operaciones CRUD.
  
- Screen: los screens es lo que se visualiza directamente en pantalla. Cada screen debe instanciar un ViewModel para poder acceder a los datos y métodos que se necesiten.


# Model (estructura de datos)

Simplemente representan objetos (de ahí que sean un data Class). Son una estructura sencilla que luego nos servirá para manejar el flujo de datos en el ViewModel. 

```java kotlin
package com.example.kotlinplotgenerator.models

data class Character(
    val id: String,
    val name: String,
    val description: String,
    val stats: Map<String, Int>,
    val spells: List<String> = listOf(),
    val abilities: List<String> = listOf(),
    val items: List<String> = listOf()
)
```


# ViewModel (encapsulando la lógica)

El ViewModel se utiliza para conectar las Screens con las Activities, manejando así la parte lógica de las Screens y encapsulando ciertas funciones y atributos así como las llamadas a la API para obtener y modificar los datos.

```java kotlin


class CharCreatorViewModel : ViewModel() {

    val character = mutableStateOf(
        Character(
            id = "1",
            name = "Hero",
            description = "Descripción del personaje",
            stats = mapOf(
                "Destreza" to 0,
                "Fuerza" to 0,
                "Constitución" to 0,
                "Inteligencia" to 0,
                "Sabiduría" to 0,
                "Carisma" to 0
            )
        )
    )

    // Método para actualizar una estadística
    fun updateStat(statName: String, newValue: Int) {
        // Creamos una copia mutable del mapa de stats
        val updatedStats = character.value.stats.toMutableMap()

        // Modificamos la estadística que queremos actualizar
        updatedStats[statName] = newValue

        // Actualizamos el valor del estado (Character) con el mapa actualizado
        character.value = character.value.copy(stats = updatedStats)
    }


    // Incrementar una estadística
    fun incrementStat(statName: String) {
        val currentValue = character.value.stats[statName] ?: 0
        updateStat(statName, currentValue + 1)
    }


    // Decrementar una estadística
    fun decrementStat(statName: String) {
        val currentValue = character.value.stats[statName] ?: 0
        updateStat(statName, currentValue - 1)
    }

}
```


## mutableStateOf
Es una función proporcionada por Jetpack Compose (una biblioteca para interfaces de usuario en Kotlin) que crea una variable que puede ser observada y reactiva a cambios. Es decir, cualquier cambio en el valor de esta variable notificará automáticamente a las partes del código que estén observando ese estado.

```java kotlin

// Se notificará a todas las partes del código que estén observando el estado si hay cambios
val state = mutableStateOf("Hello, World!")

```


## rememberSaveable (no es necesario para el View Model)

En el caso del ViewModel, no es necesario utilizar rememberSaveable, ya que el ViewModel ya gestiona la persistencia de estado durante el ciclo de vida de la actividad o fragmento de manera adecuada. : El ViewModel está diseñado específicamente para sobrevivir a cambios en el ciclo de vida de la actividad o fragmento. Esto significa que cuando la actividad o fragmento se recrea (por ejemplo, al rotar la pantalla), el ViewModel se mantiene intacto y preserva el estado dentro del ViewModel (en este caso, el objeto character).

Sin embargo, si salimos del ViewModel, nos encontramos con lo siguiente:

**Propósito**: rememberSaveable es una versión mejorada de remember, que permite guardar el estado de una variable incluso cuando la configuración de la aplicación cambia, como cuando la pantalla se rota o cuando la aplicación pasa a segundo plano y se vuelve a traer al primer plano.

**Cómo funciona:** Cuando usas rememberSaveable, Compose guarda el valor de la variable en el estado persistente a través de los cambios de configuración y restaurará ese valor automáticamente cuando la aplicación se reinicie o recupere de un cambio de configuración.

**Uso típico:** Esto es útil cuando necesitas preservar el estado de la UI entre configuraciones (como rotación de pantalla) o durante el ciclo de vida de la aplicación (como cuando se vuelve a cargar la actividad).

```kotlin

    var miVariable by rememberSaveable { mutableStateOf("0") }

```

