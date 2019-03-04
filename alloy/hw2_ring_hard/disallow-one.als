sig Node {next: set Node} 

fact isRing {
	-- each node has one next pointer
	all n: Node | one n.next
	-- each node is reachable by every node
	all n: Node | Node in n.^next 
	-- disallow one OC
	not (one Node) 
}
