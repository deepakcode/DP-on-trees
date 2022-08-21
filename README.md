# DP-on-trees (Personal notes)

### 1 Given a tree with N nodes and N-1 edges, calculate the maximum sum of the node values from root to any of the leaves without re-visiting any node. 

![image](https://user-images.githubusercontent.com/13814143/185197407-55d15ee2-d1d0-44dc-a5e0-5202f1e5be7f.png)

All the paths are marked by different colors : 

Path 1(red, 3-2-1-4) : sum of all node values = 10 

Path 2(orange, 3-2-1-5) : sum of all node values = 11 

Path 3(yellow, 3-2-3) : sum of all node values = 8 

Path 4(green, 3-1-9-9) : sum of all node values = 22 

Path 5(violet, 3-1-9-8) : sum of all node values = 21 

Path 6(pink, 3-10-1) : sum of all node values = 14 

Path 7(blue, 3-10-5) : sum of all node values = 18 

Path 8(brown, 3-10-3) : sum of all node values = 16 

**The answer is 22, as Path 4 has the maximum sum of values of nodes in its path from a root to leaves.** 

```java
package com.practice;
import java.util.LinkedList;

class TreeDP01
{
    static int[] dp = new int[100];

    static void dfs(int[] a, LinkedList<Integer>[] tree, int node, int parent) {
        dp[node] = a[node - 1];
        int maxOfNodes = 0; // Not Integer.MIN_VALUE; think why?
        for (int child : tree[node]) {

            if(child==parent)
                continue;

            dfs(a,tree,child,node);
            maxOfNodes = Math.max(maxOfNodes, dp[child]);
        }
        dp[node]= dp[node]+maxOfNodes;
    }

    //use DFS here
    static int maximumValue(int[] a,
                            LinkedList<Integer>[] tree){
        dfs(a, tree, 1, 0);
        return dp[1];
    }

    public static void main(String[] args)
    {
        int n = 14;
        LinkedList<Integer>[] tree = new LinkedList[n + 1];
        for (int i = 0; i < tree.length; i++)
            tree[i] = new LinkedList<>();

        tree[1].add(2); tree[2].add(1);
        tree[1].add(3); tree[3].add(1);
        tree[1].add(4); tree[4].add(1);
        tree[2].add(5); tree[5].add(2);
        tree[2].add(6); tree[6].add(2);
        tree[3].add(7); tree[7].add(3);
        tree[4].add(8); tree[8].add(4);
        tree[4].add(9); tree[9].add(4);
        tree[4].add(10); tree[10].add(4);
        tree[5].add(11); tree[11].add(5);
        tree[5].add(12); tree[12].add(5);
        tree[7].add(13); tree[13].add(7);
        tree[7].add(14); tree[14].add(7);

        int a[] = { 3, 2, 1, 10, 1, 3, 9,
                1, 5, 3, 4, 5, 9, 8 };

        System.out.println(maximumValue(a, tree));
    }
}
```

 
### 2 Given the structure of a company, your task is to calculate for each employee the number of their subordinates.

Input

The first input line has an integer n: the number of employees. The employees are numbered 1,2,…,n, and employee 1 is the general director of the company.

After this, there are n−1 integers: for each employee 2,3,…,n their direct boss in the company.

Output

Print n integers: for each employee 1,2,…,n the number of their subordinates.

Constraints
1≤n≤2⋅105
Time limit: 1.00 s
Memory limit: 512 MB

Example

Input:
5
1 1 2 3

Output:
4 1 1 0 0



**logic : In DFS while going down assing 1 to each node, while coming back start counting and sum up the value assinged (1).**

<img width="1107" alt="Screenshot 2022-08-18 at 5 08 50 PM" src="https://user-images.githubusercontent.com/13814143/185386190-b8602f7e-2e7f-4b7b-9e7b-6b96cfee156b.png">

![visualization](https://user-images.githubusercontent.com/13814143/185389209-2b6688ec-14cc-49cb-8371-41087356e061.gif)


```java
package com.practice;
import java.util.LinkedList;

class TreeDP01 {
    
    static int[] dp = new int[100];

    static void dfs(LinkedList<Integer>[] tree, int node, int parent) {
        dp[node-1] = 1;
        int count=0;
        for (int child : tree[node]) {
            if(child==parent)
                continue;
            dfs(tree,child,node);
            dp[node-1] = dp[node-1] + dp[child-1];
        }
    }

    //use DFS here
    static void countSubOrdinate(LinkedList<Integer>[] tree){
        dfs(tree, 1, 0);
        for(int i=0; i<5 ; i++){
            System.out.print((i+1)+"-"+(dp[i]-1)+" \n");
        }
    }

    public static void main(String[] args){
        int n = 14;
        LinkedList<Integer>[] tree = new LinkedList[n + 1];
        for (int i = 0; i < tree.length; i++)
            tree[i] = new LinkedList<>();

        tree[1].add(2); tree[2].add(1);
        tree[1].add(3); tree[3].add(1);
        tree[2].add(4); tree[4].add(2);
        tree[3].add(5); tree[5].add(3);

        countSubOrdinate(tree);
    }
}

```

This question can be solved using BFS/level order as well. 

<img width="932" alt="Screenshot 2022-08-18 at 5 14 31 PM" src="https://user-images.githubusercontent.com/13814143/185387616-ade03f29-3dd2-4ea1-9985-7a2645bfd083.png">

### 3 You are given a tree consisting of n nodes.A matching is a set of edges where each node is an endpoint of at most one edge. What is the maximum number of edges in a matching?

https://cses.fi/problemset/task/1130

Input

The first input line contains an integer n: the number of nodes. The nodes are numbered 1,2,…,n.

Then there are n−1 lines describing the edges. Each line contains two integers a and b: there is an edge between nodes a and b.

Output

Print one integer: the maximum number of pairs.

Constraints
1≤n≤2⋅105
1≤a,b≤n
Example

Input:

5

1  2

1  3

3  4

3  5

Output:
2

Explanation: One possible matching is (1,2) and (3,4).

<img width="527" alt="Screenshot 2022-08-18 at 6 12 00 PM" src="https://user-images.githubusercontent.com/13814143/185397359-9f742153-f5d5-43c7-92d2-2d7ac24c0691.png">

Solution : 

#### if not select root node then this case 
#### if root is selected then this case - 

<img width="315" alt="Screenshot 2022-08-18 at 6 06 08 PM" src="https://user-images.githubusercontent.com/13814143/185397838-d978d416-2656-457e-9cf3-5bf5c4dbad1d.png">

```java
public class TreeDP02 {

    static int[][] dp = new int[200001][2];

    static int treeMatching(int vertex, int isIncluded, LinkedList<Integer>[] tree, int parent) {

        //if present in dp then return it.
        if (dp[vertex][isIncluded] != -1)
            return dp[vertex][isIncluded];

        // included
        if (isIncluded == 1) {
            int ans = 0;
            // first do sum of all neighbour vertices! (take everything then minus the one which is not required)
            for (int neig : tree[vertex]) {
                if (neig != parent) {
                    ans += treeMatching(neig, 1, tree, vertex);
                }
            }

            int myres = 0;
            for (int neig : tree[vertex]) {
                if (neig != parent) {
                //(take everything then minus the one which is not required)
                int temp = 1 + (ans - treeMatching(neig, 1, tree, vertex))
                        + treeMatching(neig, 0, tree, vertex);

                    myres = Math.max(myres, temp);
                }
            }
            dp[vertex][isIncluded] = myres;
            return myres;
        } else {
            int ans = 0;
            for (int neig : tree[vertex]) {
                if (neig != parent) {
                    int a = treeMatching(neig, 1, tree, vertex);
                    ans += Math.max(a, treeMatching(neig, 0, tree, vertex));
                }
            }
            dp[vertex][isIncluded] = ans;
            return ans;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        LinkedList<Integer>[] tree = new LinkedList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            tree[i] = new LinkedList<>();
        }
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        tree[1].add(2);
        tree[2].add(1);
        tree[1].add(3);
        tree[3].add(1);
        tree[3].add(4);
        tree[4].add(3);
        tree[5].add(3);
        tree[3].add(5);

        int r = Math.max(
                treeMatching(1, 0, tree, -1),
                treeMatching(1, 1, tree, -1));
        System.out.println(r);
    }
}
```

### 4 Concept - Binary lifting 

https://cses.fi/problemset/task/1687




### A company has n employees, who form a tree hierarchy where each employee has a boss, except for the general director.

Your task is to process q queries of the form: who is employee x's boss k levels higher up in the hierarchy?

Input

The first input line has two integers n and q: the number of employees and queries. The employees are numbered 1,2,…,n, and employee 1 is the general director.

The next line has n−1 integers e2,e3,…,en: for each employee 2,3,…,n their boss.

Finally, there are q lines describing the queries. Each line has two integers x and k: who is employee x's boss k levels higher up?

Output

Print the answer for each query. If such a boss does not exist, print −1.

Constraints

    Time limit: 1.00 s 
    
    Memory limit: 512 MB
    
    1≤n,q≤2⋅105
    
    1≤ei≤i−1
    
    1≤x≤n
    
    1≤k≤n
    
Example

Input:

    5 3
    
    1 1 3 3
    
    4 1
    
    4 2
    
    4 3

Output:

    3
    
    1
    
    -1

```java
import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 17;

    static int table[][];

    static void build(int p[]) {

        int n = p.length;
        table = new int[MAX][n];

        for (int i = 0; i < n; i++) {
            table[0][i] = p[i];
        }
        for (int i = 1; i < MAX; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = table[i - 1][table[i - 1][j]];
            }
        }
    }

    static int query(int a, int k) {
        for (int i = 0; i < MAX; i++) {
            int mask = (1 << i);
            if ((k & mask) > 0) {
                a = table[i][a];
            }
        }
        return a;
    }
    

  public static void main(String[] args) throws Exception {
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(read.readLine());

    int[] parent = new int[n];

    for (int i = 0; i < n; i++) {
      parent[i] = Integer.parseInt(read.readLine());
    }
    int q = Integer.parseInt(read.readLine());
    // write your code here
    build(parent);

        while (q-- > 0) {
            String inp[] = read.readLine().split(" ");
            int a = Integer.parseInt(inp[0]);
            int k = Integer.parseInt(inp[1]);
            int ans = query(a, k);
            System.out.println(ans);
        }

  }
}
```

<img width="1033" alt="Screenshot 2022-08-19 at 3 38 26 PM" src="https://user-images.githubusercontent.com/13814143/185596538-c962ff8f-7a3f-4695-b891-27a546504aa3.png">


### 5 https://www.spoj.com/problems/LCA/

<img width="412" alt="Screenshot 2022-08-22 at 12 55 34 AM" src="https://user-images.githubusercontent.com/13814143/185807568-b46926c1-6a50-45f8-9a5d-0b1da8150d94.png">

```java
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    /*
    use in for reading input
    use out for printing output
    */
    static ArrayList<Integer>[] child;
    static int[] level;
    static int[] parent;
    static int MAXBIT = 9;
    static int[][] table;

    static void build(int n) {
        table = new int[MAXBIT + 1][n + 1];
        table[0] = parent;

        for (int p = 1; p <= MAXBIT; p++) {
            for (int i = 2; i <= n; i++) {
                int par = table[p - 1][i];
                table[p][i] = table[p - 1][par];
            }
        }
    }

    static void dfs(int node, int l) {
        level[node] = l;
        for (int c : child[node]) {
            parent[c] = node;
            dfs(c, l + 1);
        }
    }

    static int lca(int u, int v) {
        if (level[u] > level[v]) {
            int t = u;
            u = v;
            v = t;
        }
        ////while is not optimized
        // --level[v] > level[u]
        /*while (level[u] != level[v]) {
            v = parent[v];
        }*/

        int k = level[v] - level[u];
        for (int i = MAXBIT; i >= 0; i--) {
            int mask = 1 << i;
            if ((k & mask) > 0) {
                v = table[i][v];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = MAXBIT; i >= 0; i--) {
            int up = table[i][u];
            int vp = table[i][v];
            if (up != vp) {
                u = up;
                v = vp;
            }
        }
        u = parent[u];

        ////while is not optimized
        /*while (u != v) {
            u = parent[u];
            v = parent[v];
        }*/
        return u;
    }

    public static void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());

        child = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++)
            child[i] = new ArrayList<>();

        for (int i = 1; i <= n; i++) {// {count, c1, c2, c3, .....}

            String inp[] = in.readLine().split(" ");

            int count = Integer.parseInt(inp[0]);

            for (int j = 1; j <= count; j++) {
                int u = Integer.parseInt(inp[j]);
                child[i].add(u);
            }
        }

        level = new int[n + 1];
        parent = new int[n + 1];

        dfs(1, 1);
        build(n);
        int q = Integer.parseInt(in.readLine());

        while (q-- > 0) {
            String inp[] = in.readLine().split(" ");
            int u = Integer.parseInt(inp[0]);
            int v = Integer.parseInt(inp[1]);
            int ans = lca(u, v);
            out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(in.readLine());
        for (int i = 1; i <= t; i++) {
            out.println("Case " + i + ":");
            solve();
        }
        out.close();
    }
}
```

