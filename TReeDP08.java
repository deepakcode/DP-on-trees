package com.practice.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TReeDP08 {

    //Tree and Extra Edge
    // Not giving correct answer!
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);

    static class Pair {
        int nbr;
        int w;

        Pair(int n, int w) {
            nbr = n;
            this.w = w;
        }
    }

    static ArrayList<Pair>[] adj;
    static long[] Node2RootW;
    static int[][] table;
    static int MAXBIT = 17;
    static long[] maxChild;
    static int[] depth;

    static long dfs(int node, long sum, int p) {
        Node2RootW[node] = sum;
        table[0][node] = p;
        long max = 0;
        for (Pair edge : adj[node]) {
            if (edge.nbr != p) {
                depth[edge.nbr] = depth[node] + 1;
                long m = dfs(edge.nbr, sum + edge.w, node);
                max = Math.max(max, Math.max(m + edge.w, edge.w));
            }
        }
        maxChild[node] = max;
        return max;
    }

    static int lca(int a, int b) {
        // depth[a] < depth[b]
        if (depth[a] > depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        int k = depth[a] + depth[b];
        for (int i = MAXBIT; i >= 0; i--) {
            int mask = 1 << i;
            if ((k & mask) > 0) {
                b = table[i][b];
            }

        }
        if (a == b) return a;
        for (int i = MAXBIT; i >= 0; i--) {
            int ap = table[i][a];
            int bp = table[i][b];
            if (ap != bp) {
                a = ap;
                b = bp;
            }
        }
        return table[0][a];
    }

    static void build(int n) {
        for (int j = 1; j <= MAXBIT; j++) {
            for (int i = 1; i <= n; i++) {
                int par = table[j - 1][i];
                table[j][i] = table[j - 1][par];
            }
        }
    }

    static void solve() throws Exception {
        String inp[] = in.readLine().trim().split(" ");
        int n = Integer.parseInt(inp[0]);
        int q = Integer.parseInt(inp[1]);
        adj = new ArrayList[n + 1];// 1 .... n
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            String inps[] = in.readLine().split(" ");
            int u = Integer.parseInt(inps[0]);
            int v = Integer.parseInt(inps[1]);
            int w = Integer.parseInt(inps[1]);

            adj[u].add(new Pair(v, w));
            adj[v].add(new Pair(u, w));
        }
        Node2RootW = new long[n + 1];
        table = new int[MAXBIT + 1][n + 1];
        maxChild = new long[n + 1];

        depth = new int[n + 1];

        dfs(1, 0, 0);
        build(n);
        while (q-- > 0) {

            String inps[] = in.readLine().split(" ");
            int u = Integer.parseInt(inps[0]);
            int v = Integer.parseInt(inps[1]);
            int x = Integer.parseInt(inps[1]);

            int lca = lca(u, v);
            long lcaSum = Node2RootW[u] - Node2RootW[lca] + Node2RootW[v] - Node2RootW[lca];
            long esum = maxChild[u] + maxChild[v] + x;
            out.println(Math.max(lcaSum, esum));
        }
    }

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            solve();
        }
        out.close();
    }
}
/*
Sample Input:
1
7 3
1 2 1
1 3 -2
2 4 3
2 5 -4
5 7 5
3 6 6
2 3 1
5 4 2
5 6 0
Sample Output:
10
7
5
 */