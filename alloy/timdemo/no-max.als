sig Person {partner:set Person}

fact {
	-- everybody has a partner
	--all p:Person | some p.partner
	-- partnership is symmetric
	--partner = ~partner
	-- no disj x,y,z | x->y->z.
	not (all disj x,y,z:Person | (x->y) in partner implies (y->z) not in partner)
}
