package shopopoly

class RetailSite(
    override val name: String,
    override val purchasePrice: Int,
    override val costToBuildMinistore: Int,
    override val costToBuildSupermarket: Int,
    override val costToBuildMegastore: Int,
    override var visitorFeeOrAward: Int = Store.UNDEVELOPED.amount,
    override var owner: String = "Unattached",
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

    override fun setStoreTypeAndVisitorFee(storeType: Store) {
        this.store = storeType
        this.visitorFeeOrAward = storeType.amount
    }
}

enum class Store(val amount: Int) {
    UNDEVELOPED(10),
    MINISTORE(20),
    SUPERMARKET(30),
    MEGASTORE(50)
}