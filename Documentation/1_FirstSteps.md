# Primeros pasos con JetpackCompose

### Separación de lógica y elementos visuales

- La parte lógica de la actividad está dentro de una Clase de Kotlin denominada "NombreAcitivyt.kt" y se crea como clase.
- Los elementos visuales (screen) irán en un archivo de kotlin denominado "NombreScreen.kt" y se crea como archivo de kotlin.


De esta forma, las actividades se encargan de orquestar y preparar las pantallas, mientras que las clases con el sufijo Screen se encargan de la definición de los elementos visuales.

Siempre y cuando el Screen y la Activity estén en la misma carpeta, no será necesario importar nada. En caso contrario, habrá que realizar la importación desde el paquete correspondiente.

## Screens

Dentro de screens nos encontraremos los siguientes elementos:

- Box: Organiza los elementos manualmente (es decir, pueden incluso superponerse o usar posiciones absolutas).
- Columns: organiza los elementos SECUENCIALMENTE de manera vertical.
- Rows: organiza SECUENCIALMENTE de manera horizontal.

Por simplicidad, usaremos column y row para organizar la mayor parte del contenido, dejando el Box cuando necesitemos "apilar" los elementos, usar fondos, etc.


## Principales elementos de UI

- Text: un campo de texto plano.
- TextField: un input de texto.
- Button: el botón de toda la vida.
- Image: normalmente extraida de R.drawable.
- Icon: los iconos predefinidos.
- Spacer: un espaciador para separar.
- Checkbox: casilla de verificación.
- Slider: permite seleccionar un valor dentro de un deslizable.
- Card: un contenedor estilizado.
- AlertDialogue: mensaje de una ventana emergente.
- Cualquier campo de un formulario estándar.

## Ejemplos de uso:

### Básicos

````Kotlin

// Text

Text(
    text = "¡Bienvenido a la aplicación!",
    modifier = Modifier.padding(16.dp),
    color = Color.Blue,  // Cambia el color del texto
    fontSize = 20.sp,  // Cambia el tamaño de la fuente
    fontWeight = FontWeight.Bold,  // Cambia el grosor de la fuente
    textAlign = TextAlign.Center  // Centra el texto
)


// Button

Button(
    onClick = { /* Acción cuando se haga clic */ },
    modifier = Modifier.padding(16.dp),
    colors = ButtonDefaults.buttonColors(
        containerColor = Color.Cyan,  // Color de fondo del botón
        contentColor = Color.White   // Color del texto
    ),
    shape = RoundedCornerShape(12.dp),  // Bordes redondeados
    elevation = ButtonDefaults.elevation(defaultElevation = 8.dp)  // Sombra
) {
    Text("Haz clic aquí", fontSize = 18.sp)
}

// Image

Image(
    painter = painterResource(id = R.drawable.mi_imagen),
    contentDescription = "Descripción de la imagen",
    modifier = Modifier
        .size(200.dp)  // Tamaño de la imagen
        .clip(RoundedCornerShape(16.dp))  // Bordes redondeados
        .padding(16.dp),
    contentScale = ContentScale.Crop  // Recorte de la imagen para llenar el espacio
)


// Icon

Icon(
    imageVector = Icons.Filled.Favorite,  // El ícono que deseas mostrar
    contentDescription = "Corazón",
    modifier = Modifier
        .size(40.dp)  // Tamaño del ícono
        .padding(16.dp),
    tint = Color.Red  // Cambia el color del ícono
)


// Spacer

Spacer(modifier = Modifier.height(16.dp))  // Espaciado vertical

// AlertDialogue

AlertDialog(
    onDismissRequest = { /* Acción de cierre */ },
    title = { Text("Título del diálogo") },
    text = { Text("Mensaje del diálogo") },
    confirmButton = {
        Button(onClick = { /* Confirmar acción */ }) {
            Text("Confirmar")
        }
    }
)


````



### Formularios

