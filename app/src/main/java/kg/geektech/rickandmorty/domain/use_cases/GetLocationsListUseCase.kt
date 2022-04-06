package kg.geektech.rickandmorty.domain.use_cases

import kg.geektech.rickandmorty.domain.models.LocationsInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Response

class GetLocationsListUseCase(private val repository: RickAndMortyRepository) {

    suspend fun getLocationsList(page: Int): Response<LocationsInfo> {
        return repository.getLocationsList(page)
    }
}