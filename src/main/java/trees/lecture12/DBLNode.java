package trees.lecture12;

public class DBLNode {

	int val;
	public DBLNode next;
	public DBLNode prev;

	public DBLNode() {
		super();
	}

	public DBLNode(int val) {
		super();
		this.val = val;
	}

	public DBLNode(int val, DBLNode prev, DBLNode next) {
		super();
		this.val = val;
		this.next = next;
		this.prev = prev;

	}

	
	@Override
	public String toString() {
		return "DBLNode [val=" + val + ", next=" + next + ", prev=" + prev
				+ "]";
	}

}
