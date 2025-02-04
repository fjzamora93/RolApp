package com.example.todolist.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.domain.repository.CharacterRepository
import com.example.todolist.data.local.model.RolCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.data.local.model.Item
import com.example.todolist.data.local.repository.LocalCharacterRepository
import com.example.todolist.data.local.repository.LocalSkillRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val localCharacterRepository: LocalCharacterRepository,
    private val skillRepository: LocalSkillRepository
) : ViewModel() {

    // Usamos `mutableStateOf` para el listado de personajes
    private val _characters = MutableStateFlow<List<RolCharacter>>(emptyList())
    val characters: StateFlow<List<RolCharacter>> get() = _characters.asStateFlow()


    // Generalmente usaríamos State para ambos, pero en este caso LiveData funciona mejor (con State no va a cambiar bien)
    private val _selectedCharacter = MutableLiveData<RolCharacter?>()
    val selectedCharacter: LiveData<RolCharacter?> = _selectedCharacter

    private val _selectedCharacterItems = MutableLiveData<List<Item>>()
    val selectedCharacterItems: LiveData<List<Item>> = _selectedCharacterItems




    init {
        getAllCharacters()
    }

    // Función para obtener un personaje por ID
    fun getCharacterById(characterId: Int) {
        viewModelScope.launch {
            val rolCharacter = characterRepository.getCharacterById(characterId)
            val characterItems = characterRepository.getCharacterWithRelations(characterId)?.items

            // Aquí puedes actualizar el UI con el personaje encontrado
            _selectedCharacter.value = rolCharacter
            _selectedCharacterItems.value = characterItems ?: emptyList()
            println("Personaje: ${_selectedCharacter.value?.name} " + "Inventario ${_selectedCharacterItems.value}")
        }
    }

    // Función para obtener todos los personajes
    fun getAllCharacters() {
        viewModelScope.launch {
            val charactersList = characterRepository.getAllCharacters() // Obtén la lista de personajes
            _characters.value = charactersList // Actualiza el estado con la lista obtenida
        }
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
            _selectedCharacter.value = rolCharacter
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

    fun updateCharacterGold(newGold: Int) {
        val currentCharacter = _selectedCharacter.value ?: return
        val updatedCharacter = currentCharacter.copy(gold = newGold)
        _selectedCharacter.value = updatedCharacter
        viewModelScope.launch {
            characterRepository.updateCharacter(updatedCharacter)
        }
    }


    fun removeItemFromCharacter(
        currentCharacter: RolCharacter,
        currentItem: Item,
    ){
        viewModelScope.launch {
            localCharacterRepository.removeItemFromCharacter(currentCharacter, currentItem)
            _selectedCharacterItems.value = _selectedCharacterItems.value?.filterNot { it == currentItem }
        }

    }



}
