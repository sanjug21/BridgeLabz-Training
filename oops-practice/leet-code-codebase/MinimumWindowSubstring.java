import java.util.HashMap;

public class MinimumWindowSubstring {
    // 76. Minimum Window Substring
    // https://leetcode.com/problems/minimum-window-substring/
    public static void main(String[] args) {
        MinimumWindowSubstring obj=new MinimumWindowSubstring();
        String s="ADOBECODEBANC";
        String t="ABC";
        System.out.println(obj.minWindow(s, t));
        
    }
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        HashMap<Character,Integer>tMap=new HashMap<>();
        HashMap<Character,Integer>sMap=new HashMap<>();
        int st=-1,ed=-1,len=Integer.MAX_VALUE;
        
        for(char c:t.toCharArray()){
         tMap.put(c,tMap.getOrDefault(c, 0)+1);
        }
     
        int have=0,need=tMap.size();
        int i=0;
       
        for(int j=0;j<s.length();j++){
            char c=s.charAt(j);
            
            sMap.put(c, sMap.getOrDefault(c, 0)+1);
            
            if (tMap.containsKey(c) && sMap.get(c).equals(tMap.get(c))) {
                have++;
            }
          
            while(have==need){
                if((j-i+1)<len){
                    st=i;
                    ed=j;
                    len=j-i+1;
                }
                char leftCh=s.charAt(i);
                sMap.put(leftCh, sMap.get(leftCh)-1);
                if(tMap.containsKey(leftCh)&& sMap.get(leftCh)<tMap.get(leftCh))have--;
                i++;
            }
        }
      
        return len==Integer.MAX_VALUE?"":s.substring(st,ed+1);

    }

}
