package ru.job4j.ood.lsp;

public class BirdAndFlyingBird {

    public abstract static class Bird {
        public abstract void fly();
    }

    public static class Eagle extends Bird {
        @Override
        public void fly() {
            System.out.println("Eagle is flying");
        }
    }

    public static class Penguin extends Bird {
        @Override
        public void fly() {
            throw new UnsupportedOperationException("Penguin can't fly");
        }
    }

    public static void main(String[] args) {
        Eagle eagle = new Eagle();
        Penguin penguin = new Penguin();
        eagle.fly();
        penguin.fly();
    }
}
/*В этом примере, класс Penguin наследует от класса Bird, но нарушает
принцип LSP, поскольку пингвин не может летать и выбрасывает
исключение при вызове метода fly().*/