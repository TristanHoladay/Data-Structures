/**
 * Integer only stack which is faster than java.util.ArrayDeque
 * but it requires that you know an upper bound on the number of elements
 * that will be inside the stack at any given time. 
 */

package com.williamfiset.datastructures.stack;

public class IntStack {

    private int[] ar;
    private int pos = 0;

    //maxSize is the maximum number of items
    //that can be in the queue at any given time
    public IntStack(int maxSize) {
        ar = new int[maxSize];
    }

    //Returns the number of elements inside the stack
    public int size() {
        return pos;
    }

    //Returns true/false on whether the stack is empty
    public boolean isEmpty() {
        return pos == 0;
    }

    //Returns the element at the top of the stack
    public int peek() {
        //*my notes* the top of the stack is the end of the array
        //because of LIFO principle
        return ar[pos -1];
    }

    //Add an element to the top of the stack
    public void push(int value) {
        ar[pos++] = value;
    }

    //Make sure you check that the stack is not empty before calling pop!
    public int pop() {
        return ar[--pos];
    }

    //Example usage
    public static void main(String[] args) {
        IntStack s = new IntStack(5);

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);

        System.out.println(s.pop()); //5
        System.out.println(s.pop()); //4
        System.out.println(s.pop()); //3

        s.push(3);
        s.push(4);
        s.push(5);

        while (!s.isEmpty()) System.out.println(s.pop());

        benchMarkTest();
    }

    //BenchMark IntStack vs ArrayDeque
    private static void benchMarkTest() {
        int n = 10000000;
        IntStack intStack = new IntStack(n);

        //IntStack times at around .0324 seconds
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) intStack.push(i);
        for (int i = 0; i < n; i++) intStack.pop();
        long end = System.nanoTime();
        System.out.println("IntStack Time: " + (end - start) / 1e9);

        //ArrayDeque times at around 1.438 seconds
        java.util.ArrayDeque<Integer> arrayDeque = new java.util.ArrayDeque<>();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) arrayDeque.push(i);
        for (int i = 0; i < n; i++) arrayDeque.pop();
        end = System.nanoTime();
        System.out.println("ArrayDeque Time: " + (end - start) / 1e9);
    }

}