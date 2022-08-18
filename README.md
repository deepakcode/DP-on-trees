# DP-on-trees

### 

Given a tree with N nodes and N-1 edges, calculate the maximum sum of the node values from root to any of the leaves without re-visiting any node. 

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
