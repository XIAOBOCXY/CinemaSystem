<template>
  <div class="activity_main">
    <!-- 时间线循环活动列表 -->
    <el-timeline v-for="(item, index) in list" :key="index">
      <el-timeline-item :timestamp="item.startTime" placement="top">
        <el-card>
          <!-- 活动内容 -->
          <h4>{{ item.content }}</h4>
          <p>活动结束时间： {{ item.endTime }}</p>
          <el-button @click="handleRegister(item)" type="text"
            >报名参加</el-button
          >
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import { SearchActivities, AddRegistration } from "@/api/activity";
export default {
  data() {
    return {
      list: [], //活动
    };
  },
  methods: {
    /*  ----------------报名参加活动---------------------*/
    handleRegister(item) {
      /* 还没有登录，弹出消息 messagebox弹框 */
      if (!localStorage.getItem("uid")) {
        this.$confirm("系统还没有检测到您的登陆信息, 是否去登陆?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          /* 点击确定跳转到登录界面 */
          this.$router.push("/login");
        });
      } else {
        /* 登录的时候点击 显示message消息提示 */
        // this.$message({
        //   message: "活动报名成功",
        //   type: "success",
        // });
        //开始报名
        //console.log(item);
        let data = {
          uid: localStorage.getItem("uid"),
          aid: item.id,
        };
        AddRegistration(data).then((res) => {
          if (res.code == 200) {
            this.$message({
              message: "报名成功",
              type: "success",
            });
          }
        });
      }
    },
    formatDate(dateint) {
      //日期格式化，
      //console.log(dateint);
      var date = new Date(parseInt(dateint));
      var str = date.getFullYear() + "-";
      if (date.getMonth() < 10) {
        str += "0";
      }
      str += date.getMonth() + 1 + "-";
      if (date.getDate() < 10) {
        str += "0";
      }
      str += date.getDate() + " ";

      return str;
    },
  },
  mounted() {
    /* 4.3.1 查询活动列表 */
    SearchActivities().then((res) => {
      // if (res.code == 200) {
      //   this.list = res.data;
      // } else {
      // }
      if (res.code == 200) {
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].startTime = this.formatDate(res.data[i].startTime);
          res.data[i].endTime = this.formatDate(res.data[i].endTime);
        }
        this.list = res.data;
      }
    });
  },
};
</script>

<style lang="scss" scoped>
/* 活动界面 */
.activity_main {
  padding: 40px 0;
  width: 1200px;
  /* 活动内容 */
  h4 {
    padding: 20px 10px;
    font-size: 16px;
  }
  /* 结束时间 */
  p {
    margin: 10px 10px;
  }
  /*  活动参加按钮 */
  .el-button {
    margin: 0 10px;
    text-decoration: none;
    color: rgb(104, 156, 223);
  }
}
</style>