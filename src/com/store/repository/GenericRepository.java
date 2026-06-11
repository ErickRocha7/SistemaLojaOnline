package com.store.repository;

import com.store.model.Identifiable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericRepository<T extends Identifiable> {
    private final Map<String, T> map = new HashMap<>();
    private final List<T> list = new ArrayList<>();

    public void add(T item) {
        map.put(item.getId(), item);
        list.add(item);
    }

    public T get(String id) {
        return map.get(id);
    }

    public T remove(String id) {
        T removed = map.remove(id);
        if (removed != null) {
            list.remove(removed);
        }
        return removed;
    }

    public List<T> getAll() {
        return new ArrayList<>(list);
    }

    public boolean contains(String id) {
        return map.containsKey(id);
    }
}