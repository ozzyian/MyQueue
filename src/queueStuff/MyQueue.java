package queueStuff;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<E> implements ALDAQueue<E>{
	
	private int totalCapacity;
	private int currentCapacity;
	private int size;
	private Node<E> first;
	private Node<E> last;
	
//	public MyQueue() { 
//		totalCapacity = 100; 
//		currentCapacity = 100; 
//		size = 0;
//	}
	
	public MyQueue(int capacity) {
		if(capacity <= 0) {
			throw new IllegalArgumentException("Fel!");
		}
		totalCapacity = capacity;
		currentCapacity = capacity;
		size = 0;
	}
	
	public String toString() {
		String start = "[";
		if(size == 0) {
			start+= "]";
		}
		for(Node<E> temp=first; temp!=null; temp = temp.next) { 

			if(temp.next == null) { 
				start+= temp.value + "]";
			}else {
				start+= temp.value + ", ";
			}
		}
		
		
		return start;
	}
	
	@Override
	public Iterator<E> iterator() {
		
		Iterator<E> it = new Iterator<E>(){
			Node<E> current = first;	
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				E var = current.value;
				current = current.next;
				return var;
			}
			
		};
		return it;
	}

	@Override
	public void add(E element) {
		if(isFull())
			throw new IllegalStateException("Fel!");
		if(element == null) {
			throw new NullPointerException("Fel!");
		}
		if(currentCapacity==0) {
			throw new IllegalStateException("Fel!");
		}
		if(isEmpty()) {
			Node<E> n = new Node<E>(element, null, null);
			currentCapacity--;
			size++;
			first = n;
			last = n;
		}else {
			Node<E> n = new Node<E>(element, last, null);
			last.next = n;
			last = n;
			currentCapacity--;
			size++;
		}
	}

	@Override
	public void addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove() {
		if(isEmpty()) {
			throw new NoSuchElementException("Fel!");
		}
		Node<E> temp = first;
		if(size == 1) {
			first = null;
			last = null;
			
		} else {
			first.next.before = null;
			first = first.next;
				
		}
		size--;
		currentCapacity++;
		return temp.value;
	}

	@Override
	public E peek() {
		if(isEmpty())
			return null;
			
		else
			return first.value;
	}

	@Override
	public void clear() {
		first = null; 
		last = null;
		size = 0; 
		currentCapacity = totalCapacity; 
		
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(currentCapacity == totalCapacity) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if(currentCapacity == 0) {
			return true;
		}
		return false;
		
	}

	@Override
	public int totalCapacity() {
		return totalCapacity;
	}

	@Override
	public int currentCapacity() {
		
		return currentCapacity;
	}

	@Override
	public int discriminate(E e) {
		System.out.println(this);
		if(e==null) {
			throw new NullPointerException("Fel!");
		}
		int found = 0;
		int count = 0;
		Iterator<E> it = iterator();
		while(it.hasNext()) {
			count ++;
			
			if(e == it.next() || e.equals(it.next())) {
				found++;
				
				
			}
		}
		
		System.out.println(count);
		return found;
//		int count= 0;
//		if(!isEmpty()) {
//			
//			if ((first.value == e || first.value.equals(e)) && size!= 1) {
//				found++;
//				count++;
//				remove();
//				add(e);
//				
//			}
//			Node<E> temp = first;
//			while(count < size) {
//				
//				System.out.println(size);
//				count++;
//				if(temp.value==e || temp.value.equals(e)) {
//					System.out.println("found");
//					found++;
//					if(temp.next != null) {
//						temp.next.before = temp.before;
//						temp.before.next = temp.next;
//						size--;
//						currentCapacity++;
//						add(e);
//					}
//				}
//				temp = temp.next;
//				
//			}
//			
//		
//		}
		
	}

	 class Node<E>{
		
		 E value;
		Node<E> before;
		Node<E> next;
		public Node(E v, Node<E> b, Node<E> n) {
			value = v; 
			before = b; 
			next = n;
		}


	 }

}
