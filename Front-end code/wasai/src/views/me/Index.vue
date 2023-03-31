<template>
  <div class="all">
    <div class="left">
      <p>个人中心</p>
      <el-col :span="12">
        <el-menu
          router
          :default-active="$route.path"
          class="el-menu-vertical-demo"
        >
          <el-menu-item index="/me/cart">
            <i class="el-icon-shopping-cart-1"></i>
            <span slot="title">购物车</span>
          </el-menu-item>
          <el-menu-item index="/me/order">
            <i class="el-icon-tickets"></i>
            <span slot="title">我的订单</span>
          </el-menu-item>
          <el-menu-item index="/me/setting">
            <i class="el-icon-setting"></i>
            <span slot="title">基本设置</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </div>
    <div class="right">
      <div class="content">
        <router-view />
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
.all {
  width: 1143px;
  margin-top: 40px;
  height: 800px;
  .left {
    width: 260px;
    height: 760px;
    float: left;
    margin: 0 auto;
    border: 1px solid rgb(218, 210, 210);
    box-shadow: -2px 4px 6px rgb(218, 210, 210);
    p {
      font-size: 25px;
      color: #333333;
      padding: 40px;
      text-align: center;
    }
    .el-col {
      width: 261px;
    }
    .el-menu-item,
    .el-submenu__title {
      height: 60px;
    }
  }
  .right {
    width: 880px;
    height: 760px;
    float: left;
    border-right: 1px solid rgb(218, 210, 210);
    border-top: 1px solid rgb(218, 210, 210);
    border-bottom: 1px solid rgb(218, 210, 210);
    box-shadow: 2px 4px 6px rgb(218, 210, 210);
    .content {
      padding: 20px;
    }
  }
}
</style>