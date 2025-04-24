package ru.job4j.ood.lsp;

public class DataStorage {

    public static class Database {
        public void save(String data) {
        }
    }

    public static class FileStorage extends Database {
        @Override
        public void save(String data) {
            if (data.length() > 100) {
                System.out.println("Data is too large");
            }
        }
    }

    public static void main(String[] args) {
        FileStorage fileStorage = new FileStorage();
        String data = """
                В этом примере, класс FileStorage наследует от класса Database,
                но нарушает принцип LSP, поскольку добавляет дополнительное ограничение
                на размер данных, что не соответствует поведению базового класса.""";
        System.out.println(data.length());
        fileStorage.save(data);
    }
}
/*В этом примере, класс FileStorage наследует от класса Database,
но нарушает принцип LSP, поскольку добавляет дополнительное ограничение
на размер данных, что не соответствует поведению базового класса.*/
