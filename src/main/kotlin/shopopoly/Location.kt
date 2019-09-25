package shopopoly

open class Location(
    open val name: String = "n/a",
    open val purchasePrice: Int = 0,
    open val costToBuildMinistore: Int = 0,
    open val costToBuildSupermarket: Int = 0,
    open val costToBuildMegastore: Int = 0,
    open val visitorFeeOrAward: Int = 0,
    open val owner: String = "Unattached",
    open val store: Store = Store.UNDEVELOPED
) {
    open fun calculateCostToBuild(buildingType: Store): Int {
        return 0
    }

    open fun setStoreTypeAndVisitorFee(buildingType: Store) {}
}