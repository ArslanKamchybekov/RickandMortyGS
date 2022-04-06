package kg.geektech.rickandmorty.domain.repository

import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.models.EpisodesInfo
import kg.geektech.rickandmorty.domain.models.LocationsInfo
import retrofit2.Response

interface RickAndMortyRepository {

    suspend fun getCharactersList(page: Int): Response<CharactersInfo>

    suspend fun getLocationsList(page: Int): Response<LocationsInfo>

    suspend fun getEpisodesList(page: Int): Response<EpisodesInfo>

    suspend fun getCharacterByName(name: String): Response<CharactersInfo>

    suspend fun getCharacterByStatus(status: String): Response<CharactersInfo>

    suspend fun getCharacterBySpecies(species: String): Response<CharactersInfo>

    suspend fun getCharacterByGender(gender: String): Response<CharactersInfo>

    suspend fun getLocationByName(name: String): Response<LocationsInfo>

    suspend fun getLocationByType(type: String): Response<LocationsInfo>

    suspend fun getLocationByDimension(dimension: String): Response<LocationsInfo>

    suspend fun getEpisodeByName(name: String): Response<EpisodesInfo>

    suspend fun getEpisodeByEp(episode: String): Response<EpisodesInfo>
}