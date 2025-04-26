package ru.job4j.ood.isp;

public class Sample1 {

    interface ISmartDevice {
        void print();
        void fax();
        void scan();
    }

    static class  AllInOnePrinter implements ISmartDevice {
        public void print() { /* Да, я могу печатать. */
        }
        public void fax() { /* Да, я могу и это */
        }
        public void scan() { /* Да, я могу и это */
        }
    }

    static class EconomicPrinter implements ISmartDevice {
        public void print() { /* Да, я могу печатать. */
        }
        public void fax() { /* NotSupportedException() */
        }
        public void scan() { /* NotSupportedException() */
        }
    }
}
/*Этот интерфейс описывает умное устройство, которое может печатать,
отправлять факс и сканировать. Класс AllInOnePrinter реализует этот интерфейс.
Но если нужно обработать простое устройство (класс EconomicPrinter),
которое может только печатать, то приходится реализовывать весь интерфейс*/
