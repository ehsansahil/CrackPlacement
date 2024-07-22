import java.util.Stack;

class MyQueue {
    Stack<Integer> s1; // Stack for push operations
    Stack<Integer> s2; // Stack for pop and peek operations
    int front = 0; // Variable to track the front element

    public MyQueue() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    public void push(int x) {
        if (s1.empty()) {
            front = x; // Update the front element if s1 is empty
        }
        s1.push(x); // Push element onto s1
    }

    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop()); // Transfer elements from s1 to s2 if s2 is empty
            }
        }
        return s2.pop(); // Pop the top element from s2
    }

    public int peek() {
        return s2.isEmpty() ? front : s2.peek(); // Return the front element
    }

    public boolean empty() {
        return s1.empty() && s2.empty(); // Check if both stacks are empty
    }
}