package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte bytePrimitiveType = 7;
        short shortPrimitiveType = 132;
        int intPrimitiveType = 1857;
        long longPrimitiveType = 7869687L;
        char charPrimitiveType = 't';
        boolean booleanPrimitiveType = true;
        float floatPrimitiveType = 43.5f;
        double doublePrimitiveType = 6879.98;
        LOG.debug("""

                        There are 8 primitive types in Java:\s
                        \t- bytePrimitiveType : {} (from -128 to 128),\s
                        \t- shortPrimitiveType : {} (from -32 768 to 32 767),\s
                        \t- intPrimitiveType : {} (from -2 147 483 648 to 2 147 483 647),\s
                        \t- longPrimitiveType : {} (from -9 223 372 036 854 775 808 to 9 223 372 036 854 775 807),\s
                        \t- charPrimitiveType : {} (from '\\u0000' = int 0 to '\\uffff' = int 65535),\s
                        \t- booleanPrimitiveType : {} (true/false),\s
                        \t- floatPrimitiveType : {} (from -3.4E + 38 to 3.4E + 38),\s
                        \t- doublePrimitiveType : {} (from -1.7E + 308 to 1.7E + 308)""",
                bytePrimitiveType,
                shortPrimitiveType,
                intPrimitiveType,
                longPrimitiveType,
                charPrimitiveType,
                booleanPrimitiveType,
                floatPrimitiveType,
                doublePrimitiveType
        );
    }
}