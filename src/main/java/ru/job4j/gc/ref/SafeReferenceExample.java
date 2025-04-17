package ru.job4j.gc.ref;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class SafeReferenceExample {

    public static void main(String[] args) {

        byte[] largeData = new byte[1024 * 1024 * 100];
        System.out.println("Объект создан");
        largeData[0] = 1;

        SoftReference<byte[]> softRef = new SoftReference<>(largeData);
        WeakReference<byte[]> weakRef = new WeakReference<>(largeData);

        largeData = null;
        System.out.println("Сильная ссылка удалена");
        System.out.println(Arrays.toString(largeData));

        byte[] strongSoft = softRef.get();
        byte[] strongWeak = weakRef.get();

        if (strongSoft != null) {
            System.out.println("SoftReference: данные в памяти (размер: " + strongSoft.length + ")");
            int value = strongSoft[0];
            System.out.println(value);
        } else {
            System.out.println("SoftReference: данные удалены сборщиком мусора");
        }

        if (strongWeak != null) {
            System.out.println("WeakReference: данные в памяти (размер: " + strongWeak.length + ")");
            int value = strongWeak[0];
            System.out.println(value);
        } else {
            System.out.println("WeakReference: данные удалены сборщиком мусора");
        }

        System.gc();
        System.out.println("Вызов System.gc()");

        strongSoft = softRef.get();
        strongWeak = weakRef.get();

        if (strongSoft != null) {
            System.out.println("После GC, SoftReference: данные есть");
        } else {
            System.out.println("После GC, SoftReference: данные удалены");
        }

        if (strongWeak != null) {
            System.out.println("После GC, WeakReference: данные есть");
        } else {
            System.out.println("После GC, WeakReference: данные удалены");
        }
    }
}
