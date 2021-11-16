package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MemTracker implements Store {
    private  final List<Item> items = new ArrayList<Item>();
    private int ids = 1;

    public void init() {
    }

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        return indexOf(id) != -1 ? items.get(indexOf(id)) : null;
    }

    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public List<Item> findAllByReact(Observe<Item> observe) {
        return null;
    }

    public List<Item> findByName(String key) {
        List<Item> itemTmp = new ArrayList<Item>();
        for (Item i : items) {
            if (i.getName().equals(key)) {
                itemTmp.add(i);
            }
        }
        return itemTmp;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int idRep, Item item) {
        int index = indexOf(idRep);
        if (index != -1) {
            items.set(index, item);
                return true;
            }
        return false;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public void close() throws Exception {

    }
}