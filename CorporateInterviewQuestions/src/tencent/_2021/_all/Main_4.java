package tencent._2021._all;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author WuChao
 * @create 2021/7/2 14:44
 */
public class Main_4 {
    /**
     *  腾讯2021校园招聘技术类编程题汇总
     * [编程题]队列操作
     *
     * 数据结构基础之一 —— 队列
     * 队列有五种基本操作，插入队尾、取出队首、删除队首、队列大小、清空队列。
     *
     * 现在让你模拟一个队列的操作，具体格式参考输入。
     *
     * 输入描述:
     * 第一行输入一个整数T，表示接下来有T组测试数据。
     * 对于每组测试数据：
     * 第一行输入一个整数Q，表示有Q次操作。
     * 接下来Q行，每行输入一种队列操作方式，具体格式如下：
     *
     * 初始状态下队列为空。
     *
     * 插入队尾：PUSH X
     * 取出队首：TOP//仅仅是看一下队首元素，不要把队首元素删除
     * 删除队首：POP
     * 队列大小：SIZE
     * 清空队列：CLEAR
     *
     * 1<=T<=100
     * 1<=Q,x<=1000
     * 保证操作为以上5种的任意一种。
     *
     * 输出描述:
     * 对于每组测试数据：
     * 如果操作为“取出队首”，输出队首元素，如果无法取出，输出“-1”
     * 如果操作为“删除队首”，如果无法删除，输出“-1”
     * 如果操作为“队列大小”，输出队列大小
     * 其他操作无需输出
     *
     * 输入例子1:
     * 2
     * 7
     * PUSH 1
     * PUSH 2
     * TOP
     * POP
     * TOP
     * POP
     * POP
     * 5
     * PUSH 1
     * PUSH 2
     * SIZE
     * POP
     * SIZE
     *
     * 输出例子1:
     * 1
     * 2
     * -1
     * 2
     * 1
     */


    /**
     * 队列实现
     */
    static class MyQueue {
        LinkedList<Integer> list;

        public MyQueue() {
            this.list = new LinkedList<>();
        }

        // 5种 基本操作 ： 插入队尾、取出队首、删除队首、队列大小、清空队列。

        /**
         * 插入队尾
         *
         * @param x
         */
        public void push(int x) {
            list.add(x);
        }

        /**
         * 取出队首
         *
         * @return
         */
        public int top() {
            if (list.size() == 0) {
                return -1;
            } else {
                return list.getFirst();
            }
        }

        /**
         * 删除队首
         *
         * @return
         */
        public int pop() {
            if (list.size() == 0) {
                return -1;
            } else {
                return list.pop();
            }
        }

        /**
         * 队列大小
         *
         * @return
         */
        public int size() {
            return list.size();
        }

        /**
         * 清空队列
         */
        public void clear() {
            list.clear();
        }


    }


    /*
    测试函数
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = Integer.parseInt(scanner.nextLine());
        while (t-- > 0) {
            int q = Integer.parseInt(scanner.nextLine());
            MyQueue queue = new MyQueue();
            for (int i = 0; i < q; i++) {
                String[] commands = scanner.nextLine().split(" ");
                switch (commands[0]) {
                    case "PUSH":
                        int param = Integer.parseInt(commands[1]);
                        queue.push(param);
                        break;
                    case "TOP":
                        System.out.println(queue.top());
                        break;
                    case "POP":
                        if (queue.pop() == -1) {
                            System.out.println(-1);
                        }
                        break;
                    case "SIZE":
                        System.out.println(queue.size());
                        break;
                    case "CLEAR":
                        queue.clear();
                        break;
                }
            }
        }
    }

}
