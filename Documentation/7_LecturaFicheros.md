

# Lectura de Archivos JSON o CSV

Dentro de la arquitectura de un proyecto MVVM, la lectura de ficheros se debe realizar dentro del paquete "repository". EN nuestro caso, como trabajamos con repositorio local y remoto, estaría en el siguietne directorio:

data.local.repository.LecturaFicheros

Pasos a seguir:
- Arriba a la izquierda, cambiar el modo de vista de Android a Project o Project FIles, esto nos permitirá ver estructura de carpetas secundarias.
- Crear el fichero JSON en la ruta app/src/main/assets. Esto hará que los ficheros sean accesibles con context dentro del repositorio.
- Crear un model que respete la estructura del JSON.
- Implementar la lectura del JSON en una clase del repositorio.
- Crear un interface para nuestro Repository, dentro del package "domain".
- Dentro de esta clase repository, crear una función que recibe como parámetro un context, **el context se le debe pasar desde el viewModel**.
- Usar el repository en el ViewModel. Este VIewModel debe tener una tributo context y se lo pasará como parámetro al readFromJson.


## Implementación del método desde una clase REpositorio
```kotlin

class LocalSkillRepository  @Inject constructor (
    private val characterDao: CharacterDao,
) : SkillRepository {

    override suspend fun readFromJson(context: Context): List<Skill> {
        val jsonString: String
        try {
            jsonString = context.assets.open("skills.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return emptyList() // Si hay un error, devuelve una lista vacía
        }

        // Convertir el JSON en una lista de objetos Ejemplo
        val listType = object : TypeToken<List<Skill>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

```

# Lectura de texto plano


El almacenamiento interno está aislado para cada aplicación, lo que significa que cada aplicación tiene su propio espacio de almacenamiento privado. Esto es ideal para almacenar datos que no se deben compartir con otras aplicaciones.

```kotlin

    import android.content.Context
    import java.io.BufferedReader
    import java.io.InputStreamReader

    fun readFromInternalStorage(context: Context, fileName: String): String {
        return try {
            val fileInputStream = context.openFileInput(fileName)  // Abrimos el archivo en modo lectura
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var line: String?
            
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }

            bufferedReader.close()  // Cerramos el lector
            stringBuilder.toString()  // Devolvemos el contenido leído como String
        } catch (e: Exception) {
            e.printStackTrace()
            "Error leyendo el archivo"
        }
    }

```


# Lectura de Archivos desde el Almacenamiento Externo (requiere permisos, consultar DOC)

El almacenamiento externo es accesible por otras aplicaciones (si tienen los permisos adecuados), lo que lo hace útil para almacenar archivos que deben ser accesibles a otras aplicaciones o por el usuario. Sin embargo, el acceso a los archivos en almacenamiento externo requiere permisos explícitos (en versiones de Android 6.0 o superiores).


```kotlin

import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.BufferedReader

fun readFromExternalStorage(): String {
    val path = Environment.getExternalStorageDirectory().absolutePath + "/Download/myfile.txt"
    val file = File(path)
    
    return try {
        val fileInputStream = FileInputStream(file)
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder = StringBuilder()
        var line: String?

        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line).append("\n")
        }

        bufferedReader.close()
        stringBuilder.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        "Error leyendo el archivo"
    }
}

```

3. Permisos de Almacenamiento Externo:
Si estás trabajando con almacenamiento externo en Android 6.0 (API nivel 23) o superior, necesitas solicitar permisos explícitos para leer archivos. Esto se puede hacer mediante el siguiente código en el archivo AndroidManifest.xml y también solicitando permisos en tiempo de ejecución:
