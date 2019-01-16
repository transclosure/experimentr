sig Atom {r:set Atom}

fact isDirectedTree {
	-- acyclic:
	no iden & ^r
	-- injective:
	r.~r in iden
	-- connected:
	--(Atom -> Atom) in *(r+~r)
}

