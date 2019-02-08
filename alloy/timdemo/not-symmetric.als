sig Person {partner:set Person}

fact {
	-- everybody has a partner
	--all p:Person | some p.partner
	-- partnership is symmetric
	not (partner = ~partner)
	-- no disj x,y,z | x->y->z.
	--no disj x,y,z:Person | x.partner = y and y.partner = z
}
