package shopopoly

class RetailSite(
    val name: String,
    override val purchasePrice: Int,
    override val costToBuildMinistore: Int,
    override val costToBuildSupermarket: Int,
    override val costToBuildMegastore: Int,
    override val visitorFeeOrAward: Int,
    override var rent: Rent = Rent.UNDEVELOPED,
    override var owningGroup: String = "Unattached"
) : Location()

enum class Rent(val amount: Int) {
    UNDEVELOPED(10),
    MINI_STORE(20),
    SUPERMARKET(30),
    MEGASTORE(50)
}