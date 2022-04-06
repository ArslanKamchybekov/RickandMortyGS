package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetCharacterByGenderUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getCharacterByGender(gender: String): Response<CharactersInfo> {
        return repository.getCharacterByGender(gender)
    }
}