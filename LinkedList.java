/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index >= size || index < 0) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		Node cur = this.first;
		for (int i = 0; i < index; i++) {
			if (cur == null) {
				return null;
			}
			cur = cur.next;
		}
		return cur; 
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		if (index == size) {
			addLast(block);
			return;
		}
		if (index == 0) {
			addFirst(block);
			return;
		}
		Node cur = this.first;
		for (int i = 0; i < index - 1; i++) {
			cur = cur.next;
		}
		Node newNode = new Node(block);
		newNode.next = cur.next;
		cur.next = newNode;
		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newNode = new Node(block);
    	if (first == null) {
        	first = newNode;
        	last = newNode;
    	} else {
        	last.next = newNode; 
        	last = newNode;
		}
    	size++;
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newNode = new Node(block);
		if(first == null) {
			first = newNode;
			last = first;
			first.next = null;
		}
		else {
			newNode.next = first;
			first = newNode;
		}
		size ++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		if (index >= size || index < 0) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		if (first == null || size == 0) {
			throw new IllegalArgumentException(
					"empty list");
		}
		Node n = first;
		for(int i = 0 ; i < index ; i ++) {
			n = n.next;
		}
		return n.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		Node node = first;
		for(int i = 0 ; i < size ; i ++) {
			if (node.block.equals(block)) {
				return i;
			}
			node = node.next;
		}
		return -1;
	}


	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		if (first == null) {
			return; 
		}
		if (node == null) {
			throw new NullPointerException("ERROR NullPointerException!");
		}
		if (node.equals(first)) {
			first = first.next;
			size--;
			if (size == 0) {
				last = null;
			}
			return;
		}
		Node cur = first;
		while (cur.next != null) {
			if (cur.next.equals(node)) {
				if (node.equals(last)) {
					last = cur;
				}
				cur.next = cur.next.next;
				size--;
				return;
			}
			cur = cur.next;
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}
		if (index == 0) {
			first = first.next;
			size--;
			if (size == 0) {
				last = null;
			}
			return;
		}
		Node current = first;
		for (int i = 0; i < index - 1; i++) {
			if (current.next != null) {
				current = current.next;
			}
			else {
				throw new NullPointerException("ERROR NullPointerException!");
			}
		}
		current.next = current.next.next;
		if (index == size - 1) {
			last = current;
		}
		size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		if(block == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		if(indexOf(block) == -1) 
			throw new IllegalArgumentException("index must be between 0 and size");
		if(first == null) return;
		Node current = first;
		if(size == 1 && block.equals(first.block)) {
			first = null;
			last = null;
			size = 0;
			return;
		}
		if (block.equals(first.block)) {
			first = first.next;
			size --;
			if(size == 1) {
				last = first;
			}
			return;
		}
		while (current.next != null) {
			if (current.next.block.equals(block) && block.equals(last.block)) {
				current.next = null;
				size --;
				last = current;
				if(size == 1) {
					first = current;
				}
				return;
			}
			if (current.next.block.equals(block)) {
				current.next = current.next.next;
				size --;
				if(size == 1) {
					last = first;
				}
				return;
			}
			current = current.next;
		}
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		String ans = "";
    	Node current = first;
    	for(int i = 0 ; i < size ; i ++) {
        	ans += current.block.toString() + " "; 
        	current = current.next; 
		}
    	return ans;
	}

	public void sortByBaseAddress() {
		for (int i = 0; i < this.size - 1; i++) {
			for (int j = 0; j < this.size - i - 1; j++) {
				Node cur = getNode(j);
				Node next = cur.next;
				if (next.block.baseAddress < cur.block.baseAddress) {
					MemoryBlock max = cur.block;
					cur.block = next.block;
					next.block = max;
				}
			}
		}
	}
}