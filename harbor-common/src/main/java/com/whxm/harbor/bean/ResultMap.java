package com.whxm.harbor.bean;

import java.util.HashMap;

public class ResultMap<K, V> extends HashMap<K, V> {

    public ResultMap(int initialCapacity) {
        super(initialCapacity);
    }

    public ResultMap<K, V> build(K key, V value) {
        this.put(key, value);
        return this;
    }


    public ResultMap<K, V> clean() {
        super.clear();
        return this;
    }
}