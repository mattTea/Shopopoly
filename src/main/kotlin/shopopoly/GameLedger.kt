package shopopoly

internal class GameLedger {
    val entries = mutableListOf<Entry>()

    fun transferStartingBalance(amount: Int, player: String) {
        entries.add(Entry(-amount, "Bank", player, "Starting balance transfer"))
    }
}

internal class Entry(
    val amount: Int,
    val from: String,
    val to: String,
    val reason: String
)