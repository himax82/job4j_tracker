package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StartUITest {

        @Test
    public void whenCreateItem() {
            Output output = new ConsoleOutput();
            Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        MemTracker memTracker = new MemTracker();
            ArrayList<UserAction> actions = new ArrayList<UserAction>();
            actions.add(new CreateAction(output));
            actions.add(new ExitAction());
        new StartUI(output).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        MemTracker memTracker = new MemTracker();
        Output output = new ConsoleOutput();
        /* Добавим в tracker новую заявку */
        Item item1 = memTracker.add(new Item("First item"));
        Item item2 = memTracker.add(new Item("Second item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item2.getId()), "New item name", "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<UserAction>();
        actions.add(new EditAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, memTracker, actions);
        assertThat(memTracker.findById(item2.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        MemTracker memTracker = new MemTracker();
        Output output = new ConsoleOutput();
        Item item = memTracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<UserAction>();
        actions.add(new DeleteAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, memTracker, actions);
        assertNull(memTracker.findById(item.getId()));
    }

    @Test
    public void whenDeleteItemMockito() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        Item item = memTracker.add(new Item("Deleted item"));
        DeleteAction deleteAction = new DeleteAction(output);
        Input in = mock(Input.class);
        when(in.askInt(any(String.class))).thenReturn(0);
        when(in.askInt(any(String.class))).thenReturn(item.getId());
        deleteAction.execute(in, memTracker);
        String ln = System.lineSeparator();
        assertThat(output.toString(), is("=== Delete item ====" + ln
                + "Заявка удалена успешно." + ln));
        assertThat(memTracker.findAll().size(), is(0));
    }

    @Test
    public void whenFindAllItem() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        String ln = System.lineSeparator();
        Item item = memTracker.add(new Item("FindAll item"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<UserAction>();
        actions.add(new FindAllAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is("Menu." + ln + "0. Show all items"
                + ln + "1. Exit program."
                + ln + "=== Show all items ====" + ln + item + ln + "Menu."
                + ln + "0. Show all items" + ln + "1. Exit program." + ln));
    }

    @Test
    public void whenFindByName() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        String ln = System.lineSeparator();
        Item item1 = memTracker.add(new Item("Find name1"));
        Item item2 = memTracker.add(new Item("Find name2"));
        Input in = new StubInput(
                new String[] {"0", "Find name2", "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<UserAction>();
        actions.add(new FindByNameAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is("Menu." + ln + "0. Find items by name"
                + ln + "1. Exit program."
                + ln + "=== Find items by name ====" + ln + item2 + ln + "Menu."
                + ln + "0. Find items by name" + ln + "1. Exit program." + ln));
    }

    @Test
    public void whenFindByNameMockito() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        String ln = System.lineSeparator();
        FindByNameAction action = new FindByNameAction(output);
        Item item1 = memTracker.add(new Item("Petr"));
        Item item2 = memTracker.add(new Item("Max"));
        Input in = mock(Input.class);
        when(in.askInt(any(String.class))).thenReturn(0);
        when(in.askStr(any(String.class))).thenReturn(item2.getName());
        action.execute(in, memTracker);
        assertThat(output.toString(), is("=== Find items by name ====" + ln + item2 + ln));
    }

    @Test
    public void whenFindById() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        String ln = System.lineSeparator();
        Item item1 = memTracker.add(new Item("Find name1"));
        Item item2 = memTracker.add(new Item("Find name2"));
        int i = item1.getId();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(i), "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<UserAction>();
        actions.add(new FindByIdAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, memTracker, actions);
        assertThat(output.toString(), is("Menu." + ln + "0. Find item by id"
                + ln + "1. Exit program."
                + ln + "=== Find item by id ====" + ln + item1 + ln + "Menu."
                + ln + "0. Find item by id" + ln + "1. Exit program." + ln));
    }

    @Test
    public void whenFindByIdMockito() {
        MemTracker memTracker = new MemTracker();
        Output output = new StubOutput();
        String ln = System.lineSeparator();
        Item item1 = memTracker.add(new Item("Find name1"));
        Item item2 = memTracker.add(new Item("Find name2"));
        Item item3 = memTracker.add(new Item("Find name3"));
        FindByIdAction action = new FindByIdAction(output);
        int i = item2.getId();
        Input in = mock(Input.class);
        when(in.askInt(any(String.class))).thenReturn(0);
        when(in.askInt(any(String.class))).thenReturn(i);
        action.execute(in, memTracker);
        assertThat(output.toString(), is("=== Find item by id ====" + ln + item2 + ln));
        assertThat(memTracker.findById(i), is(item2));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"12", "0"}
        );
        MemTracker memTracker = new MemTracker();
        ArrayList<UserAction> actions = new ArrayList<UserAction>();
        actions.add(new ExitAction());
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu." + ln
                        + "0. Exit program." + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu." + ln
                        + "0. Exit program." + ln
                )
        );
    }

}