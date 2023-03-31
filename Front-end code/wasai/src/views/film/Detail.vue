<template>
  <div class="info">
    <el-page-header @back="goBack" content="详情页面"></el-page-header>
    <div class="introduct_title">剧情简介</div>
    <div class="introduct_con">{{ text }}</div>
    <div class="comment_on">电影评论</div>
    <div class="comment_on_box">
      <div
        class="comment_item"
        v-for="(item, index) in evaluateList"
        :key="index"
        v-show="evaluateList.length != 0"
      >
        <img v-if="item.user.avatar != ''" :src="item.user.avatar" alt="" />
        <img
          v-if="item.user.avatar == ''"
          src="../../assets/img/avatar.png"
          alt=""
        />
        <div class="header-name">
          <div class="anonymous_user" v-if="item.user.nickname === ''">
            匿名用户
          </div>
          <div class="anonymous_user" v-if="item.user.nickname != ''">
            {{ item.user.nickname }}
          </div>
        </div>
        <el-rate
          v-model="item.filmEvaluate.star"
          disabled
          show-score
          text-color="#ff9900"
          score-template="{value}"
        >
        </el-rate>
        <div class="comment_info">{{ item.filmEvaluate.comment }}</div>
        <i> Time: {{ item.filmEvaluate.createAt }}</i>
      </div>
    </div>
    <div v-show="evaluateList == null || evaluateList.length == 0">
      暂无评论
    </div>
  </div>
</template>
<script>
import { FindFilmEvaluateByFid, FindFilmById } from "@/api/film";
import { Col } from "element-ui";
import { globalBus } from "@/api/globalBus.js";
import config from "@/config.js";
/* import { config } from "vue/types/umd"; */
export default {
  data() {
    return {
      text: "暂无简介",
      film: {}, //电影详情
      filmId: this.$route.query.fid, //电影id
      evaluateList: [], //电影评价
    };
  },
  methods: {
    /*  ----------------返回上一层---------------------*/
    goBack() {
      this.$router.go(-1); //返回上一层
    },
    /*  ----------------查询电影评价---------------------*/
    commentQuery() {
      /* 2.4.3 查询电影评论 */
      FindFilmEvaluateByFid(localStorage.getItem("fid")).then((res) => {
        //新加入的评论，如何刷新
        if (res.code == 200) {
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].user.avatar = config.API_URL + res.data[i].user.avatar;
            console.log(res.data[i]);
            res.data[i].filmEvaluate.createAt = this.formatDate(
              res.data[i].filmEvaluate.createAt
            );
            this.evaluateList = res.data;
          }

          // if (this.evaluateList.length != 0) {
          //    if (this.evaluateList[0].user) {
          //     this.evaluateList[0].user.avatar_show =
          //       config.API_URL +
          //       "/upload?id=" +
          //       this.evaluateList[0].user.avatar;
          //   }
          // }
          // /* 查询电影评论 实际 */
          // FindFilmEvaluateByFid(this.filmId).then((res) => {
          //   if (res.code == 200) {
          //     this.evaluateList = res.data;
          //   }
          // });
        }
      });
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
      if (date.getHours() < 10) {
        str += "0";
      }
      str += date.getHours() + ":";

      if (date.getMinutes() < 10) {
        str += "0";
      }
      str += date.getMinutes() + ":";
      if (date.getSeconds() < 10) {
        str += "0";
      }
      str += date.getSeconds();
      //console.log(str);
      return str;
    },

    /*  ----------------查询电影详情---------------------*/
    queryDetail() {
      /* 2.4.2 查询电影详情 */
      FindFilmById(localStorage.getItem("fid")).then((res) => {
        if (res.code == 200) {
          this.text = res.data.introduction;
        }
      });
    },
  },
  mounted() {
    /* 查询电影详情接口 */
    this.queryDetail();
    /* 查询电影评论接口 */
    this.commentQuery();
    //监听事件
    globalBus.$on("countNumber", () => {
      this.commentQuery(); //调用函数。如何监听一次？？？
    });
  },
};
</script>
<style  lang="scss" scoped>
.info {
  .introduct_title {
    font-weight: bolder;
    font-size: 25px;
    padding-top: 40px;
    padding-bottom: 40px;
  }
  .introduct_con {
    line-height: 30px;
    text-indent: 2.5em;
    padding-top: 20px;
    padding-bottom: 20px;
  }
  .comment_on {
    font-weight: bolder;
    font-size: 25px;
    padding-top: 40px;
    padding-bottom: 40px;
  }
  .comment_on_box {
    .comment_item {
      padding-bottom: 40px;
      img {
        float: left;
        width: 55px;
        height: 55px;
        border-radius: 50%;
      }
      .header-name {
        color: #000000;
        padding-top: 5px;
        float: left;
        padding-left: 20px;
        font-weight: bolder;
        font-size: 14px;
        letter-spacing: 2px;
        .anonymous_user {
          font-weight: 400;
          color: darkgray;
          padding-left: 5px;
        }
      }
      .el-rate {
        padding-top: 30px;
        padding-left: 80px;
      }
      .comment_info {
        text-indent: 2.5em;
        line-height: 28px;
        padding-left: 40px;
        padding-top: 10px;
        padding-bottom: 20px;
        color: #91949c;
      }
      i {
        float: right;
        padding-right: 18px;
        color: #91949c;
      }
    }
  }
}
</style>