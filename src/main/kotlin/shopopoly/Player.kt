package shopopoly

internal class Player(
    val name: String,
    val boardLocation: Int = 1,
    val passedGo: Boolean = false
) {

    fun move(diceScore: Int = Dice().score()): Player {
        val calculateBoardLocation = this.boardLocation + diceScore
        val newBoardLocation =
            when (calculateBoardLocation > 13) {
                true -> calculateBoardLocation - 13
                false -> calculateBoardLocation
            }

        return Player(
            name = this.name,
            boardLocation = newBoardLocation,
            passedGo = calculateBoardLocation > 13
        )
    }
}