package com.caique.atlasandroidrecruitment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SourceResponse(@SerializedName("id") val id: String,
                     @SerializedName("name") val name: String) : Serializable