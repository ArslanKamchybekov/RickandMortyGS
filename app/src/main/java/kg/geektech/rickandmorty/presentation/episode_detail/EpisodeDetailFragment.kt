package kg.geektech.rickandmorty.presentation.episode_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentEpisodeDetailBinding

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment(R.layout.fragment_episode_detail) {

    private val binding: FragmentEpisodeDetailBinding by viewBinding()
    private val args: EpisodeDetailFragmentArgs by navArgs()

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
        val episode = args.episode
        binding.tvEpisodeDetailEpisode.text = episode.episode
        binding.tvEpisodeDetailName.text = episode.name
        binding.tvEpisodeDetailDate.text = episode.air_date
    }
}