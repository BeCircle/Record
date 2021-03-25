package algorithm;

public class RemoveElement {
    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例 1:
     * 给定 nums = [3,2,2,3], val = 3,
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 示例 2:
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * 注意这五个元素可为任意顺序。
     * 你不需要考虑数组中超出新长度后面的元素。
     * 链接：https://leetcode-cn.com/problems/remove-element
     * <p>
     * S1. 和移除排序数组中重复元素的思路，从左到右扫描，将不删除的元素往前移动。
     * 由于这里不要求保持原来的顺序，因此这种方式复制较多；
     * S2.从左到右扫描，遇到一个需要删除，从右到左寻找不需要删除的，复制过来。
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        // 从左到右扫描
        int back = nums.length - 1;
        for (int i = 0; i < nums.length && i <= back; i++) {
            if (val == nums[i]) {
                // 需要删除的元素
                // 从右到左找一个不需要删除的来填充
                while (back >= i) {
                    if (nums[back] != val) {
                        // 交换
                        int temp = nums[i];
                        nums[i] = nums[back];
                        nums[back] = temp;
                        back--;
                        break;
                    }
                    back--;
                }
            }
        }
        return back+1;
    }

    public static int removeElement2(int[] nums, int val) {
        // 同样的思路，更简单写法
        if (nums == null || nums.length < 1) {
            return 0;
        }
        // 从左到右扫描
        int back = nums.length - 1, front = 0;
        while (front<back){
            if (val == nums[front]) {
                // 需要删除的元素
                // 从右到左找一个不需要删除的来填充
                int temp = nums[front];
                nums[front] = nums[back];
                nums[back] = temp;
                back--;
                // 此时i未改变，如果nums[back]交换之前也是需要删除的，下一轮检查；
                // 但这也是多余的复制
            }else {
                front++;
            }
        }
        return back;
    }

    public static void main(String[] args) {
        int[] nums = {3, 3, 2, 3};
        int val = 3;
        int len = removeElement2(nums, val);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }
}

