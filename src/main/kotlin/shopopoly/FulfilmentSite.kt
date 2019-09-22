package shopopoly

internal class FulfilmentSite(override val name: String) : Location() {
    override val visitorFeeOrAward: Int = 20
    override val purchasePrice: Int = 100
}
