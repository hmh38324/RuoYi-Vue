package com.ruoyi.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限过滤注解
 * 如果想要将某个模块比如操作日志加上权限控制:
 *      1 在数据库中将操作日志的表中加上user_id 和 dept_id 字段
 *      2 在实体类SysOperLog中加上userId 和 deptId字段
 *      3 在需要权限控制的方法上(查询操作日志selectOperLogList)加上@DataScope(deptAlias = "d", userAlias = "u")
 *      4 在对应的Mapper.xml语句中加入 ${params.dataScope}
 *      5 修改Mapper.xml中sql语句加入查询字段和连表查询字段(参考文档) 包括VO和insert
 *      6 修改添加实体类的切面方法,set userid 和 deptId
 *
 * 
 * @author ruoyi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";
}
