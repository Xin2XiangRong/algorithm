package algorithm.cs.test;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @author chaishuai
 * @date 2019/6/6
 */
public class LRUTest {



    public static void main(String[] args) throws FileNotFoundException {
        LRUCache lruCache = new LRUCache();
        lruCache.put(1, "a");
        lruCache.put(2, "b");
        lruCache.put(3, "c");
        lruCache.get(1);    //被访问一次以后，key：1位置前移；key：2成了最久未访问的，所以最后结果[3, 1, 4]
        lruCache.put(4, "d");
        System.out.println(lruCache.keySet());

    }

    private static class LRUCache extends LinkedHashMap {

        public LRUCache() {
            super(3, 0.75F, true);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > 3;
        }

    }
}


