package com.example.dorstip_app.dashboard.Recommended

import android.media.Rating

data class ItemModel(
    var title:String="",
    var description:String="",
    var picUrl:ArrayList<String> = ArrayList(),
    var price:Double=0.0,
    var rating:Double=0.0,
    var numberInCart:Int=0
)
