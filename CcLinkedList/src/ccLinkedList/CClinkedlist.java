package ccLinkedList;


import java.util.HashMap;
import java.util.Map;

public class CClinkedlist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CClinkedlist app = new CClinkedlist();
		ListNode node1 = new ListNode(3);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(0);
		ListNode node4 = new ListNode(-4);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node2;
		app.cycleStart1(node1);
	}
	
	public void printFrom(ListNode start){
		while(start != null){
			System.out.println(start.val);
			start = start.next;
		}
	}
	
	public ListNode reverseList(ListNode node) {
        ListNode ret = null, cur = null;
        if(node == null || node.next == null){
            return node;
        }
        ret = reverseList(node.next);
        cur = ret;
        while(cur.next != null){
            cur = cur.next;
        }
        cur.next = node;
        node.next = null;
        return ret;
}
	
	public LinkList linkListBuilder(int[] A){
		LinkList result = new LinkList();
		ListNode temp;
		for(int i : A){
			temp = new ListNode(i);
			result.add(temp);
		}
		return result;
	}
	
	public void removeDupUnsortedLinkedList(ListNode head){
		Map<Integer,Integer> map = new HashMap<>();
		ListNode previous = null;
		while (head != null){
			if (map.containsKey(head.val)){
				//remove current node
				previous.next = head.next;	
			}
			else{
				map.put(head.val, 1);
				previous = head;
			}
			head = head.next;
		}
	}
	
	public void removeDupWithoutMem(ListNode head){
		ListNode running = null;
		while (head != null){
			int temp = head.val;
			running = head.next;
			ListNode previous = head;
			while (running != null){
				if (running.val == temp){
					previous.next = running.next;
				}
				else{
					previous = running;
				}
				running = running.next;				
			}
			head = head.next;
		}
	}
	
	//use two pointers, this requires exactly O(n) runtime;
	public void findNthToLast(ListNode head, int n){
		ListNode p1 = head;
		ListNode p2 = head;
		int i = 0;
		while (p2.next != null && i < n){
			p2 = p2.next;
			i++;
		}
		if (i < n){
			System.out.println("n is too large");
		}
		while (p2.next != null){
			p1 = p1.next;
			p2 = p2.next;
		}
		System.out.println(p1.val);
	}
	
	public void removeWithOnlyAccess(){
		
	}
	
	public void deleteNodeWithSingleAccess(ListNode n){
//   	wrong answer by me
//		while(n != null && n.next != null){
//			n.val = n.next.val;
//			n = n.next;
//		}
//   	need to use next well
//		impossible to delete if it's the last node
		if (n == null || n.next == null) {
			return; // Failure
			}
			ListNode next = n.next;
			n.val = next.val;
			n.next = next.next;
	}
	
	//using l2 to store result doesn't handle the condition where l2 is null
	public void addTwoListLittleEndian(ListNode l1, ListNode l2){
		int carry = 0, val1, val2;
		ListNode temp2 = null;
		while(l1 != null || l2 != null){
			val1 = l1 == null? 0:l1.val;
			val2 = l2 == null? 0:l2.val;
			if (l2 == null){
				temp2.next = new ListNode(0);
				l2 = temp2.next;
			}
			
			l2.val = val1 + val2 + carry;	
			carry = 0;
			if (l2.val >= 10){
				l2.val = l2.val % 10;
				carry = 1;
			}
			
			temp2 = l2;			
			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;
		}
		if (carry == 1) temp2.next = new ListNode(1);
	}
	
	//answer
//	LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2,
//			2  int carry) {
//			3 if (l1 == null && l2 == null) {
//			4  return null;
//			5 }
//			6 LinkedListNode result = new LinkedListNode(carry, null, null);
//			7 int value = carry;
//			8 if (l1 != null) {
//			9  value += l1.data;
//			10 }
//			11 if (l2 != null) {
//			12  value += l2.data;
//			13 }
//			14 result.data = value % 10;
//			15 LinkedListNode more = addLists(l1 == null ? null : l1.next,
//			16   l2 == null ? null : l2.next,
//			17   value > 10 ? 1 : 1);
//			18 result.setNext(more);
//			19 return result;
//			20 }
	//modifying list
	public void cycleStart1(ListNode head){
		while (head != null && head.next != head){
			ListNode next = head.next;
			head.next = head;
			head = next;
		}
		if (head == null){
			System.out.println("no cycle");
		}
		else{
			System.out.println("start point is "+head.val);
		}
	}
	
	//rabbit and turtle
	public void cycleStart2(ListNode head){
		ListNode p1 = head;
		ListNode p2 = head;
		while(p2.next != null && p2.next.next != null){
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) break;
		}
		if (p2.next == null || p2.next.next == null){
			System.out.println("no cycle");
			return;
		}
		p1 = head;
		while(p1 != p2){
			p1 = p1.next;
			p2 = p2.next;
		}
		System.out.println(p2.val);
		return;
	}
}
