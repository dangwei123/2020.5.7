private static double error(int n){
        if(n==1) return 0;
        if(n==2) return 1;
        return (n-1)*(error(n-1)+error(n-2));
    }
    private static double sum(int n){
        if(n<2){
            return 1;
        }
        return n*sum(n-1);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            double sum=sum(n);
            double error=error(n);
            double res=(error/sum)*100;
            String s=String.format("%.2f",res);
            System.out.println(s+"%");
        }
    }
	
	
	
	import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNext()){
            int n=sc.nextInt();
            int sum=sc.nextInt();
            int[] arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            long[] dp=new long[sum+1];
            dp[0]=1;
            for(int i=0;i<n;i++){
                for(int j=sum;j>=arr[i];j--){
                     dp[j]+=dp[j-arr[i]];
                }
            }
            System.out.println(dp[sum]);
        }
    }
}
