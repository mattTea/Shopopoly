Shopopoly
=========

Create Shopopoly, a game similar to the board game Monopoly, but for shops.

The objective of each player is to acquire assets such as shops and then make money by charging other players a fee when they visit them.

When a player runs out of money they leave the game and the winner is the last player to be left in the game.

------

This project will eventually cover the Shopopoly challenges [8](https://coding-challenges.jl-engineering.net/challenges/challenge-8/), [9](https://coding-challenges.jl-engineering.net/challenges/challenge-9/) and [12](https://coding-challenges.jl-engineering.net/challenges/challenge-12/) (and possibly number [10](https://coding-challenges.jl-engineering.net/challenges/challenge-10/), the solution to which can currently be found separately [here](https://github.com/mattTea/Shopopoly-Challenge10)).

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

Create a GameLedger which will hold a record of all financial transactions in the game

1. How much money each player has

1. Who owns each location

1. Whether any location has been developed to add a ministore, a supermarket or a megastore


There will be a single instance of GameLedger that will be initialised at the start of each game.

------

Create the following functions that add transactions to the GameLedger...

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