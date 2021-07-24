//https://www.interviewbit.com/old/problems/maximum-area-of-triangle/
public class Solution {
    public int solve(ArrayList<String> A) {
        if(A == null || A.size() == 0 || A.size() == 1 || A.get(0).length() == 1) return 0;
        int res=0;
        int row = A.size(), col = A.get(0).length();
        
        //matrix storing positions of last and first occurances of each vertex in each column.
        //[col][3][2] -> 3d matrix -> col, 3 for rgb, 2 for first and last positions
        int pre[][][] = new int[col][3][2];
        
        for(int i=0;i<col;i++){
            for(int j=0;j<3;j++){
                Arrays.fill(pre[i][j],-1);
            }
        }
        // r=0,g=1,b=2
        //readability can be increased?
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                //capturing the first and last occurances of each vertex in each col
                // Eg. pre[i][0][0] stores first occurance of red in col 'i'
                // pre[i][0][1] stores last occurance of red in col 'i'
                //pre[j][1][1] stores last occurance of green in col 'j'
                
                if(A.get(i).charAt(j) == 'r'){
                    if(pre[j][0][0] == -1){
                        pre[j][0][0] = i;
                    }
                    pre[j][0][1]=i;
                }else if(A.get(i).charAt(j) == 'g'){
                    if(pre[j][1][0] == -1){
                        pre[j][1][0] = i;
                    }
                    pre[j][1][1]=i;
                }else{
                    if(pre[j][2][0] == -1){
                        pre[j][2][0] = i;
                    }
                    pre[j][2][1]=i;
                }
            }
        }
        
        //r g b -> r g as base b as third vertex
        res = Math.max(res, calculateArea(0,1,2,col,pre));
        
        // Similarly r b g and b g r
        res = Math.max(res,Math.max(calculateArea(0,2,1,col,pre),calculateArea(1,2,0,col,pre)));
        
        return res;
    }
    
    //calculate max area with a and b as base and c as third vertex, 
    public int calculateArea(int a, int b, int c, int col, int pre[][][]){
        int res = 0;
        
        //base starts at col 0 goes upto last col
        for(int i=0;i<col;i++){
            
            //localmax is the base length
            int localmax=0;
            
            //checking if both the vertices are there in column 'i'
            if(pre[i][b][0]!=-1 && pre[i][a][0]!=-1)
                localmax = Math.max(pre[i][b][1] - pre[i][a][0], pre[i][a][1]- pre[i][b][0])+1;
    
            //the third vertex
            for(int j=0;j<col;j++){
                if(pre[j][c][0] != -1){
                    int area = (int)Math.ceil(((float)(localmax)*(Math.abs(i-j)+1))/2);
                    res = Math.max(res, area);
                }
            }
        }
        return res;
    }
}
