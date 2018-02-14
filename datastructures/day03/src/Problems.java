import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        LinkedList<Integer> l = new LinkedList<>();
        int i = 0;
        while(i < A.length){
            while(k > 0 && !l.isEmpty() && l.getLast() > A[i]){
                l.removeLast();
                k--;
            }
            l.add(A[i]);
            i++;
        }

        while(k>0) {
            l.removeLast();
            k--;
        }
        return l;
    }

//    public static boolean isPalindrome(Node n) {
//        Stack<Node> stk = new Stack<Node>();
//        LinkedList<Node> q = new LinkedList<>();
//        while(n != null){
//            stk.push(n);
//            q.addLast(n);
//            n = n.next;
//        }
//        while(!stk.isEmpty()){
//            if(stk.pop().val !=q.removeFirst().val) return false;
//        }
//        return true;
//    }

    public static boolean isPalindrome(Node n) {
        if( n == null || n.next == null) return true;
        Node full_speed = n; // Goes fast to reach the end of the list
        Node half_speed = n, prev_of_half = null; // Goes half the speed to be at the halfway point when full_speed finishes

        // Set up things for reversing
        Node prev = null; // only one that won't start with a value
        Node current;
        Node next;

        while(full_speed != null && full_speed.next != null){ // as long as the fast one hasn't hit the end, advance both
            full_speed = full_speed.next.next; // advance two

            prev_of_half = half_speed; // keep track of one back, as this will be what cuts off the second half of the list
            half_speed = half_speed.next; // advance one
        }
        // Reverse full_speed section
        current = half_speed; // half_speed is now the head of the second half
        prev_of_half.next = null; // eliminate the next section so that we can compare to the reversed second half
        while(current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Now, starting from n and prev (which is the last non-null), compare the two
        while(prev != null && n != null && prev.val == n.val ) { // go as long as neither is null and their values are equal
            prev = prev.next;
            n = n.next;
        }

        // in theory, if they have both been emptied or prev has only one value left, it is a palindrome
        if(prev != null && n == null && prev.next == null) { return true;}
        return (prev == null && n == null);
    }

    public static String infixToPostfix(String s) {
        StringBuilder infix = new StringBuilder();
        Stack<Character> parans = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            switch(s.charAt(i)) {
                case('('):{
                    parans.push(s.charAt(i));
                    break;
                }
                case(')'): {
                    parans.pop();
                    infix.append(ops.pop()+ " ");
                    break;
                }
                case('+'): {
                    ops.push(s.charAt(i));
                    break;
                }
                case('-'): {
                    ops.push(s.charAt(i));
                    break;
                }
                case('*'): {
                    ops.push(s.charAt(i));
                    break;
                }
                case('/'): {
                    ops.push(s.charAt(i));
                    break;
                }
                case(' '): break;
                default:{
                    infix.append(s.charAt(i) + " ");
                }
            }
        }
        return infix.toString().trim();
    }

}
