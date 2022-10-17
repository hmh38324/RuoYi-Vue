package com.ruoyi.web.controller.choose;

import com.ruoyi.choose.domain.SysStudent;
import com.ruoyi.choose.domain.SysTeacher;
import com.ruoyi.choose.service.ISysStudentService;
import com.ruoyi.choose.service.ISysTeacherService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师信息Controller
 * 
 * @author ruoyi
 * @date 2022-08-15
 */
@RestController
@RequestMapping("/sys_choose/choose_student")
public class chooseStudentController extends BaseController
{
    @Autowired
    private ISysTeacherService sysTeacherService;

    @Autowired
    private ISysStudentService sysStudentService;

    /**
     * 选择导师
     */
    @GetMapping("/getTeacherId")
    public  AjaxResult getTeacherId()
    {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        System.out.println("*****************************************************");
        System.out.println(userId);
        System.out.println("*****************************************************");
        SysTeacher sysTeacher = sysTeacherService.selectSysTeacherByTeacherId(userId);
        System.out.println("*****************************************************");
        System.out.println(sysTeacher);
        System.out.println("*****************************************************");
        return AjaxResult.success(sysTeacher);
    }

    /**
     * 选择学生
     */
    @PostMapping
    public  AjaxResult choose( SysStudent sysStudent)
    {
        //获取当前选择的学生数量和招生指标
        Long teacherId = SecurityUtils.getUserId();
        SysTeacher sysTeacher = sysTeacherService.selectSysTeacherByTeacherId(teacherId);
        Long teacherPlan = sysTeacher.getTeacherPlan();
        String ss = sysTeacher.getTeacherChosen();
        if(ss == null) ss = "";
        String[] stuIds = ss.split("-");
        int num = 0;
        List<String> listStu = new ArrayList<>();
        for(String s : stuIds){
            if(s.length()>2) num++;
            listStu.add(s);
        }
        if(num == teacherPlan){
            return AjaxResult.error("招生指标已满，不能再选择学生");
        }
        //判断学生是否已经选中
        String id = String.valueOf(sysStudent.getStudentId());
        for(String s : listStu){
            System.out.println(id);
            System.out.println(s);
            if(s.equals(id)) return AjaxResult.error("已经选中的学生不能重复选择");
        }
        Long studentId = sysStudent.getStudentId();
        SysStudent curStu = sysStudentService.selectSysStudentByStudentId(studentId);
        //判断学生有没有修改选择的导师
        Long studentChoose = curStu.getStudentChoose();
        if(!studentChoose.equals(teacherId)){
            return AjaxResult.error("该学生可能修改了选择的导师，请您刷新重试");
        }
        //修改学生的被选择位
        curStu.setStudentChosen(1L);
        sysStudentService.updateSysStudent(curStu);
        if(ss != ""){
            ss += "-" + studentId;
        }
        sysTeacher.setTeacherChosen(ss);
        //剩余名额--
        Long remain = sysTeacher.getTeacherRemain();
        sysTeacher.setTeacherRemain(remain-1);
        sysTeacherService.updateSysTeacher(sysTeacher);
        return AjaxResult.success("选中学生成功！");
    }

    /**
     * 选择学生
     */
    @GetMapping("/showChosen")
    public  TableDataInfo showChosen()
    {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        SysTeacher sysTeacher = sysTeacherService.selectSysTeacherByTeacherId(userId);
        String s = sysTeacher.getTeacherChosen();
        String[] ss = s.split("-");
        System.out.println("*************");
        for(String  sss : ss) System.out.println(sss);
        System.out.println("*************");
        List<String> stuIds = new ArrayList<>();
        for(String id : ss){
            if (id.length() > 2) stuIds.add(id);
        }
        System.out.println("*************");
        for(String  sss : stuIds) System.out.println(sss);
        System.out.println("*************");
        List<SysStudent> studentList = new ArrayList<>();
        for(String id : stuIds){
            SysStudent sysStudent = sysStudentService.selectSysStudentByStudentId(Long.valueOf(id));
            studentList.add(sysStudent);
        }
        return getDataTable(studentList);
    }
    /**
     * 删除选择的学生
     */
    @PostMapping("/deleteChosen")
    public  AjaxResult deleteChosen( SysStudent sysStudent)
    {
        String studentId = String.valueOf(sysStudent.getStudentId());
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        SysTeacher sysTeacher = sysTeacherService.selectSysTeacherByTeacherId(userId);
        String s = sysTeacher.getTeacherChosen();
        String[] ss = s.split("-");
        System.out.println("*************");
        for(String  sss : ss) System.out.println(sss);
        System.out.println("*************");
        List<String> stuIds = new ArrayList<>();
        for(String id : ss){
            if (id.length() > 2) stuIds.add(id);
        }
        StringBuilder sb = new StringBuilder();
        for(String sss : stuIds){
            if(!sss.equals(studentId)){
                sb.append("-"+sss);
            }
        }
        //修改学生的被选择位
        Long id = sysStudent.getStudentId();
        //修改学生的被选择位
        SysStudent curStu = sysStudentService.selectSysStudentByStudentId(id);
        curStu.setStudentChosen(0L);
        sysStudentService.updateSysStudent(curStu);
        sysTeacher.setTeacherChosen(sb.toString());
        //修改剩余名额
        Long remain = sysTeacher.getTeacherRemain();
        sysTeacher.setTeacherRemain(remain+1);
        sysTeacherService.updateSysTeacher(sysTeacher);
        return AjaxResult.success("删除成功！");
    }


    /**
     * 查询选中我的学生信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list( SysStudent sysStudent)
    {
        // System.out.println("id:"+id);
        Long userId = SecurityUtils.getUserId();
        // SysStudent sysStudent = new SysStudent();
        sysStudent.setStudentChoose(userId);
        // System.out.println("***********");
        // System.out.println(sysStudent);
        // System.out.println("***********");
        startPage();
        List<SysStudent> list = sysStudentService.selectSysStudentList(sysStudent);
        return getDataTable(list);
    }


}
