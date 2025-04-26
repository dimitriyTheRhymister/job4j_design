package ru.job4j.ood.isp;

public class Sample3 {

    public interface Calculator {
        void addition();
        void subtraction();
        void multiplication();
        void division();
        void exponentiation();
        void square();
        void exponent();
        void sine();
        void arxSine();
        void cosine();
        void arcCosine();
        void tangent();
        /*....................*/
    }

    public static class EngineeringCalculator implements Calculator {

        @Override
        public void addition() { /* Да, я хочу это */
        }

        @Override
        public void subtraction() { /* Да, я хочу это */
        }

        @Override
        public void multiplication() { /* Да, я хочу это */
        }

        @Override
        public void division() { /* Да, я хочу это */
        }

        @Override
        public void exponentiation() { /* Да, я хочу это */
        }

        @Override
        public void square() { /* Да, я хочу это */
        }

        @Override
        public void exponent() { /* Да, я хочу это */
        }

        @Override
        public void sine() { /* Да, я хочу это */
        }

        @Override
        public void arxSine() { /* Да, я хочу это */
        }

        @Override
        public void cosine() { /* Да, я хочу это */
        }

        @Override
        public void arcCosine() { /* Да, я хочу это */
        }

        @Override
        public void tangent() { /* Да, я хочу это */
        }
    }

    public static class PrimarySchoolCalculator implements Calculator {

        @Override
        public void addition() { /* Да, я хочу это */
        }

        @Override
        public void subtraction() { /* Да, я хочу это */
        }

        @Override
        public void multiplication() { /* Да, я хочу это */
        }

        @Override
        public void division() { /* Да, я хочу это */
        }

        @Override
        public void exponentiation() { /*??????????????????*/
        }

        @Override
        public void square() { /*??????????????????*/
        }

        @Override
        public void exponent() { /*??????????????????*/
        }

        @Override
        public void sine() { /*??????????????????*/
        }

        @Override
        public void arxSine() { /*??????????????????*/
        }

        @Override
        public void cosine() { /*??????????????????*/
        }

        @Override
        public void arcCosine() { /*??????????????????*/
        }

        @Override
        public void tangent() { /*??????????????????*/
        }
    }
}
/*Разработка калькулятора, в котором есть много функций. В одном
классе реализованы все эти функции, но некоторые из них могут быть не интересны пользователям. Например, школьнику нужны только общие функции, а функции тригонометрии и преобразования в шестнадцатеричную систему ему не нужны.*/