package com.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TreeDP05 {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    /*
    use in for reading input
    use out for printing output
    */
    static int parent[], jump[], table[][];
    static boolean[] occupied;
    static int MAXBIT = 18;

    static void setBit(int n) {
        MAXBIT = 0;
        while ((1 << MAXBIT) <= n) {
            MAXBIT++;
        }
        MAXBIT--;
    }

    static void build(int n) {
        table = new int[MAXBIT + 1][n + 1];
        table[0] = parent;
        for (int r = 1; r <= MAXBIT; r++) {
            for (int i = 1; i < n; i++) {
                int p = table[r - 1][i];
                table[r][i] = table[r - 1][p];
            }
        }
    }

    static int cal(int c) {
        int curr = jump[c];
        if (occupied[curr])
            return 0;

        int j = 1;


        //below code is not optimised
        /*while (true) {
            int p = parent[curr];
            if (occupied[p] == false) {
                j++;
                curr = p;
            } else {
                break;
            }
        }*/

        for (int i = MAXBIT; i >= 0; i--) {
            int jp = table[i][curr];// 2^j parent of curr

            if (occupied[jp]) {
            } else {
                curr = jp;
                j += 1 << i;
            }
        }

        occupied[curr] = true;
        return j;
    }

    public static void main(String[] args) throws IOException {
        // write your code here.
        int n = Integer.parseInt(in.readLine());
        setBit(n);
        parent = new int[n + 1];// 1 ... n
        String inp[] = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(inp[i]);
            parent[i + 1] = p;
        }
        occupied = new boolean[n + 1];
        occupied[0] = true;
        inp = in.readLine().split(" ");
        jump = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int j = Integer.parseInt(inp[i]);
            jump[i + 1] = j;
        }
        build(n);
        for (int i = 1; i <= n; i++) {
            int jumps = cal(i);
            out.println(jumps);
        }
        out.close();
    }
}
