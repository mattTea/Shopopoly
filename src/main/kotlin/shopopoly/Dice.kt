package shopopoly

import kotlin.random.Random.Default.nextInt

data class Dice(
    val firstDie: Int = nextInt(1, 7),
    val secondDie: Int = nextInt(1, 7)
) {
    fun score() = firstDie + secondDie
}