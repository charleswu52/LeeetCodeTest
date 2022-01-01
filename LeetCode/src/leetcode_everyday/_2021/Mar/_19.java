package leetcode_everyday._2021.Mar;

/**
 * @author WuChao
 * @since 2021/3/19 上午8:06
 */
public class _19 {
    /**
     * 每日一题：2021/3/19
     * 1603. 设计停车系统
     * 难度: easy
     * 反请你给一个停车场设计一个停车系统。停车场总共有三种不同大小的车位：大，中和小，每种尺寸分别有固定数目的车位。
     * <p>
     * 请你实现 ParkingSystem 类：
     * <p>
     * ParkingSystem(int big, int medium, int small) 初始化 ParkingSystem 类，三个参数分别对应每种停车位的数目。
     * bool addCar(int carType) 检查是否有 carType 对应的停车位。
     * carType 有三种类型：大，中，小，分别用数字 1， 2 和 3 表示。一辆车只能停在 carType 对应尺寸的停车位中。
     * 如果没有空车位，请返回 false ，否则将该车停入车位并返回 true。
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * <p>
     * 示例:
     * 0 <= big, medium, small <= 1000
     * carType 取值为 1， 2 或 3
     * 最多会调用 addCar 函数 1000 次
     * <p>
     * 数据范围：
     */

    class ParkingSystem {
        private int big, medium, small;

        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }

        public boolean addCar(int carType) {
            switch (carType) {
                case 1:
                    if (this.big <= 0) {
                        return false;
                    } else {
                        this.big--;
                        return true;
                    }
                case 2:
                    if (this.medium <= 0) {
                        return false;
                    } else {
                        this.medium--;
                        return true;
                    }
                case 3:
                    if (this.small <= 0) {
                        return false;
                    } else {
                        this.small--;
                        return true;
                    }
            }
            throw new IllegalArgumentException("输入参数不正确");
        }
    }
}
