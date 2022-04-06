package kg.geektech.rickandmorty.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kg.geektech.rickandmorty.presentation.callbacks.Models
import kotlinx.android.parcel.Parcelize

data class LocationsInfo(
    @SerializedName("info") val data: LocationsData,
    @SerializedName("results") val results: List<LocationDetail>
)

data class LocationsData(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String,
    @SerializedName("prev") val prev: String
)

@Parcelize
data class LocationDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("dimension") val dimension: String,
    @SerializedName("residents") val residents: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
) : Models, Parcelable