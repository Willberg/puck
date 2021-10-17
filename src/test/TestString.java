package test;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestString {
    private void testStr() {
        String a = "a";
        String b = "a";
        String c = a;
        String d = new String("a");

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d);

        String e = a.intern();
        String f = b.intern();
        String g = d.intern();
        System.out.println(e == f);
        System.out.println(e == g);

        System.out.println(a.equals(d));
    }

    private void testThreadPollExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        executor.execute(() -> {
            try {
                // sleep 10s
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread());
        });
        executor.shutdown();
    }

    public static void main(String[] args) {
        TestString t = new TestString();
        t.testFindLadders(t);
        t.testStr();
        t.testThreadPollExecutor();
    }

    private void testFindLadders(TestString t) {
        String[] arr = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = new ArrayList<>();
        for (String s : arr) {
            wordList.add(s);
        }
        String beginWord = "hit";
        String endWord = "cog";
        List<List<String>> ansList = t.findLadders(beginWord, endWord, wordList);
        for (List<String> list : ansList) {
            for (String s : list) {
                System.out.print(s + "->");
            }
            System.out.println("");
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ansList = new LinkedList<>();
        Set<String> dict = new HashSet<>();
        for (String s : wordList) {
            dict.add(s);
        }
        if (!dict.contains(endWord)) {
            return ansList;
        }
        dict.remove(beginWord);
        dict.remove(endWord);

        Queue<String> q1 = new ArrayDeque<>();
        Queue<String> q2 = new ArrayDeque<>();
        // 用于翻转方向,每次从后继节点较少的一边开始
        boolean reverse = false;
        // 从beginWord和endWord开始往中间找, 已经找到
        boolean found = false;
        Map<String, List<String>> next = new HashMap<>();
        q1.offer(beginWord);
        q2.offer(endWord);
        // 从上一层开始,构建下一层的节点,广度优先
        while (!q1.isEmpty()) {
            // 每一层元素
            Queue<String> q = new ArrayDeque<>();
            // 要将q1队列中的所有元素处理完
            for (String w : q1) {
                char[] wc = w.toCharArray();
                for (int i = 0; i < wc.length; i++) {
                    // 保存改变前的字符
                    char originChar = wc[i];
                    for (int j = 0; j < 26; j++) {
                        wc[i] = (char) ('a' + j);
                        String s = String.valueOf(wc);
                        if (q2.contains(s)) {
                            if (reverse) {
                                List<String> tmp = next.getOrDefault(s, new LinkedList<>());
                                tmp.add(w);
                                next.put(s, tmp);
                            } else {
                                List<String> tmp = next.getOrDefault(w, new LinkedList<>());
                                tmp.add(s);
                                next.put(w, tmp);
                            }
                            found = true;
                        }

                        if (dict.contains(s)) {
                            // 保持方向
                            if (reverse) {
                                List<String> tmp = next.getOrDefault(s, new LinkedList<>());
                                tmp.add(w);
                                next.put(s, tmp);
                            } else {
                                List<String> tmp = next.getOrDefault(w, new LinkedList<>());
                                tmp.add(s);
                                next.put(w, tmp);
                            }
                            q.add(s);
                        }
                    }
                    // 回改原字符
                    wc[i] = originChar;
                }
            }

            // 找到了最短路径
            if (found) {
                break;
            }

            // dict中删除已经被处理过的元素
            for (String s : q) {
                dict.remove(s);
            }

            if (q.size() > q2.size()) {
                q1 = q2;
                q2 = q;
                reverse = !reverse;
            } else {
                q1 = q;
            }
        }

        if (!found) {
            return ansList;
        }

        // 回溯输出路径
        List<String> path = new LinkedList<>();
        path.add(beginWord);
        backtracking(next, ansList, path, beginWord, endWord);
        return ansList;
    }

    private void backtracking(Map<String, List<String>> next, List<List<String>> ansList,
                              List<String> path, String beginWord, String endWord) {
        if (beginWord.equals(endWord)) {
            ansList.add(new LinkedList<>(path));
            return;
        }

        if (next.get(beginWord) == null) {
            return;
        }

        for (String nextString : next.get(beginWord)) {
            path.add(nextString);
            backtracking(next, ansList, path, nextString, endWord);
            path.remove(path.size() - 1);
        }
    }
}
