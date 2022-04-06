package kg.geektech.rickandmorty.presentation.characters

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.FragmentCharactersBinding
import kg.geektech.rickandmorty.domain.models.CharacterDetail
import kg.geektech.rickandmorty.presentation.adapters.ModelsAdapter
import kg.geektech.rickandmorty.utils.extensions.hideKeyboard

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val binding: FragmentCharactersBinding by viewBinding()
    private val viewModel: CharactersViewModel by viewModels()
    private val characterAdapter by lazy { (ModelsAdapter()) }
    private lateinit var parameter: String

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
    }

    private fun initListeners() {
        characterAdapter.onItemClickListener = {
            navigateWithData(it as CharacterDetail)
        }

        binding.btnSearchToolbar.setOnClickListener {
            if (binding.etCharacters.text.isBlank()) {
                Toast.makeText(requireContext(), "Fill up the field...", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.option.observe(viewLifecycleOwner) { s ->
                    parameter = s.toString()
                }
                when (parameter) {
                    "1" -> viewModel.getCharacterByStatus("Alive")
                    "2" -> viewModel.getCharacterByStatus("Dead")
                    "3" -> viewModel.getCharacterBySpecies("Alien")
                    "4" -> viewModel.getCharacterByGender("Female")
                    "5" -> viewModel.getCharacterBySpecies("Human")
                    "6" -> viewModel.getCharacterByName(binding.etCharacters.text.toString())
                }
            }
            it.hideKeyboard()
        }
        binding.btnFilterToolbar.setOnClickListener {
            context?.let { it1 ->
                viewModel.showFilterDialog(
                    it1,
                    layoutInflater,
                    binding.etCharacters
                )
            }
        }
    }

    private fun navigateWithData(character: CharacterDetail) {
        val destination =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(character)
        findNavController().navigate(destination)
    }
}