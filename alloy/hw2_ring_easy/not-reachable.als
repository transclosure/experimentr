sig Node {next: set Node} 

fact isRing { all n: Node | {
	--one n.next      -- each node has one next pointer
	not (Node in n.^next) -- each node is reachable by every node
}}
