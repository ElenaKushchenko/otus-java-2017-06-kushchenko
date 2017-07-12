package ru.otus;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created by Elena on 18.06.2017.
 */
public final class MyArrayList<E> extends AbstractList<E> {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new MyListIterator<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size)
            return (T[]) Arrays.copyOf(this.elements, this.size, a.getClass());
        System.arraycopy(this.elements, 0, a, 0, this.size);
        if (a.length > this.size)
            a[size] = null;

        return a;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E e) {
        this.ensureCapacity(size + 1);
        this.elements[size++] = e;
        return true;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object))
                return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        this.ensureCapacity(size + c.size());
        for (E element : c) {
            this.elements[size++] = element;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        this.checkIndexValid(index);

        this.ensureCapacity(size + c.size());
        System.arraycopy(this.elements, index, this.elements, index + c.size(), size - index);
        int i = 0;
        for (E element : c) {
            this.elements[index + i] = element;
            i++;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element : c) {
            this.remove(element);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) this.elements, 0, this.size, c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(int index) {
        checkIndexExists(index);

        return (E) this.elements[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E set(int index, E element) {
        checkIndexExists(index);

        E replaced = this.get(index);
        this.elements[index] = element;

        return replaced;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, E element) {
        this.checkIndexValid(index);

        this.ensureCapacity(this.size + 1);
        System.arraycopy(this.elements, index, this.elements, index + 1, this.size - index);
        this.elements[index] = element;
        this.size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(int index) {
        checkIndexExists(index);

        E removed = this.get(index);
        int tail = this.size - index - 1;
        if (tail > 0)
            System.arraycopy(this.elements, index + 1, this.elements, index, tail);
        this.size--;

        return removed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (Objects.equals(o, this.elements[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (Objects.equals(o, this.elements[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        this.checkIndexExists(index);
        return new MyListIterator<>(index);
    }

    private void grow(int minCapacity) {
        long newCapacity = Math.max(minCapacity, ((this.elements.length * 3L) / 2 + 1));
        if (newCapacity > MAX_CAPACITY) {
            newCapacity = MAX_CAPACITY;
        }

        Object[] copy = new Object[(int) newCapacity];
        System.arraycopy(this.elements, 0, copy, 0, this.size);

        this.elements = copy;
    }

    private void checkIndexExists(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    private void checkIndexValid(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }


    private class MyListIterator<T> implements ListIterator<T> {
        private int current;
        private int previous = -1;


        MyListIterator() {
            super();
            this.current = 0;
        }

        MyListIterator(int index) {
            super();
            this.current = index;
            if (index > 0) {
                this.previous = index - 1;
            }
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return this.current < (size() - 1);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T next() {
            T next = null;

            if (this.hasNext()){
                this.previous = this.current;
                next = (T) elements[this.current++];
            }

            return next;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasPrevious() {
            throw new NotImplementedException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T previous() {
            throw new NotImplementedException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int nextIndex() {
            throw new NotImplementedException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int previousIndex() {
            throw new NotImplementedException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new NotImplementedException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(T e) {
            if (this.previous < 0) {
                throw new IllegalStateException();
            }
            MyArrayList.this.set(this.previous, (E) e);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void add(T e) {
            throw new NotImplementedException();
        }
    }
}
