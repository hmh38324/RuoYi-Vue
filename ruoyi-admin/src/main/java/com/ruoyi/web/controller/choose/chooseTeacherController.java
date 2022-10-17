package com.ruoyi.web.controller.choose;

import com.ruoyi.choose.domain.SysStudent;
import com.ruoyi.choose.domain.SysTeacher;
import com.ruoyi.choose.service.ISysStudentService;
import com.ruoyi.choose.service.ISysTeacherService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 教师信息Controller
 * 
 * @author ruoyi
 * @date 2022-08-15
 */
@RestController
@RequestMapping("/sys_choose/choose_teacher")
public class chooseTeacherController extends BaseController
{
    @Autowired
    private ISysTeacherService sysTeacherService;

    @Autowired
    private ISysStudentService sysStudentService;

    /**
     * 获取教师信息详细信息
     */

    /**
     * 选择导师
     */
    @PostMapping
    public  AjaxResult choose( SysTeacher sysTeacher)
    {
        System.out.println("*****************************************************");
        System.out.println("测试jerbel");
        System.out.println(sysTeacher);
        System.out.println(sysTeacher.getTeacherName());
        System.out.println(sysTeacher.getTeacherId());
        System.out.println("*****************************************************");

        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        SysStudent sysStudent = sysStudentService.selectSysStudentByStudentId(userId);
        sysStudent.setStudentChoose(sysTeacher.getTeacherId());
        return toAjax(sysStudentService.updateSysStudent(sysStudent));
    }

    /**
     * 选择导师
     */
    @GetMapping("/showChosen")
    public  TableDataInfo showChosen()
    {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        System.out.println("*****************************************************");
        System.out.println(userId);
        System.out.println("*****************************************************");
        SysStudent sysStudent = sysStudentService.selectSysStudentByStudentId(userId);
        List<SysTeacher> teacherList = new ArrayList<>();
        Long teacherId = sysStudent.getStudentChoose();
        if(teacherId != -1){
            SysTeacher sysTeacher = sysTeacherService.selectSysTeacherByTeacherId(teacherId);
            teacherList.add(sysTeacher);
        }
        return getDataTable(teacherList);
    }

    /**
     * 删除选择的导师
     */
    @PostMapping("/deleteChosen")
    public  AjaxResult deleteChosen( SysTeacher sysTeacher)
    {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        SysStudent sysStudent = sysStudentService.selectSysStudentByStudentId(userId);
        sysStudent.setStudentChoose(-1L);
        sysStudent.setStudentChosen(0L);
        sysStudentService.updateSysStudent(sysStudent);
        return AjaxResult.success("删除成功！");
    }

    /**
     * 查询教师信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysTeacher sysTeacher)
    {
        startPage();
        List<SysTeacher> list = sysTeacherService.selectSysTeacherList(sysTeacher);
        return getDataTable(list);
    }


}
