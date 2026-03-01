// ================================================================
//   ALGORITHM ANALYSIS — TIME & SPACE COMPLEXITY IN JAVA
//   For Engineering Students | 2 Focused Examples
// ================================================================
//   Compile:  javac AlgorithmAnalysis.java
//   Run:      java AlgorithmAnalysis
// ================================================================
//
//   WHAT IS ALGORITHM ANALYSIS?
//   When you write code, two questions always matter:
//     1. How much TIME does it take as input grows?   → Time Complexity
//     2. How much MEMORY does it use as input grows?  → Space Complexity
//
//   We express both using BIG-O NOTATION.
//
//   BIG-O CHEAT SHEET (best to worst):
//   ┌─────────────┬───────────────┬──────────────────────────────┐
//   │  Notation   │   Name        │  Example for n = 1,000,000   │
//   ├─────────────┼───────────────┼──────────────────────────────┤
//   │  O(1)       │  Constant     │  1 operation                 │
//   │  O(log n)   │  Logarithmic  │  ~20 operations              │
//   │  O(n)       │  Linear       │  1,000,000 operations        │
//   │  O(n log n) │  Linearithmic │  ~20,000,000 operations      │
//   │  O(n²)      │  Quadratic    │  1,000,000,000,000 ops 😱    │
//   │  O(2ⁿ)      │  Exponential  │  10^301,030 ops 💀           │
//   └─────────────┴───────────────┴──────────────────────────────┘
//
//   KEY RULE: We always analyze the WORST CASE unless stated otherwise.
//   KEY RULE: Drop constants and lower terms → O(3n + 5) becomes O(n)
// ================================================================

import java.util.Arrays;

public class AlgorithmAnalysis {

    // ============================================================
    // GLOBAL COUNTERS — to count actual operations at runtime
    // ============================================================
    static long operationCount = 0;


    // ============================================================
    // EXAMPLE 1 — LINEAR SEARCH vs BINARY SEARCH
    // ============================================================
    //
    //   PROBLEM: Find a target value inside a sorted array.
    //   We compare two strategies:
    //     A) Linear Search  → check every element one by one
    //     B) Binary Search  → eliminate HALF the array each step
    //
    //   This example teaches:
    //     Time Complexity:  O(n)  vs  O(log n)
    //     Space Complexity: O(1)  vs  O(1)
    //
    // ────────────────────────────────────────────────────────────
    // A) LINEAR SEARCH
    // ────────────────────────────────────────────────────────────
    //
    //   ALGORITHM:
    //   Start from index 0. Check each element one by one.
    //   Return the index when found, or -1 if not found.
    //
    //   ARRAY: [2, 5, 8, 12, 16, 23, 38, 45, 56, 72]
    //   TARGET: 56
    //   STEPS:  check 2 → check 5 → check 8 → ... → check 56 ✓
    //           That's 9 comparisons out of 10 elements.
    //
    //   TIME COMPLEXITY ANALYSIS:
    //   • Best Case:    O(1)  → target is the FIRST element
    //   • Average Case: O(n/2) = O(n) → target is somewhere in the middle
    //   • Worst Case:   O(n)  → target is LAST or not present at all
    //   We always report WORST CASE → O(n)
    //
    //   SPACE COMPLEXITY:
    //   We only use a loop variable 'i' — ONE extra variable regardless of n.
    //   Space Complexity: O(1)  ← constant, does NOT grow with input
    // ────────────────────────────────────────────────────────────

    static int linearSearch(int[] arr, int target) {
        operationCount = 0; // reset counter

        for (int i = 0; i < arr.length; i++) {
            operationCount++; // COUNT: one comparison per loop iteration

            if (arr[i] == target) {
                return i; // found at index i
            }
        }
        return -1; // not found
    }


