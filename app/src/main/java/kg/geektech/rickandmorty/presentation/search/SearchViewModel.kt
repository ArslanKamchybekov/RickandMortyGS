package kg.geektech.rickandmorty.presentation.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.models.EpisodesInfo
import kg.geektech.rickandmorty.domain.models.LocationsInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import kg.geektech.rickandmorty.domain.use_cases.GetCharacterByNameUseCase
import kg.geektech.rickandmorty.domain.use_cases.GetEpisodeByNameUseCase
import kg.geektech.rickandmorty.domain.use_cases.GetLocationByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(repository: RickAndMortyRepository) :
    ViewModel() {

    private val getCharacterByNameUseCase = GetCharacterByNameUseCase(repository)
    private val getEpisodeByNameUseCase = GetEpisodeByNameUseCase(repository)
    private val getLocationByNameUseCase = GetLocationByNameUseCase(repository)

    val characters = MediatorLiveData<CharactersInfo>()
    val locations = MediatorLiveData<LocationsInfo>()
    val episodes = MediatorLiveData<EpisodesInfo>()

    fun getCharacterByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharacterByNameUseCase.getCharacterByName(name)
                if (call.isSuccessful) characters.postValue(call.body())
            }
        }
    }

    fun getLocationByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getLocationByNameUseCase.getLocationByName(name)
                if (call.isSuccessful) locations.postValue(call.body())
            }
        }
    }

    fun getEpisodeByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getEpisodeByNameUseCase.getEpisodeByName(name)
                if (call.isSuccessful) episodes.postValue(call.body())
            }
        }
    }
}