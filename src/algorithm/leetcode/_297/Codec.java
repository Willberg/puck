package algorithm.leetcode._297;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Codec {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrderSerialize(root, sb);
        return sb.substring(0, sb.length() - 1);
    }

    private void preOrderSerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(",");
            return;
        }

        sb.append(root.val).append(",");
        preOrderSerialize(root.left, sb);
        preOrderSerialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> dataList = new LinkedList<>(Arrays.asList(data.split(",")));
        return preOrderDeserialize(dataList);
    }

    private TreeNode preOrderDeserialize(List<String> dataList) {
        if ("null".equals(dataList.get(0))) {
            dataList.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = preOrderDeserialize(dataList);
        root.right = preOrderDeserialize(dataList);
        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        String s = "";
        TreeNode root = codec.deserialize("1,2,3,null,null,4,5,6,7,null,null,null,null,null,null");
        String str = codec.serialize(root);
        System.out.println(str);
        root = codec.deserialize(str);
        System.out.println(root.val);
    }
}
