# MUtable State OF en Profundidad

Hasta ahora hemos visto que podemos declarar una variable de la siguiente forma:

```kotlin
        var miVariable by remember { mutableStateOf("texto previo") }
```

Esta forma de declarar la variable hará que esté sujeta a observación por jetpack compose y sea posible cambiar su valor en cualquier momento de la siguiente manera:

```kotlin
	TextField( value = name, onValueCHange = { name = it }
```
	
Entonces, cada vez que el campo cambie de valor, también lo hará la variable en sí misma. ¿Pero qué sucede si al mismo tiempo estamos pasando dicha variable a otros composables?

Este es el caso, por ejemplo, cuando estamos trabajando con un objeto más complejo con múltiples campos que debemos ir cambiando antes de hacer la actualización en la base de datos. Para realizar este tipo de modificaciones, debemos seguir los siguientes pasos:


## Realizamos la conversión a ObserveAsState (si es necesario)

Este paso solo se aplicará en caso de que el objeto original sea un LiveData. Aquí, llamaríamos a nuestro VIewMOdel y guardamos en una constante el objeto susceptible de ser modificado.





```kotlin
    val selectedCharacter by characterViewModel.selectedCharacter.observeAsState()
    
    // AHora que se puede observar su estado, ya si podemos hacer el mutableStateOF
    
    var editableCharacter by remember { mutableStateOf(selectedCharacter!!) }



```

## Actualizando el valor a través de varios composables

A continuación vamos a ver qué pasa cuando un objeto inmutable (los data class) se pasan sucesivamente de un composable a otro y crean un infierno de callbacks. Para empezar, cada vez que queramos pasar un objeto inmutable (data class) de un composable a otro, debemos crear composables que tengan dos parámetros:

1. El objeto inmutable en sí mismo.
2. La función con lo que debre suceder con dicho objeto (normalmente, reasignar su valor).

Por ejemplo:


### PASO 1: Definimos el objeto inmutalbe

```kotlin
@Composable
fun MiPantalla() {
    // Definimos un objeto mutable usando remember
    var miObjetoEditable by remember { mutableStateOf(DataClassObject(0)) }

    // Lo pasamos a una función composable junto con la función para actualizarlo
    MiColumnaComposable(
        parametroObjeto = miObjetoEditable,
        parametroFuncion = { nuevoObjeto ->
            miObjetoEditable = nuevoObjeto
        }
    )
}

```


### PASO 2: EL composable hijo recibe los parametros

El composable hijo recibe la función lambda desde el padre, y automáticamente retropropaga hacia arriba la actualización. 


```kotlin
@Composable
fun MiColumnaComposable(
    parametroObjeto: DataClassObject,
    parametroFuncion: (DataClassObject) -> Unit
) {
    // Mostramos el valor del objeto en la UI
    Text(text = "Valor actual: ${parametroObjeto.valor}")

    // Botón que actualizará el valor del objeto
    Button(onClick = {
        // Actualizamos el valor del objeto usando la función lambda pasada
        parametroFuncion(parametroObjeto.copy(valor = parametroObjeto.valor + 1))
    }) {
        Text("Incrementar valor")
    }
}

```

## INfierno de callbacks

EL problema es que en el ejemplo anterior únicamente trabajábamos con dos niveles de composables... ¿pero y si lo intentásemos con 3 niveles de composables?
ENtonces sería necesario retropropagar la función desde el primer nivel padre hasta el último, para que después la actualización vuelva hacia arriba.

Aquí tenemos un ejemplo de cómo se podría hacer:


### Primer nivel

Simplemnete declaramos las variables y los composables, nos encargamos de pasarle los parámetros que pidan
.
```kotlin

@Composable
fun Body(
    modifier: Modifier = Modifier
) {
   val characterViewModel: CharacterViewModel = hiltViewModel()
   val selectedCharacter by characterViewMOdel.selectedCharacter.observeAsState()
   var editableCharacter by remember { mutableStateOf(selectedCharacter!!) 
   
   // Pasamos el valor a otra función composable
   StatSection(
       editableCharacter = editableCharacter,
       onCharacterChange = { editableCharacter = it }
    )
 
   // Creamos una botón para actualizar el objeto en el ViewMOdel
   UpdateCharacterButton(editableCharacter)
}

  
```

### Segundo nivel

En realidad en este nivel no está sucediendo "NADA" como tal, ya que estamos cogiendo el editableCharacter y se lo estamos dando como tal al tercer nivel del composable a través de una copia (recordamos que los atributos de los objetos inmutables no se pueden alterar). Pero en realidad nos da igual pasarle una copia, ya que en el nivel padre, al función onCharacterCHange estamos diciendo que el editableCharacter = a sí mismo, es decir, al propio parámetro que se le acabe pasando a la función.

PUesto que en este segundo nivel el parámetro es una copia de editableCharacter, esta variable se va a sobreescribir con todos los datos que tenga de la copia.

```kotlin
   
// La función composable que va a recibir el objeto
@Composable
fun StatSection(
    editableCharacter: RolCharacter,
    onCharacterChange: (RolCharacter) -> Unit
) {
    CharacterNumberField(
        label = "Fuerza",
        value = editableCharacter.strength,
        onValueChange = { onCharacterChange(editableCharacter.copy(strength = it)) }
    )
    CharacterNumberField(
        label = "Destreza",
        value = editableCharacter.dexterity,
        onValueChange = { onCharacterChange(editableCharacter.copy(dexterity = it)) }
    )

}

```

### Tercer nivel

Este es el tercer nivel. onValueChange está esperando recibir una función que recibe como parámetro un número entero.
En realidad, nosotros no debemos preocuparnos hasta este nivel del parámetro. Anteriormente simplemente le pasábamos la función de un composable a otro, sin mirar qué había dentro. Pero ahora sí que debemos llamar a esta función y pasarle el parámetro correcot, que no será otro que una copia de localValue.

JUsto en ese momento, comienza la propagación hacia atrás:

- El 'it' en el segundo nivel se reasigna al atributo que está en la copia del personaje.
- EL 'it' del primer nivel se encarga de reasignar la variable completa a una copia que ahora está modificada.


```kotlin
@Composable
fun CharacterNumberField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var localValue by remember { mutableStateOf(value) }
    Column(modifier = modifier.padding(4.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = {
                localValue -= 1
                onValueChange(localValue)
            }) {
                Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrement")
            }
            Text(text = localValue.toString(), style = MaterialTheme.typography.bodyMedium)
            IconButton(onClick = {
                localValue += 1
                onValueChange(localValue)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Increment")
            }
        }
    }
}
```
