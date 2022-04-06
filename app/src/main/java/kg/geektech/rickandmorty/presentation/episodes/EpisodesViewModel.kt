package kg.geektech.rickandmorty.presentation.episodes

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FilterDialogBinding
import kg.geektech.rickandmorty.domain.models.EpisodesInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import kg.geektech.rickandmorty.domain.use_cases.GetEpisodesListUseCase
import kg.geektech.rickandmorty.domain.use_cases.GetEpisodeByEpisodeUseCase
import kg.geektech.rickandmorty.domain.use_cases.GetEpisodeByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(repository: RickAndMortyRepository) :
    ViewModel() {

    private val getEpisodesListUseCase = GetEpisodesListUseCase(repository)
    private val getEpisodeByNameUseCase = GetEpisodeByNameUseCase(repository)
    private val getEpisodeByEpisodeUseCase = GetEpisodeByEpisodeUseCase(repository)
    var option = MutableLiveData<String>()
    val episodes = MutableLiveData<EpisodesInfo>()

    init {
        getEpisodesList(1)
    }

    private fun getEpisodesList(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getEpisodesListUseCase.getEpisodes(page)
                if (call.isSuccessful) episodes.postValue(call.body())
            }
        }
    }

    fun getEpisodeByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getEpisodeByNameUseCase.getEpisodeByName(name)
                if (call.isSuccessful) episodes.postValue(call.body())
            }
        }
    }

    fun getEpisodeByEpisode(episode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getEpisodeByEpisodeUseCase.getEpisodeByEpisode(episode)
                if (call.isSuccessful) episodes.postValue(call.body())
            }
        }
    }

    fun showFilterDialog(context: Context, layoutInflater: LayoutInflater, searchTv: EditText) {
        val dialog = Dialog(context)
        val dialogBinding = FilterDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        context.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.filter_episode,
                R.layout.option_text
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner)
                dialogBinding.filterSpinner.adapter = adapter
            }
        }
        dialog.show()

        dialogBinding.filterSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    1 -> {
                        option.postValue("1")
                        searchTv.hint = "Episode:"
                    }

                    2 -> {
                        option.postValue("2")
                        searchTv.hint = "Number:"
                    }

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                option.postValue("")
            }
        }
        dialogBinding.btnSelect.setOnClickListener {
            dialog.hide()
        }
    }
}