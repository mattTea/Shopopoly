package shopopoly

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.mockk.every
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object PlayerTest : Spek({

    describe("player") {

        it("should have a name") {
            assertThat(Player("Matt", 6).name).isEqualTo("Matt")
        }

        it("should have a board location") {
            assertThat(Player("Matt", 6).boardLocation).isEqualTo(6)
        }
    }

    describe("move") {

        val diceMock = mockk<Dice>()
        every { diceMock.score() } returns(7)

        val diceMock2 = mockk<Dice>()
        every { diceMock2.score() } returns(4)

        it("should add dice roll score to boardLocation") {
            val player = Player("Matt", 5)
            assertThat(player.move(diceMock.score()).boardLocation).isEqualTo(12)
        }

        it("should restart boardLocation to 1 after moving past 13") {
            val player = Player("Matt", 12)
            assertThat(player.move(diceMock.score()).boardLocation).isEqualTo(6)
        }

        it("should set passedGo to true if location 13 is passed") {
            val player = Player("Matt", 12)
            assertThat(player.move(diceMock.score()).passedGo).isTrue()
        }

        it("should not set passedGo to true if location 13 is not passed") {
            val player = Player("Matt", 2)
            player.move(diceMock2.score())

            assertThat(player.passedGo).isFalse()
        }
    }
})