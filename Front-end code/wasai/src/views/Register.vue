<template>
  <div class="register">
    <div class="register_info">
      <!-- 注册上方标题(logo+哇塞电影-注册) -->
      <div class="register_title">
        <img src="../assets/img/logo.png" alt="" />
        <div class="register_text">哇塞电影-注册</div>
      </div>

      <!-- 注册界面表单 -->
      <div class="register_form">
        <!-- 账号 -->
        <p>UserName</p>
        <el-input
          v-model="username"
          placeholder="账号"
          @blur="isExistUserName"
        ></el-input>

        <!-- 密码 -->
        <p>PassWord</p>
        <form>
          <el-input
            v-model="password"
            placeholder="密码"
            show-password
          ></el-input>
        </form>
        <form>
          <!-- 确认密码 -->
          <p>Check PassWord</p>
          <el-input
            v-model="checkpassword"
            placeholder="密码"
            show-password
          ></el-input>
        </form>
        <!-- 邮箱地址 -->
        <p>邮箱</p>
        <el-input
          style="width: 450px"
          v-model="email"
          placeholder="请输入邮箱地址,获得邮箱验证码"
          @blur="checkEmail"
          :style="isPassEmail ? '' : 'border:1px solid red;'"
        ></el-input
        ><el-button
          type="info"
          icon="el-icon-message"
          circle
          @click="sendEmail"
        ></el-button>
        <!-- 邮箱地址 -->
        <p>邮箱验证码</p>
        <el-input v-model="emailCode" placeholder="请输入验证码"></el-input>
        <!-- 忘记密码部分 -->
        <div class="agreement">
          <el-checkbox v-model="agree" class="checkbox_text"
            >我已同意“哇塞电影”用户注册协议</el-checkbox
          >
        </div>
        <!-- 注册按钮部分 -->
        <div class="btn">
          <el-button
            class="register-form-button"
            type="primary"
            @click="register()"
            >SIGN IN</el-button
          >
        </div>

        <!-- 跳转到登录界面部分 -->
        <div class="register_footer">
          <el-link href="/login" class="rg" :underline="false">
            我有账号？去登录
            <i class="el-icon-right"></i>
          </el-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Register, SendEmailCode, IsExistUserName } from "../api/user";
export default {
  data() {
    return {
      username: "", //账号
      password: "", //密码
      checkpassword: "", //确认密码
      agree: false, //是否同意协议
      email: "",
      emailCode: "",
      isPassEmail: true,
    };
  },
  methods: {
    //判断用户名是否存在
    isExistUserName() {
      const vo = {
        email: this.username,
      };
      console.log(vo);
      IsExistUserName(vo).then((res) => {
        console.log(res.data);
        if (res.code == 200) {
          if (res.data.success === "success") {
            this.$message({
              message: res.data.msg,
              type: "success",
            });
          } else {
            this.$message.error(res.data.msg);
          }
        }
      });
    },
    //邮件输入框的失去焦点时间
    checkEmail() {
      const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      if (mailReg.test(this.email)) {
        this.isPassEmail = true;
      } else {
        this.isPassEmail = false;
      }
      console.log(this.isPassEmail);
    },

    //发送email验证码
    sendEmail() {
      //客户端对传入的邮箱内容，进行基本的邮箱格式验证。验证成功后发消息到服务端。
      //https://www.jb51.net/article/67628.htm

      if (this.isPassEmail) {
        const emailVo = {
          email: this.email,
        };
        console.log(emailVo);

        //如何发送
        SendEmailCode(emailVo).then((res) => {
          console.log(res.data);
          if (res.code == 200) {
            this.$message({
              message: res.data.msg,
              type: "success",
            });
            //保存验证码
            localStorage.setItem("code", res.data.code);
          }
        });
      } else {
        this.$message({
          message: "邮箱格式不正确",
          type: "warning",
        });
      }
    },
    /*  ----------------注册---------------------*/
    register() {
      //用户名长度
      if (this.username.length < 6) {
        this.$message({
          message: "请输入不少于6位的用户名",
          type: "warning",
        });
        return;
      }
      //密码为空
      if (this.password.length == 0) {
        this.$message({
          message: "请输入密码",
          type: "warning",
        });
        return;
      }
      //没勾选协议
      if (this.agree == false) {
        this.$message({
          message: "请勾选，我已同意“哇塞电影”用户注册协议",
          type: "warning",
        });
        return;
      }
      //两次密码不同
      if (this.password != this.checkpassword) {
        this.$message({
          message: "您两次输入的密码不同",
          type: "warning",
        });
        return;
      }
      const registerInfo = {
        username: this.username,
        password: this.password,
        email: this.email,
        token: localStorage.getItem("code"),
        emailCode: this.emailCode,
      };
      /* 2.1.2 客户端注册接口 */
      Register(registerInfo).then((res) => {
        if (res.code == 200) {
          this.$message({
            message: res.data.msg,
            type: "success",
          });
          this.$router.push("/login");
        } else {
          this.$message({
            message: "登陆失败",
            type: "warning",
          });
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.register {
  width: 100%;
  height: 100%;
  .register_info {
    width: 500px;
    margin: 50px auto;
    /* 注册上方标题(logo+哇塞电影-注册) */
    .register_title {
      height: 75px;
      /* logo图片 */
      img {
        width: 75px;
        height: 75px;
        float: left;
        margin-left: 30px;
      }
      /* logo右侧文字 哇塞电影-注册 */
      .register_text {
        float: left;
        line-height: 75px;
        font-size: 30px;
        font-weight: bold;
        margin-left: 50px;
      }
    }
    /* 注册界面表单 */
    .register_form {
      font-size: 16px;
      color: #91949c;
      font-weight: bold;
      /* username和password  输入框上的文字 */
      p {
        height: 60px;
        line-height: 60px;
      }
      /* 同意协议部分 */
      .agreement {
        .checkbox_text {
          font-size: 16px;
          color: #91949c;
          font-weight: bold;
          margin-top: 20px;
        }
      }
      /* 注册按钮部分 */
      .btn {
        .register-form-button {
          width: 100%;
          height: 50px;
          margin-top: 25px;
          font-size: 16px;
        }
      }
      /* 跳转到登录界面部分 */
      .register_footer {
        height: 50px;
        line-height: 50px;
        text-align: center;
        .rg {
          font-weight: bold;
          font-size: 16px;
          margin-top: 25px;
        }
      }
    }
  }
}
</style>