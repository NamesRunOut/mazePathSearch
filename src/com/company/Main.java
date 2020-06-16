package com.company;

class stack{
    char[] a;
    int n;

    public stack() {
        a = new char[2048];
        n=0;
    }
    int currentSize(){
        return n;
    }
    char top(){
        return a[n-1];
    }
    void pop(){
        n--;
    }
    void push(char x){
        a[n]=x;
        n++;
    }
    boolean isEmpty(){
        return n == 0;
    }
}

class findPathRecursively {
    public static void printResult(stack a){
        for (int i=1;i<a.currentSize();i++){
            System.out.print(a.a[i]+" ");
        }
        System.out.println();
    }

    public static stack result = new stack();
    public static boolean findPath(stack result, int[][] a, int startX, int startY, int endX, int endY, int n, int m, char mode) {
        if (startY<0 || startY>=m || startX<0 || startX>=n) return false;
        if (a[startY][startX]==1 || a[startY][startX]==2) return false;
        if (startY==endY && startX==endX) {result.push(mode); return true;}
        a[startY][startX]=2;
        result.push(mode);
        if (findPath(result, a, startX, (startY + 1), endX, endY, n, m, 'S')) return true;
        if (findPath(result, a, startX, (startY - 1), endX, endY, n, m, 'N')) return true;
        if (findPath(result, a, (startX + 1), startY, endX, endY, n, m, 'E')) return true;
        if (findPath(result, a, (startX - 1), startY, endX, endY, n, m, 'W')) return true;
        result.pop();
        return false;
    }
}

class findPathIteratively{
    public static void printResult(stack a){
        for (int i=0;i<a.currentSize();i++){
            System.out.print(a.a[i]+" ");
        }
        System.out.println();
    }

    public static stack findPath(int[][] a, int startX, int startY, int endX, int endY, int n, int m) {
        stack result = new stack();
        int i=startY;
        int j=startX;
        int prev=0;
        boolean notHere=false;
        while(i!=endY || j!=endX){
            if (i+1<m && a[i+1][j]!=1 && a[i+1][j]!=2 && (prev!=2 || notHere)) {
                i = i + 1;
                result.push('S');
                if (notHere) notHere=false;
                if (i==endY && j==endX) break;
                if ((i+1>=m || a[i+1][j]==1 || a[i+1][j]==2) && (j-1<0 || a[i][j-1]==1 || a[i][j-1]==2) && (j+1>=n || a[i][j+1]==1 || a[i][j+1]==2)){
                    a[i][j] = 2;
                    i = i - 1;
                    result.pop();
                    notHere=true;
                }
                char t1, t2;
                if (result.currentSize()>=2) {
                    t1 = result.top();result.pop();
                    t2 = result.top();result.pop();
                    if (t2 != 'N' || t1 != 'S') {
                        result.push(t2); result.push(t1);
                    }
                }
                prev=1;
            } else if(i-1>=0 && a[i-1][j]!=1 && a[i-1][j]!=2 && (prev!=1 || notHere)){
                i=i-1;
                result.push('N');
                if (notHere) notHere=false;
                if (i==endY && j==endX) break;
                if ((i-1<0 || a[i-1][j]==1 || a[i-1][j]==2) && (j-1<0 || a[i][j-1]==1 || a[i][j-1]==2) && (j+1>=n || a[i][j+1]==1 || a[i][j+1]==2)){
                    a[i][j]=2;
                    i=i+1;
                    result.pop();
                    notHere=true;
                }
                prev=2;
                char t1, t2;
                if (result.currentSize()>=2) {
                    t1 = result.top();result.pop();
                    t2 = result.top();result.pop();
                    if (t2 != 'S' || t1 != 'N') {
                        result.push(t2); result.push(t1);
                    }
                }
            } else if(j+1<n && a[i][j+1]!=1 && a[i][j+1]!=2 && (prev!=4 || notHere)){
                j=j+1;
                result.push('E');
                if (notHere) notHere=false;
                if (i==endY && j==endX) break;
                if ((i-1<0 || a[i-1][j]==1 || a[i-1][j]==2) && (i+1>=m || a[i+1][j]==1 || a[i+1][j]==2) && (j+1>=n || a[i][j+1]==1 || a[i][j+1]==2)) {
                    a[i][j] = 2;
                    j=j-1;
                    result.pop();
                    notHere=true;
                }
                prev=3;
                char t1, t2;
                if (result.currentSize()>=2) {
                    t1 = result.top();result.pop();
                    t2 = result.top();result.pop();
                    if (t2 != 'W' || t1 != 'E') {
                        result.push(t2); result.push(t1);
                    }
                }
            } else if(j-1>=0 && a[i][j-1]!=1 && a[i][j-1]!=2 && (prev!=3 || notHere)){
                j=j-1;
                result.push('W');
                if (notHere) notHere=false;
                if (i==endY && j==endX) break;
                if ((i-1<0 || a[i-1][j]==1 || a[i-1][j]==2) && (i+1>=m || a[i+1][j]==1 || a[i+1][j]==2) && (j-1<0 || a[i][j-1]==1 || a[i][j-1]==2)) {
                    a[i][j] = 2;
                    j = j + 1;
                    result.pop();
                    notHere=true;
                }
                prev=4;
                char t1, t2;
                if (result.currentSize()>=2) {
                    t1 = result.top();result.pop();
                    t2 = result.top();result.pop();
                    if (t2 != 'E' || t1 != 'W') {
                        result.push(t2); result.push(t1);
                    }
                }
            } else {
                stack resultX = new stack();
                resultX.push('X');
                return resultX;
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        int n = 5; // width
        int m = 5; // height
        int[][] a = {
                {1,1,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,0},
                {0,0,0,1,0},
                {0,1,0,1,0}
        };
        int[][] aCopy = {
                {1,1,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,0},
                {0,0,0,1,0},
                {0,1,0,1,0}
        };
        // 1 - marks a wall
        // 0 - marks a corridor
        // 2 - already visited corridor
        int startN = 0;
        int startM = 4;
        // start coords
        int endN = 4;
        int endM = 4;
        // target coords
        stack result = new stack();

        // prints 'X' if path wasn't found
        // otherwise returns a set of directions {'N','E','W','S'} indicating a path to follow from starting position, to reach the target
        System.out.print("Recursive: ");
        if (!findPathRecursively.findPath(result, a, startN, startM, endN, endM, n, m, 'X')) System.out.println("X");
        else findPathRecursively.printResult(result);
        System.out.print("Iterative: ");
        findPathIteratively.printResult(findPathIteratively.findPath(aCopy, startN, startM, endN, endM, n, m));
    }
}
