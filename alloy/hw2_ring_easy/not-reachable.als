sig Node {next: set Node} 

fact isRing {
	-- each node has one next pointer
	--all n: Node | one n.next
	-- each node is reachable by every node
	not (all n: Node | Node in n.^next)
}
