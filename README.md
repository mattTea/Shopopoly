Shopopoly
=========

Create Shopopoly, a game similar to the board game Monopoly, but for shops.

The objective of each player is to acquire assets such as shops and then make money by charging other players a fee when they visit them.

When a player runs out of money they leave the game and the winner is the last player to be left in the game.

------

This project will cover the Shopopoly challenges [8](https://coding-challenges.jl-engineering.net/challenges/challenge-8/), [9](https://coding-challenges.jl-engineering.net/challenges/challenge-9/), [10](https://coding-challenges.jl-engineering.net/challenges/challenge-10/) (the solution to which can currently be found separately [here](https://github.com/mattTea/Shopopoly-Challenge10)), and [12](https://coding-challenges.jl-engineering.net/challenges/challenge-12/).

------

## Challenge 8 - Shopopoly pt.1

Design the data types that represent each `Location` on the board. These may be...

1. `Free Parking`
    - There is only one of these. Players are not charged a fee for visiting this location and cannot purchase this location

1. `Go`
    - This is the location on the board that all players start from
    - Players passing through this location pick up a fee of **£100**

1. `Factory` or `Warehouse`
    - There are four of these, each with a unique name
    - They all have a purchase price of **£100**
    - The rent is **£20** for anybody visiting any of them

1. `Retail` site
    - There will be up to 20 of these
    - Each has a **unique name and purchase price**
    
    - It is possible for an owner to build a shop on a retail site in one of three sizes (mini store, supermarket, megastore). The cost of building each type of shop is a property of the retail site
    - Each retail site (with an owner) will charge different amounts to visitors (who aren't the owner)
    - The rent at each site is different depending on whether the site is undeveloped, contains a ministore, a supermarket or a megastore
    - Each retail site belongs to a group which can contain either two or three retail sites.
    
------

## Challenge 9 - Shopopoly pt.2

Create a `GameLedger` which will hold a record of all financial transactions in the game

1. How much money each player has

1. Who owns each location

1. Whether any location has been developed to add a ministore, a supermarket or a megastore


There will be a single instance of `GameLedger` that will be initialised at the start of each game.

------

Create the following functions that add transactions to the `GameLedger`...

- Amount transferred from the `Bank` to a `Player`. This is the starting balance for each player in the game.

- Bank paying a fee to a `Player`, e.g. when the player passes through the `Go` location.

- Rent being paid from one player to another, e.g. when a player lands on a `RetailSite` location owned by another player.

- `Player` paying the `Bank` to purchase a `Location`.

- `Player` paying the `Bank` for building a specific type of building on a `Location`. Types of building include ministore, supermarket or megastore.

------

#### Assume the following about GameLedger

- All data is stored in memory - you don’t have to keep data in a file or database system.

- In a later challenge you will be asked to create functions to query the `GameLedger`.

- You will need to know the sequence that transactions were added to `GameLedger` in order to query it accurately in some cases.

- `GameLedger` just does what it is told. It does not know whether a request is valid, e.g. it doesn’t know whether a player is purchasing a location that someone else has already purchased.

- `GameLedger` is likely to need to hold different types of transactions as Shopopoly evolves.

- Any monetary values are whole positive values of GBP sterling.

- You don’t need to worry about concurrency.

------

For `Players` create a simple type that contains a property that uniquely identify each instance of a player.

------

## Challenge 10 - Shopopoly pt.3

Enrich the `Player` data type and create the `Dice` data type.

Each `Player` data type will have two properties:

- `name` this is just a 30 character string.
- `boardLocation` the data type of this property is up to you. You must be able to use it to determine the Location of the player.


The game of Shopopoly requires the player to shake two 6 sided dice and move forward by the total number of the two dice.

The `Dice` data type contains two integer values between one and six, one for each dice.
The two values are generated randomly when an instance of `Dice` is created.

To enable a player to move a function needs to be created that adds the `Dice` to the `boardLocation` to create a new `boardLocation` for the player.
Locations are organised in a loop so when you move past the last Location, you move onto the first Location.

To detect whether a player has passed go (i.e. landed on the go square or passed over it)
a function needs to be created that accepts two boardLocations as input and returns true if the player has passed go.
Please assume the board has at least 13 Locations on it so you can’t pass through go more than once in one move.

------

#### Some tips

You don’t need to have completed Challenge 8 or Challenge 9 to complete this challenge.
The `Location` data type can just be a simple class or interface but you may need a way to detect whether a location is the location for the go square so you can work out when someone passes go.

You will probably need to create a mechanism for determining the sequence of each location on the board.

------

#### Problem breakdown

1. `Player` has name property
2. `Player` has boardLocation property
3. `Dice` has 2 integer values
4. `Dice` randomly returns value between 2 and 12
5. Create `move` function on `Player` -> add `Dice` to `boardLocation` to create new `boardLocation`
6. `boardLocation` to reset to 1 after passing 13
7. `boardLocation` of 1 to be the 'Go' square

------

## Challenge 12 - Shopopoly pt.4

Currently `GameLedger` contains a historical record of all financial records in a game of Shopopoly.
Now create functions that will report on the status of each game by interrogating the `GameLedger`.

------

Create the following new functions...

1. Calculate the balance for a player
    - This should return either the total amount of money a player has (i.e. credit) or total amount of money owed by a player (i.e. debt)
    - As before, monetary values must be greater or equal to zero
    - If the player has zero return it as a credit amount.

2. Determine the locations/buildings owned by a player
    - Only return the last building added to a location as under the rules of shopopoly properties must be built sequentially from smallest to largest
    - It should return an empty list if the player owns no locations

3. Determine the owner of a location and the rent (if any) that needs to be paid
    - If the player has built on the property, rent is only payable on the last thing built
    - If the location has no owner return something to indicate that

------

See [challenge](https://coding-challenges.jl-engineering.net/challenges/challenge-12/) for optional extra functions to be created

------

#### Tips

Like before, the GameLedger is dumb so doesn't contain logic to determine whether a transaction is valid before creating it.
(For example, a transaction could be created that allows a player to buy a location that someone else already has.

To deal with that kind of scenario only consider the last transaction when evaluating the status of a location.

------