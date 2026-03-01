# Algorithm Analysis — Time & Space Complexity in Java
### 2 Focused Examples

---

## How to Run

```bash
javac AlgorithmAnalysis.java
java AlgorithmAnalysis
```

> Requires Java 8 or higher. Check with `java -version`.

---

## What is Algorithm Analysis?

When you write a program, two questions always matter — especially as your data grows large:

1. **How much TIME does it take?** → Time Complexity
2. **How much MEMORY does it use?** → Space Complexity

We measure both using **Big-O Notation** — a mathematical way to describe how an algorithm *scales* as input size `n` grows. We don't measure actual seconds or bytes because those depend on hardware. We measure *growth rate* — how the workload increases relative to input size.

---

## Big-O Notation — The Foundation

Big-O describes the **worst-case upper bound** on how much time or space an algorithm needs.

### Complexity Classes (best to worst)

```
O(1)       → Constant      — always the same, no matter how big n is
O(log n)   → Logarithmic   — grows slowly; doubles n → 1 extra step
O(n)       → Linear        — doubles n → doubles the work
O(n log n) → Linearithmic  — common in good sorting algorithms
O(n²)      → Quadratic     — doubles n → 4x the work
O(2ⁿ)      → Exponential   — add 1 to n → DOUBLES the work
```

### Real-scale impact (n = 1,000,000):

| Notation | Operations | Approx time at 10⁹ ops/sec |
|----------|-----------|---------------------------|
| O(1) | 1 | instant |
| O(log n) | ~20 | instant |
| O(n) | 1,000,000 | ~1 ms |
| O(n log n) | ~20,000,000 | ~20 ms |
| O(n²) | 1,000,000,000,000 | ~17 minutes |
| O(2ⁿ) | 10^301,030 | longer than the universe's age |

### Simplification Rules

```
O(3n + 5)     → O(n)      drop constants and lower-order terms
O(n² + n)     → O(n²)     lower term (n) is irrelevant for large n
O(2 log n)    → O(log n)  drop the coefficient
O(n + log n)  → O(n)      O(n) dominates O(log n)
```

---

## File Structure

```
AlgorithmAnalysis.java
│
├── linearSearch(arr, target)    → O(n) time,     O(1) space
├── binarySearch(arr, target)    → O(log n) time,  O(1) space
│
├── fibNaive(n)                  → O(2ⁿ) time,    O(n) space
├── fibMemo(n)                   → O(n) time,      O(n) space
│   ├── memo[]                   → cache array
│   └── computed[]               → tracks what is cached
│
└── main()                       → runs demos, tables, and timings
```

---

## Example 1 — Linear Search vs Binary Search

### The Problem

Find a target value inside a **sorted array** and return its index.

```java
int[] arr    = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72};
int   target = 56;
```

---

### Strategy A — Linear Search

**How it works:** Start from index 0. Check each element one by one. Stop when found or reach the end.

```java
static int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) return i;
    }
    return -1;
}
```

**Step-by-step trace for target = 56:**
```
Check arr[0] = 2   → no
Check arr[1] = 5   → no
Check arr[2] = 8   → no
Check arr[3] = 12  → no
Check arr[4] = 16  → no
Check arr[5] = 23  → no
Check arr[6] = 38  → no
Check arr[7] = 45  → no
Check arr[8] = 56  → YES! return 8
Total: 9 comparisons
```

#### Time Complexity Analysis

| Case | When | Comparisons |
|------|------|------------|
| Best | Target is first element | O(1) |
| Average | Target is in the middle | O(n/2) = O(n) |
| Worst | Target is last, or absent | O(n) |

We always report **worst case** → **O(n)**

As n doubles, the number of operations doubles. Perfectly linear.

#### Space Complexity Analysis

Look at what variables linear search uses:
```java
int i;   // loop counter — one integer
```

That's it. One variable. Whether `n = 10` or `n = 10,000,000`, it still only uses one variable. **Space Complexity: O(1)** — constant.

---

### Strategy B — Binary Search

**Prerequisite:** Array must be **sorted** (ascending or descending).

**How it works:** Look at the middle element. If target is smaller, discard the right half. If larger, discard the left half. Repeat on the surviving half.

```java
static int binarySearch(int[] arr, int target) {
    int low  = 0;
    int high = arr.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;

        if      (arr[mid] == target) return mid;
        else if (arr[mid] < target)  low  = mid + 1;  // discard LEFT
        else                         high = mid - 1;  // discard RIGHT
    }
    return -1;
}
```

> **Why `low + (high - low) / 2` instead of `(low + high) / 2`?**
> If `low` and `high` are both large integers (near `Integer.MAX_VALUE`), `low + high` overflows to a negative number. The safe formula prevents this.

