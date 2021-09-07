package org.goldstine.bean;

import java.util.List;

public class Lock {
    private Integer id;
    private String lockName;

    //一把钥匙对应一个锁
    //一把锁对应多把钥匙
    private List<Key> keys;
    //1-1关联，1-n关联 n-n关联

    /**
     * 对于1-n;n-1的关系，对应的外键关系存放在多的一张表
     * 对于n-n的关系，通过一张中间表存储对应的外键关系
     * @return
     */

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "id=" + id +
                ", lockName='" + lockName + '\'' +
                '}';
    }
}
