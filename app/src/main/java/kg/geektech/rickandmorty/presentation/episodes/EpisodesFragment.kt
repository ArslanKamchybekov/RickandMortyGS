package kg.geektech.rickandmorty.presentation.episodes

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentEpisodesBinding
import kg.geektech.rickandmorty.domain.models.EpisodeDetail
import kg.geektech.rickandmorty.presentation.adapters.ModelsAdapter
import kg.geektech.rickandmorty.utils.extensions.hideKeyboard

@AndroidEntryPoint
class EpisodesFragment : Fragment(R.layout.fragment_episodes) {

    private lateinit var parameter: String
    private val binding: FragmentEpisodesBinding by viewBinding()
    private val viewModel: EpisodesViewModel by viewModels()
    private val episodeAdapter: ModelsAdapter by lazy { ModelsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        initListeners()
    }

    private fun initRv() {
        viewModel.episodes.observe(viewLifecycleOwner) {
            episodeAdapter.setList(it.results)
        }
        binding.rvEpisodes.adapter = episodeAdapter
    }

    private fun initListeners() {
        episodeAdapter.onItemClickListener = {
            navigateWithData(it as EpisodeDetail)
        }
        binding.btnSearchToolbar.setOnClickListener {
            if (binding.etEpisodes.text.isBlank()) {
                Toast.makeText(requireContext(), "Fill up the field...", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.option.observe(viewLifecycleOwner) { s ->
                    parameter = s.toString()
                }
                when (parameter) {
                    "1" -> viewModel.getEpisodeByName(binding.etEpisodes.text.toString())
                    "2" -> viewModel.getEpisodeByEpisode(binding.etEpisodes.text.toString())
                }
            }
            it.hideKeyboard()
        }
        binding.btnFilterToolbar.setOnClickListener {
            context?.let { it1 ->
                viewModel.showFilterDialog(
                    it1,
                    layoutInflater,
                    binding.etEpisodes
                )
            }

        }
    }


    private fun navigateWithData(episode: EpisodeDetail) {
        val destination =
            EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailFragment(episode)
        findNavController().navigate(destination)
    }
}

