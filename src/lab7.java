import java.util.*;

public class lab7 {
    public static int findCelebrity(int[][] matrix) {
        int n = matrix.length;
//        System.out.println(n);
        // create a stack to keep track of potential celebrities
        Stack<Integer> stack = new Stack<>();

        // add all people to the stack
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        // keep eliminating non-celebrities until only one potential celebrity remains
        while (stack.size() > 1) {
            int person1 = stack.pop();
            int person2 = stack.pop();
//            System.out.println(person1 + ", " + person2);
//            System.out.println(matrix[person1][person2]);
            // if person1 knows person2, person1 cannot be the celebrity
            if (matrix[person1][person2] == 1) {
                stack.push(person2);
            }
            // If person1 does not know person2, person2 cannot be the celebrity
            else {
                stack.push(person1);
            }
        }

//        System.out.println(stack);
        int celebrity = stack.pop();

        // check if the potential celebrity is actually a celebrity
        for (int i = 0; i < n; i++) {
            if (i != celebrity && (matrix[celebrity][i] == 1 || matrix[i][celebrity] == 0)) {
                return -1;
            }
        }

        return celebrity;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 0},
                {1, 0, 0},
                {1, 1, 0}
        };
        int celebrity = findCelebrity(matrix);
        if (celebrity == -1) {
            System.out.println("There is no celebrity in the group.");
        } else {
            System.out.println("The celebrity is person " + celebrity + ".");
        }
    }
}
