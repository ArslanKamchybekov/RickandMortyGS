package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.EpisodesInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetEpisodeByNameUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getEpisodeByName(name: String): Response<EpisodesInfo>{
        return repository.getEpisodeByName(name)
    }
}