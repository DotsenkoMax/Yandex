package Contest551;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class mainF {
    public static Scanner enter = new Scanner(System.in);
    public static PrintWriter out = new PrintWriter(System.out);
    public static long mod=998244353;
    public static long cnk[][]=new long[(int)2e3+2][(int)2e3+2];
    public static long fact[]=new long[2*(int)2e3+2];
    public static long reverse[]=new long[2*(int)2e3+2];
    public static long deg_2[]=new long[(int)2e3+2];
    public static void main(String[] args) {
        long t=System.currentTimeMillis();
        cnk[0][0]=1;
        for (int i = 1; i <cnk.length ; i++) {
            cnk[0][i]=1;
            for (int j = 1; j <=i ; j++) {
                cnk[j][i]=cnk[j-1][i-1]+cnk[j][i-1];
                cnk[j][i]%=mod;
            }
        }
        fact[0]=1;
        deg_2[0]=1;
        for (int i = 1; i <fact.length ; i++) {
            fact[i]=fact[i-1]*i;
            fact[i]%=mod;
        }
        for (int i = 1; i <deg_2.length ; i++) {
            deg_2[i]=deg_2[i-1]*2;
            deg_2[i]%=mod;
        }
        reverse[1]=1;
        for (int i = 3; i <reverse.length ; i+=2) {
            reverse[i]=bin_pow(fact[i],mod-2,mod);
        }
       /* System.out.println(Arrays.toString(reverse));
        System.out.println(fact[5]);
        System.out.println(reverse[5]);*/
        int n=enter.nextInt();
        int k=enter.nextInt();
        long l=enter.nextInt();
        l%=mod;
        long ans=0;
        for (int i = k; i <=n ; i++) {
            long tmp=0;
            for (int s = 0,c=1; s <=n-i ; s++,c*=-1) {
                long a=1;
                a*=cnk[s][n-i]*deg_2[s];
                a%=mod;
                a*=fact[s+i];
                a%=mod;
                a*=fact[s+i];
                a%=mod;
                a*=reverse[2*(s+i)+1];
                a%=mod;
                tmp+=a*c;
                tmp%=mod;
               // System.out.println(reverse[2*(s+i)+1]);
            }
            //System.out.println(tmp);
            /*ans+=cnk[i][n]*deg_2[i]*tmp;
            ans%=mod;*/
            long  b=1;
            b*=tmp*deg_2[i];
            b%=mod;
            //System.out.println(b);
            b*=cnk[i][n];
            b%=mod;
            ans+=b;
            ans%=mod;
        }
        ans*=l;
        ans%=mod;
        System.out.println((ans<0)? (ans+mod) : ans);


      //  System.out.println(System.currentTimeMillis()-t);



    }
    public static long bin_pow(long a,long b, long mod){//a^b %mod
        long ans=1;
        while(b!=0){
            if((b&1)==1) ans*=a;
            a*=a;
            ans%=mod;
            a%=mod;
            b>>=1;
        }
        return ans;
    }
}
