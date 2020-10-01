package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

 public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
     private Node head;
     private Node a;
     private int count;

     protected class Node {
         public Node next;
         public Node prev;
         public double x;
         public double y;
     }

     //addNode добавляет новый узел в конец списка,если список  пустой то голова списка становится узел
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

     // возращает ссылку на узел номер index
     private Node getNode(int index) {
         if (index > (count / 2)) {
             a = head.prev;
             for (int i = count - 1; i > 0; i--) {
                 if (i == index) {
                     return a;
                 } else {
                     a = a.prev;
                 }
             }
         } else {
             a = head;
             for (int i = 0; i < count; i++) {
                 if (index == i) {
                     return a;
                 } else {
                     a = a.next;
                 }
             }
         }
         return null;
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
     public void setY(int index, double valueY) {
         valueY = getNode(index).y;
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
     protected double extrapolateLeft(double x) {
         return 0;
     }

     @Override
     protected double extrapolateRight(double x) {
         return 0;
     }

     @Override
     protected double interpolate(double x, int floorIndex) {
         return 0;
     }
 }








