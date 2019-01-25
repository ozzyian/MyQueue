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
	
	
	public MyQueue(int capacity) {
		if(capacity <= 0) {
			throw new IllegalArgumentException("Fel!");
		}
		this.first =  new Node<E>(null, null, null);
		this.last =  new Node<E>(null, first, null);
		first.next = last;
		totalCapacity = capacity;
		currentCapacity = capacity;
		size = 0;
	}
	
	
	public String toString() {
		String start = "[";
		if(size == 0) {
			start+= "]";
		}
		for(Node<E> temp=first.next; temp!=last; temp = temp.next) { 

			if(temp.next == last) { 
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
	
			Node<E> current = first.next;
			@Override
			public boolean hasNext() {
				
				return current!=last ;
			}

			@Override
			public E next() {

				
				if(!hasNext()) {
					throw new NoSuchElementException("Fel!");
				}
				E value = current.value;
				current = current.next;
				return value;
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
			Node<E> n = new Node<E>(element, first, last);
			currentCapacity--;
			size++;
			first.next = n;
			last.before = n;
		}else {
			Node<E> n = new Node<E>(element, last.before, last);
			last.before.next = n;
			last.before = n;
			currentCapacity--;
			size++;
		}
	}

	@Override
	public void addAll(Collection<? extends E> c) {
		if(c.isEmpty()) {
			throw new NullPointerException("Fel!");
		}
		
		for(E value : c) {
			add(value);
		}
		
	}

	@Override
	public E remove() {
		if(isEmpty()) {
			throw new NoSuchElementException("Fel!");
		}
		Node<E> temp = first.next;
		if(size == 1) {
			first.next = last;
			last.before = first;
			
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
			return first.next.value;
	}

	@Override
	public void clear() {
		first =  new Node<E>(null, null, null);
		last =  new Node<E>(null, first, null);
		first.next = last;
	
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
		
		int found  = 0;
		if(e==null) {
			throw new NullPointerException("Fel!");
		}
		Node<E> tempBefore;
		Node<E> tempNext;

		if(!isEmpty()) {
			for(Node<E> temp = first.next; temp!=last; temp = temp.next) {
				if(temp.value == e || temp.value.equals(e)) {
					found++;
					tempBefore = temp.before;
					tempNext = temp.next;
					tempBefore.next = tempNext;
					tempNext.before = tempBefore;
					size--;
					currentCapacity++;
				}
			}
			if(found>0) {
				for(int i=0; i<found; i++) {

					add(e);
					
				}
			}
			
			
		}
			
		return found;

		
	}

	 private static class Node<E>{
		
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
