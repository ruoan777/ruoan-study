package com.ruoan.study.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月04日 17:12:00
 */
public class MyMap implements Map<String, String> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public String get(Object key) {
        return null;
    }

    @Override
    public String put(String key, String value) {
        return null;
    }

    @Override
    public String remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<String> keySet() {
        return null;
    }

    @Override
    public Collection<String> values() {
        return null;
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }

    @Override
    public String getOrDefault(Object key, String defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super String> action) {

    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super String, ? extends String> function) {

    }

    @Override
    public String putIfAbsent(String key, String value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(String key, String oldValue, String newValue) {
        return false;
    }

    @Override
    public String replace(String key, String value) {
        return null;
    }

    @Override
    public String computeIfAbsent(String key, Function<? super String, ? extends String> mappingFunction) {
        return null;
    }

    @Override
    public String computeIfPresent(String key, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return null;
    }

    @Override
    public String compute(String key, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return null;
    }

    @Override
    public String merge(String key, String value, BiFunction<? super String, ? super String, ? extends String> remappingFunction) {
        return null;
    }
}
