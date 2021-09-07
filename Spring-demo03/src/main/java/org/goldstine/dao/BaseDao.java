package org.goldstine.dao;

/**
 * 在BaseDao中定义对应的增删改查方法
 *
 * @param <T>
 */
public abstract class BaseDao<T> {
    public abstract void save();
}
