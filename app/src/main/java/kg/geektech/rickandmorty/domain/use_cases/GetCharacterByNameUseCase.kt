package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetCharacterByNameUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getCharacterByName(name: String): Response<CharactersInfo>{
        return repository.getCharacterByName(name)
    }
}