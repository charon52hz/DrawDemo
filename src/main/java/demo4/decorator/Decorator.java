package demo4.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Decorator implements Output, Import {

    private Output output;
    private Import input;

    public Decorator(Output output) {
        this.output = output;
    }

    public Decorator(Import input) {
        this.input = input;
    }

    @Override
    public void output(String path) {
        output.output(path);
    }

    @Override
    public void input(String path) {
        input.input(path);
    }
}
