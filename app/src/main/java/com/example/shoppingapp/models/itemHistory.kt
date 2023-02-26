package com.example.shoppingapp.models

import com.google.type.Date
import java.util.*

data class itemHistory(
    var title : String,
    var desc : String,
    var image : String,
    var price:Int,
    var inStock:Boolean,
    var date:String
)
