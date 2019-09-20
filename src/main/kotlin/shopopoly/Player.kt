package shopopoly

internal class Player {
    var bankBalance: Int = 0

    fun landOnOrPass(location: Location): Int {
        bankBalance += location.visitorFeeOrAward
        return bankBalance
    }
}