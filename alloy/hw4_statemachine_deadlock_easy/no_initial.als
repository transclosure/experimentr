sig State { succ: set State }
sig Initial extends State {}

fact {
	-- one or more initial states
	not (some Initial)	
	-- (e) Deadlock: there is some state that is reachable from the initial state (or is the initial state) that has no successors
	--some s': State | some s: Initial | (s->s' in *succ) and no s'.succ
}

--run {} for 5 State
