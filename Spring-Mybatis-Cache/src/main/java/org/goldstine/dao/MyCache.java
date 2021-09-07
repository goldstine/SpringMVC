package org.goldstine.dao;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * mybatis对于缓存并不专业，所以将缓存通过接口开放Cache,实现第三方缓存整合
 * 对于缓存，mybatis将Executor包装成类
 *
 * ehcache:ehcache非常专业的java进程内的缓存框架      这里的二级缓存概念不一样，
 *（1）首先导入依赖 ehcache   mybatis-ehcache中间包
 * 导入依赖的日志包：log4j   slf4j-api   slf4j-log4j12
 *
 *(2)ehcache要工作有一个配置文件
 * 文件名叫ehcache.xml；放在类路径下
 */

public class MyCache implements Cache {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public void putObject(Object key, Object value) {
//        new redis();
        //通过第三方缓存数据库中放数据
    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