````kotlin

// TextField

TextField(
    value = "Texto inicial",  // Texto inicial del campo
    onValueChange = { newText -> /* Actualiza el estado con el nuevo texto */ },
    label = { Text("Introduce tu nombre") },  // Etiqueta del campo
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    textStyle = TextStyle(fontSize = 18.sp),  // Tamaño de la fuente
    colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Blue,  // Color cuando está enfocado
        unfocusedIndicatorColor = Color.Gray  // Color cuando no está enfocado
    ),
    singleLine = true  // Solo una línea de texto
)


// Checkbox

var checkedState by remember { mutableStateOf(false) }  // Estado de la casilla

Checkbox(
    checked = checkedState,  // Si está marcado o no
    onCheckedChange = { checked -> checkedState = checked },  // Cambia el estado
    modifier = Modifier.padding(16.dp),
    colors = CheckboxDefaults.colors(
        checkedColor = Color.Green,  // Color cuando está marcado
        uncheckedColor = Color.Gray  // Color cuando no está marcado
    )
)


// Slider

var sliderValue by remember { mutableStateOf(0.5f) }  // Valor inicial del slider

Slider(
    value = sliderValue,  // Valor actual
    onValueChange = { value -> sliderValue = value },  // Cambia el valor cuando el usuario lo ajusta
    valueRange = 0f..1f,  // Rango de valores (de 0 a 1)
    steps = 5,  // Cantidad de pasos entre los valores (opcional)
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    onValueChangeFinished = { /* Acción cuando el usuario deja de interactuar */ },
    colors = SliderDefaults.colors(
        thumbColor = Color.Red,  // Color del círculo que se mueve
        activeTrackColor = Color.Blue,  // Color de la parte activa de la barra
        inactiveTrackColor = Color.Gray  // Color de la parte inactiva de la barra
    )
)


````


### Contenedores

````kotlin

// Card + Column
@Composable
fun CustomCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)  // Espaciado alrededor de la tarjeta
            .fillMaxWidth(),  // Asegura que la tarjeta ocupe todo el ancho disponible
        shape = MaterialTheme.shapes.medium,  // Forma de la tarjeta (bordes redondeados)
        elevation = 8.dp,  // Sombra para dar la apariencia de elevación
        backgroundColor = Color.White  // Color de fondo de la tarjeta
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)  // Relleno dentro de la tarjeta
        ) {
            // Imagen de la tarjeta
            Image(
                painter = painterResource(id = R.drawable.sample_image),  // Ruta a la imagen
                contentDescription = "Descripción de la imagen",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),  // Bordes redondeados en la imagen
                contentScale = ContentScale.Crop  // Ajuste de la imagen
            )

            Spacer(modifier = Modifier.height(8.dp))  // Espaciado entre la imagen y el texto

            // Título
            Text(
                text = "Título de la Tarjeta",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Descripción
            Text(
                text = "Esta es una descripción detallada dentro de la tarjeta. Aquí puedes agregar más información sobre el contenido que estás mostrando.",
                style = MaterialTheme.typography.body2,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Botón de acción
            Button(
                onClick = { /* Acción al presionar el botón */ },
                modifier = Modifier.align(Alignment.End)  // Alinea el botón a la derecha
            ) {
                Text("Acción")
            }
        }
    }
}


// Box

Box(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(Color.LightGray)
) {
    // Aquí se pueden colocar elementos sobrepuestos
    Text(
        text = "Texto encima del Box",
        modifier = Modifier.align(Alignment.Center)
    )
}



// Row

Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp),  // Espaciado entre los elementos
    verticalAlignment = Alignment.CenterVertically  // Alineación vertical de los elementos
) {
    Icon(Icons.Default.Home, contentDescription = "Icono de inicio")
    Text("Texto dentro de una fila")
    Button(onClick = { /* Acción al presionar el botón */ }) {
        Text("Botón en la fila")
    }
}



````