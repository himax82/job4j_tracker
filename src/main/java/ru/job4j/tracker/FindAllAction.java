package ru.job4j.tracker;

import java.util.List;

public class FindAllAction implements UserAction {

    private final Output out;

    public FindAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Show all items ====");
        List<Item> items = tracker.findAllByReact(out::println);
        if (items.size() == 0) {
            out.println("Хранилище еще не содержит заявок");
        }
        return true;
    }
}
