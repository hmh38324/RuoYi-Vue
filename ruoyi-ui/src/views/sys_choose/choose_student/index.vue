<template>
  <div class="app-container">
    <el-col style="text-align: center;">
        <h2>已选中的学生</h2>
    </el-col>
    <el-table v-loading="loading" :data="chosenStudent" >
      <el-table-column label="编号" align="center" prop="studentId" />
      <el-table-column label="学生名称" align="center" prop="studentName" />
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
        <h2>选择您的学生</h2>
    </el-col> 
    <el-table v-loading="loading" :data="studentList" >
      <el-table-column label="编号" align="center" prop="studentId" />
      <el-table-column label="学生名称" align="center" prop="studentName" />
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
import { listStudent, choose, showChosen ,deleteChosen} from "@/api/sys_choose/choose_student";

export default {
  name: "Student",
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
      // 学生信息表格数据
      studentList: [],
      //选择的学生列表
      chosenStudent:[],
      //导师编号
      teacher_id: {},
      aid: null,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentName: null,
        studentChoose: 0,
        studentChosen: null,
        studentComment: null
      },
      // 表单参数
      form: [],
      del: [],
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getChosenStudent();
  },
  methods: {
    /** 查询学生信息列表 */
    getList() {
      console.log(this.aid);
      this.loading = true;
      this.queryParams.studentChoose = this.teacher_id;
      listStudent(this.query).then(response => {
        this.studentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
   handleChoose(row) {
      this.form = {
        studentName: row.studentName,
        studentId: row.studentId};
      //提交选中的导师信息
       this.submitTeacher();
    },
     /** 提交选中结果 */
    submitTeacher() {
      const that = this;
      choose(this.form).then(response => {
        console.log(response);
        this.$modal.msgSuccess("选择成功");
        this.open = false;
        this.getList();
        this.getChosenStudent();
      });   
    },
    //获取已经选择的导师
    getChosenStudent(){
      showChosen().then(response => {
        this.chosenStudent = response.rows;
      })
    },
    //删除已选中学生
    handleDelete(row){
      this.del = {
        studentName: row.studentName,
        studentId: row.studentId};
     this.delStu();
    },
    delStu(){
       deleteChosen(this.del).then(response => {
        this.$modal.msgSuccess("删除成功");
        this.open = false;
        this.getList();
        this.getChosenStudent();
      });
      this.getChosenStudent();
    },

  }
};
</script>
