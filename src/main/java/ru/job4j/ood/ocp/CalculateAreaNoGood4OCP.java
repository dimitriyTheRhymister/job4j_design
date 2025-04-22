package ru.job4j.ood.ocp;

public class CalculateAreaNoGood4OCP {

    private static class Circle {
        public double getRadius() {
            return 0;
        }
    }

    private static class Rectangle {
        public double getWidth() {
            return 0;
        }

        public double getHeight() {
            return 0;
        }
    }

    public double calculateArea(Object shape) {
        if (shape instanceof Circle circle) {
            return Math.PI * circle.getRadius() * circle.getRadius();
        } else if (shape instanceof Rectangle rectangle) {
            return rectangle.getWidth() * rectangle.getHeight();
        } else {
            /*
            ���������� ����� ������ ��������� ��������� ����� ������,
            � ������� OCP: "����������� �������� (������, ������, �������...)
            ������ ���� ������� ��� ����������, �� ������� ��� �����������."
            */
            throw new UnsupportedOperationException("Unsupported shape");
        }
    }
}
