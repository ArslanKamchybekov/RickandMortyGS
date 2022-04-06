package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.LocationsInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetLocationByDimensionUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getLocationByDimension(dimension: String): Response<LocationsInfo>{
        return repository.getLocationByDimension(dimension)
    }
}