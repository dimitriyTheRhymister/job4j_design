package ru.job4j.ood.dip;

import java.io.FileWriter;
import java.io.IOException;

public class Sample1 {
    public static class FileDataStoreBad {
        public void save(String data) {
            try (FileWriter writer = new FileWriter("data.txt")) {
                writer.write(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class UserServiceBad {
        private final FileDataStoreBad dataStoreBad;

        public UserServiceBad() {
            this.dataStoreBad = new FileDataStoreBad();
        }

        public void saveUser(String userData) {
            dataStoreBad.save(userData);
        }

        public static void main(String[] args) {
            UserServiceBad userServiceBad = new UserServiceBad();
            userServiceBad.saveUser("John Doe");
        }
    }
    /*В этом примере UserService зависит от конкретной реализации
    FileDataStore. Если мы захотим изменить хранилище данных на базу
    данных или облачное хранилище, нам придется модифицировать UserService,
    что нарушает DIP.*/

    public interface DataStore {
        void save(String data);
    }

    public static class FileDataStoreGood implements DataStore {
        @Override
        public void save(String data) {
            try (FileWriter writer = new FileWriter("data.txt")) {
                writer.write(data);
            } catch (IOException e) {
                /* Обработка исключения */
            }
        }
    }

    public static class UserServiceGood {
        private final DataStore dataStore;

        public UserServiceGood(DataStore dataStore) {
            this.dataStore = dataStore;
        }

        public void saveUser(String userData) {
            dataStore.save(userData);
        }

        public static void main(String[] args) {
            DataStore fileDataStore = new FileDataStoreGood();
            UserServiceGood userService = new UserServiceGood(fileDataStore);
            userService.saveUser("John Doe");
        }
    }
}
