
package fixtures.libs.java;

public class Quicksort {
    public static void main(String[] a) {
        int[] L;
        int i;
        Quicksort q;

        L = new int[10];

        i = 0;
        while (i < L.length) {
            L[i] = L.length - i;

            i = i + 1;
        }

        q = new Quicksort();

        q.quicksort(L);
        q.printL(L);
    }

    public boolean printL(int[] L) {
        int i;
        i = 0;
        while (i < L.length) {
            io.println(L[i]);
            i = i + 1;
        }

        return true;

    }

    public boolean quicksort(int[] L) {
        return this.quicksort(L, 0, L.length - 1);
    }

    public boolean quicksort(int[] L, int lo, int hi) {
        int p;

        if (lo < hi) {
            p = this.partition(L, lo, hi);

            this.quicksort(L, lo, p - 1);
            this.quicksort(L, p + 1, hi);
        } else {
        }

        return true;
    }

    public int partition(int[] L, int lo, int hi) {
        int p;
        int i;
        int j;
        int tmp;

        p = L[hi];
        i = lo;
        j = lo;

        while (j < hi) {
            if (L[j] < p) {
                tmp = L[i];
                L[i] = L[j];
                L[j] = tmp;

                i = i + 1;
            } else {
            }

            j = j + 1;
        }

        tmp = L[i];
        L[i] = L[hi];
        L[hi] = tmp;

        return i;

    }
}
