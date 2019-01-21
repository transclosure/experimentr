sig Atom {r: set Atom}

fact isUndirectedTree {
	-- symmetric
	r = ~r
	-- connected
	-- (Atom -> Atom) in *r
	-- no self-loops
	no iden & r
	-- minimally-connected: remove any given edge, and there should be no other path between those two nodes
	all n1, n2: Atom |
		let e = n1->n2 + n2->n1 |
			e in r implies e not in ^(r - e)
}
