package com.example.todolist.ui.character

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.repository.CharacterRepository
import com.example.todolist.data.local.model.RolCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.local.model.Item
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    // Usamos `mutableStateOf` para el listado de personajes
    private val _characters = mutableStateOf<List<RolCharacter>>(emptyList())
    val characters: State<List<RolCharacter>> get() = _characters

    private val _selectedCharacter = MutableLiveData<RolCharacter?>()
    val selectedCharacter: LiveData<RolCharacter?> = _selectedCharacter


    init {
        getAllCharacters() // Llamamos a la función para cargar los personajes al inicio
    }

    // Función para obtener todos los personajes
    fun getAllCharacters() {
        viewModelScope.launch {
            val charactersList = characterRepository.getAllCharacters() // Obtén la lista de personajes
            _characters.value = charactersList // Actualiza el estado con la lista obtenida
            println("Todos los personajes: $charactersList") // Para depurar
        }
    }

    // Función para obtener un personaje por ID
    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            val rolCharacter = characterRepository.getCharacterById(characterId)
            // Aquí puedes actualizar el UI con el personaje encontrado
            _selectedCharacter.value = rolCharacter
            println("Personaje encontrado: $_selectedCharacter.value")
        }
    }

    // TODO: Retorna una lista de objetos
    fun getItems(): List<Item>  {
        println("Buscando objetos")
        return emptyList()
    }

    // Función para insertar un nuevo personaje
    fun insertCharacter(rolCharacter: RolCharacter) {
        viewModelScope.launch {
            // Completar el personaje (por ejemplo, asignar valores predeterminados)
            rolCharacter.completeCharacter()
            characterRepository.insertCharacter(rolCharacter)

            // Fetch the updated list of characters
            val updatedCharacters = characterRepository.getAllCharacters()
            _characters.value = updatedCharacters

            _selectedCharacter.value = updatedCharacters.lastOrNull()
            println("Personaje seleccionado dentro de CharacterViewModel: ${_selectedCharacter.value}")
        }
    }



    // Función para actualizar un personaje
    fun updateCharacter(rolCharacter: RolCharacter) {
        viewModelScope.launch {
            characterRepository.updateCharacter(rolCharacter)
            println("Personaje actualizado: $rolCharacter")
        }
    }

    // Función para eliminar un personaje
    fun deleteCharacter(rolCharacter: RolCharacter) {
        viewModelScope.launch {
            characterRepository.deleteCharacter(rolCharacter)
            println("Personaje eliminado: $rolCharacter")
            val updatedCharacters = characterRepository.getAllCharacters()
            _characters.value = updatedCharacters
        }
    }
}
