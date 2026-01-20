import java.util.*;;
public class GroupAnagrams {
    // 49. Group Anagrams
    // https://leetcode.com/problems/group-anagrams/


    public static void main(String[] args) {
        String[] strs={"eat","tea","tan","ate","nat","bat"};
        GroupAnagrams obj=new GroupAnagrams();
        List<List<String>> ans = obj.groupAnagrams(strs);
        System.out.println("Grouped Anagrams: " + ans);
        
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map=new HashMap<>();
        for(String str:strs){
            char[] ch=str.toCharArray();
            Arrays.sort(ch);
            String key=String.valueOf(ch);
            if(!map.containsKey(key)){
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());

    }

}