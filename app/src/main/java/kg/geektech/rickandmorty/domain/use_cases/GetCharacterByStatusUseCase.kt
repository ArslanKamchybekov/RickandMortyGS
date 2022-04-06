package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetCharacterByStatusUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getCharacterByStatus(status: String): Response<CharactersInfo>{
        return repository.getCharacterByStatus(status)
    }
}