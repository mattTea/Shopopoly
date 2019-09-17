Shopopoly
=========

Create Shopopoly, a game similar to the board game Monopoly, but for shops.

The objective of each player is to acquire assets such as shops and then make money by charging other players a fee when they visit them.

When a player runs out of money they leave the game and the winner is the last player to be left in the game.

------

This project will eventually cover the Shopopoly challenges [8](https://coding-challenges.jl-engineering.net/challenges/challenge-8/), [9](https://coding-challenges.jl-engineering.net/challenges/challenge-9/) and [12](https://coding-challenges.jl-engineering.net/challenges/challenge-12/) (and possibly number [10](https://coding-challenges.jl-engineering.net/challenges/challenge-10/), the solution to which can currently be found separately [here](https://github.com/mattTea/Shopopoly-Challenge10).

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