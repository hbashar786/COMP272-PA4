import java.util.TreeMap;
import java.util.TreeSet;


public class LockerAssigner {

    private final TreeSet<Integer> free = new TreeSet<>(); 
    private final int x; 

    
    public LockerAssigner(int x) 
    {
        if (x < 0)
            {
                throw new IllegalArgumentException("x has to be greater than or equal to 0");
            }
            
            this.x = x;
            
            for (int i = 1; i <= x; i++) 
            {
                free.add(i);
            }
    }

    
    public int assign(int preferred) 
    {
        if (free.isEmpty()) 
        {
            return -1;
        }

        Integer above = free.ceiling(preferred);     
        Integer below = free.floor(preferred);  

        int chosen;

        if (below == null) 
            {
                chosen = above;
            } 
            else if (above == null) 
            {
                chosen = below;
            } 
            else 
            {
                long distBelow = (long) preferred - below;
                long distAbove = (long) above - preferred;
                chosen = (distBelow <= distAbove) ? below : above;  
            }

            free.remove(chosen);

            return chosen;
    }

    
    public boolean release(int locker) 
    {
        if (locker < 1 || locker > x) 
            {
                return false;
            }
            
            return free.add(locker);                  
    }

    
}

    /*
     * ======================== Exercise 3, Part B — Analysis =======================
     * Let n be the number of lockers.
     *
     * Q1. Which structure did you use (TreeSet, TreeMap, or both), what does it
     *     store, and why does it fit this problem?
     *      
     * --  I used TreeSet<Integers> structure and it's storing the numbers of the free lockers.
     *     This method is suppose to keep them sorted. Floor and ceiling will try and find the closest
     *     preferred number directly without scannig.
     *
     * Q2. If you had stored the free lockers in an unsorted ArrayList instead,
     *     what would be the worst-case running time of assign and of release?
     * 
     * --  The worst case running time of assign would be O(n) because an unsorted list must be scanned
     *     fully inorder to find the closest available locker. The worst case running time of release would be 
     *     O(n) because it is required to scan the list to check that locker might not already be free.
     *
     * Q3. What is the worst-case running time of assign and release with your
     *     solution? Justify it using the fact that TreeSet and TreeMap are
     *     red-black trees.
     * 
     * --  The worst-case running time of both assign and release with my solution would be
     *     O(log n) because TreeSet method has a red-black tree with n elements, so its height equals to 
     *     O(log n), and floor and ceiling, add and remove they all follow one path which is root to leaf. And
     *     assign will do a constant number of calls and release will do the same.
     * ============================================================================
     */
