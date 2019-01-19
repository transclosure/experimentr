sig Node {next: set Node} 

fact isRing {
	no Node => some none
	all n: Node | 	{
		one n.next      -- each node has one next pointer
		Node in n.^next -- each node is reachable by every node
	}			
}
