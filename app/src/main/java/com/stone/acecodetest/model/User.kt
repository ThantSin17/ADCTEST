package com.stone.acecodetest.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable


@Entity
data class User(
    @Json(name = "id")
    @PrimaryKey
    val id: Int,

    @ColumnInfo
    @Json(name = "name")
    val name: String,

    @ColumnInfo
    @Json(name = "email")
    val email: String,

    @Json(name = "address")
    @Embedded(prefix = "address")
    val address: AddressT,


    @ColumnInfo
    @Json(name = "phone")
    val phone: String,

    @ColumnInfo
    @Json(name = "website")
    val website: String
) :Serializable{
}



data class AddressT(

    @Json(name = "street")
    val street: String,

    @Json(name = "suite")
    val suite: String,

    @Json(name = "city")
    val city: String,

    @Json(name = "zipcode")
    val zipcode: String
):Serializable