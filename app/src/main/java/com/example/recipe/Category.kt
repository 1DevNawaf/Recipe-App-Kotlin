package com.example.recipe

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
--------Parcelize And serializing---------

serializing an object is to convert the object into a format that can be
easily stored and transmitted, then reconstructed later.

usually serializing is converting objects into formats like JSON or XML
depending on the library or the mechanism that are being used.


but in our app we used the library kotlinx.parcelize.Parcelable
when we implement "Parcelable", the Object is converted to binary representation
this binary format is optimized for the Android, and the most important thing is
the state preservation/restoration; that what we are need for our app when
navigating through the screens
 */

@Parcelize
data class Category(
    val idCategory : String,
    val strCategory : String,
    val strCategoryThumb : String,
    val strCategoryDescription : String,
):Parcelable

data class CategoriesResponse(val categories : List<Category>)
