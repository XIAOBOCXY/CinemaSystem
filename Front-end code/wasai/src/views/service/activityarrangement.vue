<template>
  <div class="main">
    <div class="button">
      <el-button @click="open()">添加影院活动</el-button>
    </div>
    <el-table :data="activities" style="width: 100%" stripe>
      <el-table-column label="开始时间" width="180px"
        ><template slot-scope="scope"> {{ scope.row.startTime }}</template>
      </el-table-column>
      <el-table-column label="结束时间" width="180px">
        <template slot-scope="scope">{{ scope.row.endTime }}</template>
      </el-table-column>
      <el-table-column label="活动内容">
        <template slot-scope="scope">{{ scope.row.content }}</template>
      </el-table-column>
      <el-table-column label="参加人数" width="80px">
        <template slot-scope="scope">{{ scope.row.number }}</template>
      </el-table-column>

      <el-table-column width="100px" label="操作">
        <template slot-scope="scope">
          <el-button
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="提示" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="开始时间" :label-width="formLabelWidth">
          <div class="block">
            <span class="demonstration"></span>
            <el-date-picker
              v-model="activity.startTime"
              type="datetime"
              placeholder="选择日期时间"
            >
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item label="结束时间" :label-width="formLabelWidth">
          <div class="block">
            <span class="demonstration"></span>
            <el-date-picker
              v-model="activity.endTime"
              type="datetime"
              placeholder="选择日期时间"
            >
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item label="结束时间" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="activity.content"
          >
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitaddfilm(activity)"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  SearchActivities,
  Addactivity,
  deleteactivity,
} from "@/api/activity.js";
export default {
  inject: ["reload"],
  data() {
    return {
      activities: [],
      activity: {},
      dialogFormVisible: false,
      formLabelWidth: "120px",
    };
  },
  methods: {
    /*  ----------------活动查询---------------------*/
    activityQuery() {
      SearchActivities().then((res) => {
        /* console.log(res); */
        if (res.code == 200) {
          this.activities = res.data;
          for (var i = 0; i < this.activities.length; i++) {
            res.data[i].startTime = this.FormData(res.data[i].startTime);
            res.data[i].endTime = this.FormData(res.data[i].endTime);
          }
        }
      });
    },
    FormData(dateint) {
      //日期格式化
      //console.log(dateint)
      var date = new Date(parseInt(dateint));
      var str =
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1) +
        "-" +
        date.getDate() +
        "  " +
        date.getHours() +
        ":" +
        date.getMinutes() +
        ":" +
        date.getSeconds();
      return str;
    },
    handleDelete(index, row) {
      //console.log(index, row);
      this.$confirm("确定要删除此活动？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteactivity(row.id).then((res) => {
            if (res.code == 200) {
              // console.log(res.data);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
            }
          });
          this.reload();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    submitaddfilm(activity) {
      // if (this.shangjia === 0) {
      //   item.status = 0;
      // } else {
      //   item.status = 1;
      // }
      // if (this.radio === 1) {
      //   item.department = "客服部";
      // } else if (this.radio === 2) {
      //   item.department = "运营部";
      // } else if (this.radio === 3) {
      //   item.department = "宣传部";
      // } else if (this.radio === 4) {
      //   item.department = "策划部";
      // } else if (this.radio === 5) {
      //   item.department = "人事部";
      // } else if (this.radio === 6) {
      //   item.department = "监督部";
      // }
      // item.releaseTime = this.formatDate(this.releaseTime);
      // console.log(item);
      // console.log("fileName");
      // console.log(fileName);
      let data = {
        content: this.activity.content,
        startTime: this.activity.startTime,
        endTime: this.activity.endTime,
        number: 0,
        createTime: new Date(),
        updateTime: new Date(),
      };
      console.log(data);
      Addactivity(data).then((res) => {
        if (res.code == 200) {
          console.log(res.data);
          this.$message({
            type: "success",
            message: "新增活动成功!",
          });
          this.reload();
          this.dialogFormVisible = false;
        }
      });
    },
    open() {
      this.dialogFormVisible = true;
    },
  },
  mounted() {
    /* 活动查询接口 */
    this.activityQuery();
  },
};
</script>

<style lang="scss" scope>
.main {
  background-color: #f4f4f4;
  padding: 20px;
  .el-carousel {
    padding-top: 20px;
    width: 1250px;
    margin: 0 auto;
    .CarImg {
      width: 100%;
      height: 100%;
    }
  }
  .button {
    padding-left: 30px;
    padding-bottom: 20px;
    .el-button {
      margin-right: 50px;
    }
  }
  .upfont {
    color: #00cc33;
  }
  .unupfont {
    color: #ff6633;
  }
}
</style>