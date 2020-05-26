package ger.girod.spacex.domain.models.launch

data class SecondStageModel(
    val block: Int,
    val payloads: List<PayloadModel>
)