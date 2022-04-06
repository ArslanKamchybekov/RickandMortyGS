package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.EpisodesInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetEpisodeByEpisodeUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getEpisodeByEpisode(episode: String):Response<EpisodesInfo>{
        return repository.getEpisodeByEp(episode)
    }
}