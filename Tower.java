/**
 * @author Joanne Hayashi
 * EN.605.202.84.SP19: Lab 2 - Tower of Hanoi
 */
package Module8Lab2;

// class Tower - is a stack of integers representing a tower

// TODOs:
//        add method peek
//        add error handling

public class Tower {
    int tower[];
    int numDisks;
    int top;
    String name;

    public Tower(int numDisks, String name) {
        this.numDisks = numDisks;
        this.tower = new int[numDisks];
        this.top = -1;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isEmpty() {
            return (this.top == - 1);
    }

    public boolean isFull() {
            return (this.top == this.tower.length - 1);
    }
    
    public void push(int disk) {
            if (!this.isFull()) {
                tower[++top] = disk;
            } else {
                System.out.println("push: Tower stack is full.");
                System.exit(5);
            }
    }

    public int pop() {
            int disk = 0;
            if (!this.isEmpty()) {
                disk = tower[top--];
            } else {
                System.out.println("pop(): Tower stack is empty.");
                System.exit(6);
            }
        return disk;
    }

    public void reset() {
        while (!this.isEmpty()) {
            this.pop();
        }
    }
    
    public void print() {
        if (top == -1) {
            System.out.println("Tower " + name + ": [ EMPTY ]");
        } else {
            System.out.print("Tower " + name + ": [ ");
            for (int i = 0; i <= top; i++) {
                System.out.print(tower[i] + " ");
            }
            System.out.println("]");
        }
    }
}