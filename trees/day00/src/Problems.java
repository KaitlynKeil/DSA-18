import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // Here, I would sort the list, then start from the middle and recursively
        //  build down. So if you have a list of 7 things, add (by index) 3, 1, 5, 0, 2, 4, 6
        return new BinarySearchTree<>();
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if(n1 == null && n2 == null) {return true;}
        else if(n1 == null || n2 == null) {return false;}
        return (n1.key == n2.key) &&
                (isIsomorphic(n1.leftChild, n2.leftChild) || isIsomorphic(n1.leftChild, n2.rightChild)) &&
                (isIsomorphic(n1.rightChild, n2.rightChild) || isIsomorphic(n1.rightChild, n2.leftChild));
    }
}
