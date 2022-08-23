package com.practice.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TreeDP06 {

   static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //use in for reading input
    //use out for printing output

    static class Pair {
        int max;
        int count;

        Pair(int m, int c) {
            max = m;
            count = c;
        }
    }

    static int[] value;
    static int[] parent;
    static ArrayList<Integer>[] childs;
    static int[][] table;
    static int MAXBIT = 19;
    static Pair[] upperCeil;

    static int cal(int u, int w) {
        int curr = u;

        /*while (true) {
            int p = parent[curr];
            curr = p;
            if (upperCeil[p].max <= w)
                break;
        }*/

        for(int i=MAXBIT; i>=0;i--) {
            int p = table[i][curr];
            if (upperCeil[p].max > w) {
                curr = p;
            }
        }

        curr = parent[curr];

        return upperCeil[u].count - upperCeil[curr].count;
    }

    static void dfs(int node, int max, int count) {
        if (value[node] > max) {
            max = value[node];
            count++;
        }
        upperCeil[node] = new Pair(max, count);

        for (int c : childs[node]) {
            dfs(c, max, count);
        }
    }

    static void build(int n) {
        table = new int[MAXBIT+1][n + 1];
        table[0] = parent;
        for (int b = 1; b <= MAXBIT; b++) {
            for (int i = 1; i <= n; i++) {
                int p = table[b - 1][i];
                table[b][i] = table[b - 1][p];
            }
        }
    }

    static void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        value = new int[n + 1];

        String[] inp = in.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(inp[i]);
            value[i + 1] = v;
        }

        inp = in.readLine().split(" ");

        childs = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            childs[i] = new ArrayList<>();
        }

        parent = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            int p = Integer.parseInt(inp[i - 2]);
            parent[i] = p;
            childs[p].add(i);
        }
        upperCeil = new Pair[n + 1];
        upperCeil[0] = new Pair(0, 0);
        dfs(1, 0, 0);

        build(n);

        int pr = 0;
        int q = Integer.parseInt(in.readLine());

        while (q-- > 0) {
            inp = in.readLine().split(" ");
            int u = pr ^ Integer.parseInt(inp[0]);
            int w = pr ^ Integer.parseInt(inp[1]);
            pr = cal(u, w);
            out.println(pr);
        }
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            solve();
        }
        out.close();
    }
}

/*
1
5
2 3 1 4 5
1 2 2 3
2
2 1
1 0
 */

/*
2
1
*/
