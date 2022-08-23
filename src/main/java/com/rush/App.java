package com.rush;

import java.util.Random;

public class App {
    static int[][] DP;
    static int[][] S;  // table storing optimal positions of k
    static int matrices = 6;
    static int bound = 500; // maximum dimension of a matrix in any direction
    static Random rnd = new Random();

    App() {}

    App(int n, int b) {
        matrices = n;
        bound = b;
    }

    // filling up the tables
    public static void MCM (int[] p) {
        int n = matrices + 1;
        DP = new int[n][n];  // DP table
        S = new int[n][n];

        int j, dp;
        for (int l = 2; l < n; l++) {
            for (int i = 1; i < n - l + 1; i++) {
                j = i + l - 1;

                DP[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++){
                    dp = DP[i][k] + DP[k + 1][j] + (p[i - 1] * p[k] * p[j]);
                    if (dp < DP[i][j]) {
                        DP[i][j] = dp;
                        S[i][j] = k;
                    }
                }
            }
        }
    }

    public static int[][] findProduct(int i, int j, int[] p) {
        // base case; returning a matrix with those dimensions for which i = j.
        if (i == j) {
            return fillMatrix(p[i - 1], p[i]);
        } else {
            return multiply(findProduct(i, S[i][j], p), findProduct(S[i][j] + 1, j, p));
        }
    }

    public static int[][] fillMatrix(int m, int n) {
        int[][] M = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = rnd.nextInt();
            }
        }

        return M;
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        if (A[0].length != B.length) {
            return null;
        } else {
            int p = A.length;
            int q = A[0].length;
            int r = B[0].length;

            int[][] C = new int[p][r];

            for (int i = 0; i < p; i++) {
                for (int j = 0; j < r; j++) {
                    C[i][j] = 0;
                    for (int k = 0; k < q; k++) {
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
            return C;
        }
    }

    public static void main(String[] args) {
        // filling dimension array
        int[] p = new int[matrices + 1];
        for (int i = 0; i <= matrices; i++) {
            p[i] = rnd.nextInt(1, bound);
        }

        MCM(p);
        findProduct(1, matrices, p);
    }
}