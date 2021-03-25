package Company;

public class ShopeeI {
    public static int maxSubArray(int [] nums){
        if (nums==null || nums.length<1){
            return 0;
        }
        // 双指针，和为负数则移动两个指针，否则移动一个指针
        int p2=0;
        int current = 0;
        int res = -999999;
        int len = nums.length;
        while ( p2<len) {
            current += nums[p2];
            if (current>res){
                res = current;
            }
            if (current<0){
                p2++;
                current = 0;
            }else{
                p2++;
            }
        }
        return res;
    }

    public static void Problem1() {
        int [] case1 = {-2,1,-3,4,-1,2,1,-5,4};
        // res=6
        System.out.println(maxSubArray(case1));
    }


    public static int MissingNum(int [] nums) {
        if (nums==null||nums.length<1){
            return -1;
        }
        int n = nums.length;
        double res = n*(n+1)/2.0;
        for (int i: nums){
            res-=i;
        }
        return (int) res;
    }

    public static void Problem2(){
        int [] case1 = {3,0,1};
        int [] case2 = {9,6,4,2,3,5,7,0,1};
        System.out.println(MissingNum(case1));
        System.out.println(MissingNum(case2));
    }

    public static String addStrings(String num1, String num2) {
        // 数组，低位相加，最后统一处理进位
        int len1 = num1.length();
        int len2 = num2.length();
        int resLen = Math.max(len1, len2);
        int [] res = new int[resLen];
        int loc = 0;
        while (loc<resLen) {
            if (loc<len1){
                res[loc] += (num1.charAt(len1-1-loc)-'0');
            }
            if (loc<len2){
                res[loc] += (num2.charAt(len2-1-loc)-'0');
            }
            loc++;
        }
        // 处理进位
        for (int i=0; i<resLen-1; i++) {
            if (res[i]>=10){
                res[i]%=10;
                res[i+1] += 1;
            }
        }
        // 构建结果字符串
        StringBuilder sb = new StringBuilder();
        for (int i = resLen-1; i >=0 ; i--) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static void Problem3() {
        String case1 = "78812308759876748123781826380198723";
        String case2 = "17826937618236512765307128937068123";
        // 966392
        System.out.println(addStrings(case1, case2));
    }

    public static void main(String[] args) {
        Problem3();
    }
}
