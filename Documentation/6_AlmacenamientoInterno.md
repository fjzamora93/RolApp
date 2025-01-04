# Almacenamiento interno (Room + Sqlite)

El almacenamiento interno en una App de ANdroid generalmente se realiza con Sqlite a través de Room (que es una ORM para trabajar de manera más eficiente). La base de datos interna del emulador de AndroidStudio se suele guardar aquí (aunque también es posible mostrarlo dandole a view> en el menú de arriba) 

```bash

/home/javier/.cache/Google/AndroidStudio2024.1/device-explorer/MediumPhone/_/data/data/com.example.todolist/databases

``

La implementación de ROom sigue una estructura similar a esta:

```bash
com.example.app
│
├── data
|   ├── database 
|   |    ├── Database 
|   |    ├── myModelDao
|   |
│   ├── models
|   |    ├── myModel 
|   |        
│   └── repository
|        ├── RoomMyyModelRepository 
|        ├── PostgreSQLModelRepository 
│
├── domain (lógica de negocio sin dependencias)      
|        ├── repository (interfaces)
|        │       ├── MyModelRepository.kt 
│	 │
|        ├── usecases      
│
├── di (dependency injection)    
|        ├── AppModule     
│
├── presentation
│    │── myModel 
│    │    ├── MyModelListScreen.kt      // Pantalla que lista todos los personajes
│    │    ├── MyModelDetailScreen.kt    // Pantalla con detalles de un personaje
│    │    ├── MyModelViewModel.kt       // ViewModel co la lógica interna del modelo
│    │    └── MyModelScreenEvents.kt    // Eventos específicos de la pantalla
│    │
│    └── mySecondModel   	// Agrupamos otro tema distinto de la app 
│        ├── MySecondModelScreen.kt    
|	 ├── MySecondModeliewModel.kt   
│        ├── ... // etc
│
└── utils
```



# Pasos para trabajar con Room

Puesto que la arquitectura de la App, como vemos, es bastante grande, debemos saber en qué orden hay que seguir los pasos:


1. Agregar las dependencias al graddle.
2. Crear un modelo en data.local.model.
3. Crear el dao correspondiente al modelo en data.local.database
4. Crear una interface independiente en domain.repository
5. Implementamos la interfaces creada en data.local.repository

** Pasamos a la lógica del UI **
6. Creamos un paquete dentro presentation para encapsular la lógica del UI.
7. Creamos un ViewModel que llame al Repository para manejar los datos.
8. Creamos un Screen que utilice los datos obtenidos en el ViewModel.
9. Implementamos el screen dentro del Activity.


**NOTA**
Ciertamente, sería posible implementar el DAO directamente en el ViewModel (si lo hiciéramos, nos ahorraríamos el paso 4 y 5). PEro esto provocaría un acoplamiento fuerte y en caso de volver a querer utilizar los datos en otro ViewModel, deberíamos duplicar la lógica. 



### 1. Agregar las dependencias de Room
   
Agregar el plugin de kotlin-kapt y añadir las 3 dependencias para el uso de Room dentro de build.graddle.kts.



### 2. Crear las entidades



Dentrod el paquete models, crear una clase que represente una entidad en la base de datos. Esta clase será un data class con la anotación @Entity.

```kotlin

    import androidx.room.Entity
    import androidx.room.PrimaryKey

    @Entity(tableName = "users")
    data class User(
        @PrimaryKey(autoGenerate = true) 
        val id: Int = 0, // AutoIncrement
        val name: String,
        val password: String
    )

```

En el caso de que vayamos a crear una relación entre dos tablas, podemos hacerlo de la siguiente manera:

```kotlin

    import androidx.room.Entity
    import androidx.room.ForeignKey
    import androidx.room.PrimaryKey

    @Entity(tableName = "stats",
        foreignKeys = [
            ForeignKey(
                entity = Character::class,
                parentColumns = ["id"],
                childColumns = ["character_id"],
                onDelete = ForeignKey.CASCADE
            )
        ])
    data class Stat(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val character_id: Int,  // Relacionado con Character
        val statName: String,  // Ejemplo: "Strength"
        val value: Int
    )


