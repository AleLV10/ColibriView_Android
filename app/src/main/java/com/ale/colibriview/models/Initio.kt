package com.ale.colibriview.models

/*data class Initio(
    var titleI: String="",
    var descriptionI:String="",


)

 */

data class Initio(val titleI: String?, val descriptionI: String?) {
    // Constructor predeterminado o sin argumentos
    constructor() : this(null, null)
}
