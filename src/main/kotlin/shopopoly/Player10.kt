package shopopoly

class Player10(
    val name: String,
    val boardLocation: Int = 1,
    val passedGo: Boolean = false
) {

    fun move(diceScore: Int = Dice().score()): Player10 {
        val calculateBoardLocation = this.boardLocation + diceScore
        val newBoardLocation =
            when (calculateBoardLocation > 13) {
                true -> calculateBoardLocation - 13
                false -> calculateBoardLocation
            }

        return Player10(
            name = this.name,
            boardLocation = newBoardLocation,
            passedGo = calculateBoardLocation > 13
        )
    }
}