<template>
  <div class="left">
    <el-col :span="12">
      <el-menu
        class="el-menu-vertical-demo"
        :collapse="isCollapse"
        background-color="#34495E"
        text-color="#fff"
        active-text-color="#ffd04b"
        :default-active="this.$router.path"
        router
      >
        <div class="houtainame" v-if="!isCollapse">
          <img width="25" src="../../assets/img/logo.png" alt="" />后台管理
        </div>
        <div v-else class="menuimg">
          <img width="25" src="../../assets/img/logo.png" alt="" />
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-document"></i>
            <span>影视管理</span>
          </template>
          <el-menu-item index="/back/moviemanage/movielist"
            >电影列表</el-menu-item
          >
          <el-menu-item index="/back/moviemanage/moviechedule"
            >院线排片</el-menu-item
          >
          <el-menu-item index="/back/moviemanage/addmovie"
            >新增电影</el-menu-item
          >
          <el-menu-item index="/back/moviemanage/postermanage"
            >轮播海报</el-menu-item
          >
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-user"></i>
            <span>用户管理</span>
          </template>
          <el-menu-item index="/back/usermanage/userlist"
            >用户列表</el-menu-item
          >
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-s-order"></i>
            <span>订单管理</span>
          </template>
          <el-menu-item index="/back/ordermanage/orderlist"
            >订单列表</el-menu-item
          >
          <el-menu-item index="/back/ordermanage/abnormalorder"
            >异常订单</el-menu-item
          >
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">
            <i class="el-icon-s-opportunity"></i>
            <span>员工管理</span>
          </template>
          <el-menu-item index="/back/workermanage/workerlist"
            >员工列表</el-menu-item
          >
          <el-menu-item index="/back/workermanage/addworker"
            >新增员工</el-menu-item
          >
        </el-submenu>
      </el-menu>
    </el-col>
  </div>
</template>

<script>
export default {
  props: ["isCollapse"],
  data() {
    return {
      //isCollapse: true,
    };
  },
  methods: {},
};
</script>

<style lang="scss" scoped>
.left {
  margin: 0;
  padding: 0;
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
    transition: 0s;
  }
  .houtainame {
    padding: 15px;
    color: #f5fffa;
    font-size: 20px;
    text-align: center;
    img {
      vertical-align: middle;
    }
  }
  .menuimg {
    line-height: 60px;
    vertical-align: middle;
    padding-left: 17.5px;
    padding-top: 8px;
  }
}
</style>