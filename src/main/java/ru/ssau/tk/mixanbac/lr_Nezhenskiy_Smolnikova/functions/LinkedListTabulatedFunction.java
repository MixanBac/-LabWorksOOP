package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable {
    private static final long serialVersionUID = -7586757294847649471L;
    private Node head;

    protected static class Node implements Serializable {
        private static final long serialVersionUID = -5659942879517986295L;
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }
    public LinkedListTabulatedFunction() {
    }

    @Override
    public Iterator<Point> iterator() {

        return new Iterator<>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(node.x, node.y);
                if (node == head.prev) {
                    node = null;
                } else {
                    node = node.next;
                }
                return point;
            }
        };
    }


    @Override
    public double getX(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("пиши индекс правильный");
        }
        return getNode(index).x;
    }


    @Override
    public double getY(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index is out of bounds");
        }
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x == x) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.y == y) {
                return i;
            }
            indexNode = indexNode.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("X находится за левой границей");
        }
        Node indexNode = head;
        for (int i = 0; i < count; i++) {
            if (indexNode.x < x) {
                indexNode = indexNode.next;
            } else {
                if (i == 0) {
                    return 0;
                }
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node leftNode = getNode(floorIndex);
        Node rightNode = leftNode.next;
        if (x < leftNode.x || x > rightNode.x) {
            throw new InterpolationException("X находится за пределами ");
        }
        return interpolate(x, leftNode.x, rightNode.x, leftNode.y, rightNode.y);
    }


    public int getCount() {
        return count;
    }


    private Node getNode(int index) {
        Node indexNode;
        if (index <= (count / 2)) {
            indexNode = head;
            for (int i = 0; i < count; i++) {
                if (i == index) {
                    return indexNode;
                }
                indexNode = indexNode.next;
            }
        } else {
            indexNode = head.prev;
            for (int i = count - 1; i > 0; i--) {
                if (i == index) {
                    return indexNode;
                }
                indexNode = indexNode.prev;
            }
        }
        throw new IllegalArgumentException();
    }


    //addNode добавляет новый узел в конец списка,если список  пустой то головой списка становится узел
    private void addNode(double x, double y) {
        Node newNode = new Node();
        newNode.x = x;
        newNode.y = y;
        if (head == null) {
            head = newNode;
            newNode.prev = newNode;
            newNode.next = newNode;

        } else {

            Node last = head.prev;
            newNode.prev = last;
            newNode.next = head;
            head.prev = newNode;
            last.next = newNode;
        }
        count += 1;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("длина таблицы меньше 2 точек");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }


    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("колличество точек меньше 2 ");
        }
        if ((xFrom >= xTo)) {
            throw new IllegalArgumentException("писать допустимые значения");
        }
        final double step = (xTo - xFrom) / (count - 1);
        double a = xFrom;
        for (int i = 0; i < count; i++) {
            this.addNode(a, source.apply(a));
            a += step;
        }
    }
}





































