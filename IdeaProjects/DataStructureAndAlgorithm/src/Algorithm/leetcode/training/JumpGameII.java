package Algorithm.leetcode.training;

public class JumpGameII {
    public static void main(String[] args) {
        int[] nums={2,3,1,1,4};
        boolean b = jumpOk(nums);
        System.out.println(b);
    }
    //Algorithm.leetcode.training.JumpGameII object internals:
    // OFFSET  SIZE    TYPE DESCRIPTION                               VALUE
    //      0    12         (object header)                           N/A 开启指针压缩，klass pointer为4字节
    //     12     4     int JumpGameII.a                              N/A
    //     16     4   int[] JumpGameII.b                              N/A
    //     20     4         (loss due to the next object alignment)
    //Instance size: 24 bytes
    //Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

    /**
     *
     * @param nums
     * @return
     */
    public static boolean jumpOk(int[] nums){
        int length = nums.length;
        if (length==0||nums==null) return false;
        if (length==1&&nums[0]==0) return true;
        else if (length==1&&nums[0]!=0) return false;
        boolean[] flag=new boolean[length];
        flag[0]=true;
        for (int i = 1; i < length; i++) {
            flag[i]=false;
            for (int j = 0; j < i; j++) {
                if (flag[j]&&j+nums[j]>=i){
                    flag[i]=true;
                    break;
                }
            }
        }
        return flag[length-1];
    }
}