<template>
  <div class="film">
    <div class="film-header"></div>
    <!-- 详细图片 -->
    <div class="film-card">
      <img :src="film.cover" alt="" />
    </div>

    <div class="film-des">
      <!-- 评分 -->
      <div class="p1">
        {{ film.name }}
        <el-rate
          v-model="film.star"
          disabled
          show-score
          text-color="#ff9900"
          score-template="{value}"
        >
        </el-rate>
      </div>
      <div class="p2">电影类型:{{ film.type }}</div>
      <div class="p2">{{ film.region }} / {{ film.duration }}分钟</div>
      <div class="p2">上映日期:{{ film.releaseTime }} 上映</div>
      <div class="top_btn">
        <router-link :to="'/film/ticket?fid=' + filmId">
          <!-- <router-link to="/film/ticket"> -->
          <el-button type="danger" class="btn1">
            <i class="el-icon-s-finance">特惠购票</i>
          </el-button>
        </router-link>
        <!-- </router-link> -->
        <el-button type="danger" class="btn2" @click="openComment">
          <i class="el-icon-star-on">评分</i>
        </el-button>
      </div>
    </div>
    <div class="film-content">
      <router-view />
    </div>

    <el-dialog
      :show-colose="false"
      title="电影评分"
      :visible.sync="dialogTableVisible1"
      width="30%"
      ><el-rate v-model="dialogvalue" :colors="colors"></el-rate>
      <br />
      <el-input
        type="textarea"
        :rows="8"
        placeholder="请输入内容"
        v-model="inputContent"
      >
      </el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="submitComment">确认提交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { FindFilmById, AddFilmEvaluate } from "@/api/film";
import { globalBus } from "@/api/globalBus";
/* import config from "@/config"; */
export default {
  data() {
    return {
      film: {}, //电影详情
      form: {
        fid: "",
        uid: "",
        star: 5,
        comment: "",
      }, //评分表单
      dialogvalue: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"], //评分颜色
      dialogTableVisible1: false, //评分提示框
      filmId: this.$route.query.fid, //电影id
      textarea: "", //评分输入的内容
      /* value1: null, */
      /* list: [{}, {}, {}], */
      /* evaluateList: {}, */
      inputContent: "",
      timer: "",
    };
  },
  methods: {
    /*  ----------------评分---------------------*/
    openComment() {
      if (!localStorage.getItem("uid")) {
        this.$confirm("系统还没有检测到您的登陆信息, 是否去登陆?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            /* 点击确定跳转到登录界面 */
            this.$router.push("/login");
          })
          .catch(() => {
            this.$message({
              message: "已取消为电影评分",
              type: "indo",
            });
          });
      } else {
        this.dialogTableVisible1 = true;
      }
    },
    /*  ----------------确认提交电影评分---------------------*/
    submitComment() {
      this.form.fid = this.film.id;
      this.form.uid = localStorage.getItem("uid");
      this.form.star = this.dialogvalue;
      this.form.comment = this.inputContent;
      const form = this.form;
      /* 2.4.4 评分*/
      AddFilmEvaluate(form).then((res) => {
        if (res.code == 200) {
          this.$message({
            message: "评价成功",
            type: "indo",
          });
          //alert(res);
          //console.log(res.data);
          this.dialogTableVisible1 = false;
          //this.timer = setTimeout("this.myFlush", 10000);//延迟函数如何调用
          this.timer = setTimeout(() => {
            //console.log('countNumber');
            globalBus.$emit("countNumber");
          }, 10000);
          //重新跳转，不起作用
          if (res.data.success == "ok") {
            //setTimeout("","2000");//延迟函数如何调用
          }
          //this.$router.push("/filmdetail/detail?fid=" + item.id);
        }
      });
    },
    formatDate(dateint) {
      //日期格式化，
      //console.log(dateint);
      var date = new Date(parseInt(dateint));
      var str =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      return str;
    },
    /*  ----------------查询电影详情---------------------*/
    queryFilmDetail() {
      /* 2.4.2 查询电影详情 */
      let fid = localStorage.getItem("fid");
      FindFilmById(fid).then((res) => {
        if (res.code == 200) {
          res.data.releaseTime = this.FormData(res.data.releaseTime);
          this.film = res.data;
          /* this.film.cover_show =
            config.API_URL + "/upload?id=" + this.film.cover; */
        }
      });
      /* 2.4.2 查询电影详情 实际 */
      /*  FindFilmById(this.filmId).then((res) => {
        if (res.code == 200) {
          this.film = res.data;
          this.film.cover_show =
            config.API_URL + "/upload?id=" + this.film.cover;
        }
      }); */
    },
    FormData(dateint) {
      //日期格式化
      //console.log(dateint)
      var date = new Date(parseInt(dateint));
      var str =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      return str;
    },
  },
  mounted() {
    /* 查询电影详情接口 */
    this.queryFilmDetail();
  },
};
</script>
<style lang="scss" scoped>
.film {
  width: 100%;
  .film-header {
    width: 100%;
    background-color: #5e84fd;
    height: 380px;
    position: relative;
    /* margin-top: 12px; */
  }
  .film-card {
    position: absolute;
    top: 200px;
    width: 240px;
    height: 330px;
    margin-right: 100px;
    margin-left: 200px;
    overflow: hidden;
    img {
      width: 100%;
      height: 100%;
    }
  }
  .film-des {
    color: white;
    width: 500px;
    height: 270px;
    position: absolute;
    left: 530px;
    top: 200px;
    overflow: hidden;
    .p1 {
      font-size: 30px;
      float: left;
      padding: 10px 0;
      .el-rate {
        float: right;
        padding-left: 50px;
        width: 200px;
        height: 40px;
      }
    }
    .p2 {
      padding: 10px 0;
      clear: both;
    }
    .top_btn {
      margin-right: 20px;
      margin-top: 15px;
      .router_link {
        margin-left: 20px;
        .btn1 {
          width: 130px;
          letter-spacing: 2px;
          i {
            padding-right: 5px;
            font-size: 15px;
          }
        }
      }
    }
    .btn2 {
      width: 130px;
      letter-spacing: 2px;
      margin-top: 15px;
      margin-left: 10px;
      i {
        padding-right: 5px;
        font-size: 15px;
      }
    }
  }
  .film-content {
    padding: 80px 200px;
    letter-spacing: 2px;
  }
}
</style>