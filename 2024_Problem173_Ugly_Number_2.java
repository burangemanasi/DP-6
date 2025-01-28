//264. Ugly Number 2 - https://leetcode.com/problems/ugly-number-ii/description/
//Time Complexity: O(n*log(N)) ~ N: greater than 'n'
//Space Complexity: O(N)

class Solution {
    public int nthUglyNumber(int n) {
        //default minHeap
        PriorityQueue<Long> pq = new PriorityQueue<>(); //to process in increasing order
        HashSet<Long> set = new HashSet<>(); //visited set to avoid duplicate entry in pq

        int[] prime = {2,3,5};
        long currUgly = 1;

        pq.add(currUgly);
        set.add(currUgly);

        while(n > 0){
            currUgly = pq.poll();
            for(int pr: prime){
                long newUgly = currUgly * pr;
                if(!set.contains(newUgly)){
                    pq.add(newUgly);
                    set.add(newUgly);
                }
            }
            n--;
        }
        return (int)currUgly;
    }
}

//Optimal Solution using 3 Pointers
//Time Complexity: O(n)
//Space Complexity: O(n)
class Solution {
    public int nthUglyNumber(int n) {
        int[] arr = new int[n];
        arr[0] = 1;

        int p2 = 0, p3 = 0, p5 = 0;
        int n2 = 2, n3 = 3, n5 = 5;

        for(int i=1; i<n; i++){
            arr[i] = Math.min(n2, Math.min(n3, n5));

            if(arr[i] == n2){
                p2++;
                n2 = arr[p2] * 2; //uglyNumber:2
            }
            if(arr[i] == n3){
                p3++;
                n3 = arr[p3] * 3; //uglyNumber:3
            }
            if(arr[i] == n5){
                p5++;
                n5 = arr[p5] * 5; //uglyNumber:5
            }
        }

        return arr[n-1];
    }
}