    // ────────────────────────────────────────────────────────────
    // B) BINARY SEARCH
    // ────────────────────────────────────────────────────────────
    //
    //   PREREQUISITE: Array MUST be sorted.
    //
    //   ALGORITHM:
    //   Look at the MIDDLE element.
    //   If target == middle  → found!
    //   If target < middle   → discard right half, search left half
    //   If target > middle   → discard left half, search right half
    //   Repeat on the remaining half until found or range is empty.
    //
    //   VISUAL TRACE for array of 10 elements, target = 56:
    //   [2, 5, 8, 12, 16, 23, 38, 45, 56, 72]
    //    low=0               mid=4          high=9
    //   arr[4] = 16. 56 > 16 → eliminate LEFT half
    //
    //   [_, _, _, _, _, 23, 38, 45, 56, 72]
    //                   low=5    mid=7    high=9
    //   arr[7] = 45. 56 > 45 → eliminate LEFT half
    //
    //   [_, _, _, _, _, _, _, _, 56, 72]
    //                            low=8 mid=8 high=9
    //   arr[8] = 56. FOUND! ✓ Only 3 comparisons for 10 elements!
    //
    //   TIME COMPLEXITY ANALYSIS:
    //   Each step cuts the problem in HALF.
    //   After 1 step: n/2 elements remain
    //   After 2 steps: n/4 elements remain
    //   After k steps: n/2^k elements remain
    //   We stop when 1 element remains: n/2^k = 1 → k = log₂(n)
    //   Worst Case Time Complexity: O(log n)
    //
    //   PRACTICAL IMPACT:
    //   n = 1,000      → Linear: 1,000 steps   | Binary:  10 steps
    //   n = 1,000,000  → Linear: 1,000,000 steps| Binary:  20 steps
    //   n = 1,000,000,000 → Linear: 1 billion   | Binary:  30 steps ← jaw drop!
    //
    //   SPACE COMPLEXITY:
    //   Only 3 variables: low, high, mid — constant regardless of n.
    //   Space Complexity: O(1)  ← same as linear search!
    //   Both have O(1) space — the BIG difference is entirely in TIME.
    // ────────────────────────────────────────────────────────────

    static int binarySearch(int[] arr, int target) {
        operationCount = 0; // reset counter

        int low  = 0;
        int high = arr.length - 1;

        while (low <= high) {
            operationCount++; // COUNT: one comparison per iteration

            int mid = low + (high - low) / 2; // avoids integer overflow vs (low+high)/2

            if (arr[mid] == target) {
                return mid;             // found!
            } else if (arr[mid] < target) {
                low = mid + 1;          // discard LEFT half
            } else {
                high = mid - 1;         // discard RIGHT half
            }
        }
        return -1; // not found
    }


    // ============================================================
    // EXAMPLE 2 — FIBONACCI: NAIVE vs MEMOIZED
    // ============================================================
    //
    //   PROBLEM: Compute the nth Fibonacci number.
    //   We compare two strategies:
    //     A) Naive Recursion  → recomputes the same values repeatedly
    //     B) Memoization      → stores computed values, never repeats work
    //
    //   This example teaches:
    //     Time Complexity:  O(2ⁿ) vs O(n)
    //     Space Complexity: O(n)  vs O(n)
    //     The concept of TIME-SPACE TRADEOFF
    //
    // ────────────────────────────────────────────────────────────
    // A) NAIVE RECURSIVE FIBONACCI
    // ────────────────────────────────────────────────────────────
    //
    //   RECURRENCE:
    //   fib(0) = 0
    //   fib(1) = 1
    //   fib(n) = fib(n-1) + fib(n-2)
    //
    //   RECURSION TREE for fib(5):
    //                        fib(5)
    //                      /        \
    //                  fib(4)       fib(3)
    //                 /     \       /    \
    //             fib(3) fib(2) fib(2) fib(1)
    //             /  \    / \    / \
    //          fib(2)f(1) f(1)f(0)f(1)f(0)
    //          / \
    //        f(1)f(0)
    //
    //   Counting the nodes: 15 calls for fib(5)
    //   For fib(6): 25 calls. fib(7): 41 calls.
    //   Pattern: roughly DOUBLES each time n increases by 1.
    //
    //   TIME COMPLEXITY ANALYSIS:
    //   T(n) = T(n-1) + T(n-2) + O(1)
    //   This solves to approximately 2^n total calls.
    //   Time Complexity: O(2ⁿ)  ← EXPONENTIAL — catastrophically slow!
    //   fib(50) → ~1,125,899,906,842,623 calls. Takes minutes!
    //
    //   SPACE COMPLEXITY ANALYSIS:
    //   Space = depth of the call stack at any one moment.
    //   The deepest path is fib(n)→fib(n-1)→...→fib(0) = n frames.
    //   Space Complexity: O(n)  ← the call stack height
    // ────────────────────────────────────────────────────────────

    static long callCount = 0; // tracks actual recursive calls

