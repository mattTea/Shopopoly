package shopopoly

internal class GameLedger {
    val entries = mutableListOf<Entry>()

    fun transferStartingBalance(amount: Int, player: Player) {
        entries.add(
            Entry(
                amount = amount,
                from = Bank,
                to = player,
                reason = "Starting balance transfer"
            )
        )
    }

    fun payAward(location: Location, player: Player) {
        entries.add(
            Entry(
                amount = location.visitorFeeOrAward,
                from = Bank,
                to = player,
                reason = "Award fee",
                location = location
            )
        )
    }

    fun payVisitorFee(location: Location, feePayer: Player) {
        location.owner?.let {
            entries.add(
                Entry(
                    amount = location.visitorFeeOrAward,
                    from = feePayer,
                    to = location.owner!!,
                    reason = "Visitor payment",
                    location = location
                )
            )
        }
    }

    fun buyLocation(location: Location, buyer: Player) {
        entries.add(
            Entry(
                amount = location.purchasePrice,
                from = buyer,
                to = Bank,
                reason = "Location purchase",
                location = location
            )
        )
    }

    fun payBuildingFee(location: Location, buildingType: Store, builder: Player) {
        location.setStoreTypeAndVisitorFee(buildingType)

        entries.add(
            Entry(
                amount = location.calculateCostToBuild(buildingType),
                from = builder,
                to = Bank,
                reason = "Fee to build $buildingType on retail site",
                location = location
            )
        )
    }

    fun calculatePlayerBalance(player: Player): PlayerBalance {
        val playerCreditEntries = entries.filter { it.to == player }
        val playerDebitEntries = entries.filter { it.from == player }

        val balance = playerCreditEntries.sumBy { it.amount } - playerDebitEntries.sumBy { it.amount }

        return if (balance < 0) {
            Debt(balance)
        } else {
            Credit(balance)
        }
    }

    fun getPremisesOwnedBy(player: Player): List<Location> {
        return entries
            .filter { it.from == player && it.reason == "Location purchase" }
            .map { it.location }
    }

    fun getOwnerAndFeesPaid(location: Location): Pair<Player, Int> {
        val owner = entries.last { it.to == location.owner }.to
        val feesPaid = entries.filter { it.location == location }.sumBy { it.amount }
        return Pair(owner, feesPaid)
    }
}

internal class Entry(
    val amount: Int,
    val from: Player,
    val to: Player,
    val reason: String,
    val location: Location = Location()
)

open class PlayerBalance {
    open val amount = 0
}

internal class Credit(balance: Int) : PlayerBalance() {
    override val amount = balance
}

internal class Debt(balance: Int) : PlayerBalance() {
    override val amount = -balance
}