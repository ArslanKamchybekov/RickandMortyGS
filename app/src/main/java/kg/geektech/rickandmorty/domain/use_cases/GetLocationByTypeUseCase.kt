package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.LocationsInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetLocationByTypeUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getLocationByType(type: String): Response<LocationsInfo> {
        return repository.getLocationByType(type)
    }
}