    static long fibNaive(int n) {
        callCount++; // COUNT: every function call

        if (n == 0) return 0; // base case 1
        if (n == 1) return 1; // base case 2

        return fibNaive(n - 1) + fibNaive(n - 2); // TWO recursive calls per step
    }


    // ────────────────────────────────────────────────────────────
    // B) MEMOIZED FIBONACCI
    // ────────────────────────────────────────────────────────────
    //
    //   KEY INSIGHT: In the naive version, fib(3) is computed TWICE,
    //   fib(2) is computed THREE times. Pure waste!
    //
    //   MEMOIZATION FIX: Store the result the first time.
    //   Every subsequent call just looks up the stored value.
    //
    //   VISUAL TRACE for fib(5) with memoization:
    //   fib(5) → fib(4) → fib(3) → fib(2) → fib(1) = 1 [STORED]
    //                                       → fib(0) = 0 [STORED]
    //                              fib(2) already in memo → return 1
    //                   fib(3) already in memo → return 2
    //            fib(4) already in memo → return 3
    //   Total: 9 calls instead of 15. For n=50: 99 calls instead of trillions!
    //
    //   TIME COMPLEXITY ANALYSIS:
    //   Each fib(i) is computed EXACTLY ONCE and stored.
    //   There are n unique sub-problems (fib(0) to fib(n)).
    //   Time Complexity: O(n)  ← LINEAR — massively faster!
    //
    //   SPACE COMPLEXITY ANALYSIS:
    //   We store n results in the memo array.
    //   Plus the call stack still goes n levels deep.
    //   Space Complexity: O(n)  ← SAME as naive!
    //
    //   ── TIME-SPACE TRADEOFF ──
    //   Memoization TRADES space to SAVE time.
    //   Time:  O(2ⁿ)  → O(n)   ← ENORMOUS improvement
    //   Space: O(n)   → O(n)   ← no extra cost!
    //   In this case it's a free lunch — same space, exponentially less time.
    // ────────────────────────────────────────────────────────────

    static long[] memo = new long[100];
    static boolean[] computed = new boolean[100];
    static long memoCallCount = 0;

    static long fibMemo(int n) {
        memoCallCount++; // COUNT: every function call

        if (n == 0) return 0;
        if (n == 1) return 1;

        if (computed[n]) return memo[n]; // LOOKUP: already computed, skip recursion

        memo[n]     = fibMemo(n - 1) + fibMemo(n - 2); // compute ONCE
        computed[n] = true;                              // mark as stored
        return memo[n];
    }


    // ============================================================
    //   MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║    ALGORITHM ANALYSIS — TIME & SPACE COMPLEXITY          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");


