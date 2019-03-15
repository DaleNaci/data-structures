import java.util.*;
import java.lang.*;

public class ReviewTask {

    public static void main(String[] args) {

        // #1
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < (int)(Math.random() * 101 + 50); i++) {
            list.add((int)(Math.random() * 100 + 1));
        }

        // #2
        System.out.println(list);

        // #3
        int arrDim = (int)(Math.ceil(Math.sqrt(list.size())));
        int[][] arr = new int[arrDim][arrDim];
        while (list.size() > 0) {
            int x = (int)(Math.random() * arrDim);
            int y = (int)(Math.random() * arrDim);
            if (arr[x][y] == 0)
                arr[x][y] = list.remove(0);
        }

        // #4
        System.out.println("2D ARRAY");
        for (int[] a : arr) {
            for (int i : a) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

        // #5
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arrDim; i++) {
            for (int j = 0; j < arrDim; j++) {
                stack.push(arr[j][i]);
            }
        }
        System.out.println();
        System.out.println();

        // #6, 7
        System.out.println("STACK");
        HashSet<Integer> hSet = new HashSet<>();
        for (int i = 0; i < arrDim * arrDim; i++) {
            hSet.add(stack.peek());
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
        System.out.println();

        // #8, 9
        System.out.println("HASH SET");
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        Iterator<Integer> iter = hSet.iterator();
        while (iter.hasNext()) {
            int next = iter.next();
            System.out.print(next + " ");
            pQueue.add(next);
        }
        System.out.println();
        System.out.println();

        // #10, 11
        System.out.println("PRIORITY QUEUE");
        TreeMap<Integer, TreeSet<Integer>> tMap = new TreeMap<>();
        boolean firstEven = false;
        TreeSet<Integer> tempTSet = new TreeSet<Integer>();
        int key = -1;
        while (pQueue.peek() != null) {
            int next = pQueue.poll();
            System.out.print(next + " ");
            if (next % 2 == 0 && !firstEven) {
                firstEven = true;
                key = next;
            }
            if (firstEven) {
                if (next % 2 == 0) {
                    tMap.put(key, tempTSet);
                    key = next;
                    tempTSet = new TreeSet<Integer>();
                } else {
                    tempTSet.add(next);
                }
            }
        }
        System.out.println();

        // #12
        System.out.println("TREESET");
        for (Map.Entry<Integer, TreeSet<Integer>> entry : tMap.entrySet()) {
            Integer tempKey = entry.getKey();
            TreeSet<Integer> tempTree = entry.getValue();
            System.out.println(tempKey + ": " + tempTree);
        }
    }
}
