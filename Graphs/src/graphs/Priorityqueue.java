
package graphs;

/*
 * This is a priority queue for Nodes implemented as min binary heap
 * comparing f_values.
 */

public class Priorityqueue {
    
    private Node[] heap;
    int max_size;
    int size;
    
    public Priorityqueue(int max) {
        max_size = max+1;
        heap = new Node[max_size];
        size = 0;       
        heap[0] = new Node(0,0,Integer.MIN_VALUE);
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    private int left(int i) {
        return 2*i;
    }
    private int right(int i) {
        return 2*i+1;
    }
    private int parent(int i) {
        return i / 2;
    }
    private boolean isleaf(int i) {
        return ((i > size/2) && (i <= size));
    }
    private void swap(int p1, int p2) {
        Node tmp;
        
        tmp = heap[p1];
        heap[p1] = heap[p2];
        heap[p2] = tmp;
    }
    public void insert(Node n) {
        size++;
        heap[size] = n;
        int current = size;
        
        while (heap[current].f_value < heap[parent(current)].f_value) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
    public Node removemin() {
        swap(1, size);
        size--;
        if (size != 0) {
            pushdown(1);
        }
        return heap[size+1];
    }
    
    private void pushdown(int position) {
        int smallestchild;
        while (!isleaf(position)) {
            smallestchild = left(position);
            if ((smallestchild < size) && (heap[smallestchild].f_value > heap[smallestchild +1].f_value)) {
                smallestchild = smallestchild + 1;
            }
            if (heap[position].f_value <= heap[smallestchild].f_value) {
                return;
            }
            swap(position, smallestchild);
            position = smallestchild;
        }
    }
    
}
