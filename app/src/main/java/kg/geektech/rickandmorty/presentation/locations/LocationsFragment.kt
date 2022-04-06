package kg.geektech.rickandmorty.presentation.locations

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentLocationsBinding
import kg.geektech.rickandmorty.domain.models.LocationDetail
import kg.geektech.rickandmorty.presentation.adapters.ModelsAdapter
import kg.geektech.rickandmorty.utils.extensions.hideKeyboard

@AndroidEntryPoint
class LocationsFragment : Fragment(R.layout.fragment_locations) {

    private lateinit var parameter: String
    private val binding: FragmentLocationsBinding by viewBinding()
    private val viewModel: LocationsViewModel by viewModels()
    private val locationAdapter by lazy { (ModelsAdapter()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        initListeners()

    }

    private fun initRv() {
        viewModel.locations.observe(viewLifecycleOwner) {
            locationAdapter.setList(it.results)
        }
        binding.rvLocations.adapter = locationAdapter
    }

    private fun initListeners() {
        locationAdapter.onItemClickListener = {
            navigateWithData(it as LocationDetail)
        }
        binding.btnSearchToolbar.setOnClickListener {
            if (binding.etLocations.text.isBlank()) {
                Toast.makeText(requireContext(), "Fill up the field...", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.option.observe(viewLifecycleOwner) { s ->
                    parameter = s.toString()
                }
                when (parameter) {
                    "1" -> viewModel.getLocationByName(binding.etLocations.text.toString())
                    "2" -> viewModel.getLocationByType(binding.etLocations.text.toString())
                    "3" -> viewModel.getLocationByDimension(binding.etLocations.text.toString())
                }
            }
            it.hideKeyboard()
        }

        binding.btnFilterToolbar.setOnClickListener {
            context?.let { it1 ->
                viewModel.showFilterDialog(
                    it1,
                    layoutInflater,
                    binding.etLocations
                )
            }
        }
    }

    private fun navigateWithData(location: LocationDetail) {
        val destination =
            LocationsFragmentDirections.actionLocationsFragmentToLocationDetailFragment(location)
        findNavController().navigate(destination)
    }
}
