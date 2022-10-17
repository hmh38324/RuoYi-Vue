package com.ruoyi.choose.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.choose.mapper.SysTeacherMapper;
import com.ruoyi.choose.domain.SysTeacher;
import com.ruoyi.choose.service.ISysTeacherService;

/**
 * 教师信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-19
 */
@Service
public class SysTeacherServiceImpl implements ISysTeacherService 
{
    @Autowired
    private SysTeacherMapper sysTeacherMapper;

    /**
     * 查询教师信息
     * 
     * @param teacherId 教师信息主键
     * @return 教师信息
     */
    @Override
    public SysTeacher selectSysTeacherByTeacherId(Long teacherId)
    {
        return sysTeacherMapper.selectSysTeacherByTeacherId(teacherId);
    }

    /**
     * 查询教师信息列表
     * 
     * @param sysTeacher 教师信息
     * @return 教师信息
     */
    @Override
    public List<SysTeacher> selectSysTeacherList(SysTeacher sysTeacher)
    {
        return sysTeacherMapper.selectSysTeacherList(sysTeacher);
    }

    /**
     * 新增教师信息
     * 
     * @param sysTeacher 教师信息
     * @return 结果
     */
    @Override
    public int insertSysTeacher(SysTeacher sysTeacher)
    {
        return sysTeacherMapper.insertSysTeacher(sysTeacher);
    }

    /**
     * 修改教师信息
     * 
     * @param sysTeacher 教师信息
     * @return 结果
     */
    @Override
    public int updateSysTeacher(SysTeacher sysTeacher)
    {
        return sysTeacherMapper.updateSysTeacher(sysTeacher);
    }

    /**
     * 批量删除教师信息
     * 
     * @param teacherIds 需要删除的教师信息主键
     * @return 结果
     */
    @Override
    public int deleteSysTeacherByTeacherIds(Long[] teacherIds)
    {
        return sysTeacherMapper.deleteSysTeacherByTeacherIds(teacherIds);
    }

    /**
     * 删除教师信息信息
     * 
     * @param teacherId 教师信息主键
     * @return 结果
     */
    @Override
    public int deleteSysTeacherByTeacherId(Long teacherId)
    {
        return sysTeacherMapper.deleteSysTeacherByTeacherId(teacherId);
    }
}
