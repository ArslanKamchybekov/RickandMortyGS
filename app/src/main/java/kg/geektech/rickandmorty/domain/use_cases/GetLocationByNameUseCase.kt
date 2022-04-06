package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.LocationsInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetLocationByNameUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getLocationByName(name: String): Response<LocationsInfo>{
        return repository.getLocationByName(name)
    }
}