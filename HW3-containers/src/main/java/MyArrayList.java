import java.util.*;

/**
 * Created by Elena on 18.06.2017.
 */
public final class MyArrayList<E> extends AbstractList<E> implements List<E> {
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
        return new MyListIterator<>();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size)
            return (T[]) Arrays.copyOf(this.elements, this.size, a.getClass());
        System.arraycopy(this.elements, 0, a, 0, this.size);
        if (a.length > this.size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        this.ensureCapacity(size + 1);
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
        for (Object object : c) {
            if (!contains(object))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        this.ensureCapacity(size + c.size());
        for (E element : c) {
            this.elements[size++] = element;
        }
        return true;
    }

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

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object element : c) {
            this.remove(element);
        }
        return true;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort((E[]) this.elements, 0, this.size, c);
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }

    @Override
    public E get(int index) {
        checkIndexExists(index);
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndexExists(index);

        E replaced = this.get(index);
        this.elements[index] = element;

        return replaced;
    }

    @Override
    public void add(int index, E element) {
        this.checkIndexValid(index);

        this.ensureCapacity(size + 1);
        System.arraycopy(this.elements, index, this.elements, index + 1, size - index);
        this.elements[index] = element;

        size++;
    }

    @Override
    public E remove(int index) {
        checkIndexExists(index);

        E removed = this.get(index);

        int tail = size - index - 1;
        if (tail > 0)
            System.arraycopy(this.elements, index + 1, this.elements, index, tail);
        this.elements[--size] = null;

        return removed;
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
        return new MyListIterator<>();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        this.checkIndexExists(index);
        return new MyListIterator<>(index);
    }

    private void grow(int minCapacity) {
        long newCapacity = Math.max(minCapacity, ((elements.length * 3L) / 2 + 1));
        if (newCapacity > MAX_CAPACITY) {
            newCapacity = MAX_CAPACITY;
        }

        Object[] copy = new Object[(int) newCapacity];
        System.arraycopy(this.elements, 0, copy, 0, size);
        this.elements = copy;
    }

    private void checkIndexExists(int index) {
        if (0 > index || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    private void checkIndexValid(int index) {
        if (0 > index) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    private class MyListIterator<T> implements ListIterator<T> {
        private int current;
        private int previous = -1;


        MyListIterator() {
            super();
            current = 0;
        }

        MyListIterator(int index) {
            super();
            current = index;
        }


        @Override
        public boolean hasNext() {
            return current < size() - 1;
        }

        @Override
        public T next() {
            T next = null;

            this.previous = this.current;
            if (this.hasNext()){
                next = (T) elements[this.current++];
            }

            return next;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {
        }

        @Override
        public void set(T e) {
        }

        @Override
        public void add(T e) {
        }
    }
}
