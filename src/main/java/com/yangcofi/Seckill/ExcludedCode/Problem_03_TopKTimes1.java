package com.yangcofi.Seckill.ExcludedCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Question:TOP K 给定数组
 * */
public class Problem_03_TopKTimes1 {
    public static class Node{
        public String s;
        public int times;

        public Node(String s, int times) {
            this.s = s;
            this.times = times;
        }
    }

    public static void printResult(String[] s, int topK){

        HashMap<String,Integer> hashMap = new HashMap<String, Integer>();
        for (int i = 0 ; i < s.length ; i ++){
            if (hashMap.containsKey(s[i])){
                hashMap.put(s[i], hashMap.get(s[i]) + 1);
            }else {
                hashMap.put(s[i],1);
            }
        }
        //为什么我们要建小根堆，因为我们是要找最大的三个。堆建完之后，之后加入的节点必须和我的根节点作比较，如果它比根节点还小，你肯定进不去这个堆。
        //可以保证将最大的三个保持在堆里面，如果你选建大根堆，那么从hashMap中开始的三个组成堆，之后的元素必须要比堆顶大才能进入，好，万一我此时要进入的没有堆顶大
        //但实际上我是原数组中出现次数第二多的，就出错了。
        Node[] node = new Node[topK];
        int index = 0;
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()){
            String key = entry.getKey();
            int times = entry.getValue();
            Node curNode = new Node(key, times);
            if (index < node.length){
                node[index] = curNode;
                //组建一个小根堆
                heapInsert(node,index);
            }else {
                if (node[0].times < curNode.times){
                    node[0] = curNode;
//                    heapfy();
                }
            }
        }
    }

    private static void heapfy(Node[] heap, int index, int heapSize) {
    }

    public static void heapInsert(Node[] heap, int index){
        while(index != 0){
            int parentIndex = (index - 1) / 2;
            if (heap[index].times < heap[parentIndex].times){
                swap(heap, index, parentIndex);
                index = parentIndex;
            }else {
                break;
            }
        }
    }

    public static void swap(Node[] heap, int index1, int index2){
        Node tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }


    public static void main(String[] args) {
        
    }

}



