package kg.geektech.rickandmorty.data.repository

import kg.geektech.rickandmorty.data.remote.RickAndMortyApi
import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.models.EpisodesInfo
import kg.geektech.rickandmorty.domain.models.LocationsInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(private val api: RickAndMortyApi) :
    RickAndMortyRepository {

    override suspend fun getCharactersList(page: Int): Response<CharactersInfo> {
        return api.getCharactersList(page)
    }

    override suspend fun getLocationsList(page: Int): Response<LocationsInfo> {
        return api.getLocationsList(page)
    }

    override suspend fun getEpisodesList(page: Int): Response<EpisodesInfo> {
        return api.getEpisodesList(page)
    }

    override suspend fun getCharacterByName(name: String): Response<CharactersInfo> {
        return api.getCharacterByName(name)
    }

    override suspend fun getCharacterByStatus(status: String): Response<CharactersInfo> {
        return api.getCharacterByStatus(status)
    }

    override suspend fun getCharacterBySpecies(species: String): Response<CharactersInfo> {
        return api.getCharacterBySpecies(species)
    }

    override suspend fun getCharacterByGender(gender: String): Response<CharactersInfo> {
        return api.getCharacterByGender(gender)
    }

    override suspend fun getLocationByName(name: String): Response<LocationsInfo> {
        return api.getLocationByName(name)
    }

    override suspend fun getLocationByType(type: String): Response<LocationsInfo> {
        return api.getLocationByType(type)
    }

    override suspend fun getLocationByDimension(dimension: String): Response<LocationsInfo> {
        return api.getLocationByDimension(dimension)
    }

    override suspend fun getEpisodeByName(name: String): Response<EpisodesInfo> {
        return api.getEpisodeByName(name)
    }

    override suspend fun getEpisodeByEp(episode: String): Response<EpisodesInfo> {
        return api.getEpisodeByEpisode(episode)
    }
}