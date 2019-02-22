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
one sig Final extends State {}

pred noEating [animals: set Animal] {
    some Goat & animals implies #(Goat & animals) >= #(Wolf & animals)
}

fact finalState {
    Final.far = Animal
	no Final.near
	Final.boat = Far
	noEating[Final.near]
    noEating[Final.far]
}
--run {} for exactly 1 State, exactly 3 Goat, exactly 3 Wolf, 4 Int
