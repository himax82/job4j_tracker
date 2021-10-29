package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HbmTrackerTest {

    @Test
    public void whenCreateAndFindById() {
        Item item = new Item("Item1");
        Store store = new HbmTracker();
        store.add(item);
        assertThat(store.findById(item.getId()).getName(), is("Item1"));
    }

    @Test
    public void whenReplaceItemAndFindById() {
        Item item1 = new Item("Item1");
        Store store = new HbmTracker();
        int id = store.add(item1).getId();
        Item item2 = new Item(id, "Item2");
        store.replace(id, item2);
        assertThat(store.findById(id).getName(), is("Item2"));
    }

    @Test
    public void whenDeleteItem() {
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        Store store = new HbmTracker();
        int id1 = store.add(item1).getId();
        int id2 = store.add(item2).getId();
        assertThat(store.delete(id1), is(true));
        assertThat(store.delete(id2), is(true));
    }

    @Test
    public void whenFindAllItem() {
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        Store store = new HbmTracker();
        store.add(item1).getId();
        store.add(item2).getId();
        assertThat(store.findAll().size(), is(2));
    }

    @Test
    public void whenFindItemByName() {
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        Store store = new HbmTracker();
        store.add(item1);
        store.add(item2);
        Item itemFind = store.findByName(item2.getName()).get(0);
        assertThat(itemFind.getName(), is(item2.getName()));
    }
}