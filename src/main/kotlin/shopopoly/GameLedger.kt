package shopopoly

internal class GameLedger {
    val entries = mutableListOf<Entry>()

    fun transferStartingBalance(amount: Int, player: Player) {
        entries.add(Entry(
            amount = amount,
            from = "Bank",
            to = player.name,
            reason = "Starting balance transfer"
        ))
    }

    fun payAward(location: Location, player: Player) {
        entries.add(Entry(
            amount = location.visitorFeeOrAward,
            from = "Bank",
            to = player.name,
            reason = "Award fee"
        ))
    }

    fun payVisitorFee(location: Location, locationOwner: Player, rentPayer: Player) {
        entries.add(Entry(
            amount = location.visitorFeeOrAward,
            from = rentPayer.name,
            to = locationOwner.name, // owner could be a property of the location in future
            reason = "Visitor payment"
        ))
    }

    fun buyLocation(location: Location, buyer: Player) {
        entries.add(Entry(
            amount = location.purchasePrice,
            from = buyer.name,
            to = "Bank",
            reason = "Location purchase"
        ))
    }
}

internal class Entry(
    val amount: Int,
    val from: String, // to and from to be Player class in future (if I change "Bank" to a similar class)
    val to: String,
    val reason: String
)