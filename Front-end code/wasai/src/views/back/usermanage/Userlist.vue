<template>
  <el-table :data="user" style="width: 100%" stripe border height="600px">
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
          <el-form-item label="邮箱">
            <span>{{ props.row.email }}</span>
          </el-form-item>
          <el-form-item label="性别">
            <span>{{ props.row.gender }}</span>
          </el-form-item>
          <el-form-item label="生日">
            <span>{{ props.row.birthday }}</span>
          </el-form-item>
          <el-form-item label="个人简介">
            <span>{{ props.row.info }}</span>
          </el-form-item>
          <el-form-item label="更新时间">
            <span>{{ props.row.updateTime }}</span>
          </el-form-item>
          <el-form-item label="用户头像URL">
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
              <span><img class="img" :src="User.avatar" alt="" /></span>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
              </span>
            </el-dialog>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column label="用户ID" prop="id" width="150px"> </el-table-column>
    <el-table-column label="用户名" prop="username"> </el-table-column>
    <el-table-column label="昵称" prop="nickname"> </el-table-column>
    <el-table-column label="注册时间" prop="createTime"> </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>



<script>
import { ListAllUser, deleteuser, updateuser, UserInfo2 } from "@/api/user";
export default {
  inject: ["reload"],
  data() {
    return {
      user: [],
      User: {},
      dialogVisible: false,
    };
  },
  methods: {
    handleDelete(index, row) {
      this.$confirm("确定要删除此用户？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteuser(row.id).then((res) => {
            if (res.code == 200) {
              // console.log(res.data);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
            }
          });
          this.listalluser();
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
    listalluser() {
      ListAllUser().then((res) => {
        if (res.code == 200) {
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].createTime = this.formatDate(res.data[i].createTime);
            res.data[i].updateTime = this.formatDate(res.data[i].updateTime);
            this.user = res.data;
            console.log(this.user);
          }
        } else {
        }
      });
    },
    editpassword(index, row) {
      this.$prompt("请输入新的密码", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
      })
        .then(({ value }) => {
          UserInfo2(row.id).then((res) => {
            if (res.code == 200) {
              this.User = res.data;
              this.User.password = { value }.value;
              console.log(this.User);
            }
            updateuser(this.User).then((res) => {
              if (res.code == 200) {
                this.$message({
                  type: "success",
                  message: "修改成功",
                });
                console.log(res.data);
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
    openavatar(index, row) {
      this.dialogVisible = true;
      UserInfo2(row.id).then((res) => {
        if (res.code == 200) {
          this.User = res.data;
          console.log(this.User);
        }
      });
    },
  },
  mounted() {
    /*查询用户列表 */
    this.listalluser();
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
.img {
  width: 400px;
}
</style>
