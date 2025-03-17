package com.senne.oneiros.tools;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ByteWriter implements Collection<Byte> {

    private static final int DEFAULT_CAPACITY = 100;
    private static final int INCREMENT = 20;
    transient Byte[] elementData;
    private int size;

    public ByteWriter() {
        elementData = new Byte[DEFAULT_CAPACITY];
    }

    public ByteWriter(Collection<? extends Byte> collection) {
        elementData = new Byte[DEFAULT_CAPACITY];
        addAll(collection);
    }

    public ByteWriter(byte[] collection) {
        elementData = new Byte[DEFAULT_CAPACITY];
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.stream(elementData).anyMatch(o::equals);
    }

    @Override
    public @NotNull Iterator<Byte> iterator() {
        Byte[] bytes = Arrays.copyOf(elementData, size);

        return new Iterator<>() {

            private int index = 0;
            private Byte[] data = bytes;

            @Override
            public boolean hasNext() {
                return index < size - 1;
            }

            @Override
            public Byte next() {
                return data[index++];
            }
        };
    }

    @Override
    public @NotNull Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    public byte[] toByteArray() {
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = elementData[i];
        }
        return bytes;
    }

    @Override
    public @NotNull <T> T[] toArray(@NotNull T[] ts) {
        if (ts.length < this.size) {
            return (T[]) Arrays.copyOf(this.elementData, this.size, ts.getClass());
        } else {
            System.arraycopy(this.elementData, 0, ts, 0, this.size);
            if (ts.length > this.size) {
                ts[this.size] = null;
            }

            return ts;
        }
    }

    @Override
    public boolean add(Byte aByte) {
        if (size == elementData.length) elementData = Arrays.copyOf(elementData, elementData.length + INCREMENT);
        elementData[size++] = aByte;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(o)) found = true;
            if (found) elementData[i] = elementData[i + 1];
        }
        if (found) size--;

        if (size < elementData.length - INCREMENT) elementData = Arrays.copyOf(elementData, elementData.length - INCREMENT);

        return true;
    }

    public boolean remove(int index) {
        for (int i = index; i < size; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size] = null;
        size--;

        if (size < elementData.length - INCREMENT) elementData = Arrays.copyOf(elementData, elementData.length - INCREMENT);

        return true;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> collection) {
        return collection.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Byte> collection) {
        for (Byte b : collection) {
            add(b);
        }
        return true;
    }

    public boolean addAll(@NotNull Byte[] bytes) {
        for (Byte b : bytes) {
            add(b);
        }
        return true;
    }

    public boolean addAll(@NotNull byte[] bytes) {
        for (byte b : bytes) {
            add(b);
        }
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> collection) {
        collection.forEach(this::remove);
        return true;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> collection) {
        for (int i = 0; i < size; i++) {
            if (collection.contains(elementData[i])) remove(i);
        }
        return true;
    }

    public byte[] getFirst(int amount) {
        Byte[] bytes = Arrays.copyOf(elementData, amount);
        byte[] result = new byte[amount];
        for (int i = 0; i < amount; i++) {
            result[i] = bytes[i];
        }

        for (int i = 0; i < size - amount; i++) {
            elementData[i] = elementData[i + amount];
        }
        size -= amount;

        return result;
    }

    @Override
    public void clear() {
        elementData = new Byte[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || !(o instanceof ByteWriter)) return false;
        if (((ByteWriter) o).size() != size) return false;

        for (int i = 0; i < size; i++) {
            if (!elementData[i].equals(((ByteWriter) o).get(i))) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        Object[] es = this.elementData;
        int hashCode = 1;
        for(int i = 0; i < size; ++i) {
            Object e = es[i];
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

    private Byte get(int i) {
        return elementData[i];
    }

    public void add(int i, Byte aByte) {
        if (i >= size) throw new IndexOutOfBoundsException();
        elementData[i] = aByte;
    }
}
