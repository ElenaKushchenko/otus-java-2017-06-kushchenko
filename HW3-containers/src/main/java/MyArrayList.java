import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Created by Elena on 18.06.2017.
 */
public final class MyArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private Object[] elements;
    private int size;


    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("Illegal array size: " + initialSize);
        }
        this.elements = new Object[initialSize];
    }

    public MyArrayList(Collection<? extends E> c) {
        this.elements = c.toArray();
        this.size = this.elements.length;
    }


    public void trimToSize() {
        if (this.size < this.elements.length) {
            this.elements = this.isEmpty() ? new Object[0] : Arrays.copyOf(this.elements, this.size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > this.elements.length) {
            this.grow(minCapacity);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        throw new NotImplementedException();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        throw new NotImplementedException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new NotImplementedException();
    }

    @Override
    public boolean add(E e) {
        this.growIfNeeded(size + 1);
        this.elements[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(o, this.elements)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new NotImplementedException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new NotImplementedException();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        throw new NotImplementedException();
    }

    @Override
    public void add(int index, E element) {
        throw new NotImplementedException();
    }

    @Override
    public E remove(int index) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (Objects.equals(o, elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new NotImplementedException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new NotImplementedException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }

    private void growIfNeeded(int minCapacity) {
        if (elements.length < minCapacity) {
            this.grow(Math.max(minCapacity, (elements.length * 3) / 2 + 1));
        }
    }

    private void grow(int capacity) {
        Object[] copy = new Object[capacity];
        System.arraycopy(this.elements, 0, copy, 0, size);
        this.elements = copy;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }
}
