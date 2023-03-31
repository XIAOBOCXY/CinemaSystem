<template>
  <div class="login">
    <div class="login_info">
      <!-- 登录上方标题(logo+哇塞电影-登录) -->
      <div class="login_title">
        <img src="https://s2.loli.net/2022/04/03/Qu2ef8Gcq4ibnzX.png" alt="" />
        <div class="logo_text">哇塞电影-客服管理</div>
      </div>

      <!-- 登录界面表单 -->
      <div class="login_form">
        <form>
          <!-- 账号 -->
          <p>UserName</p>
          <el-input v-model="userName" placeholder="账号"></el-input>
        </form>
        <form>
          <!-- 密码 -->
          <p>PassWord</p>
          <el-input
            v-model="passWord"
            placeholder="密码"
            show-password
          ></el-input>
        </form>
        <!-- 忘记密码部分 -->
        <div class="forget">
          <el-tooltip content="请联系管理员" placement="bottom" effect="dark">
            <el-link class="login_link" :underline="false">忘记密码？</el-link>
          </el-tooltip>
        </div>

        <!-- 登录按钮部分 -->
        <div class="btn">
          <el-button
            class="login-form-button"
            type="primary"
            @click="getLogin()"
            >SIGN IN</el-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Login } from "../api/worker";
export default {
  data() {
    return {
      userName: "", //账号
      passWord: "", //密码
    };
  },
  methods: {
    /*  ----------------登录---------------------*/
    getLogin() {
      const LoginData = {
        username: this.userName,
        password: this.passWord,
      };
      console.log(LoginData);
      /* 2.1.1 客户端登录接口 */
      Login(LoginData).then((res) => {
        console.log(res);
        if (res.code == 200) {
          if (res.data.success === "success") {
            localStorage.setItem("token", res.data.token);
            localStorage.setItem("id", res.data.user.id);
            this.$message({
              message: res.data.msg,
              type: "success",
            });
            this.$router.push("/service");
          } else {
            this.$message({
              message: res.data.msg,
              type: "success",
            });
          }
        } else {
          this.$message({
            message: res.data.msg,
            type: "success",
          });
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.login {
  width: 100%;
  height: 100%;
  .login_info {
    width: 500px;
    margin: 50px auto;
    /* 登录上方标题(logo+哇塞电影-登录) */
    .login_title {
      height: 75px;
      /* logo图片 */
      img {
        width: 75px;
        height: 75px;
        float: left;
        margin-left: 30px;
      }
      /* logo右侧文字 哇塞电影-登录 */
      .logo_text {
        float: left;
        line-height: 75px;
        font-size: 30px;
        font-weight: bold;
        margin-left: 50px;
      }
    }
    /* 登录界面表单 */
    .login_form {
      font-size: 16px;
      color: #91949c;
      font-weight: bold;
      /* username和password  输入框上的文字 */
      p {
        height: 60px;
        line-height: 60px;
      }
      /* 忘记密码部分 */
      .forget {
        float: right;
        .login_link {
          font-size: 16px;
          color: #91949c;
          font-weight: bold;
          margin-top: 20px;
        }
      }
      /* 登录按钮部分 */
      .btn {
        .login-form-button {
          width: 100%;
          height: 50px;
          margin-top: 25px;
          font-size: 16px;
          /* background-color: #5a84fd; */
        }
      }
      /* 跳转到注册界面部分 */
      .login_footer {
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