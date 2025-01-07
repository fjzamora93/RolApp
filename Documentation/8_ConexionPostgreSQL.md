# Conexión a PostgreSQL

### 1. Desplegamos con railway

deploy > postgresql > Data > Connect

Una vez está desplegado, podemos ir a Data y darle a Connect.

Dentro del connect, si le damos a public network, veremos algo como esto:

```bash
Connection URL

    postgresql://postgres:******@junction.proxy.rlwy.net:41780/railway

    jdbc:postgresql://junction.proxy.rlwy.net:41780/railway
                   

```

Partes de la Connection String:
- **postgresql://:** Indica que estás usando PostgreSQL como motor de base de datos.
- **postgres:** Es el nombre de usuario (username) que usas para conectarte.
- **********:** Es tu contraseña (password). Railway la oculta en el panel por seguridad.
- **junction.proxy.rlwy.net:** Es el host de la base de datos. En este caso, Railway usa un proxy.
- **41780:** Es el puerto que PostgreSQL está escuchando.
- **railway:** Es el nombre de tu base de datos

### 2. Configuramos Dbeaver

Ahora que está la base de datos creada, podemos acceder a ella a través de Dbeaver. Para ello, abrimos Dbeaver y creamos una nueva conexión:

1. Abre DBeaver y selecciona File > New > Database Connection o haz clic en el ícono de "Nueva conexión" (el enchufe).
2. Selecciona PostgreSQL.
3. Ve a conectar por URL y pegar la url facilitada en railway, pero dbeaver espera este formato:

```bash

    jdbc:postgresql://<host>:<puerto>/<base_de_datos>

    # Si la URL que nos ha dado railway es esta:
    postgresql://postgres:******@junction.proxy.rlwy.net:41780/railway

    # Dbeaver espera algo como esto:
    jdbc:postgresql://junction.proxy.rlwy.net:41780/railway

```

Adicionalmente nos pueden pedir un usuario y contraseña:
- usuario: postgres
- contraseña: lo que esté cubierto por los asteriscos.

### 3. Creando tablas

En DBeaver, puedes navegar por la estructura de la base de datos y los esquemas. Aquí están los pasos:

Expandir el esquema:

1. En el panel izquierdo de DBeaver, busca tu base de datos bajo PostgreSQL > railway > esquemas.
2. Aquí puedes ver los esquemas disponibles. Por defecto, el esquema principal es public, pero si tienes otros esquemas, puedes seleccionar el que prefieras.
3. Seleccionar el esquema adecuado. Haz clic derecho sobre el esquema (por ejemplo, public, tablas), y selecciona "Create" > "Table" para comenzar a crear una nueva tabla. 

La estructura que vas a encontrar es esta:

```bash
postgres
    │_Bases_de_datos
        │_railway
            │_esquemas
                │_public
                    │_tablas
                        │_tabla1
                        │_tabla2
                        │_tabla3
``` 

Al hacer click en create table, tendrás acceso al menú de las tablas.

Arriba podrás poner el nombre, y debajo verás un pequeño menú donde tendrás la opción de añadir nuevas columnas.

Igualmente, puede ser más rápido realizar querys para crear las tablas.