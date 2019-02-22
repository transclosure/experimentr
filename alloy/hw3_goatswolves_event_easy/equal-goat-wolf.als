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
one sig Pre extends State {}
one sig Post extends State {}

pred noEating [animals: set Animal] {
    some Goat & animals implies #(Goat & animals) >= #(Wolf & animals)
}

one sig Event {
    toMove: set Animal
}

fact event {
	-- no floating
	all a:Animal | a in Pre.near or a in Pre.far
	no a:Animal | a in Pre.near and a in Pre.far
	all a:Animal | a in Post.near or a in Post.far
	no a:Animal | a in Post.near and a in Post.far
	-- no eating
    noEating[Pre.near]
    noEating[Pre.far]
	noEating[Post.near]
    noEating[Post.far]
	-- valid transition
	some Event.toMove
	#(Event.toMove) <= 2
	Pre.boat = Near implies {
        Event.toMove in Pre.near
        Post.near = Pre.near - Event.toMove
        Post.far = Pre.far + Event.toMove
        Post.boat = Far
    } else {
        Event.toMove in Pre.far
        Post.near = Pre.near + Event.toMove
        Post.far = Pre.far - Event.toMove
        Post.boat = Near
    }
    #Goat = #Wolf
}
--run {} for exactly 2 State, exactly 1 Event, exactly 3 Goat, exactly 3 Wolf, 4 Int
