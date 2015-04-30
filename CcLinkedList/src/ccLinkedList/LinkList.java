package ccLinkedList;

public class LinkList {
	public ListNode head;
	
	
	public void add(ListNode newNode){
		if (head == null){
			head = newNode;
		}
		else{
			ListNode current = head;
			while (current.next != null){
				current = current.next;
			}
			current.next = newNode;
		}
	}
	
	public void printList(){
		ListNode curr = head;
		while(curr != null){
			System.out.println(curr.val);
			curr = curr.next;
		}
	}
}
