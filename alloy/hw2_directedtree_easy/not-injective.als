sig Atom {r:set Atom}

fact isDirectedTree {
	-- acyclic:
	--no iden & ^r
	-- injective:
	not (r.~r in iden)
	-- connected:
	--(Atom -> Atom) in *(r+~r)
}
