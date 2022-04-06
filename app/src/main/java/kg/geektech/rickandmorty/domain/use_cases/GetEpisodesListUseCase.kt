package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.EpisodesInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetEpisodesListUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getEpisodes(page: Int): Response<EpisodesInfo>{
        return repository.getEpisodesList(page)
    }
}