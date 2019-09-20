package shopopoly

class RetailSite(
    val name: String,
    val purchasePrice: Int,
    val costToBuildMinistore: Int,
    val costToBuildSupermarket: Int,
    val costToBuildMegastore: Int,
    override val visitorFeeOrAward: Int,
    var rent: Rent = Rent.UNDEVELOPED,
    var owningGroup: String = "Unattached"
) : Location()

enum class Rent(val amount: Int) {
    UNDEVELOPED(-10),
    MINI_STORE(-20),
    SUPERMARKET(-30),
    MEGASTORE(-50)
}