package kg.geektech.rickandmorty.presentation.character_detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentCharacterDetailBinding

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val binding: FragmentCharacterDetailBinding by viewBinding()
    private val args: CharacterDetailFragmentArgs by navArgs()

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
        val character = args.character
        Picasso.get().load(character.image).into(binding.ivCharacterDetail)
        binding.tvCharacterDetailFirstLocationName.text = character.location.name
        binding.tvCharacterDetailGender.text = character.gender
        binding.tvCharacterDetailSpeciesName.text = character.species
        binding.tvCharacterDetailName.text = character.name
        binding.tvCharacterDetailStatus.text = character.status
    }
}





