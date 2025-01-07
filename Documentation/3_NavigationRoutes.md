# Sistemas de Rutas y navegación

Dentro de Kotlin existen varios sistemas de rutas y navegación. EL que vamos a abordar aquí, se basa en utilizar la inyección de dependencias a través de la función de HiltViewModel.

Para ello, vamos a requerir los siguientes elementos dentro de la arquitectura de nuestro programa:

1. Una clase MyApplication con el decorador **@HiltAndroidApp** . Esta es la clase que permitirá inicializar el contenedor global de dependencias. Es posible que no haya nada más dentro de esta clase.

2. Una actividad principal cuyo contenido renderice el **Gráfico de Navegación (NavGraph)**. 

3. Un Gráfico de Navegación (una función @Composable a la que podemos llamar NavGraph) que servirá para definir la estructura de navegación de la App.

4. Un sistema de rutas entre pantallas, que vienen a ser una forma rápida de acceder al String que define cada ruta, que podemos definir dentro de una clase "sealed class ScreensRoutes"). 

5. Un ViewModel con el decorador **@HiltViewModel** y la inyección del constructor **@Inject constructor** (esto es lo que permitirá la inyección de dependencias sin tener que estar pasando el parámetro del ViewModel constantemente).


6. Las Screens recibien un parámetro de navegación y dentro de cada Screen se declara una constante para el **view model: ViewModel = hiltViewModel()** (a partir de su declaración, esta constante se pasará a todas las funciones hijas como parámetro).



## 1. Clase MyApplication y @HiltAndroidApp

Es probable que esta clase esté vacía y no tenga nada dentro, pero se trata de un componente crucial para la inicialización de la aplicación. Hilt configura un contenedor de dependencias global a nivel de aplicación. Sin este contenedor, las dependencias no pueden ser gestionadas.

```kotlin
@HiltAndroidApp
class MyApplication: Application()
```

Aunque tu clase Application esté vacía, la función principal de la clase es servir de punto de entrada para inicializar Hilt. 

## 2. MainActivity

A continuación nos encontraremos con nuestra actividad principal. Hasta ahora estábamos acostumbrado a renderizar directamente las Screens dentro de la actividad dentro del setContent. SIn embargo, en adelante utilizaremos el setContent para renderizar el gráfico de navegación.

Para trabajar con la inyección de dependencias, deberemos indicar que se trata del punto de entrada con el decorador @AndroidENtryPOint. 

Además, deberemos crear un controlador de navegación que le pasaremos como parámetro al GRáfico de Navegación:

```kotlin
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            val navHostController: NavHostController = rememberNavController()

		NavGraph(
		    navController = navHostController,
		)
		    
        }
    }
}
```

## 3. Gráfico de navegación (NavGraph.kt)

Es la clase que va a permitirnos gestionar de forma lógica la navegación dentro de la aplicación y cambiar entre pantallas.

Como vemos, es una función composable que recibe como parámetro un controlador de navegación. Opcionalmente, podríamos pasarle más parámetros, como cuál queremos que sea el startDestination de la App (aunque también podemos definirlo directamente dentro de esta clase).

``
@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = ScreensRoutes.CharacterScreen.route 
    ) {
        // Aquí iría la lógica para la primera pantalla 
        composable(
            ScreensRoutes.FirstScreen.route
        ) {
            FirstScreen(
                navController = navController
            )
        }

        // Aquí iría la lógica para la segunda pantalla 
        composable(
            ScreensRoutes.SecondScreen.route,
        ) {
            SecondScreen(navController = navController)
        }
    }
}
``

Para realizar el gráfico de navegación, llamamos a la función "composable", que recibe como parámetro un **String** que será la ruta y que tendremos definida en nuestro archivo ScreensRoutes.kt.

A continuación, debemos definir el contenido de dicha función composable, que, como podemos ver, será cada Screen según corresponda con sus distintos parámetros. Debemos tener en cuenta que si queremos generar rutas personalizadas durante la navegación (como añadir la $id de un objeto) será necesario recurrir a una lógica adicional.


## 4. Rutas (ScreensRoutes)

Simplemente es un archivo donde definiremos las rutas. SI no existiese este archivo, deberíamos estar "hardcodeando" la ruta de destino cada vez que quisiéramos realizar una navegación. En su lugar, las encapsulamos dentro de esta clase y podemos acceder a cualquier ruta que esté definido dentro. 

``kotlin
sealed class ScreensRoutes(val route: String) {
    
    object TaskListScreen: ScreensRoutes("TaskListScreen")
    object TaskDetailScreen : ScreensRoutes("TaskDetailScreen/{taskId}") {
        fun createRoute(taskId: Int) = "TaskDetailScreen/$taskId"
    }
}
``

Ahora, cada vez que queramos utilizar una ruta, simplemente tenemos que llamar a esta clase + la ruta que hayamos elegido. POr ejemplo:

- ScreensRoutes.TaskListScreen.route


## 5. ViewModel 

EL ViewModel se quedará como hasta ahora excepto por dos diferencias:
- INcluirá el decorador @HiltViewModel, que permitirá la inyección de dependencias.
- Tendrá otro decorador @Inject constructor (similar a como veíamos en Angular).

POr todo lo demás, la clase se mantiene exactamente igual:

```kotlin


@HiltViewModel
class MyFIrstViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {

    private val _items = mutableStateOf<List<Item>>(emptyList())
    val items: State<List<Items>> get() = _items
    
    fun getAllItems(){
    
    	// REsto de la lógica...
    
```

## 6. Screens

Las Screens sufren dos modificaciones:

1. Recibirán obligatoriamente un parámetro que será el controlador de navegación (navController : NavHostController). Este parámetro permitirá la navegación entre pantallas.

2. Dentro de cada Screen, tendremos que declarar nuestro ViewModel. Este ViewMOdelo no se vuelve a declarar en toda la pantalla y, si fuese necesario, se debe pasar a todas las funciones composables hijas (o se le pasa una parte de los datos, si es eso lo que nos interesa).

```kotlin

@Composable
fun MyFirstScreen(
	navController: NavHostController
) {
	val myFirstModel : MyFirstModel = hiltViewModel()
	
	Body(
	    myModel = myFirstModel
	    // Aquí el body aplicaría la lógica que hiciera falta sobre el modelo
	)
	
	Button (onClick => {
	    navController.navigate(ScreensRoutes.MySecondScreen.route)
	}) {
	     Text("Este botón es lo que permitirá la navegación")
	 }
}

```

## COnsideraciones adicionales:

Siguiendo todos estos pasos, el sistema de navegación y rutas debería estar completo.

Hay que tener en cuenta que en el caso de que queramos realizar la navegación a los detalles de un objeto, se estaría creando la ruta de forma dinámica y sería necesario aplicar algo de lógica adicional (como pasar el parámetro con la Id de la ruta, y gestionar correctamente la creación de dicha ruta).
	
	
	
















