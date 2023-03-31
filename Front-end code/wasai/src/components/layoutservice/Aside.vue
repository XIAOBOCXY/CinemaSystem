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
          <img width="25" src="../../assets/img/logo.png" alt="" />哇塞影院-客服
        </div>
        <div v-else class="menuimg">
          <img width="25" src="../../assets/img/logo.png" alt="" />
        </div>
        <el-menu-item index="/service/messarrangement">
          <template slot="title">
            <i class="el-icon-s-promotion"></i>
            <span>影院留言</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/service/activityarrangement">
          <template slot="title">
            <i class="el-icon-s-flag"></i>
            <span>活动安排</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/service/infoarrangement">
          <template slot="title">
            <i class="el-icon-s-data"></i>
            <span>信息统计</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/service/mycomment">
          <template slot="title">
            <i class="el-icon-edit"></i>
            <span>我的评价</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/service/setting">
          <template slot="title">
            <i class="el-icon-s-tools"></i>
            <span>个人设置</span>
          </template>
        </el-menu-item>
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