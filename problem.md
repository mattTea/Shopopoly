Problem breakdown
=================

1. Create basic classes for each of the 4 location types

2. Enrich location type classes with additional requirements
    - ~~number of instances possible?~~

3. All location types as sub-types of Location?

4. How does a `player` interact with a `location`?

    ```kotlin
    player.landOnOrPass(location)
    ```

    - When called with a location...

    ```kotlin
    fun landOnOrPass(location: Location): Int {
        bankBalance += location.feeOrReward
    }
    ```

5. Challenge 10 refactor
    - Create an enum class representing the complete board, such as...
    
    ```kotlin
    enum class Board {
        GO(1),
        FREE_PARKING(7),
        FULFILMENT_SITE(2),
        RETAIL_SITE(3),
        ...
    }
    ``` 

6. Amend tests to test behaviour (not state) when Player class is introduced