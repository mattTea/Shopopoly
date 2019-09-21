package shopopoly

open class Location(
    open val purchasePrice: Int = 0,
    open val costToBuildMinistore: Int = 0,
    open val costToBuildSupermarket: Int = 0,
    open val costToBuildMegastore: Int = 0,
    open val visitorFeeOrAward: Int = 0,
    open var rent: Rent = Rent.UNDEVELOPED,
    open var owningGroup: String = "Unattached",
    open var store: Store = Store.UNDEVELOPED
) {
    open fun calculateCostToBuild(buildingType: Store): Int {
        return 0
    }
}