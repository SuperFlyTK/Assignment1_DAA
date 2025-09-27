# Assignment 1 — Divide and Conquer Algorithms

## Overview
This project implements and analyzes several classic divide-and-conquer algorithms.  
The goal is to compare their theoretical recurrence relations with experimental measurements and ensure safe recursion patterns.

Implemented algorithms:
- **MergeSort**
- **QuickSort** (robust with randomized pivot)
- **Deterministic Select** (Median-of-Medians)
- **Closest Pair of Points (2D)**

Metrics collected:
- Number of comparisons
- Number of swaps (for QuickSort/Select)
- Recursion depth
- Running time
- Memory allocations (where applicable)

---

## Architecture Notes
- A shared **`Metrics`** class is used across all algorithms to track comparisons, swaps, and recursion depth.
- Recursion depth is controlled by:
    - **MergeSort**: using a cut-off to insertion sort for small subarrays.
    - **QuickSort**: always recursing into the smaller partition first (bounded stack depth).
    - **Deterministic Select**: recursing only into the partition containing the k-th element.
    - **Closest Pair**: recursive splitting by x-coordinate, with linear merge in the “strip” phase.
- Arrays are reused where possible (e.g., MergeSort uses a reusable buffer for merging).

---

## Recurrence Analysis

### MergeSort
- Recurrence:  
  \( T(n) = 2T(n/2) + \Theta(n) \)
- By Master Theorem (Case 2):  
  \( T(n) = \Theta(n \log n) \).
- Depth of recursion: \( O(\log n) \).

### QuickSort (Randomized)
- Recurrence (average case):  
  \( T(n) = T(\alpha n) + T((1-\alpha)n) + \Theta(n) \), with expected balanced splits.
- Expected complexity: \( \Theta(n \log n) \).
- Depth bounded by \( O(\log n) \) with high probability.

### Deterministic Select (Median-of-Medians)
- Recurrence:  
  \( T(n) = T(n/5) + T(7n/10) + \Theta(n) \).
- Using Akra–Bazzi intuition:  
  \( T(n) = \Theta(n) \).
- Ensures worst-case linear time.

### Closest Pair of Points
- Recurrence:  
  \( T(n) = 2T(n/2) + \Theta(n) \).
- Same as MergeSort, solved by Master Theorem (Case 2):  
  \( T(n) = \Theta(n \log n) \).
- Strip check requires scanning at most 7–8 neighbors per point.

---

## Experimental Results

### Time vs Input Size
- MergeSort and QuickSort both scale as \( n \log n \).
- Deterministic Select shows linear scaling, but with larger constants due to median-of-medians overhead.
- Closest Pair follows \( n \log n \) growth.

### Depth vs Input Size
- MergeSort depth grows as \( \log n \).
- QuickSort depth is bounded (≈ \( 2 \log_2 n \)).
- Select depth grows linearly with recursion into smaller partitions, but stays logarithmic in practice.

### Constant-Factor Effects
- Cache performance strongly affects MergeSort (linear merging).
- QuickSort benefits from in-place partitioning but suffers on adversarial inputs without randomization.
- Java garbage collection (GC) adds noise for large inputs.

---

## Summary
- The experimental results align closely with theoretical predictions.
- Constant factors (e.g., pivot strategy, buffer reuse) significantly impact observed performance.
- MergeSort is stable and predictable.
- QuickSort performs best on average but requires randomization for robustness.
- Deterministic Select guarantees linear time but is slower in practice than QuickSelect.
- Closest Pair algorithm validates the divide-and-conquer paradigm beyond sorting and selection.

---

## Git Workflow
Branches used:
- `feature/metrics`
- `feature/mergesort`
- `feature/quicksort`
- `feature/select`
- `feature/closest`
- `feature/cli`
