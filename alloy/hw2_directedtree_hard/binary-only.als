sig Atom {r:set Atom}

fact isDirectedTree {
	-- acyclic:
	no iden & ^r
	-- injective:
	r.~r in iden
	-- connected:
	(Atom -> Atom) in *(r+~r)
	-- binary OC
	no disj x,y,z:Atom | some a:Atom | (x+y+z) in a.r
}
