package org.goldstine.dao;

import org.apache.ibatis.annotations.Param;
import org.goldstine.bean.Teacher;

import java.util.List;

/**
 * 对应的dao层也可以称为mapper
 */
public interface TeacherDao {

    public Teacher getTeacherById(Integer id);

    //通过条件进行查询
    public List<Teacher> getTeacherByCondition(Teacher teacher);

    public List<Teacher> getTeacherByIdIn(@Param("ids") List<Integer> ids);

    //修改
    public int updateTeacher(Teacher teacher);

}
