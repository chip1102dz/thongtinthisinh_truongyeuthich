package com.example.myapplication

import java.io.Serializable

data class User(
    var id: Int = 0,
    var name: String = "",
    var date: String = "",
    var address: String = "",
    var gioitinh: String = "",
    var truong_cntt: Boolean = false,
    var truong_kinhte: Boolean = false,
    var truong_supham: Boolean = false
) : Serializable {
}