        // ═══════════════════════════════════════════════════════
        // EXAMPLE 1: LINEAR SEARCH vs BINARY SEARCH
        // ═══════════════════════════════════════════════════════
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  EXAMPLE 1: Linear Search O(n) vs Binary Search O(log n)║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72};
        int target = 56;

        System.out.println("\n  Array:  " + Arrays.toString(sortedArray));
        System.out.println("  Target: " + target);

        // Run both searches
        int linIndex = linearSearch(sortedArray, target);
        long linOps  = operationCount;

        int binIndex = binarySearch(sortedArray, target);
        long binOps  = operationCount;

        System.out.println("\n  ┌──────────────────┬────────────┬─────────────┬───────────┐");
        System.out.println("  │  Algorithm       │  Found At  │  Operations │  O-Notation│");
        System.out.println("  ├──────────────────┼────────────┼─────────────┼───────────┤");
        System.out.printf ("  │  Linear Search   │  index %-3d │     %-7d │  O(n)      │%n", linIndex, linOps);
        System.out.printf ("  │  Binary Search   │  index %-3d │     %-7d │  O(log n)  │%n", binIndex, binOps);
        System.out.println("  └──────────────────┴────────────┴─────────────┴───────────┘");

        // Show scaling across different array sizes
        System.out.println("\n  ── How operations SCALE with array size ──");
        System.out.println("  ┌──────────────┬──────────────┬──────────────┐");
        System.out.println("  │  Array Size  │  Linear O(n) │ Binary O(log)│");
        System.out.println("  ├──────────────┼──────────────┼──────────────┤");

        int[] sizes = {10, 100, 1000, 100000, 1000000, 1000000000};
        for (int n : sizes) {
            long linearOps = n;
            long binaryOps = (long)(Math.log(n) / Math.log(2)) + 1;
            System.out.printf("  │  %,12d│  %,12d│  %,12d│%n", n, linearOps, binaryOps);
        }
        System.out.println("  └──────────────┴──────────────┴──────────────┘");
        System.out.println("\n  ✓ Both use O(1) SPACE — only 3 variables regardless of array size.");
        System.out.println("  ✓ Binary search is faster purely because of a smarter STRATEGY.");


        // ═══════════════════════════════════════════════════════
        // EXAMPLE 2: NAIVE FIBONACCI vs MEMOIZED FIBONACCI
        // ═══════════════════════════════════════════════════════
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  EXAMPLE 2: Naive Fib O(2ⁿ) vs Memoized Fib O(n)        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");

        System.out.println("\n  ── Call count comparison (how many function calls are made) ──");
        System.out.println("  ┌──────┬────────────────┬──────────┬──────────────────┬──────────┐");
        System.out.println("  │  n   │  Naive Result  │ Naive    │  Memo Result     │  Memo    │");
        System.out.println("  │      │                │ Calls    │                  │  Calls   │");
        System.out.println("  ├──────┼────────────────┼──────────┼──────────────────┼──────────┤");

        int[] fibInputs = {5, 10, 15, 20, 25, 30};
        for (int n : fibInputs) {
            callCount = 0;
            memoCallCount = 0;
            computed = new boolean[100]; // reset memo for fair comparison

            long naiveResult = fibNaive(n);
            long naiveCalls  = callCount;

            long memoResult  = fibMemo(n);
            long memoCalls   = memoCallCount;

            System.out.printf("  │  %-3d │  %,14d│  %,7d│  %,16d│  %,7d│%n",
                    n, naiveResult, naiveCalls, memoResult, memoCalls);
        }
        System.out.println("  └──────┴────────────────┴──────────┴──────────────────┴──────────┘");

        // Live timing comparison
        System.out.println("\n  ── Real execution time for fib(40) ──");

        callCount = 0;
        long t1 = System.currentTimeMillis();
        long r1 = fibNaive(40);
        long time1 = System.currentTimeMillis() - t1;
        System.out.printf("  Naive    → Result: %,d | Calls: %,d | Time: %d ms%n",
                r1, callCount, time1);

        memoCallCount = 0;
        computed = new boolean[100];
        long t2 = System.currentTimeMillis();
        long r2 = fibMemo(40);
        long time2 = System.currentTimeMillis() - t2;
        System.out.printf("  Memoized → Result: %,d | Calls: %,d | Time: %d ms%n",
                r2, memoCallCount, time2);

        // Space complexity breakdown
        System.out.println("\n  ── Space Complexity Breakdown ──");
        System.out.println("  ┌──────────────────┬────────────────────────┬──────────────┐");
        System.out.println("  │  Algorithm       │  Memory Used           │  O-Notation  │");
        System.out.println("  ├──────────────────┼────────────────────────┼──────────────┤");
        System.out.println("  │  Naive Fib       │  Call stack — n frames │  O(n)        │");
        System.out.println("  │  Memoized Fib    │  Call stack + memo[]   │  O(n)        │");
        System.out.println("  └──────────────────┴────────────────────────┴──────────────┘");
        System.out.println("\n  ✓ Both use O(n) space — but memoized trades that space");
        System.out.println("    to cut time from O(2ⁿ) to O(n). This is a TIME-SPACE TRADEOFF.");


        // ═══════════════════════════════════════════════════════
        // SUMMARY
        // ═══════════════════════════════════════════════════════
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║   SUMMARY                                                ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║                                                          ║");
        System.out.println("║  Example 1: Linear vs Binary Search                     ║");
        System.out.println("║    Linear   → Time O(n)      Space O(1)                 ║");
        System.out.println("║    Binary   → Time O(log n)  Space O(1)  ← faster       ║");
        System.out.println("║    Lesson: A smarter strategy beats brute force.         ║");
        System.out.println("║                                                          ║");
        System.out.println("║  Example 2: Naive vs Memoized Fibonacci                 ║");
        System.out.println("║    Naive    → Time O(2ⁿ)  Space O(n)                   ║");
        System.out.println("║    Memoized → Time O(n)   Space O(n)  ← far faster      ║");
        System.out.println("║    Lesson: Store results. Never compute the same         ║");
        System.out.println("║            sub-problem twice.                            ║");
        System.out.println("║                                                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
}
