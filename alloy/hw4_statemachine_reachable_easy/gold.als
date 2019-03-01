sig State { succ: set State }
sig Initial extends State {}

fact {
	-- one or more initial states
	some Initial	
	-- (d) Reachable: all non-initial states are reachable from some initial state
	all s': State - Initial | some s: Initial | s->s' in ^succ
}

--run {} for 5 State
