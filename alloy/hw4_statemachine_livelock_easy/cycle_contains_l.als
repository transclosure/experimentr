sig State { succ: set State }
sig Initial extends State {}

fact {
	-- one or more initial states
	--some Initial	
	-- (f) Livelock: c is the "cycle state," and l is the livelocked state
	some c, l: State | {
		// Can reach the cycle from an initial state without using l
		// The (Initial - l) can be replaced with Initial, because of the next constraint
		c in (Initial - l).*(succ - State -> l - l -> State)
		// Cycle doesn't contain l
		c in c.^(succ)
		// l is reachable from the cycle
		--l in c.^succ
	}
}

--run {} for 5 State
