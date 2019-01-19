sig Atom {r:set Atom}

fact isDirectedTree {
	one Node => some none
	-- acyclic:
	no iden & ^r
	-- injective:
	r.~r in iden
	-- connected:
	(Atom -> Atom) in *(r+~r)
}
