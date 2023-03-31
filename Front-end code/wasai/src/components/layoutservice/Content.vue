<template>
  <div>
    <div class="header">
      <i v-if="!isCollapse" class="el-icon-s-fold" @click="changemenuleft"></i>
      <i v-else class="el-icon-s-unfold" @click="changemenuleft"></i>
      <i class="el-icon-full-screen" @click="screen"></i>
      <i class="el-icon-refresh" @click="reload"></i>
      <div class="right">
        <el-link v-if="isLogin == false" href="/login3" :underline="false"
          >登录</el-link
        >
        <div>
          <el-dropdown v-if="isLogin == true">
            <el-button type="text"
              ><img alt="" :src="worker.avatar" />
              <div class="adminname">
                {{ this.worker.username }}
                <i class="el-icon-caret-bottom"></i>
              </div>
            </el-button>

            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-link :underline="false" href="/service/setting">
                  <i
                    style="font-size: 15px; padding-right: 3px"
                    class="el-icon-user-solid"
                  ></i
                  >个人设置</el-link
                >
              </el-dropdown-item>

              <el-dropdown-item divided>
                <el-button type="text" @click="handleLogout" :underline="false">
                  <i
                    style="font-size: 15px; padding-right: 3px"
                    class="el-icon-switch-button"
                  ></i
                  >退出登录
                </el-button>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { Workerinfo, LogoutWorker } from "@/api/worker";
export default {
  props: ["isCollapse"],
  data() {
    return {
      fullscreen: false,
      worker: {},
      isLogin: true,
    };
  },
  methods: {
    changemenuleft() {
      //点击切换按钮时，修改父组件的数据 isCollapse
      this.$emit("changeCollapse");
    },
    screen() {
      let element = document.documentElement;

      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      } else {
        if (element.requestFullscreen) {
          element.requestFullscreen();
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
          // IE11
          element.msRequestFullscreen();
        }
      }
      this.fullscreen = !this.fullscreen;
    },
    reload() {
      location.reload();
    },
    /*  ----------------退出登录---------------------*/
    handleLogout() {
      //进不来
      //提交到服务器中，返回一个生命周期很短的token
      //查询用户信息
      LogoutWorker(localStorage.getItem("id")).then((res) => {
        if (res.code == 200) {
          console.log(res.data.token);
          localStorage.setItem("token", res.data.token);
          localStorage.clear(); //清理本地存储
        }
        this.$router.push("/login3");
      });
      // localStorage.clear();
      // localStorage.removeItem("uid");
      // localStorage.removeItem("token");
      // this.$router.push("/login");
    },
    /*  ----------------获取用户信息---------------------*/
    queryUser() {
      // console.log(localStorage.getItem("id"));
      if (localStorage.getItem("id") != null) {
        /* 2.2.3.1 查询用户信息 */
        Workerinfo().then((res) => {
          // console.log(res);
          this.isLogin = true;
          this.worker = res.data;
        });
      }
    },
  },
  mounted() {
    /* 获取用户信息接口 */
    this.queryUser();
  },
};
</script>

<style lang="scss" scoped>
.header {
  width: 100%;
  height: 60px;
  float: 1;
  i {
    font-size: 24px;
    line-height: 60px;
    padding: 5px;
  }
  background-color: #e5e5e5;
  .right {
    width: 200px;
    float: right;
    line-height: 60px;
    margin-right: 50px;
    img {
      float: left;
      width: 25px;
      height: 25px;
      border-radius: 50%;
    }
    .adminname {
      line-height: 60px;
      padding-left: 10px;
      padding-right: 40px;
      margin-top: -20px;
    }
    .el-dropdown {
      .el-button {
        width: 200px;
        height: 60px;
        img {
          width: 35px;
          height: 35px;
          border-radius: 50%;
        }
        .header_name {
          float: right;
          line-height: 60px;
        }
      }
    }
  }
}
</style>