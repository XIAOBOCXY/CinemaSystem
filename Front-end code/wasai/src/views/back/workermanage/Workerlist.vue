<template>
  <el-table :data="worker" style="width: 100%" stripe border height="600px">
    <el-table-column type="expand" flex>
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="用户名">
            <span>{{ props.row.username }}</span>
          </el-form-item>
          <el-form-item label="昵称">
            <span>{{ props.row.nickname }}</span>
          </el-form-item>
          <el-form-item label="密码">
            <span>
              <el-link
                :underline="false"
                type="primary"
                @click="editpassword(props.$index, props.row)"
                >编辑密码</el-link
              ></span
            >
          </el-form-item>

          <el-form-item label="性别">
            <span>{{ props.row.gender }}</span>
          </el-form-item>
          <el-form-item label="入职部门">
            {{ props.row.department }}</el-form-item
          >
          <el-form-item label="状态">
            {{ props.row.entry }}
            <el-link
              :underline="false"
              type="primary"
              @click="changeenrty(props.$index, props.row)"
              >修改员工离职和入职状态</el-link
            >
          </el-form-item>
          <el-form-item label="更新时间">
            <span>{{ props.row.updateTime }}</span>
          </el-form-item>
          <el-form-item label="员工头像">
            <span
              ><el-link
                :underline="false"
                type="primary"
                @click="openavatar(props.$index, props.row)"
                >查看</el-link
              ></span
            >
            <el-dialog
              title="查看头像"
              :visible.sync="dialogVisible"
              width="30%"
            >
              <span><img class="img1" :src="User.avatar" alt="" /></span>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
              </span>
            </el-dialog>
          </el-form-item>
          <el-form-item>
            <span
              ><template>
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(props.$index, props.row)"
                  >删除员工</el-button
                >
              </template></span
            >
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column label="用户ID" prop="id" width="150px"> </el-table-column>
    <el-table-column label="用户名" prop="username" width="150px">
    </el-table-column>
    <el-table-column label="所在部门">
      <template slot-scope="scope">
        <el-tag>{{ scope.row.department }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="联系电话" prop="phone"> </el-table-column>
    <el-table-column label="入职时间" prop="createTime"> </el-table-column>
  </el-table>
</template>



<script>
import {
  ListAllWorker,
  WorkerInfo2,
  updateworker,
  WorkerInfo3,
  deleteworker,
} from "@/api/worker.js";
export default {
  inject: ["reload"],
  data() {
    return {
      worker: [],
      dialogVisible: false,
      User: {},
    };
  },
  methods: {
    handleDelete(index, row) {
      //console.log(index, row);
      this.$confirm("确定要删除此员工？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteworker(row.id).then((res) => {
            if (res.code == 200) {
              // console.log(res.data);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
            }
          });
          this.start();
          this.reload();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    formatDate(dateint) {
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
    editpassword(index, row) {
      this.$prompt("请输入新的密码", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      })
        .then(({ value }) => {
          WorkerInfo2(row.id).then((res) => {
            if (res.code == 200) {
              this.User = res.data;

              this.User.password = { value }.value;
              console.log(this.User);
            }
            updateworker(this.User).then((res) => {
              if (res.code == 200) {
                this.$message({
                  type: "success",
                  message: "修改成功",
                });
                console.log(res.data);
                this.reload();
              }
            });
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "取消输入",
          });
        });
    },
    changeenrty(index, row) {
      WorkerInfo2(row.id).then((res) => {
        if (res.code == 200) {
          this.User = res.data;
          if (this.User.entry === "1") {
            this.User.entry = "0";
          } else {
            this.User.entry = "1";
          }
          console.log(this.User);
        }
        updateworker(this.User).then((res) => {
          if (res.code == 200) {
            this.$message({
              type: "success",
              message: "修改成功",
            });
            console.log(res.data);
          }
        });
      });
    },
    openavatar(index, row) {
      this.dialogVisible = true;
      WorkerInfo3(row.id).then((res) => {
        if (res.code == 200) {
          this.User = res.data;
          console.log("aaaaaaa");
          console.log(this.User);
        }
      });
    },
    start() {
      ListAllWorker().then((res) => {
        if (res.code == 200) {
          console.log(res);
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].createTime = this.formatDate(res.data[i].createTime);
            res.data[i].updateTime = this.formatDate(res.data[i].updateTime);
            if (res.data[i].entry === "1") {
              res.data[i].entry = "在职";
            } else {
              res.data[i].entry = "离职";
            }

            this.worker = res.data;
          }
          console.log(this.worker);
        }
      });
    },
  },
  mounted() {
    /*查询员工列表 */
    this.start();
  },
};
</script>

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 200px;
  color: #99a9bf;
  padding-left: 40px;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.el-button {
  margin-left: 40px;
}
.img1 {
  width: 400px;
}
</style>
