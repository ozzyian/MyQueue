package queueStuff;

import java.util.Collection;
import java.util.Iterator;

public class MyQueue<E> implements ALDAQueue<E>{
	
	private int totalCapacity;
	private int currentCapacity;
	private Node<E> first;
	private Node<E> last;
	
	public MyQueue(int capacity) {
		totalCapacity = capacity;
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(E element) {
		Node<E> n = new Node<E>();
		n.value = element;
		if(first==null) {
			first = n;
		}
		
	}

	@Override
	public void addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		first = new Node<E>();
		last = new Node<E>();
		first.next = last;
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return 0;
	}

	private class Node<E>{
		
		E value;
		Node<E> before;
		Node<E> next;
		
	}

}
