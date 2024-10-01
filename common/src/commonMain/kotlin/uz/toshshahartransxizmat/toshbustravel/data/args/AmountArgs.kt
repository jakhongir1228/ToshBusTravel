package uz.toshshahartransxizmat.toshbustravel.data.args

data class AmountArgs(
    val vehicleId:Int,
    val from:String,
    val to:String,
    val aLatitude:Double,
    val aLongitude:Double,
    val bLatitude:Double,
    val bLongitude:Double,
    val cLatitude:Double,
    val cLongitude:Double,
    val distanceOfPoints:Double?
)
