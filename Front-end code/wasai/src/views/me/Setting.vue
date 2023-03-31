<template>
  <div>
    <h2>基本设置</h2>
    <div class="left">
      <p>昵称</p>
      <div><el-input v-model="user.nickname"></el-input></div>
      <p>密码</p>
      <el-input v-model="user.password" show-password></el-input>
      <!-- <p>性别</p>
      <el-radio v-model="radio" label="1">男</el-radio>
      <el-radio v-model="radio" label="2">女</el-radio> -->
      <div class="filmselect">
        <p>入职部门</p>
        <el-radio-group v-model="user.gender">
          <el-radio label="男">男</el-radio>
          <el-radio label="女">女</el-radio>
        </el-radio-group>
      </div>
      <p>出生年月</p>
      <el-date-picker
        type="date"
        v-model="user.birthday"
        style="width: 100%"
        placeholder="请选择日期"
      ></el-date-picker>
      <p>邮箱</p>
      <el-input v-model="user.email"></el-input>
      <p>个人简介</p>
      <el-input
        type="user.introduce"
        :rows="4"
        v-model="user.info"
        resize="none"
      >
      </el-input>
      <div class="btn">
        <el-button type="primary" @click="submitaddfilm(user, fileName)"
          >更新基本信息</el-button
        >
      </div>
    </div>

    <div class="right">
      <p>头像修改</p>
      <el-upload
        class="avatar-uploader"
        action="http://localhost:8888/api/user/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
      >
        <img v-if="imageUrl" :src="imageUrl" class="img1" />
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </div>
  </div>
</template>
<script>
import { updateuser, UserInfo2, UserInfo3 } from "@/api/user.js";
export default {
  inject: ["reload"],
  data() {
    return {
      user: {
        nickname: "",
        password: "",
        email: "",
        birthday: "",
        gender: "",
        info: "",
      },
      imageUrl: "",
      //   radio: "",
      fileName: "",
      User: "",
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
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
    submitaddfilm(item, fileName) {
      console.log(item);
      // if (this.radio === 1) {
      //   item.gender = "男";
      // } else if (this.radio === 2) {
      //   item.gender = "女";
      // }
      // item.releaseTime = this.formatDate(this.releaseTime);
      let data = {
        id: localStorage.getItem("uid"),
        // username: this.user.username,
        password: this.user.password,
        avatar: fileName,
        nickname: this.user.nickname,
        email: this.user.email,
        birthday: this.user.birthday,
        gender: this.user.gender,
        info: this.user.info,
        createTime: new Date(),
        updateTime: new Date(),
        // slat: this.user.slatIndex,
        // slatIndex: this.user.slatIndex,
        // errorNum: this.user.errorNum,
        // status: this.user.status,
      };
      console.log(data.password);
      console.log(this.User.password);
      if (typeof data.password === "undefined") {
        this.$message({
          type: "warning",
          message: "请填写密码!",
        });
      } else {
        if (data.avatar === "") {
          data.avatar = this.User.avatar;
        }
        console.log(data);
        updateuser(data).then((res) => {
          if (res.code == 200) {
            console.log(res.data);
            this.$message({
              type: "success",
              message: "修改信息成功!",
            });
            this.reload();
            this.dialogFormVisible = false;
          }
        });
      }
    },
    // submitaddfilm(item, fileName) {
    //   console.log(item);
    //   if (this.radio === 1) {
    //     item.gender = "男";
    //   } else if (this.radio === 2) {
    //     item.gender = "女";
    //   }
    //   UserInfo3(localStorage.getItem("uid")).then((res) => {
    //     if (res.code == 200) {
    //       console.log("res.data");
    //       console.log(this.user);
    //       console.log(res.data);
    //       this.User = res.data;
    //       this.User.username = this.user.username;
    //       this.User.password = this.user.password;
    //       this.User.avatar = this.fileName;
    //       this.User.nickname = this.user.nickname;
    //       this.User.email = this.user.email;
    //       this.User.gender = this.user.gender;
    //       this.User.info = this.user.info;
    //       this.User.createTime = this.user.createTime;
    //       console.log(this.User);
    //     }
    //   }),
    //     updateuser(this.User).then((res) => {
    //       console.log("11111111111111111111111");
    //       if (res.code == 200) {
    //         this.$message({
    //           type: "success",
    //           message: "修改成功",
    //         });
    //         console.log(res.data);
    //       }
    //     });
    // },

    start() {
      // console.log(localStorage.getItem("uid"));
      UserInfo2(localStorage.getItem("uid")).then((res) => {
        if (res.code == 200) {
          this.User = res.data;
          console.log(this.User);
        }
      });
    },
  },
  mounted() {
    this.start();
  },
};
</script>
<style lang="scss" scoped>
div {
  h2 {
    font-size: 20px;
    color: #333333;
    margin: 40px 20px 20px 20px;
    font-weight: normal;
  }
  .left {
    float: left;
    width: 400px;
    height: 600px;
    margin: 0 20px;
    p {
      margin: 20px 0 10px 0;
    }
    .btn {
      margin: 40px 0;
    }
  }
  .right {
    width: 200px;
    height: 200px;
    margin-left: 80px;
    float: left;
    .img1 {
      width: 100px;
    }
  }
}
</style>