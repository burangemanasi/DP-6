//5. Longest Palindromic Substring - https://leetcode.com/problems/longest-palindromic-substring/description/
//Time Complexity: O(n^2) ~ for each char we must check the palindrome substring
//Space Complexity: O(1)

class Solution {
    int max;
    int start, end;
    public String longestPalindrome(String s) {
        int n = s.length();

        for(int i=0; i<n; i++){
            extendAround(s, i, i); //for odd palindrome length
            if(i<n-1 && s.charAt(i) == s.charAt(i+1)){
                extendAround(s, i, i+1);//for even length palindrome
            }
        }
        return s.substring(start, end+1);
    }

    private void extendAround(String s, int left, int right){
        while(left >=0 && right<s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        //bring them inbound to store the found palindrome length
        left++;
        right--;

        if(max < right - left){
            start = left;
            end = right;
            max = right-left;
        }
    }
}

//DP
//Time Complexity: O(n^2)
//Space Complexity: O(n^2)
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start=0, end=0; //pointers to track the palindrome string

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    //if i and j is either same character or there is no character in middle
                    //eg., string is 'bb' or 'b'
                    if(i-j <=1 || dp[i-1][j+1]){ //diagonal up
                        dp[i][j] = true;
                        if(end - start < i-j){
                            start=j;
                            end=i;
                        }
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return s.substring(start, end+1);
    }
}

//DP - Optimizing on Space
//Time Complexity: O(n^2)
//Space Complexity: O(n)
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        int start=0, end=0; //pointers to track the palindrome string

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(s.charAt(i) == s.charAt(j)){
                    //if i and j is either same character or there is no character in middle
                    //eg., string is 'bb' or 'b'
                    if(i-j <=1 || dp[j+1]){ //diagonal up
                        dp[j] = true;
                        if(end - start < i-j){
                            start=j;
                            end=i;
                        }
                    } else {
                        dp[j] = false;
                    }
                } else {
                    dp[j] = false;
                }
            }
        }
        return s.substring(start, end+1);
    }
}