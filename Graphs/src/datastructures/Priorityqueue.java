
package datastructures;

/**
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
    
    /**
     * Return true if heap is empty.
     * 
     */
    public boolean is_empty() {
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
    /**
     * Swaps two nodes in heap which are in positions p1 and p2
     * @param p1 position of node to be swapped
     * @param p2 position of second node to be swapped.
     */
    private void swap(int p1, int p2) {
        Node tmp;
        
        tmp = heap[p1];
        heap[p1] = heap[p2];
        heap[p2] = tmp;
    }
    /**
     * Inserts a node to heap.
     * @param n node to be inserted.
     */
    public void insert(Node n) {
        size++;
        if (size > heap.length-1) {
            double_up();
        }
        heap[size] = n;
        int current = size;
        
        while (heap[current].f_value < heap[parent(current)].f_value) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
    /**
     * Removes and returns smallest node.
     * @return Node with smallest f_value
     */
    public Node removemin() {
        swap(1, size);
        size--;
        if (size != 0) {
            pushdown(1);
        }
        return heap[size+1];
    }
    /**
     * We search for node with same x and y value as given node. Then we remove it from heap.
     * @param n node to look for.
     * @return removed node.
     */
    public Node remove_middle(Node n) {
        int index = 0;
        int i = 1;
        while (index == 0 && i <= size) {
            if (heap[i].x == n.x && heap[i].y == n.y) {
                index = i;
                break;
            }
            ++i;
        }
        if (index == 0) {
            return null;
        }
        Node ret = heap[index];
        swap(index, size);
        size--;
        if (size != 0) {
            pushdown(size);
        }
        return ret;
    }
    /**
     * If array fills up and we need to increase size we double it up.
     */
    private void double_up() {
        Node[] tmp = new Node[size*2];
        for (int i = 0; i < size; ++i) {
            tmp[i] = heap[i];
        }
        heap = tmp;
    }
    /**
     * Pushes down node from given position to keep up heap property.
     * @param position 
     */
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
