package kg.geektech.rickandmorty.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentSearchBinding
import kg.geektech.rickandmorty.domain.models.CharacterDetail
import kg.geektech.rickandmorty.domain.models.EpisodeDetail
import kg.geektech.rickandmorty.domain.models.LocationDetail
import kg.geektech.rickandmorty.presentation.adapters.ModelsAdapter
import kg.geektech.rickandmorty.utils.extensions.hideKeyboard

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModels()
    private val characterAdapter by lazy { ModelsAdapter() }
    private val locationAdapter by lazy { ModelsAdapter() }
    private val episodeAdapter by lazy { ModelsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        initListeners()
    }

    private fun initRv() {
        viewModel.characters.observe(viewLifecycleOwner) {
            characterAdapter.setList(it.results)
        }
        binding.rvCharacters.adapter = characterAdapter
        viewModel.locations.observe(viewLifecycleOwner) {
            locationAdapter.setList(it.results)
        }
        binding.rvLocations.adapter = locationAdapter
        viewModel.episodes.observe(viewLifecycleOwner) {
            episodeAdapter.setList(it.results)
        }
        binding.rvEpisodes.adapter = episodeAdapter
    }

    private fun initListeners() {
        characterAdapter.onItemClickListener = {
            navigateCharacter(it as CharacterDetail)
        }
        episodeAdapter.onItemClickListener = {
            navigateEpisode(it as EpisodeDetail)
        }
        locationAdapter.onItemClickListener = {
            navigateLocation(it as LocationDetail)
        }
        binding.btnSearch.setOnClickListener {
            if (binding.etSearch.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "Type the character's name", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.getCharacterByName(binding.etSearch.text.toString())
                viewModel.getLocationByName(binding.etSearch.text.toString())
                viewModel.getEpisodeByName(binding.etSearch.text.toString())
            }
            it.hideKeyboard()
        }
    }

    private fun navigateCharacter(character: CharacterDetail) {
        val destination =
            SearchFragmentDirections.actionSearchFragmentToCharacterDetailFragment(character)
        findNavController().navigate(destination)
    }

    private fun navigateEpisode(episode: EpisodeDetail) {
        val destination =
            SearchFragmentDirections.actionSearchFragmentToEpisodeDetailFragment(episode)
        findNavController().navigate(destination)
    }

    private fun navigateLocation(location: LocationDetail) {
        val destination =
            SearchFragmentDirections.actionSearchFragmentToLocationDetailFragment(location)
        findNavController().navigate(destination)
    }
}