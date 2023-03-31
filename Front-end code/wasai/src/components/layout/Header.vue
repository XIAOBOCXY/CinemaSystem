<template>
  <div class="header">
    <el-card class="box-card">
      <div class="header_main">
        <!-- logo和文字哇塞影院 -->
        <div class="header_logo">
          <img width="50" src="../../assets/img/logo.png" alt="" />
          <div class="logo_text">哇塞影院</div>
        </div>

        <!-- 文字链接 -->
        <div class="header_links">
          <el-link href="/" class="header_link" :underline="false"
            >首页</el-link
          >
          <el-link href="/films" class="header_link" :underline="false"
            >电影</el-link
          >
          <el-link href="/activity" class="header_link" :underline="false"
            >活动</el-link
          >
          <el-link href="/message" class="header_link" :underline="false"
            >留言</el-link
          >
        </div>
        <div class="header-search">
          <el-autocomplete
            style="width: 250px"
            v-model="state"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入电影名"
            @select="handleSelect"
          />
        </div>

        <div class="top_login">
          <el-link
            v-if="isLogin == false"
            href="/login"
            class="header-link login_links"
            :underline="false"
            >登录</el-link
          >
          <div class="header_user_login">
            <el-dropdown v-if="isLogin == true">
              <el-button type="text"
                ><img alt="" :src="user.avatar" />
                <div class="header_name">
                  {{ this.user.nickname }}
                  <i class="el-icon-caret-bottom"></i>
                </div>
              </el-button>

              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-link
                    :underline="false"
                    href="/me/cart"
                    style="padding-right: 7px"
                  >
                    <i
                      style="font-size: 15px; padding-right: 3px"
                      class="el-icon-shopping-cart-2"
                    ></i
                    >购物车</el-link
                  >
                </el-dropdown-item>

                <el-dropdown-item>
                  <el-link :underline="false" href="/me/order">
                    <i
                      style="font-size: 15px; padding-right: 3px"
                      class="el-icon-s-order"
                    ></i
                    >我的订单</el-link
                  >
                </el-dropdown-item>

                <el-dropdown-item>
                  <el-link :underline="false" href="/me/setting">
                    <i
                      style="font-size: 15px; padding-right: 3px"
                      class="el-icon-user-solid"
                    ></i
                    >个人设置</el-link
                  >
                </el-dropdown-item>

                <el-dropdown-item divided>
                  <el-button
                    type="text"
                    @click="handleLogout"
                    :underline="false"
                  >
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
    </el-card>
  </div>
</template>
    
<script>
import { UserInfo, SearchFilm, LogoutUser } from "@/api/user";
import { globalBus } from "@/api/globalBus";
export default {
  data() {
    return {
      state: "", //头部模糊查询中的state
      searchList: [], //头部模糊查询列表
      timeout: null, //头部模糊查询时间
      isLogin: false, //是否登录
      user: {}, //登录的用户信息
    };
  },
  methods: {
    /*  ----------------模糊查询---------------------*/
    querySearchAsync(queryString, cb) {
      if (queryString) {
        /* 2.3.4 头部模糊查询 */
        SearchFilm(this.state).then((res) => {
          setTimeout(() => {
            this.searchList = res.data;
            cb(this.loadSearchList());
          }, 0);
        });
      }
    },
    /* 输入的内容，调一个方法 实际 */
    /* querySearchAsync(queryString, cb) {
      if (queryString) {
        SearchFilm(queryString).then((res) => {
          setTimeout(() => {
            this.searchList = res.data;
            cb(this.loadSearchList());
          }, 600);
        });
      }
    },*/
    /*  ----------------加载模糊查询列表---------------------*/
    loadSearchList() {
      let arr = new Array(0);
      for (let i = 0; i < this.searchList.length; i++) {
        arr.push({
          id: this.searchList[i].id,
          value: this.searchList[i].name,
        });
      }
      return arr;
    },
    /*  ----------------模糊搜索跳转---------------------*/
    handleSelect(item) {
      // //接受id和value 跳转
      // //console.log(item);
      // localStorage.setItem("fid", item.id);
      // this.$router.push("/film/detail?fid=" + item.id);
      // location.reload();

      //接受id和value 跳转
      let id = item.id;
      localStorage.setItem("fid", id);
      //跳转后没哟刷新
      //https://www.jianshu.com/p/771d1645377d
      var date = new Date();
      //console.log(date.getTime());
      //this.$router.push("/filmdetail/detail?fid=" + id+"&num="+date.getTime());
      //location.reload();
      globalBus.$emit("countNumber"); //使用全局事件，对事件进行引发。
    },
    /*  ----------------退出登录---------------------*/
    handleLogout() {
      //进不来
      //提交到服务器中，返回一个生命周期很短的token
      //console.log("asfdasdfasdfasdfasd");
      //查询用户信息
      LogoutUser(localStorage.getItem("uid")).then((res) => {
        if (res.code == 200) {
          console.log(res.data.token);
          localStorage.setItem("token", res.data.token);
          localStorage.clear(); //清理本地存储
        }
        this.$router.push("/login");
      });
      // localStorage.clear();
      // localStorage.removeItem("uid");
      // localStorage.removeItem("token");
      // this.$router.push("/login");
    },
    /*  ----------------获取用户信息---------------------*/
    queryUser() {
      if (localStorage.getItem("uid") != null) {
        /* 2.2.3.1 查询用户信息 */
        UserInfo().then((res) => {
          /* console.log(res); */
          this.isLogin = true;
          this.user = res.data;
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

<style lang='scss'>
.header {
  background-color: white;
  .header_main {
    width: 1300px;
    height: 70px;
    margin: 0 auto;
    .header_logo {
      float: left;
      width: 200px;
      img {
        float: left;
        margin-right: 20px;
        margin-top: 10px;
      }
      .logo_text {
        height: 70px;
        line-height: 70px;
        font-size: 26px;
        font-weight: bold;
      }
    }
    .header_links {
      float: left;
      padding-left: 20px;
      padding-top: 23px;
      letter-spacing: 2px;
      .header_link {
        padding-left: 25px;
        font-size: 20px;
      }
    }
    .header-search {
      float: left;
      margin: 15px 45px;
    }
    .top_login {
      .login_links {
        font-size: 16px;
        float: right;
        padding-top: 22px;
        padding-right: 50px;
      }
      .header_user_login {
        width: 120px;
        float: right;
        font-size: 16px;
        height: 50px;

        .el-dropdown {
          .el-button {
            width: 120px;
            height: 50px;
            img {
              width: 45px;
              height: 45px;
              border-radius: 50%;
            }
            .header_name {
              float: right;
              line-height: 50px;
            }
          }
        }
      }
    }
  }
}
</style>