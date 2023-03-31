<template>
  <el-container>
    <el-header><Header></Header></el-header>
    <el-main><router-view></router-view></el-main>
    <el-footer><Footer></Footer></el-footer>
  </el-container>
</template>
<script>
import Header from "@/components/layout/Header.vue";
import Footer from "@/components/layout/Footer.vue";
export default {
  name: "Layout",
  components: {
    Header,
    Footer,
  },
};
</script>

<style scoped>
* {
  width: 1519px;
  margin: 0 auto;
}
.el-header {
  padding: 0;
  height: 110px !important;
}
.el-main {
  overflow: auto;
  padding: 0;
  /* padding: 80px 0; */
}

.el-footer {
  padding: 0;
}
</style>