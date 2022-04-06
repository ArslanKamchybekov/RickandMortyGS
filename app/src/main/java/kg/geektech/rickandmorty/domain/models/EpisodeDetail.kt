package kg.geektech.rickandmorty.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kg.geektech.rickandmorty.presentation.callbacks.Models
import kotlinx.android.parcel.Parcelize

data class EpisodesInfo(
    @SerializedName("info") val data: EpisodesData,
    @SerializedName("results") val results: List<EpisodeDetail>
)


data class EpisodesData(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String,
    @SerializedName("prev") val prev: String
)

@Parcelize
data class EpisodeDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("air_date") val air_date: String,
    @SerializedName("episode") val episode: String,
    @SerializedName("characters") val characters: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
) : Models, Parcelable