abstract sig Animal {}
sig Goat extends Animal {}
sig Wolf extends Animal {}

abstract sig Position {}
one sig Near extends Position {}
one sig Far extends Position {}

abstract sig State {
    near: set Animal,
    far: set Animal,
    boat: Position
}
one sig Initial extends State {}

pred noEating [animals: set Animal] {
    some Goat & animals implies #(Goat & animals) >= #(Wolf & animals)
}

fact initialState {
    Initial.near = Animal
    no Initial.far
    Initial.boat = Near
	noEating[Initial.near]
    noEating[Initial.far]
}
--run {} for exactly 1 State, exactly 3 Goat, exactly 3 Wolf, 4 Int
