package shopopoly

internal class GameLedger {
    val entries = mutableListOf<Entry>()

    fun transferStartingBalance(amount: Int, player: Player) {
        entries.add(Entry(
            amount = amount,
            from = Bank,
            to = player,
            reason = "Starting balance transfer"
        ))
    }

    fun payAward(location: Location, player: Player) {
        entries.add(Entry(
            amount = location.visitorFeeOrAward,
            from = Bank,
            to = player,
            reason = "Award fee"
        ))
    }

    fun payVisitorFee(location: Location, locationOwner: Player, rentPayer: Player) {
        entries.add(Entry(
            amount = location.visitorFeeOrAward,
            from = rentPayer,
            to = locationOwner,
            reason = "Visitor payment"
        ))
    }

    fun buyLocation(location: Location, buyer: Player) {
        entries.add(Entry(
            amount = location.purchasePrice,
            from = buyer,
            to = Bank,
            reason = "Location purchase"
        ))
    }

    fun payBuildingFee(location: Location, buildingType: Store, builder: Player) {
        entries.add(Entry(
            amount = location.calculateCostToBuild(buildingType),
            from = builder,
            to = Bank,
            reason = "Fee to build $buildingType on retail site"
        ))
    }
}

internal class Entry(
    val amount: Int,
    val from: Player,
    val to: Player,
    val reason: String
)