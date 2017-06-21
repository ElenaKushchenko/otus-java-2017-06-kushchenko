import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by Elena on 18.06.2017.
 */
public final class MyArrayList<E> extends AbstractList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size;


    public MyArrayList() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Illegal list size: " + initialSize);
        }
        this.data = (E[]) new Object[initialSize];
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        throw new NotImplementedException();
    }

    public Iterator<E> iterator() {
        throw new NotImplementedException();
    }

    public Object[] toArray() {
        return this.data;   //ToDo;
    }

    public <T> T[] toArray(T[] a) {
        throw new NotImplementedException();
    }

    public boolean add(E e) {
        //ToDo
        return true;
    }

    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public void clear() {
        throw new NotImplementedException();
    }

    public E get(int index) {
        throw new NotImplementedException();
    }

    public E set(int index, E element) {
        throw new NotImplementedException();
    }

    public void add(int index, E element) {
        throw new NotImplementedException();
    }

    public E remove(int index) {
        throw new NotImplementedException();
    }

    public int indexOf(Object o) {
        throw new NotImplementedException();
    }

    public int lastIndexOf(Object o) {
        throw new NotImplementedException();
    }

    public ListIterator<E> listIterator() {
        throw new NotImplementedException();
    }

    public ListIterator<E> listIterator(int index) {
        throw new NotImplementedException();
    }

    public List<E> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }
}
