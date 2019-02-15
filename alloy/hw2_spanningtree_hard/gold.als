sig Atom {graph: set Atom, tree1 : set Atom, tree2: set Atom}

pred isUndirectedTree (r: Atom -> Atom) {
	-- symmetric
	r = ~r
	-- connected
	(Atom -> Atom) in *r
	-- no self-loops
	no iden & r
	-- minimally-connected: remove any given edge, and there should be no other path between those two nodes
	all n1, n2: Atom |
		let e = n1->n2 + n2->n1 |
			e in r implies
				e not in ^(r - e)
} 

pred spans (graph1, graph2: Atom -> Atom) {
	graph1 in graph2
	graph1.Atom + Atom.graph1 = graph2.Atom + Atom.graph2
} 

fact {
	-- both trees are distinct, valid undirected trees that span the graph
    spans [tree1, graph] and spans [tree2, graph]
    isUndirectedTree [tree1] and isUndirectedTree [tree2] 
    tree1 != tree2
}
