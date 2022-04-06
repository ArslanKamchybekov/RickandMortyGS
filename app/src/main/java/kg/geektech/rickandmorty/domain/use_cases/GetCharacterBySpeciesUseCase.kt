package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetCharacterBySpeciesUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getCharacterBySpecies(species: String): Response<CharactersInfo>{
        return repository.getCharacterBySpecies(species)
    }
}