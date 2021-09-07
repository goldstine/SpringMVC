package org.example;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.goldstine.bean.Teacher;
import org.goldstine.dao.TeacherDao;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheTest {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        String resource="mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    }

    /**
     * 一级缓存：Mybatis:sqlsession级别的缓存，默认存在
     * （1）只要之前查询过的数据，mybatis就会保存在一个缓存中，map；下次获取直接从缓存中拿
     *
     * 一级缓存失效的几种情况：
     *（1）不同的sqlSession，使用不同的一级缓存
     *      只有在同一个sqlSession期间查询到的数据会保存在这个SqlSession的缓存中
     *      下次使用这个sqlSession查询会从缓存中拿
     * （2）同一个方法，不同的参数，由于可能之前没有查询过，所以还会发新的sql
     * （3）在这个sqlSession期间执行上任何一次增删改操作，增删改操作会把缓存清空
     *(4)手动清空了缓存
     *
     * 每次查询，先看一级缓存中有没有，如果没有就去发送新的sql
     *
     *
     * 本地缓存不能被关闭，但可以调用clearCache()来清空本地缓存，或者改变缓存的作用域
     * 在mybatis3.1之后，可以配置本地缓存的作用域在mybatis.xml中配置
     *
     *mybatis，全局作用域缓存，二级缓存默认不开启，需要手动配配置
     * mybatis提供二级缓存的接口以及实现，缓存实现要求pojo实现Serializable接口
     * 二级缓存在sqlSession关闭或提交之后才会生效
     *
     *
     * eviction:缓存回收策略
     *          LRU:最近最少使用的，移除最长时间不被使用的对象
     *          FIFO:先进先出，按对象进入缓存的顺序来移除他们
     *          SOFT:软引用：移除基于垃圾回收状态和弱引用规则的对象
     *          WEAK:弱引用，更积极地移除基于垃圾回收器状和弱引用规则的对象
     *
     *          默认的规则LRU
     *
     *          flushInterval:刷新间隔，单位毫秒
     *
     *          默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句刷新
     *
     *          size:引用数目，正整数
     *          代表缓存最多可以存储多少个对象，太大容易导致内存溢出
     *
     *          readOnly:只读
     *              true：只读缓存，会给所有调用者返回缓存对象的相同实例，因此这些对象不能被修改，这提供了很重要的性能优势
     *              false:读写缓存  默认是false
     *
     *
     * 查询数据库的顺序：
     *      （1）不会出现一级缓存和二级缓存中有同一个数据
     *      二级缓存中，一级缓存关闭了就有了
     *      一级缓存中，二级缓存中没有此数据，就会看一级缓存，一级缓存没有去查数据库
     *              数据库的查询后的结果放在一级缓存中了
     *        （2）任何时候都是先看二级缓存，如果二级缓存中没有对应数据，再看一级缓存，如果都没有就去看数据库
     *
     *        2---1---库
     *
     * 通常不会使用mybatis自带的二级缓存，使用第三方二级缓存
     *
     * Mybatis---Cache   将缓存设计成接口Cache接口,可以自定义缓存
     *
     *
     *
     */
    @Test
    public void test(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);
            Teacher teacherById = mapper.getTeacherById(1);
            System.out.println(teacherById);
            System.out.println("==============");
            Teacher teacherById1 = mapper.getTeacherById(1);
            System.out.println(teacherById==teacherById1);//true
        }finally {
            sqlSession.close();
        }
    }

    //修改操作
    @Test
    public void test02(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);

//            执行任何一个增删改操作
            Teacher teacher = new Teacher();
            teacher.setId(3);
            teacher.setName("3333");
            mapper.updateTeacher(teacher);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test01(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try{
            TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);
            Teacher teacherById = mapper.getTeacherById(1);
            System.out.println(teacherById);
            System.out.println("==============");

//            执行任何一个增删改操作  都会将缓存数据清空
//            Teacher teacher = new Teacher();
//            teacher.setId(3);
//            teacher.setName("3333");
//            mapper.updateTeacher(teacher);


            //也可以手动将缓存数据清空
            sqlSession.clearCache();

            Teacher teacherById1 = mapper.getTeacherById(1);
            System.out.println(teacherById==teacherById1);//true
        }finally {
            sqlSession.close();
        }
    }

    //测试二级缓存
    @Test
    public void test03(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);
        TeacherDao mapper1 = sqlSession1.getMapper(TeacherDao.class);

        Teacher teacherById = mapper.getTeacherById(1);
        System.out.println(teacherById);
        sqlSession.close();

        Teacher teacherById1 = mapper1.getTeacherById(1);
        System.out.println(teacherById1);
        sqlSession1.close();
    }


}
