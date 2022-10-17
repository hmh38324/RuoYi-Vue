package com.ruoyi.web.controller.choose;

import com.ruoyi.choose.domain.SysTeacher;
import com.ruoyi.choose.service.ISysTeacherService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 教师信息Controller
 * 
 * @author ruoyi
 * @date 2022-08-15
 */
@RestController
@RequestMapping("/choose/teacher")
public class SysTeacherController extends BaseController
{
    @Autowired
    private ISysTeacherService sysTeacherService;

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

    /**
     * 导出教师信息列表
     */
    @Log(title = "教师信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTeacher sysTeacher)
    {
        List<SysTeacher> list = sysTeacherService.selectSysTeacherList(sysTeacher);
        ExcelUtil<SysTeacher> util = new ExcelUtil<SysTeacher>(SysTeacher.class);
        util.exportExcel(response, list, "教师信息数据");
    }

    /**
     * 获取教师信息详细信息
     */
    @GetMapping(value = "/{teacherId}")
    public AjaxResult getInfo(@PathVariable("teacherId") Long teacherId)
    {
        return AjaxResult.success(sysTeacherService.selectSysTeacherByTeacherId(teacherId));
    }

    /**
     * 新增教师信息
     */
    @Log(title = "教师信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTeacher sysTeacher)
    {
        return toAjax(sysTeacherService.insertSysTeacher(sysTeacher));
    }

    /**
     * 修改教师信息
     */
    @Log(title = "教师信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTeacher sysTeacher)
    {
        return toAjax(sysTeacherService.updateSysTeacher(sysTeacher));
    }

    /**
     * 删除教师信息
     */
    @Log(title = "教师信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teacherIds}")
    public AjaxResult remove(@PathVariable Long[] teacherIds)
    {
        return toAjax(sysTeacherService.deleteSysTeacherByTeacherIds(teacherIds));
    }
}
