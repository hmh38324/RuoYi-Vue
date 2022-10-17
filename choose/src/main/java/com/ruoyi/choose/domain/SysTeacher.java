package com.ruoyi.choose.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 教师信息对象 sys_teacher
 * 
 * @author ruoyi
 * @date 2022-08-19
 */
public class SysTeacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long teacherId;

    /** 学生名称 */
    @Excel(name = "学生名称")
    private String teacherName;

    /** 导师招生指标 */
    @Excel(name = "导师招生指标")
    private Long teacherPlan;

    /** 导师选择的学生（id-id-id） */
    @Excel(name = "导师选择的学生", readConverterExp = "i=d-id-id")
    private String teacherChosen;

    /** 可招收专业和方向 */
    @Excel(name = "可招收专业和方向")
    private String teacherIntroduce;

    /** 还可以招收多少学生（初始值为计划招生数） */
    @Excel(name = "还可以招收多少学生", readConverterExp = "初=始值为计划招生数")
    private Long teacherRemain;

    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }
    public void setTeacherPlan(Long teacherPlan) 
    {
        this.teacherPlan = teacherPlan;
    }

    public Long getTeacherPlan() 
    {
        return teacherPlan;
    }
    public void setTeacherChosen(String teacherChosen) 
    {
        this.teacherChosen = teacherChosen;
    }

    public String getTeacherChosen() 
    {
        return teacherChosen;
    }
    public void setTeacherIntroduce(String teacherIntroduce) 
    {
        this.teacherIntroduce = teacherIntroduce;
    }

    public String getTeacherIntroduce() 
    {
        return teacherIntroduce;
    }
    public void setTeacherRemain(Long teacherRemain) 
    {
        this.teacherRemain = teacherRemain;
    }

    public Long getTeacherRemain() 
    {
        return teacherRemain;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teacherId", getTeacherId())
            .append("teacherName", getTeacherName())
            .append("teacherPlan", getTeacherPlan())
            .append("teacherChosen", getTeacherChosen())
            .append("teacherIntroduce", getTeacherIntroduce())
            .append("teacherRemain", getTeacherRemain())
            .toString();
    }
}
