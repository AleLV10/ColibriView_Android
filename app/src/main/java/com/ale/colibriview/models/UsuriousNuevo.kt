package com.ale.colibriview.models

data class UsuriousNuevo(
                var name:String,
                var mail:String,
                var contrasena:String,
                var filephath:String=""
    ){
    constructor(): this ("","","","")
}
