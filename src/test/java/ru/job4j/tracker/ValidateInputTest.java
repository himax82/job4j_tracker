package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"3"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(3));
    }

    @Test
    public void whenManyValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1", "3"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected1 = input.askInt("Enter menu:");
        int selected2 = input.askInt("Enter menu:");
        assertThat(selected2, is(3));
    }

    @Test
    public void whenInvalidMinusInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-5"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-5));
    }
}