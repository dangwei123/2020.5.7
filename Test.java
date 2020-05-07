两个节点之间的最大距离
public class MaxDistincts {
    private int record;
    public int getMaxDistincts(TreeNode root){
        if(root==null){
            record=0;
            return 0;
        }
        int leftmax=getMaxDistincts(root.left);
        int leftrecord=record;
        int rightmax=getMaxDistincts(root.right);
        int rightrecord=record;
        int currecord=leftrecord+rightrecord+1;
        record=Math.max(leftrecord,rightrecord)+1;
        return Math.max(currecord,Math.max(leftmax,rightmax));
    }
}


给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/subtree-of-another-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null&&t==null) return true;
        if(s==null||t==null) return false;
        if(isSub(s,t)){
            return true;
        }
        return isSubtree(s.left,t)||isSubtree(s.right,t);
    }
    private boolean isSub(TreeNode s,TreeNode t){
        if(s==null&&t==null) return true;
        if(s==null||t==null) return false;
        return s.val==t.val&&isSub(s.left,t.left)&&isSub(s.right,t.right);
    }
}

根据一棵树的前序遍历与中序遍历构造二叉树。
class Solution {
    private int index;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return build(preorder,inorder,0,preorder.length-1,map);
    }
    private TreeNode build(int[]pre,int[] in,int begin,int end,Map<Integer,Integer> map){
        if(index>=in.length||begin>end){
            return null;
        }
        TreeNode root=new TreeNode(pre[index]);
        int ri=map.get(pre[index++]);
        root.left=build(pre,in,begin,ri-1,map);
        root.right=build(pre,in,ri+1,end,map);
        return root;
    }
}

根据一棵树的中序遍历与后序遍历构造二叉树。
class Solution {
    private int index;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        index=inorder.length-1;
        return build(inorder,postorder,0,inorder.length-1,map);
    }
    private TreeNode build(int[] in,int[] post,int begin,int end,Map<Integer,Integer> map){
        if(index<0||begin>end){
            return null;
        }
        TreeNode root=new TreeNode(post[index]);
        int ri=map.get(post[index--]);
        root.right=build(in,post,ri+1,end,map);
        root.left=build(in,post,begin,ri-1,map);
        return root;
    }

}

返回与给定的前序和后序遍历匹配的任何二叉树。

 pre 和 post 遍历中的值是不同的正整数。
 class Solution {
    private int i;
    private int j;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root=new TreeNode(pre[i++]);
        if(root.val!=post[j]){
            root.left=constructFromPrePost(pre,post);
        }
        if(root.val!=post[j]){
            root.right=constructFromPrePost(pre,post);
        }
        j++;
        return root;
    }
}

根据前序数组和中序数组生成后序数组
public class BuildArray {

    public int[] buildArray(int[] pre,int[] in){
        Map<Integer,Integer> map=new HashMap<>();
        int n=in.length;
        for(int i=0;i<n;i++){
            map.put(in[i],i);
        }
        int[] post=new int[n];
        build(pre,in,0,n-1,0,n-1,post,n-1,map);
        return post;
    }
    private int build(int[]pre,int[]in,int begin1,int end1,int begin2,int end2,
                      int[] post,int index,Map<Integer,Integer> map){
        if(begin1>end1){
            return index;
        }
        post[index--]=pre[begin1];
        int i=map.get(pre[begin1]);
        int countleft=i-begin2;
        int countright=end2-i;
        build(pre,in,end1-countright+1,end1,i+1,end2,post,index,map);
        return build(pre,in,begin1+1,begin1+countleft,begin2,i-1,post,index,map);
    }
}

给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n<1){
            return new ArrayList<>();
        }
        return generate(1,n);
    }
    private List<TreeNode> generate(int left,int right){
        List<TreeNode> res=new ArrayList<>();
        if(left>right){
            res.add(null);
            return res;
        }
        for(int i=left;i<=right;i++){
            
            List<TreeNode> leftchild=generate(left,i-1);
            List<TreeNode> rightchild=generate(i+1,right);
            for(TreeNode l:leftchild){
                for(TreeNode r:rightchild){
                    TreeNode root=new TreeNode(i);
                    root.left=l;
                    root.right=r;
                    res.add(root);
                }
            }
        }

        return res;
    }
}

给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
class Solution {
    public int numTrees(int n) {
        int[] dp=new int[n+1];
        dp[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                dp[i]+=dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}
完全二叉树的节点个数
public class NumOfPrefectTree {
    private int h;
    public int numOfPrefect(TreeNode root){
        if(root==null) return 0;
        h=mostLeft(root,1);
        return getNum(root,1);
    }
    private int getNum(TreeNode root,int level){
        if(level==h){
            return 1;
        }
        if(mostLeft(root.right,level+1)==h){
            return (1<<(h-level))+getNum(root.right,level+1);
        }else{
            return (1<<(h-level-1))+getNum(root.left,level+1);
        }
    }
    private int mostLeft(TreeNode root,int level){
        while(root!=null){
            level++;
            root=root.left;
        }
        return level-1;
    }
}
