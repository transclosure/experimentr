sig Node {
	edge: set Node
}
fact hasroot { one n: Node | n not in Node.edge }
fact loneparent { all n: Node | lone n.~edge }
fact acyclic { all n: Node | n not in n.^edge }

