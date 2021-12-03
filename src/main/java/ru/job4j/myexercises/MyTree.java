package ru.job4j.myexercises;

public class MyTree {
    public static void main(String[] args) {
        Tree root =
                new Tree(20,
                    new Tree(7,
                            new Tree(4, null, new Tree(6)), new Tree(9)),
                    new Tree(35,
                            new Tree(31, new Tree(28), null),
                            new Tree(40, new Tree(38), new Tree(52))));
        System.out.println("Sum Tree : " + sumWide(root));
    }
    static class Tree {
        int value;
        Tree left;
        Tree right;

        public Tree(int value, Tree left, Tree right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Tree(int value) {
            this.value = value;
        }
        public int sum() {
            int sum = value;

            if (left != null) {
                sum += left.sum();
            }
            if (right != null) {
                sum += right.sum();
            }
            return sum;
        }
    }
    public static int sumDeep(Tree root) {
        SimpleStack<Tree> stack = new SimpleStack<>();
        stack.push(root);
        int sum = 0;
        while (!stack.isEmpty()) {
            Tree node = stack.pop();
            System.out.println(node.value);
            sum = sum + node.value;
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return sum;
    }
    public static int sumWide(Tree root) {
        SimpleQueue<Tree> queue = new SimpleQueue<>();
        queue.add(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            Tree node = queue.remove();
            System.out.println(node.value);
            sum = sum + node.value;
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        return sum;
    }
}
