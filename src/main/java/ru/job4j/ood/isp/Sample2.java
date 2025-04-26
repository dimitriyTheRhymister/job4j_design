package ru.job4j.ood.isp;

public class Sample2 {

    public interface Toy {
        void setPrice(double price);
        void setColor(String color);
        void move();
        void fly();
    }

    public static class Airplane implements Toy {
        public void setPrice(double price) { /* ok */
        }
        public void setColor(String color) { /* ok */
        }
        public void move() { /* Да, я могу и это */
        }
        public void fly() { /* Да, я могу и это */
        }
    }

    public static class House implements Toy {
        public void setPrice(double price) { /* ok */
        }
        public void setColor(String color) { /* ok */
        }
        public void move() { /* NotSupportedException() */
        }
        public void fly() { /* NotSupportedException() */
        }
    }
}
/*Класс, который представляет игрушечный самолёт, может реализовать этот
интерфейс и предоставить реализации всех методов. Но класс, который
представляет игрушечный дом, вынужден реализовывать все методы, хотя ему
не нужна функция fly()*/