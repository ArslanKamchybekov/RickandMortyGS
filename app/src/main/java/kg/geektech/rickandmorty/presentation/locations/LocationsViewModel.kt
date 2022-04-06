package kg.geektech.rickandmorty.presentation.locations

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
import kg.geektech.rickandmorty.domain.models.LocationsInfo
import kg.geektech.rickandmorty.domain.repository.RickAndMortyRepository
import kg.geektech.rickandmorty.domain.use_cases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(repository: RickAndMortyRepository) :
    ViewModel() {

    private val getLocationsListUseCase = GetLocationsListUseCase(repository)
    private val getLocationByDimensionUseCase = GetLocationByDimensionUseCase(repository)
    private val getLocationByNameUseCase = GetLocationByNameUseCase(repository)
    private val getLocationByTypeUseCase = GetLocationByTypeUseCase(repository)
    var option = MutableLiveData<String>()
    val locations = MutableLiveData<LocationsInfo>()

    init {
        getLocationsList(1)
    }

    private fun getLocationsList(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getLocationsListUseCase.getLocationsList(page)
                if (call.isSuccessful) locations.postValue(call.body())
            }
        }
    }

    fun getLocationByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getLocationByNameUseCase.getLocationByName(name)
                if (call.isSuccessful) locations.postValue((call.body()))
            }
        }
    }

    fun getLocationByType(type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getLocationByTypeUseCase.getLocationByType(type)
                if (call.isSuccessful) locations.postValue(call.body())
            }
        }
    }

    fun getLocationByDimension(dimension: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                val call = getLocationByDimensionUseCase.getLocationByDimension(dimension)
                if (call.isSuccessful) locations.postValue(call.body())
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
                R.array.filter_location,
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
                        searchTv.hint = "Location:"
                    }

                    2 -> {
                        option.postValue("2")
                        searchTv.hint = "Type:"
                    }

                    3 -> {
                        option.postValue("3")
                        searchTv.hint = "Dimension:"
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


