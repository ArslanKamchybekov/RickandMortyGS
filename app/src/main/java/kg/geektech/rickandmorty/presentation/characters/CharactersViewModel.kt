package kg.geektech.rickandmorty.presentation.characters

import android.annotation.SuppressLint
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
import kg.geektech.rickandmorty.domain.models.CharactersInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import kg.geektech.rickandmorty.domain.use_cases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(repository: RickAndMortyRepository) :
    ViewModel() {

    private val getCharactersListUseCase = GetCharactersListUseCase(repository)
    private val getCharacterByNameUseCase = GetCharacterByNameUseCase(repository)
    private val getCharacterByStatusUseCase = GetCharacterByStatusUseCase(repository)
    private val getCharacterBySpeciesUseCase = GetCharacterBySpeciesUseCase(repository)
    private val getCharacterByGenderUseCase = GetCharacterByGenderUseCase(repository)
    val characters = MutableLiveData<CharactersInfo>()
    var option = MutableLiveData<String>()

    init {
        getCharactersList(1)
    }

    private fun getCharactersList(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharactersListUseCase.getCharactersList(page)
                if (call.isSuccessful) characters.postValue(call.body())
            }
        }
    }

    fun getCharacterByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharacterByNameUseCase.getCharacterByName(name)
                if (call.isSuccessful) characters.postValue(call.body())
            }
        }
    }

    fun getCharacterByStatus(status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharacterByStatusUseCase.getCharacterByStatus(status)
                if (call.isSuccessful) characters.postValue(call.body())
            }
        }
    }

    fun getCharacterBySpecies(species: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharacterBySpeciesUseCase.getCharacterBySpecies(species)
                if (call.isSuccessful) characters.postValue(call.body())
            }
        }
    }

    fun getCharacterByGender(gender: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getCharacterByGenderUseCase.getCharacterByGender(gender)
                if (call.isSuccessful) characters.postValue(call.body())
            }
        }
    }

    fun showFilterDialog(context: Context, layoutInflater: LayoutInflater, editText: EditText) {
        val dialog = Dialog(context)
        val dialogBinding = FilterDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        context.let {
            ArrayAdapter.createFromResource(
                it.applicationContext,
                R.array.filter_characters,
                R.layout.option_text
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner)
                dialogBinding.filterSpinner.adapter = adapter
            }
        }
        dialog.show()

        @SuppressLint("SetTextI18n")
        dialogBinding.filterSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    1 -> {
                        option.postValue("1")
                        editText.setText("Alive")
                    }

                    2 -> {
                        option.postValue("2")
                        editText.setText("Dead")
                    }

                    3 -> {
                        option.postValue("3")
                        editText.setText("Alien")
                    }

                    4 -> {
                        option.postValue("4")
                        editText.setText("Female")
                    }

                    5 -> {
                        option.postValue("5")
                        editText.setText("Human")
                    }
                    6 -> {
                        option.postValue("6")
                        editText.hint = "Enter the name :"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        dialogBinding.btnSelect.setOnClickListener {
            dialog.hide()
        }
    }
}
