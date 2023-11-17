package com.ale.colibriview

data class UsuarioNuevo(
                var name:String,
                var mail:String,
                var contrasena:String,
                var filephath:String=""
    ){
    constructor(): this ("","","","")
}
