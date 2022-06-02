package lists;

public interface List<T> extends Iterable<T> {
	void clear();
	void add(T data);
	void add(T data, int pos);
	T remove(int pos);
	boolean isEmpty();
	boolean isFull();
	void setData(int pos, T data);
	T getData(int pos);
	int getSize();
	int find(T data);
	int findLast(T data);
}
