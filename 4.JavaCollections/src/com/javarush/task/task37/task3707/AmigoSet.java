package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>((int) Math.max(16, (int) (collection.size() / .75f) + 1));
        for (E e : collection) {
            map.put(e, PRESENT);
        }
    }

    @Override
    public boolean add(E e) {
        Object result = map.put(e, PRESENT);
        if (result == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public AmigoSet<E> clone() throws InternalError {
        try {
            AmigoSet<E> copy = new AmigoSet<>();
            copy.map = (HashMap<E, Object>) this.map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError(e.getMessage());
        }
    }

    private void writeObject(ObjectOutputStream out) {
        try {
            out.defaultWriteObject();
            out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
            out.writeInt(map.size());
            for (E e : map.keySet()) {
                out.writeObject(e);
            }
        } catch (IOException e) {
        }
    }

    private void readObject(ObjectInputStream in) {
        try {
            in.defaultReadObject();
            int capacity = in.readInt();
            float factor = in.readFloat();
            int size = in.readInt();
            map = new HashMap<>(capacity, factor);
            for (int i = 0; i < size; i++) {
                map.put((E)in.readObject(), PRESENT);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
