import java.util.*;

public class heap<T> {
    T[] arr;
    int size = 0;
    boolean maxHeap = true;
    Comparator comp;

    public heap() {
        this.arr = new T[10];
    }

    public heap(int n) {
        this.arr = new T[n];
    }

    public heap(Comparator comp) {
        this.comp = comp;
    }

    public heap(T[] input) {
        this.arr = new T[input.length];
        for (int i = 0; i < input.length; i++) {
            arr[i] = input[i];
        }
        this.size = input.length;
        for (int i = input.length - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }

    public heap(T[] input, boolean maxHeap) {
        this.maxHeap = maxHeap;
        this.arr = new T[input.length];
        for (int i = 0; i < input.length; i++) {
            arr[i] = input[i];
        }
        this.size = input.length;
        for (int i = input.length - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }

    public void downHeapify(int par) {
        int lc = (par * 2) + 1;
        int rc = (par * 2) + 2;
        int idx = par;
        if (lc < size && compareTo(idx, lc)) {
            idx = lc;
        }
        if (rc < size && compareTo(idx, rc)) {
            idx = rc;
        }
        if (idx != par) {
            swap(arr, par, idx);
            downHeapify(idx);
        }
    }

    public void upHeapify(int idx) {
        int par = (idx / 2) - 1;
        if (par >= 0 && compareTo(par, idx)) {
            swap(arr, par, idx);
            upHeapify(par);
        }
    }

    public T get() {
        return size > 0 ? arr[0] : null;
    }

    public void add(T v) {
        if (size == arr.length) {
            T[] temp = new T[size * 2];
            for (int i = 0; i < size; i++)
                temp[i] = arr[i];
            arr = temp;
        }
        arr[size] = v;
        upHeapify(size);
        this.size++;
    }

    public void remove() {
        swap(arr, 0, size - 1);
        size--;
        downHeapify(0);
    }

    public void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void display() {
        int ct = 1, i = 0;
        while (i < size) {
            for (int j = 1; j <= ct; j++) {
                if (i + j - 1 < size)
                    System.out.print(arr[j + i - 1] + " ");
            }
            i += ct;
            ct *= 2;
            System.out.println();
        }
    }

    public boolean compareTo(int par, int child) {
        boolean res = false;
        if (comp == null) {
            Comparable ith = (Comparable) arr[par];
            Comparable jth = (Comparable) arr[child];
            if (ith.compareTo(jth) > 0) {
                res = true;
            } else {
                res = false;
            }
        } else {
            T ith = arr[par];
            T jth = arr[child];
            if (comp.compare(ith, jth) > 0) {
                res = true;
            } else {
                res = false;
            }
        }
        if (maxHeap)
            return res;
        else
            return !res;
    }

    // **************************************************/
    public void heapSort() {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, 0, arr.length - i - 1);
            downHeapify_(0, arr.length - i - 1);
        }
    }

    public void downHeapify_(int par, int end) {
        int lc = (par * 2) + 1;
        int rc = (par * 2) + 2;
        int idx = par;
        if (lc < end && compareTo(idx, lc) > 0) {
            idx = lc;
        }
        if (rc < end && compareTo(idx, rc) > 0) {
            idx = rc;
        }
        if (idx != par) {
            swap(arr, par, idx);
            downHeapify_(idx, end);
        }
    }

    public void displayArray() {
        for (T ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }
}