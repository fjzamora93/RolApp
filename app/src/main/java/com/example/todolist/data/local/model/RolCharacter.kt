package com.example.todolist.data.local.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlin.random.Random

@Entity(tableName = "rolCharacterTable")
data class RolCharacter(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    // Datos del personaje
    var name: String = "",
    var description: String = "",
    var rolClass: RolClass = RolClass.NINGUNA,
    var race: Race = Race.NINGUNA,
    var height: Range = Range.MEDIO,
    var weight: Range = Range.MEDIO,
    var age: Int = 18,


    // Arrays (pendientes de implementar)
    @Ignore var skills: List<Skill> = emptyList(),
    var spells: String = "",
    // var customItems: String = "",  // Sección para objetos personalizados

    // Stats
    var strength: Int = 11,
    var dexterity: Int = 11,
    var constitution: Int = 11,
    var intelligence: Int = 11,
    var wisdom: Int = 11,  // Educación en Chutlhú
    var charisma: Int = 11,  // Apariencia en Chutlhu

    // Stats derivados (se calculan con la información anterior y NO tienen formulario propio)
    // DE la llamada de CHutlhú (dentro del screen, el sistema de Chutlhu y aquelarre se basan en d100, no en d20)
    var power : Int = 11,  // Cálculo de la séptima edición: 3d6 x 5
    var size: Range = Range.MEDIO,
    var sanity : Int = 11,
    var currentSanity : Int = sanity,

    // Otros stats calculados
    var speed: Range = Range.MEDIO, // calculada a partir de Strength, Size y Dexterity
    var hp: Int = 10, // calculada a partir de Constitution y size
    var currentHp: Int = hp,
    var ap: Int = 0, // calculada a partir de inteligencia, sabiduría o pow
    var currentAp: Int = ap,
    var level: Int = 1,

    )

enum class RolClass {
    NINGUNA, MAGO, BRUJO, HECHICERO, BARDO, CLÉRIGO, GUERRERO, PÍCARO, PALADÍN, DRUIDA
}

enum class Race {
    NINGUNA, HUMANO, ELFO, ENANO, ORCO, DRAGON, MEDIANO
}

enum class Range {
    MUY_ALTO, ALTO,  MEDIO, BAJO, MUY_BAJO
}