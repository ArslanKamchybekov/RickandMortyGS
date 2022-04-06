package kg.geektech.rickandmorty.presentation.callbacks

import androidx.recyclerview.widget.DiffUtil
import kg.geektech.rickandmorty.domain.models.CharacterDetail
import kg.geektech.rickandmorty.domain.models.EpisodeDetail
import kg.geektech.rickandmorty.domain.models.LocationDetail

interface Models {
    override fun equals(other: Any?): Boolean
}

class DiffCallback : DiffUtil.ItemCallback<Models>() {
    override fun areItemsTheSame(oldItem: Models, newItem: Models): Boolean {
        return when {
            oldItem is CharacterDetail && newItem is CharacterDetail -> {
                oldItem.id == newItem.id
            }
            oldItem is LocationDetail && newItem is CharacterDetail -> {
                oldItem.id == newItem.id
            }
            oldItem is EpisodeDetail && newItem is EpisodeDetail -> {
                oldItem.id == newItem.id
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: Models, newItem: Models): Boolean {
        return when {
            oldItem is CharacterDetail && newItem is CharacterDetail -> {
                oldItem == newItem
            }
            oldItem is LocationDetail && newItem is CharacterDetail -> {
                oldItem == newItem
            }
            oldItem is EpisodeDetail && newItem is EpisodeDetail -> {
                oldItem == newItem
            }
            else -> false
        }
    }

}