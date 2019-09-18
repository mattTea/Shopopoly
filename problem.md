Problem breakdown
=================

1. Create basic classes for each of the 4 location types

1. Look at enriching location type classes with additional requirements
    - number of instances possible

1. How does a `player` interact with a `location`?

```kotlin
player.landOnOrPass(location)
```

- When called with a location...

```kotlin
fun landOnOrPass(location: Location): Int {
    bankBalance += location.feeOrReward
}
```