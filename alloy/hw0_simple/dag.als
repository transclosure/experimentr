sig Node {
	edge: set Node
}
fact acyclic { all n: Node | n not in n.^edge }
