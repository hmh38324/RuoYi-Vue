package com.ruoyi.choose.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生信息对象 sys_student
 * 
 * @author ruoyi
 * @date 2022-08-15
 */
public class SysStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long studentId;

    /** 学生名称 */
    @Excel(name = "学生名称")
    private String studentName;

    /** 学生选择的导师的编号 */
    @Excel(name = "学生选择的导师的编号")
    private Long studentChoose;

    /** 是否被自己选择的导师选择（0是 1否） */
    @Excel(name = "是否被自己选择的导师选择", readConverterExp = "0=是,1=否")
    private Long studentChosen;

    /** 备注 */
    @Excel(name = "备注")
    private String studentComment;

    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }
    public void setStudentChoose(Long studentChoose) 
    {
        this.studentChoose = studentChoose;
    }

    public Long getStudentChoose() 
    {
        return studentChoose;
    }
    public void setStudentChosen(Long studentChosen) 
    {
        this.studentChosen = studentChosen;
    }

    public Long getStudentChosen() 
    {
        return studentChosen;
    }
    public void setStudentComment(String studentComment) 
    {
        this.studentComment = studentComment;
    }

    public String getStudentComment() 
    {
        return studentComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("studentId", getStudentId())
            .append("studentName", getStudentName())
            .append("studentChoose", getStudentChoose())
            .append("studentChosen", getStudentChosen())
            .append("studentComment", getStudentComment())
            .toString();
    }
}
