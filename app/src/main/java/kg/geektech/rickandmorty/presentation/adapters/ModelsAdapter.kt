package kg.geektech.rickandmorty.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kg.geektech.rickandmorty.R
import kg.geektech.rickandmorty.databinding.ItemCharacterBinding
import kg.geektech.rickandmorty.databinding.ItemEpisodeBinding
import kg.geektech.rickandmorty.databinding.ItemLocationBinding
import kg.geektech.rickandmorty.domain.models.CharacterDetail
import kg.geektech.rickandmorty.domain.models.EpisodeDetail
import kg.geektech.rickandmorty.domain.models.LocationDetail
import kg.geektech.rickandmorty.presentation.callbacks.DiffCallback
import kg.geektech.rickandmorty.presentation.callbacks.Models

open class ModelsAdapter : ListAdapter<Models, RecyclerView.ViewHolder>(DiffCallback()) {

    private var list = mutableListOf<Any>()
    var onItemClickListener: ((Any) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_character -> CharacterViewHolder(
                ItemCharacterBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            R.layout.item_location -> LocationViewHolder(
                ItemLocationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            R.layout.item_episode -> EpisodeViewHolder(
                ItemEpisodeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw IllegalArgumentException("Unknown view type...")
        }

    }

    override fun getItemViewType(position: Int): Int {

        return when (list[position]) {

            is CharacterDetail -> R.layout.item_character

            is LocationDetail -> R.layout.item_location

            is EpisodeDetail -> R.layout.item_episode

            else -> throw IllegalArgumentException("Unknown view type...")
        }
    }


    fun setList(list: List<Models>) {
        this.list.addAll(list)
        submitList(list)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = getItem(position)
        when (holder) {
            is CharacterViewHolder -> {
                val character = model as CharacterDetail
                holder.onBindCharacter(character)
                holder.characterLayout.setOnClickListener { onItemClickListener?.invoke(character) }
            }
            is LocationViewHolder -> {
                val location = model as LocationDetail
                holder.onBindLocation(location)
                holder.locationLayout.setOnClickListener { onItemClickListener?.invoke(location) }
            }
            is EpisodeViewHolder -> {
                val episode = model as EpisodeDetail
                holder.onBindEpisode(episode)
                holder.episodeLayout.setOnClickListener { onItemClickListener?.invoke(episode) }
            }
        }
    }
}

class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    var episodeLayout = binding.episodeLayout
    fun onBindEpisode(episodeDetail: EpisodeDetail) {
        binding.tvEpisode.text = episodeDetail.episode
        binding.tvName.text = episodeDetail.name
        binding.tvDate.text = episodeDetail.air_date
    }
}

class CharacterViewHolder(private val binding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val characterLayout = binding.characterLayout
    fun onBindCharacter(characterDetail: CharacterDetail) {
        Picasso.get().load(characterDetail.image).into(binding.ivCharacter)
        binding.tvCharacterName.text = characterDetail.name
        binding.tvLocationName.text = characterDetail.location.name
        binding.tvCharacterSpecies.text = characterDetail.species
        binding.tvCharacterStatus.text = characterDetail.status
    }
}

class LocationViewHolder(private val binding: ItemLocationBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val locationLayout = binding.locationLayout
    fun onBindLocation(locationDetail: LocationDetail) {
        binding.tvType.text = locationDetail.type
        binding.tvDimension.text = locationDetail.dimension
        binding.tvName.text = locationDetail.name
    }
}