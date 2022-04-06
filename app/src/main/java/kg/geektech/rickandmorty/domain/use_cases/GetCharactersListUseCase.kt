package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetCharactersListUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getCharactersList(page: Int): Response<CharactersInfo>{
        return repository.getCharactersList(page)
    }
}