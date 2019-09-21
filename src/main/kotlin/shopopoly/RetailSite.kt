package shopopoly

class RetailSite(
    val name: String,
    override val purchasePrice: Int,
    override val costToBuildMinistore: Int,
    override val costToBuildSupermarket: Int,
    override val costToBuildMegastore: Int,
    override val visitorFeeOrAward: Int,
    override var rent: Rent = Rent.UNDEVELOPED,
    override var owningGroup: String = "Unattached",
    override var store: Store = Store.UNDEVELOPED
) : Location() {

    override fun calculateCostToBuild(storeType: Store): Int {
        return when(storeType) {
            Store.UNDEVELOPED -> 0
            Store.MINISTORE -> costToBuildMinistore
            Store.SUPERMARKET -> costToBuildSupermarket
            else -> costToBuildMegastore
        }
    }
}

// TODO poss turn the rent amounts into a method to calculate (and add `rentalCostForMinistore`, etc parameters) similar to Store class
enum class Rent(val amount: Int) {
    UNDEVELOPED(10),
    MINISTORE(20),
    SUPERMARKET(30),
    MEGASTORE(50)
}

enum class Store {
    UNDEVELOPED,
    MINISTORE,
    SUPERMARKET,
    MEGASTORE
}