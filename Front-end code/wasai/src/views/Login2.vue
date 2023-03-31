<template>
  <div class="login">
    <div class="login_info">
      <!-- 登录上方标题(logo+哇塞电影-登录) -->
      <div class="login_title">
        <img src="https://s2.loli.net/2022/04/03/Qu2ef8Gcq4ibnzX.png" alt="" />
        <div class="logo_text">哇塞电影-后台登录</div>
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
        <form>
          <!-- 身份 -->
          <p>Role</p>
          <el-select v-model="value" placeholder="请选择你的身份">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </form>
        <el-checkbox v-model="checked" class="jizhu">记住密码</el-checkbox>
        <el-link target="_blank" :underline="false" class="chongzhi"
          >重置密码</el-link
        >
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
import { Login } from "../api/admin";
export default {
  data() {
    return {
      userName: "", //账号
      passWord: "", //密码
      options: [
        {
          value: "系统管理员",
          label: "系统管理员",
        },
      ],
      value: "",
      checked: false,
    };
  },
  methods: {
    /*  ----------------登录---------------------*/
    getLogin() {
      const LoginData = {
        username: this.userName,
        password: this.passWord,
      };
      /* 2.1.1 客户端登录接口 */
      Login(LoginData).then((res) => {
        console.log(res);
        if (res.code == 200) {
          if (res.data.success === "success") {
            if (this.checked == true) {
              // localStorage.setPassword("password", LoginData.password);
            }
            localStorage.setItem("token", res.data.token);
            localStorage.setItem("id", res.data.user.id);
            this.$message({
              message: res.data.msg,
              type: "success",
            });
            this.$router.push("/back");
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
      .jizhu {
        margin-top: 20px;
      }
      .chongzhi {
        float: right;
        margin-top: 20px;
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
    }
  }
}
</style>