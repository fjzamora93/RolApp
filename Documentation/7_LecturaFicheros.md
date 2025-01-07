

# Lectura de Archivos JSON o CSV
En aplicaciones móviles, es común leer archivos en formatos como JSON o CSV. Puedes utilizar bibliotecas como Gson o Moshi para leer archivos JSON, y Apache Commons CSV o simplemente las funciones de Kotlin para CSV.

```kotlin

import com.google.gson.Gson
import java.io.FileReader

data class User(val name: String, val age: Int)

fun readJsonFile(filePath: String): User? {
    return try {
        val fileReader = FileReader(filePath)
        val gson = Gson()
        gson.fromJson(fileReader, User::class.java)  // Convertir el JSON en objeto
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
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