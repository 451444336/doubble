package com.born.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 * 
* @ClassName: ContainerUtil 
* @Description: 容器工具类 
* @author lijie 
* @date 2018年5月18日 下午2:53:14 
*
 */
public class ContainerUtils {

	private static final int ARRAY_DEF_LEN = 10;

    /**
     * 创建hashmap集合
     *
     * @return
     */
    public static <K, V> Map<K, V> map() {

        return new HashMap<K, V>(16);
    }

    public static <K, V> Map<K, V> map(int size) {

        return new HashMap<K, V>(size);
    }

    /**
     * 得到县城同步的map
     *
     * @return
     */
    public static <K, V> Map<K, V> synchronizedMap() {

        return Collections.synchronizedMap(new HashMap<K, V>(16));
    }

    /**
     * 创建 ArrayList
     *
     * @return
     */
    public static <T> List<T> aList() {

        return new ArrayList<T>();
    }

    /**
     * 创建指定长度的ArrayList
     *
     * @param num
     * @return
     */
    public static <T> ArrayList<T> aList(int num) {
        ArrayList<T> arrays = new ArrayList<T>();
        if (num > ARRAY_DEF_LEN) {
            arrays.ensureCapacity(num);
        }
        return arrays;
    }

    /**
     * 构造一个包含指定 collection 中的元素的新 ArrayList
     *
     * @return
     */
    public static <T> List<T> aList(Collection<? extends T> c) {
        if (c != null && c.size() > 0) {
            return new ArrayList<T>(c);
        }
        return new ArrayList<T>();
    }

    /**
     * 创建LinkedList
     *
     * @return
     */
    public static <T> LinkedList<T> lList() {

        return new LinkedList<T>();
    }

    /**
     * 创建 hashset
     *
     * @return
     */
    public static <T> Set<T> set() {

        return new HashSet<T>();
    }

    /**
     * 构造一个包含指定 collection 中的元素的新 set。
     *
     * @param c
     * @return
     */
    public static <T> Set<T> set(Collection<? extends T> c) {
        if (c != null && c.size() > 0) {
            return new HashSet<T>(c);
        }
        return new HashSet<T>();
    }

    /**
     * 创建基于 LinkedList 的 Queue
     *
     * @return
     */
    public static <T> Queue<T> queue() {

        return new LinkedList<T>();
    }
}
