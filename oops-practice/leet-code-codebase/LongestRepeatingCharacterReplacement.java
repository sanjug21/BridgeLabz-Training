public class LongestRepeatingCharacterReplacement {
    // 424. Longest Repeating Character Replacement
    // https://leetcode.com/problems/longest-repeating-character-replacement/
    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement obj=new LongestRepeatingCharacterReplacement();
        String s="AABABBA";
        int k=1;
        System.out.println(obj.characterReplacement(s, k));
    }
    
    public int characterReplacement(String s, int k) {
        int ans = 0;
        int fre[] = new int[26];
        int i = 0;
        int maxFre = 0;
        for (int j = 0; j < s.length(); j++) {
            maxFre = Math.max(maxFre, ++fre[s.charAt(j) - 'A']);
            if (j - i + 1 - maxFre > k) {
                fre[s.charAt(i++) - 'A']--;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}