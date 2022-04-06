package kg.geektech.rickandmorty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.geektech.rickandmorty.BuildConfig.BASE_URL
import kg.geektech.rickandmorty.data.remote.RickAndMortyApi
import kg.geektech.rickandmorty.data.repository.RickAndMortyRepositoryImpl
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi() : RickAndMortyApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRickAndMortyRepository(api: RickAndMortyApi) : RickAndMortyRepository{
        return RickAndMortyRepositoryImpl(api)
    }

}