**Step-by-step trace for target = 56:**
```
Array: [2, 5, 8, 12, 16, 23, 38, 45, 56, 72]
        0  1  2   3   4   5   6   7   8   9

Step 1: low=0, high=9, mid=4 → arr[4]=16
        56 > 16 → discard left half → low = 5

Step 2: low=5, high=9, mid=7 → arr[7]=45
        56 > 45 → discard left half → low = 8

Step 3: low=8, high=9, mid=8 → arr[8]=56
        56 == 56 → FOUND at index 8 ✓

Total: 3 comparisons (vs 9 for linear!)
```

#### Time Complexity Analysis

Each step cuts the search space in half:

```
After step 1: n/2  elements remain
After step 2: n/4  elements remain
After step 3: n/8  elements remain
After step k: n/2ᵏ elements remain

We stop when 1 element remains:
  n / 2ᵏ = 1
  2ᵏ = n
  k = log₂(n)
```

**Time Complexity: O(log n)**

#### Space Complexity Analysis

Variables used:
```java
int low, high, mid;  // three integers — always just three
```

No arrays, no recursion, no extra data structures. **Space Complexity: O(1)**

#### Scaling Comparison

```
Array Size      Linear O(n)    Binary O(log n)
─────────────   ───────────    ───────────────
10              10             4
100             100            7
1,000           1,000          10
100,000         100,000        17
1,000,000       1,000,000      20
1,000,000,000   1,000,000,000  30
```

One billion elements. Linear search needs 1 billion steps. Binary search needs 30.

---

## Example 2 — Naive Fibonacci vs Memoized Fibonacci

### The Problem

Compute the nth number in the Fibonacci sequence.

```
Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 ...
Index:    0  1  2  3  4  5  6   7   8   9  10
```

Mathematical definition:
```
fib(0) = 0
fib(1) = 1
fib(n) = fib(n-1) + fib(n-2)
```

---

### Strategy A — Naive Recursive

**How it works:** Directly translate the math definition into code. Call yourself twice per step.

```java
static long fibNaive(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fibNaive(n - 1) + fibNaive(n - 2);
}
```

It looks clean. But look at the recursion tree:

```
                       fib(5)
                     /        \
                 fib(4)       fib(3)      ← fib(3) computed TWICE
                /     \       /    \
            fib(3) fib(2) fib(2) fib(1)  ← fib(2) computed THREE times
            /   \    /  \   /  \
         fib(2)f(1) f(1)f(0) f(1)f(0)
          /  \
        f(1) f(0)
```

For `fib(5)`: 15 total calls to compute 1 number.
For `fib(6)`: 25 calls. `fib(7)`: 41 calls. `fib(8)`: 67 calls.

The call count nearly **doubles every time n increases by 1**.

#### Time Complexity Analysis

The recurrence relation for the number of calls is:
```
T(n) = T(n-1) + T(n-2) + O(1)
```

This is the Fibonacci recurrence itself — it grows like the golden ratio (φ ≈ 1.618) raised to the power of n. For analysis purposes: **T(n) ≈ 2ⁿ**

**Time Complexity: O(2ⁿ)** — Exponential. Catastrophically slow.

```
fib(10)  → 177 calls
fib(20)  → 21,891 calls
fib(30)  → 2,692,537 calls
fib(40)  → 331,160,281 calls    ← ~330 million. Takes seconds.
fib(50)  → ~1.1 trillion calls  ← Takes 20+ minutes.
fib(100) → 10^21 calls          ← The sun will die first.
```

#### Space Complexity Analysis

Even though millions of calls are made, at any given moment the active call stack only has one *path* from root to leaf — the deepest path goes `fib(n) → fib(n-1) → fib(n-2) → ... → fib(0)`.

**Space Complexity: O(n)** — the call stack depth at any moment is n.

---

### Strategy B — Memoized Fibonacci

**Core insight:** The naive version recomputes `fib(3)`, `fib(2)`, `fib(1)` over and over. What if we stored the result the first time and just looked it up every subsequent time?

```java
static long[] memo     = new long[100];
static boolean[] computed = new boolean[100];

static long fibMemo(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;

    if (computed[n]) return memo[n];      // already done? return instantly

    memo[n]     = fibMemo(n-1) + fibMemo(n-2);  // compute once
    computed[n] = true;                          // mark as stored
    return memo[n];
}
```

> **Why a separate `computed[]` boolean array?**
> Because `fib(0) = 0`, and `memo[0]` initializes to `0` too. Without the boolean flag, we can't distinguish "not yet computed" from "computed and the answer is 0". The `computed[]` flag removes this ambiguity.

