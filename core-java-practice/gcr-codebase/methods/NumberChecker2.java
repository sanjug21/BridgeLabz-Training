import java.util.*;

public class NumberChecker2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a number to check: ");
        int number=sc.nextInt();
        
        int count=countDigits(number);
        int[] digits=getDigits(number);
        
        System.out.println("Count of digits: "+count);
        System.out.println("Digits: "+Arrays.toString(digits));
        System.out.println("Is Duck Number: "+isDuckNumber(digits));
        System.out.println("Is Armstrong Number: "+isArmstrong(digits));
        
        int[] largest=findLargestAndSecondLargest(digits);
        System.out.println("Largest: "+largest[0]+", Second Largest: "+largest[1]);
        
        int[] smallest=findSmallestAndSecondSmallest(digits);
        System.out.println("Smallest: "+smallest[0]+", Second Smallest: "+smallest[1]);
        
        System.out.println("Sum of digits: "+sumDigits(digits));
        System.out.println("Sum of squares of digits: "+sumSquareDigits(digits));
        System.out.println("Is Harshad Number: "+isHarshad(number));
        
        int[][] freq=getDigitFrequency(number);
        System.out.println("Digit Frequency:");
        for(int[] f:freq)if(f[1]>0)System.out.println("Digit "+f[0]+": "+f[1]);
        
        System.out.println("Is Palindrome: "+isPalindrome(digits));
        System.out.println("Is Prime: "+isPrime(number));
        System.out.println("Is Neon: "+isNeon(number));
        System.out.println("Is Spy: "+isSpy(number));
        System.out.println("Is Automorphic: "+isAutomorphic(number));
        System.out.println("Is Buzz: "+isBuzz(number));
        System.out.println("Is Perfect: "+isPerfect(number));
        System.out.println("Is Abundant: "+isAbundant(number));
        System.out.println("Is Deficient: "+isDeficient(number));
        System.out.println("Is Strong: "+isStrong(number));
        
        sc.close();
    }

    public static int countDigits(int n){
        if(n==0)return 1;
        int count=0;
        while(n!=0){
            n/=10;
            count++;
        }
        return count;
    }

    public static int[] getDigits(int n){
        int count=countDigits(n);
        int[] digits=new int[count];
        int temp=n;
        for(int i=count-1;i>=0;i--){
            digits[i]=temp%10;
            temp/=10;
        }
        return digits;
    }

    public static boolean isDuckNumber(int[] digits){
        for(int i=0;i<digits.length;i++)if(digits[i]==0)return true;
        return false;
    }

    public static boolean isArmstrong(int[] digits){
        int sum=0;
        int p=digits.length;
        for(int d:digits)sum+=Math.pow(d,p);
        // Reconstruct number from digits to compare
        int num=0;
        for(int d:digits)num=num*10+d;
        return sum==num;
    }

    public static int[] findLargestAndSecondLargest(int[] digits){
        int max=Integer.MIN_VALUE;
        int secondMax=Integer.MIN_VALUE;
        for(int d:digits){
            if(d>max){
                secondMax=max;
                max=d;
            }else if(d>secondMax && d!=max){
                secondMax=d;
            }
        }
        return new int[]{max,secondMax};
    }

    public static int[] findSmallestAndSecondSmallest(int[] digits){
        int min=Integer.MAX_VALUE;
        int secondMin=Integer.MAX_VALUE;
        for(int d:digits){
            if(d<min){
                secondMin=min;
                min=d;
            }else if(d<secondMin && d!=min){
                secondMin=d;
            }
        }
        return new int[]{min,secondMin};
    }

    public static int sumDigits(int[] digits){
        int sum=0;
        for(int d:digits)sum+=d;
        return sum;
    }

    public static double sumSquareDigits(int[] digits){
        double sum=0;
        for(int d:digits)sum+=Math.pow(d,2);
        return sum;
    }

    public static boolean isHarshad(int n){
        int sum=sumDigits(getDigits(n));
        return n%sum==0;
    }

    public static int[][] getDigitFrequency(int n){
        int[][] freq=new int[10][2];
        for(int i=0;i<10;i++)freq[i][0]=i;
        int[] digits=getDigits(n);
        for(int d:digits)freq[d][1]++;
        return freq;
    }

    public static int[] reverseArray(int[] digits){
        int[] rev=new int[digits.length];
        for(int i=0;i<digits.length;i++)rev[i]=digits[digits.length-1-i];
        return rev;
    }

    public static boolean areArraysEqual(int[] a,int[] b){
        if(a.length!=b.length)return false;
        for(int i=0;i<a.length;i++)if(a[i]!=b[i])return false;
        return true;
    }

    public static boolean isPalindrome(int[] digits){
        int[] rev=reverseArray(digits);
        return areArraysEqual(digits,rev);
    }

    public static boolean isPrime(int n){
        if(n<=1)return false;
        for(int i=2;i<=Math.sqrt(n);i++)if(n%i==0)return false;
        return true;
    }

    public static boolean isNeon(int n){
        int sq=n*n;
        int sum=sumDigits(getDigits(sq));
        return sum==n;
    }

    public static boolean isSpy(int n){
        int[] digits=getDigits(n);
        int sum=0;
        int prod=1;
        for(int d:digits){
            sum+=d;
            prod*=d;
        }
        return sum==prod;
    }

    public static boolean isAutomorphic(int n){
        int sq=n*n;
        String s1=String.valueOf(n);
        String s2=String.valueOf(sq);
        return s2.endsWith(s1);
    }

    public static boolean isBuzz(int n){
        return n%7==0 || n%10==7;
    }

    public static boolean isPerfect(int n){
        int sum=0;
        for(int i=1;i<n;i++)if(n%i==0)sum+=i;
        return sum==n;
    }

    public static boolean isAbundant(int n){
        int sum=0;
        for(int i=1;i<n;i++)if(n%i==0)sum+=i;
        return sum>n;
    }

    public static boolean isDeficient(int n){
        int sum=0;
        for(int i=1;i<n;i++)if(n%i==0)sum+=i;
        return sum<n;
    }

    public static boolean isStrong(int n){
        int[] digits=getDigits(n);
        int sum=0;
        for(int d:digits)sum+=factorial(d);
        return sum==n;
    }

    public static int factorial(int n){
        int f=1;
        for(int i=1;i<=n;i++)f*=i;
        return f;
    }
}