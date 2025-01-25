# Definir relaciones con Room

## Relación de muchos a muchos

Para reflejar una relación de muchos a muchos, será necesario construir un "model" intermedio donde estará la relación entre las dos clases.
Para empezar, las dos clases que se cruzan no tienen directamente una referencia el uno del otro, por lo que se quedarían con sus atributos habituales:

```kotlin

// CLASE 1
@Entity
data class Character(
    @PrimaryKey val characterId: Long,
    val name: String
)

// CLASE 2
@Entity
data class Item(
    @PrimaryKey val itemId: Long,
    val name: String,
    val range: String,
    val damageDice: String
)
```

A partir de aquí, construimos un CrossRef model, que incluirá las referencias a ambas clases.

```kotlin

@Entity(
    tableName = "character_item_cross_ref",
    primaryKeys = ["characterId", "itemId"],
    foreignKeys = [
        ForeignKey(
            entity = RolCharacter::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Item::class,
            parentColumns = ["itemKey"],
            childColumns = ["itemId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CharacterItemCrossRef(
    val characterId: Int,
    val itemKey: Int
)


```


A continuación, habrá que crear un dataclass que nos permita acceder a una clase conteniendo a la otra -si queremos que sea en las dos direcciones, deberemos crear entonces dos dataclass.
Por ejmplo, habría un CHaractersWIthItems y un ItemsWithCharacters.

```kotlin
data class CharacterWithItems(
    @Embedded val character: RolCharacter,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = CharacterItemCrossRef::class,
            parentColumn = "characterId",
            entityColumn = "itemId"
        )
    )
    val items: List<Item>
)



```