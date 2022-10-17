<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="导师姓名" prop="teacherName">
        <el-input
          v-model="queryParams.teacherName"
          placeholder="请输入导师名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <el-col style="text-align: center;">
        <h2>已选中导师</h2>
    </el-col>
    <!-- 我选中的导师 -->
    <el-table v-loading="loading" :data="chosenTeacher">
      <el-table-column label="编号" align="center" prop="teacherId" />
      <el-table-column label="导师名称" align="center" prop="teacherName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-col style="text-align: center;">
        <h2>导师列表</h2>
    </el-col>
    <!-- 导师列表 -->
    <el-table v-loading="loading" :data="teacherList" >
      <el-table-column label="编号" align="center" prop="teacherId" />
      <el-table-column label="导师名称" align="center" prop="teacherName" />
      <el-table-column label="招生指标" align="center" prop="teacherPlan" />
      <el-table-column label="招生方向" align="center" prop="teacherIntroduce" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleChoose(scope.row)"
          >选择</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
 
  </div>
</template>

<script>
import { listTeacher, choose, showChosen, deleteChosen} from "@/api/sys_choose/choose_teacher";
// import { listTeacher } from "@/api/choose/teacher";

export default {
  name: "Teacher",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 教师信息表格数据
      teacherList: [],
      // 选中的教师的信息
      chosenTeacher: [],
      //选中的教师的id
      form: {},
      del: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherName: null
      },
    };
  },
  created() {
    this.getList();
    //加载我已经选择的导师
    this.getChosenTeacher();
  },
  methods: {
    /** 查询教师信息列表 */
    getList() {
      this.loading = true;
      listTeacher(this.queryParams).then(response => {
        this.teacherList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /* 查询我已经选择的导师信息 */
    getChosenTeacher() { 
        showChosen().then(response => {
        this.chosenTeacher = response.rows;
      });
    },


     /** 选中导师的操作 */
    handleChoose(row) {
      this.form = {
        teacherName: row.teacherName,
        teacherId: row.teacherId};
      //修改当前选中的导师的信息
      this.chosenTeacher = [
        {
          teacherId: row.teacherId,
          teacherName: row.teacherName
        }
      ];
      const id = row.teacherId;
      //提交选中的导师信息
      this.submitTeacher();
    },
     /** 提交选中结果 */
    submitTeacher() {
            choose(this.form).then(response => {
              this.$modal.msgSuccess("选择成功");
              this.open = false;
              this.getList();
            });   
    },

     //删除已选中学生
    handleDelete(row){
      this.del = {
        teacherName: row.teacherName,
        teacherId: row.teacherId};
     this.delTeacher();
    },
    delTeacher(){
       deleteChosen(this.del).then(response => {
        this.$modal.msgSuccess("删除成功");
        this.open = false;
        this.getList();
        this.getChosenTeacher();
      });
      this.getChosenTeacher();
    },

  }
};
</script>
