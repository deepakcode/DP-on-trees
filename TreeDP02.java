package com.practice;

import java.util.Arrays;
import java.util.LinkedList;

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