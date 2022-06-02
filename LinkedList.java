//Lucas Pego de Souza
//Rafael Gilberto Kampa
package lists;

import java.util.Iterator;
import java.util.Objects;

public class LinkedList<T> implements List<T> {

	private Node<T> base = null;
	private Node<T> top = null;
	private int size = 0;
	
	@Override
	public void add(T data) {
		var node = new Node<T>(data);
		if (isEmpty()) {
			this.base = node;
		} else {
			node.previous = this.top;
			top.next = node;
		}
		this.top = node;
		this.size = this.size + 1;
		
	}
	@Override
	public void add(T data, int pos) {
		Objects.checkIndex(pos, this.getSize()+1);
		
		//Adicionar ao fim
		if (pos == this.getSize()) {
			this.add(data);
			return;
		}
		
		var node = new Node<T>(data);
		var next = this.getNode(pos);
		var previous = next.previous;
		
		node.previous = previous;
		node.next = next;
		
		//Se for adicionar o primeiro dado
		if (previous == null) {
			this.base = node;
		} else {
			previous.next = node;
		}
		
		next.previous = node;
		this.size = this.size + 1;
		
	}
	
	private T removeNode(Node<T> node) {
		T data = node.data;
		var previous = node.previous;
		var next = node.next;
		
		if (previous == null) {
			this.base = next;
		} else {
			previous.next = next;
		}
		
		if (next == null) {
			top = previous;
		} else {
			next.previous = previous;
		}
		
		this.size = this.size - 1;
		return data;
	}
	
	@Override
	public T remove(int pos) {
		return removeNode(getNode(pos));
	}
	
	@Override
	public void clear() {
		this.base = null;
		this.top = null;
		this.size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return this.base == null;
	}
	
	@Override
	public boolean isFull() {
		return false;
	}
	
	@Override
	public void setData(int pos, T data) {
		this.getNode(pos).data = data;
	}
	
	@Override
	public T getData(int pos) {
		return this.getNode(pos).data;
	}
	
	@Override
	public int getSize() {
		return this.size;
	}
	
	@Override
	public int find(T data) {
		if (isEmpty()) { 
			return -1;
		}
		var atual = this.base;
		int pos = 0;
		
		while (atual != null) {
			if (Objects.equals(atual.data, data)) {
				return pos;
			}
			pos = pos + 1;
			atual = atual.next;
		}
		return -1;
	}
	
	@Override
	public int findLast(T data) {
		if (isEmpty()) { 
			return -1;
		}
		var atual = this.top;
		int pos = this.size - 1;
		
		while (atual != null) {
			if (Objects.equals(atual.data, data)) {
				return pos;
			}
			pos = pos - 1;
			atual = atual.previous;
		}
		return -1;
	}
	
	private Node<T> getNode(int pos) {
		Objects.checkIndex(pos, this.size);
		int half = this.size / 2;
		
		if (pos <= half) { // Itera do início pro meio
			Node<T> atual = this.base;
			for (int i = 0; i <= pos; i++) {
				atual = atual.next;
			}
			return atual;
		}
		// Itera do final pro meio
		Node<T> atual = this.top;
		for (int i = this.size - 1; i != pos; i--) {
			atual = atual.previous;
		}
		return atual;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<T> {
		private Node<T> atual = null;
		
		@Override
		public boolean hasNext() {
			return !isEmpty() && atual != top;
		}
		
		@Override
		public T next() {
			atual = (atual == null ? base: atual.next);
			return atual.data;
		}
		
		@Override
		public void remove() {
			LinkedList.this.removeNode(atual);
			atual = atual.previous;
		}
	}
}