```



### 3. Crear el DAO (Data Access Object)

```kotlin
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}
```




### 4. Configurar la base de datos (AppDatabase.kt)


```bash
src/
└── main/
    └── java/
        └── com/
            └── tu_paquete/
                ├── data/
                │   └── database/
                │       ├── AppDatabase.kt  <-- Clase de la base de datos
                │       ├── User.kt         <-- Entidad
                │       ├── UserDao.kt      <-- DAO
                │       └── DatabaseInstance.kt <-- Singleton de la base de datos

```

Ahora vamos a configurar Room. La clase AppDatabase extiende RoomDatabase y representa la base de datos en sí misma.

- **El parámetro entities = [User::class]** indica que esta base de datos tiene una entidad llamada User. Una entidad es una clase que representa una tabla en la base de datos.
- **El parámetro version = 1** es la versión de la base de datos, que se incrementa si realizas cambios en el esquema (por ejemplo, agregando nuevas tablas o columnas).
- **exportSchema = false** es una opción que previene que Room exporte un esquema de base de datos a un archivo .json cuando compiles el proyecto.


```kotlin


    import androidx.room.Database
    import androidx.room.RoomDatabase

    @Database(entities = [User::class, Employee::class, Job::class, Department::class], version = 1, exportSchema = false)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
        abstract fun employeeDao(): EmployeeDao
        abstract fun jobDao(): JobDao
        abstract fun departmentDao(): DepartmentDao
    }

   
```

### 5. Crear una instancia de la base de datos

Usa el patrón Singleton para asegurarte de que solo haya una instancia de la base de datos.


```kotlin


import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseInstance {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    // Define tus migraciones fuera del bloque synchronized
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Ejemplo de migración: añadir la columna "email"
            database.execSQL("ALTER TABLE users ADD COLUMN email TEXT")
        }
    }

    fun getDatabase(context: Context): AppDatabase {
        // Si la instancia ya está creada, la devuelve
        return INSTANCE ?: synchronized(this) {
            // Si la instancia aún no ha sido creada, la crea
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"  // Nombre de la base de datos
            )
            .addMigrations(MIGRATION_1_2)  // Agrega tu migración aquí
            .build()
            INSTANCE = instance
            instance
        }
    }
}

```

### 6. Usar la base de datos

Ahora puedes usar la base de datos en tus actividades, fragments o ViewModels.

```kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(private val db: AppDatabase) : ViewModel() {

    fun insertUser(user: User) {
        viewModelScope.launch {
            db.userDao().insertUser(user)
        }
    }

    fun getAllUsers(onResult: (List<User>) -> Unit) {
        viewModelScope.launch {
            val users = db.userDao().getAllUsers()
            onResult(users)
        }
    }
}

```


### 7. Manejo de Coroutines y LiveData (PENDIENTE DE INVESTIGAR)

Room es compatible con Coroutines y LiveData, por lo que puedes devolver un Flow o un LiveData en tus DAOs.

````Kotlin
@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsersFlow(): Flow<List<User>>
}
````

Luego puedes observar tus cambios en tiempo real:

````Kotlin
userDao.getAllUsersFlow().collect { users ->
    // Actualiza tu UI con los usuarios
}
````
Siempre usa Coroutines o Flow: Nunca accedas a Room desde el hilo principal.



### 8. Flujo de la base de datos
    
1. UserViewModel: Llamas a los métodos de tu ViewModel (por ejemplo, insertUser() o getAllUsers()).
2. ViewModel: El ViewModel interactúa con la base de datos a través de AppDatabase, que es el punto de acceso a Room.
3. DAO (Data Access Object): El ViewModel llama a los métodos de tu UserDao, que contienen las consultas SQL para interactuar con la base de datos.
4. Room: Room maneja la interacción con una base de datos SQLite, donde los datos se almacenan de forma persistente.


### ¿Dónde se encuentra la base de datos SQLite generada por Room?

Por defecto, Room crea y almacena la base de datos en el sistema de archivos del dispositivo dentro del directorio de la aplicación. Específicamente, la base de datos SQLite se guarda en el almacenamiento interno del dispositivo en un archivo dentro de la carpeta de la aplicación.

La ubicación del archivo de base de datos será algo como:

/data/data/<nombre_del_paquete>/databases/<nombre_de_base_de_datos>