**Step-by-step trace for fib(5):**
```
fibMemo(5) → not computed → call fibMemo(4)
  fibMemo(4) → not computed → call fibMemo(3)
    fibMemo(3) → not computed → call fibMemo(2)
      fibMemo(2) → not computed → call fibMemo(1)
        fibMemo(1) → base case → return 1
      fibMemo(2) = fibMemo(1) + fibMemo(0) = 1+0 = 1 → STORED
    fibMemo(3) = fibMemo(2) + fibMemo(1) = 1+1 = 2 → STORED
  fibMemo(4) = fibMemo(3) + fibMemo(2)
             = memo[3] (lookup!)  + memo[2] (lookup!)
             = 2 + 1 = 3 → STORED
fibMemo(5) = memo[4] (lookup!) + memo[3] (lookup!)
           = 3 + 2 = 5 → STORED

Total: 9 calls (vs 15 naive) — and for large n the gap becomes astronomical
```

#### Time Complexity Analysis

Each value from `fib(0)` to `fib(n)` is computed **exactly once**. There are `n + 1` unique sub-problems.

**Time Complexity: O(n)** — Linear. A trillion-fold improvement over naive.

```
fib(10)  → 17 calls    (naive: 177)
fib(20)  → 37 calls    (naive: 21,891)
fib(30)  → 57 calls    (naive: 2,692,537)
fib(40)  → 77 calls    (naive: 331,160,281)
fib(100) → 197 calls   (naive: 10^21)
```

#### Space Complexity Analysis

Two things consume space:
1. The `memo[]` array: stores n values → O(n)
2. The call stack: still goes n levels deep on the first call → O(n)

**Space Complexity: O(n)** — same as naive.

---

### The Time-Space Tradeoff

Memoization is a classic example of a **time-space tradeoff** — you deliberately use more memory to save computation time.

| | Naive | Memoized |
|--|--|--|
| Time | O(2ⁿ) | O(n) |
| Space | O(n) | O(n) |
| Tradeoff | — | **Same space, exponentially less time** |

In this particular case it's almost a free lunch — we don't even pay extra space. But in general, many optimizations involve this exact trade: buy speed with memory, or save memory at the cost of speed. Recognising when to make that trade is a core engineering judgment.

---

## How `main()` Works

The `main()` method is a **demonstration runner** only — no algorithm logic lives there. It:

1. Runs both search algorithms on the same array and prints the operation counts side by side
2. Generates a scaling table across array sizes from 10 to 1 billion to show the growth difference
3. Runs both Fibonacci versions and counts actual function calls
4. Times both versions on `fib(40)` using `System.currentTimeMillis()` — the time gap is visible in real milliseconds
5. Shows a space complexity breakdown table

The `operationCount` and `callCount` static variables are incremented inside each algorithm so the output shows *real* operation counts, not theoretical estimates.

---

## Summary

| Example | Algorithm | Time | Space | Key lesson |
|---------|-----------|------|-------|------------|
| 1 | Linear Search | O(n) | O(1) | Brute force baseline |
| 1 | Binary Search | O(log n) | O(1) | Smarter strategy → same space, far less time |
| 2 | Naive Fibonacci | O(2ⁿ) | O(n) | Redundant recursion is catastrophic |
| 2 | Memoized Fibonacci | O(n) | O(n) | Store results, never repeat work |

**Three rules everyone should internalize:**

1. **Always ask how your algorithm scales** — code that works fine on 1,000 rows may be unusable on 1,000,000 rows
2. **A smarter strategy beats a faster computer** — O(log n) on an old machine beats O(n) on a supercomputer for large n
3. **Never recompute what you've already computed** — memoization and caching are the simplest ways to avoid exponential blowups

---

## Common Mistakes to Avoid

```java
// ❌ Forgetting the sorted array requirement for binary search
int[] unsorted = {56, 2, 38, 5, 12};
binarySearch(unsorted, 38);  // WRONG ANSWER — binary search needs sorted input

// ❌ Using (low + high) / 2 — integer overflow risk
int mid = (low + high) / 2;           // can overflow if both are large
int mid = low + (high - low) / 2;     // ✅ safe

// ❌ Memoization without tracking computed state
static long[] memo = new long[100];
if (memo[n] != 0) return memo[n];     // ❌ fib(0) = 0 → will always recompute
if (computed[n]) return memo[n];      // ✅ use separate boolean flag

// ❌ Confusing time and space complexity
// Space complexity is about MEMORY AT ONE POINT IN TIME
// not total memory ever allocated.
// Naive fib makes 2^n calls but the stack is at most n deep → O(n) space
```
