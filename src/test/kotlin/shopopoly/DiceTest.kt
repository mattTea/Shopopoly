package shopopoly

import assertk.assertThat
import assertk.assertions.containsAll
import assertk.assertions.isBetween
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object DiceTest : Spek({

    describe("dice") {

        it("should return 2 integers") {
            assertThat(Dice(2, 4).firstDie).isEqualTo(2)
            assertThat(Dice(2, 4).secondDie).isEqualTo(4)
        }

        it("should return 2 integers between 1 and 6") {
            assertThat(Dice().firstDie).isBetween(1, 6)
            assertThat(Dice().secondDie).isBetween(1, 6)
        }

        it("should return all values between 2 and 12") {

            val diceRolls = mutableListOf<Int>()
            repeat(2000) {
                val dice = Dice()
                diceRolls.add(dice.firstDie + dice.secondDie)
            }

            assertThat(diceRolls).containsAll(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        }
    }
})