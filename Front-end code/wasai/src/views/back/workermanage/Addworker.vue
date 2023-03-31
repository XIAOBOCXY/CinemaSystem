<template>
  <div class="main">
    <p class="title">新增员工</p>
    <div class="filmname">
      <p>用户名</p>
      <el-input v-model="worker.username" placeholder="请输入用户名"></el-input>
    </div>
    <div class="filmname">
      <p>密码</p>
      <el-input
        v-model="worker.password"
        placeholder="请输入密码"
        show-password
      ></el-input>
    </div>
    <div class="filmname">
      <p>昵称</p>
      <el-input v-model="worker.nickname" placeholder="请输入昵称"></el-input>
    </div>
    <div class="filmregion">
      <p>性别</p>
      <el-select v-model="worker.gender" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </div>
    <div class="filmname">
      <p>联系电话</p>
      <el-input v-model="worker.phone" placeholder="请输入联系电话"></el-input>
    </div>
    <div class="filmselect">
      <p>入职部门</p>
      <el-radio-group v-model="radio">
        <el-radio :label="1">客服部</el-radio>
        <el-radio :label="2">运营部</el-radio>
        <el-radio :label="3">宣传部</el-radio>
        <el-radio :label="4">策划部</el-radio>
        <el-radio :label="5">人事部</el-radio>
        <el-radio :label="6">监督部</el-radio>
      </el-radio-group>
    </div>
    <div class="filmcover">
      <p>员工头像</p>
      <el-upload
        class="avatar-uploader"
        action="http://localhost:8888/api/user/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </div>
    <div class="button">
      <el-button type="primary" @click="submitaddfilm(worker, fileName)"
        >确认提交</el-button
      ><el-button>取消</el-button>
    </div>
  </div>
</template>

<script>
import { AddFilm } from "@/api/film.js";
import { upload } from "@/api/user.js";
import { Addworker } from "@/api/worker.js";
export default {
  inject: ["reload"],
  data() {
    return {
      input: "",
      options: [
        {
          value: "男",
          label: "男",
        },
        {
          value: "女",
          label: "女",
        },
      ],
      // film: {
      //   cover: "",
      //   name: "",
      //   region: "",
      //   duration: "",
      //   releaseTime: "",
      //   status: "",
      //   type: "",
      //   introduction: "",
      // },
      // shangjia: "",
      // radio: "",
      imageUrl: "",
      // fileName: "",
      radio: "",
      worker: {},
    };
  },
  methods: {
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
    // handleChange(value) {
    //   // console.log(value);
    // },
    submitaddfilm(item, fileName) {
      console.log(item);
      // if (this.shangjia === 0) {
      //   item.status = 0;
      // } else {
      //   item.status = 1;
      // }
      if (this.radio === 1) {
        item.department = "客服部";
      } else if (this.radio === 2) {
        item.department = "运营部";
      } else if (this.radio === 3) {
        item.department = "宣传部";
      } else if (this.radio === 4) {
        item.department = "策划部";
      } else if (this.radio === 5) {
        item.department = "人事部";
      } else if (this.radio === 6) {
        item.department = "监督部";
      }
      // item.releaseTime = this.formatDate(this.releaseTime);
      console.log(item);
      console.log("fileName");
      console.log(fileName);
      let data = {
        avatar: this.fileName,
        username: this.worker.username,
        password: this.worker.password,
        nickname: this.worker.nickname,
        phone: this.worker.phone,
        entry: 1,
        gender: this.worker.gender,
        department: this.worker.department,
        createTime: new Date(),
        updateTime: new Date(),
      };
      console.log(data);
      Addworker(data).then((res) => {
        if (res.code == 200) {
          console.log(res.data);
          this.$message({
            type: "success",
            message: "新增员工成功!",
          });
          this.reload();
          this.dialogFormVisible = false;
        }
      });
    },
    handleAvatarSuccess(res, file) {
      this.fileName = res.data.fileName;
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
  },
};
</script>

<style lang="scss" scoped>
.main {
  height: 820px;
  background-color: #f4f4f4;
  padding: 20px;
  .title {
    font-size: 18px;
    color: gray;
  }
  .filmcover {
    margin-top: 20px;
    p {
      float: left;
      padding-right: 20px;
    }
    .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #409eff;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      line-height: 178px;
      text-align: center;
    }
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }
  }
  .filmname {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
    .el-input {
      width: 360px;
    }
  }
  .filmregion {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
    .el-select {
      width: 360px;
    }
  }
  .filmtime {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
  }
  .filmstart {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
  }
  .filmon {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
  }
  .filmselect {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
    .el-radio-group {
      width: 1150px;
      .el-radio {
        padding: 5px;
      }
    }
  }
  .filminfo {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
    div {
      float: left;
      width: 400px;
    }
  }
  .button {
    clear: both;
    padding-left: 80px;
    margin-top: 150px;
    .el-button {
      width: 100px;
    }
  }
}
</style>