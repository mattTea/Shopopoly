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
}

internal class Entry(
    val amount: Int,
    val from: String,
    val to: String,
    val reason: String
)