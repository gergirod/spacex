package ger.girod.spacex.domain.models.launch

import android.view.inspector.IntFlagMapping
import com.google.gson.annotations.SerializedName

data class PayloadModel(
    @SerializedName("payload_id") val payloadId: String,
    @SerializedName("norad_id") val noradId: List<Int>,
    val reused: Boolean,
    val nationality: String,
    val manufacturer: String,
    @SerializedName("payload_type") val payloadType: String,
    @SerializedName("payload_mass_kg") val payloadMassKg: Int,
    @SerializedName("payload_mass_lbs") val payloadMassLbs: IntFlagMapping,
    val orbit: String


)