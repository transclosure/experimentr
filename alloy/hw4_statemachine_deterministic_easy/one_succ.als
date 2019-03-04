sig State { succ: set State }
sig Initial extends State {}

fact {
	-- one or more initial states
	--some Initial	
	-- (a) Deterministic: each state has at most one successor
	all s: State | lone s.succ
	-- OC
	all s: State | some s.succ
}

--run {} for 5 State