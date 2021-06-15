package com.example.giphytesttaskkyrylohryzhuk.data.model

data class GiphyModel(
    val `data`: List<Data>
)

data class Data(
    val images: Images
)

data class Images(
    val original: Original
)

data class Original(
    val url: String
)