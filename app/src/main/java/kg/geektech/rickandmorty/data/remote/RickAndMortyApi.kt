package kg.geektech.rickandmorty.data.remote

import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.models.EpisodesInfo
import kg.geektech.rickandmorty.domain.models.LocationsInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("api/character/")
    suspend fun getCharactersList(
        @Query("page") page: Int
    ): Response<CharactersInfo>

    @GET("api/location")
    suspend fun getLocationsList(
        @Query("page") page: Int
    ): Response<LocationsInfo>

    @GET("api/episode")
    suspend fun getEpisodesList(
        @Query("page") page: Int
    ): Response<EpisodesInfo>

    @GET("api/character/")
    suspend fun getCharacterByName(
        @Query("name") name: String
    ): Response<CharactersInfo>

    @GET("api/character/")
    suspend fun getCharacterBySpecies(
        @Query("species") species: String
    ): Response<CharactersInfo>

    @GET("api/character/")
    suspend fun getCharacterByGender(
        @Query("gender") gender: String
    ): Response<CharactersInfo>

    @GET("api/character/")
    suspend fun getCharacterByStatus(
        @Query("status") status: String
    ): Response<CharactersInfo>

    @GET("api/location")
    suspend fun getLocationByName(
        @Query("name") name: String
    ): Response<LocationsInfo>

    @GET("api/location")
    suspend fun getLocationByDimension(
        @Query("dimension") dimension: String
    ): Response<LocationsInfo>

    @GET("api/location")
    suspend fun getLocationByType(
        @Query("type") type: String
    ): Response<LocationsInfo>

    @GET("api/episode")
    suspend fun getEpisodeByEpisode(
        @Query("episode") episode: String
    ): Response<EpisodesInfo>

    @GET("api/episode")
    suspend fun getEpisodeByName(
        @Query("name") name: String
    ): Response<EpisodesInfo>

}