<template>
  <div class="ticket">
    <el-page-header @back="goBack" content="电影购票"></el-page-header>
    <div class="ticket_box" v-if="list.length === 0">影院还没有添加排片</div>
    <div class="item" v-for="(item, index) in list" :key="index">
      <div class="d1">{{ item.name }}</div>
      <div class="d2">{{ item.data }} {{ item.startTime }}</div>
      <el-tag effect="plain">{{ item.type }}</el-tag>
      <div class="item_btn">
        <el-button type="danger" @click="handleSelectSeat(item.id)" round
          >选座购票</el-button
        >
      </div>
      <div class="item_price">¥{{ item.price }}</div>
    </div>
  </div>
</template>
<script>
import { FindArrangementFilmById } from "@/api/film";
export default {
  data() {
    return {
      list: [], //电影排片
    };
  },
  methods: {
    formatDate(dateint) {
      //日期格式化，
      //console.log(dateint);
      var date = new Date(parseInt(dateint));
      var str =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      return str;
    },
    /*  ----------------返回上一层---------------------*/
    goBack() {
      this.$router.go(-1);
    },
    /*  ----------------选择购票---------------------*/
    handleSelectSeat(id) {
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
        console.log(id);
        localStorage.setItem("arrangementId", id);
        this.$router.push("/seat");
      }
    },
    /*  ----------------查询电影排片---------------------*/
    queryarrangementfilm() {
      /* 2.4.5 查询电影排片 */
      FindArrangementFilmById(localStorage.getItem("fid")).then((res) => {
        if (res.code == 200) {
          //this.list = res.data.arrangements;
          //console.log(res.data)
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].startTime = this.formatDate(res.data[i].startTime);
          }
          this.list = res.data;
        }
      });
    },
  },
  mounted() {
    /* 查询电影排片接口 */
    this.queryarrangementfilm();
  },
};
</script>
<style  lang="scss" scoped>
.ticket {
  width: 1100px;
  margin: 0 auto;
  .ticket_box {
    font-weight: bold;
    letter-spacing: 2px;
    font-size: 25px;
    color: red;
    padding-top: 10px;
  }
  .item {
    margin: 10px 0;
    height: 100px;
    .d1 {
      line-height: 30px;
      font-size: 16px;
    }
    .d2 {
      font-size: 14px;
      color: rgb(175, 166, 166);
      padding: 5px 0;
    }
    .item_btn {
      float: right;
      position: relative;
      top: -30px;
    }
    .item_price {
      float: right;
      color: rgb(211, 84, 84);
      position: relative;
      top: -20px;
      right: 30px;
      font-size: 18px;
    }
  }
}
</style>