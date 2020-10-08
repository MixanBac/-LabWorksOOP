package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

 public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
     private Node head;

     protected class Node {
         public Node next;
         public Node prev;
         public double x;
         public double y;
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

     LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
         for (int i = 0; i < xValues.length; i++) {
             this.addNode(xValues[i], yValues[i]);
         }
     }

     public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
         this.count = count;
         double step = (xTo - xFrom) / (count - 1);
         double a = xFrom;
         for (int i = 0; i < count; i++) {
             this.addNode(a, source.apply(a));
             a += step;
         }
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
     protected double extrapolateRight(double x) {
         if (head.x == head.prev.x) {
             return head.y;
         }
         return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
     }

     @Override
     protected double extrapolateLeft(double x) {
         if (head.x == head.prev.x) {

             return head.y;
         }
         return interpolate(x, head.x, head.next.x, head.y, head.next.y);
     }

     public int getCount() {
         return count;
     }
     @Override
     protected double interpolate(double x, int floorIndex) {
         Node indexNode = head;
         if (floorIndex == 0) {
             return extrapolateLeft(x);
         } else if (floorIndex == count) {
         }
         if (floorIndex == count) {
             return extrapolateRight(x);
         } else {
             for (int i = 0; i < count; i++) {
                 if (i == floorIndex) {
                     return interpolate(x, indexNode.x, indexNode.next.x, indexNode.y, indexNode.next.y);
                 }
                 indexNode = indexNode.next;
             }
             for (int i = 0; i < count; i++) {
                 if (i == floorIndex) {
                     return interpolate(x, indexNode.x, indexNode.next.x, indexNode.y, indexNode.next.y);
                 }
                 indexNode = indexNode.next;
             }
             return 0;
         }
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
     public double getX(int index) {
         return getNode(index).x;
     }

     @Override
     public double getY(int index) {
         return getNode(index).y;
     }

     @Override
     public void setY(int index, double value) {
         getNode(index).y = value;
     }

     private Node getNode(int index) {
         Node indexNode = head;
         for (int i = 0; i < count; i++) {
             if (i == index) {
                 return indexNode;
             }
             indexNode = indexNode.next;
         }
         return new Node();
     }
 }


































