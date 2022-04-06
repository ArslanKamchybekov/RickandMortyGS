package kg.geektech.rickandmorty.presentation.location_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentLocationDetailBinding

class LocationDetailFragment : Fragment(R.layout.fragment_location_detail) {

    private val binding: FragmentLocationDetailBinding by viewBinding()
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListeners()
    }

    private fun initListeners() {
        binding.fabBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initData() {
        val location = args.location
        binding.tvLocationDetailDimension.text = location.dimension
        binding.tvLocationDetailName.text = location.name
        binding.tvLocationDetailType.text = location.type
    }
}