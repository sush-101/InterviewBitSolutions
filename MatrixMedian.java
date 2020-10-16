www.interviewbit.com/problems/matrix-median/

public class Solution {
    public int findMedian(int[][] A) {
        int left = Integer.MAX_VALUE,right = Integer.MIN_VALUE;
        int row = A.length,col = A[0].length;
        int result = -1;
        
        for(int i=0;i<row;i++){
            
            if(A[i][0] < left)left = A[i][0];
            
            if(A[i][col-1] > right)right = A[i][col-1];
        }
        
        while(left<=right){
            int mid = (left+right)/2;
            int lessthanmid = 0;
            int count = 0; //To count no.of times mid appears in the matrix
            
            for(int i=0;i<row;i++){
                
                //custom binarySearchCustom method, returns
                //-ve for INSERTION point, 
                //+ve -> index of LAST occurrenceof mid
                int index = binarySearchCustom(A[i],mid);
                
                //numbers at (i < index) are strictly less than mid incaseof INSERTION point
                if(index < 0){
                    index = -index-1;
                    lessthanmid += index;
                }
                
                //index of last occurrence of mid -> numbers at (i < index) may or 
                //may not be strictly less than mid
                else{
                    //not including A[i][index] to lessthanmid counter
                    lessthanmid += index;
                    count++;
                    
                    //if mid occurs at multiple rows, then 
                    //include A[i][index] to lessthanmid counter
                    if(count > 1){
                        lessthanmid += 1;
                    }
                }
            }
            
            if(lessthanmid == (row*col)/2){
                if(count == 0)left = mid+1;
                else return mid;
            }else if(lessthanmid > (row*col)/2){
                if(count > 0)result = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
            
        }
        
        return result;
    }
    int binarySearchCustom(int arr[],int target){
        int n = arr.length,left = 0,right = n-1;
        int result = -1;
        while(left <= right){
            int mid = (left+right)/2;
            if(arr[mid] == target){
                if(mid < n-1 && arr[mid] == arr[mid+1]){
                    left = mid+1;
                }else return mid;
                
            }else if(arr[mid] < target){
                left = mid+1;
                
            }else{
                right = mid-1;
            }
        }
        return -left-1;
    }
}
