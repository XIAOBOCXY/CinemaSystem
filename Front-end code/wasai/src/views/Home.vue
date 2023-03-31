<template>
  <div class="home">
    <!-- banner轮播图 -->
    <el-carousel height="500px" indicator-position="outside">
      <el-carousel-item v-for="(item, index) in posterList" :key="index">
        <img class="CarImg" :src="item.url" alt="" @click="handleclick(item)" />
      </el-carousel-item>
    </el-carousel>
    <!-- aside侧边栏 -->
    <div class="as">
      <div class="top">
        <span>热门榜单Top10</span>
        <!-- top1 -->
        <div class="top1" @click="handleclick(top1)">
          <!-- <router-link :to="'/film/detail?fid=' + top1.id"> -->
          <div class="ranking-top-icon">1</div>
          <img
            width="150px"
            height="200px"
            :src="top1.cover"
            :alt="top1.name"
          />
          <ul class="top1_text">
            <!-- top1电影名称 -->
            <li>{{ top1.name }}</li>
            <li>上映时间：{{ top1.releaseTime }}</li>
            <li class="hot_text">热度值：{{ top1.hot }}</li>
          </ul>
          <!-- </router-link> -->
        </div>
        <!-- top2 -->
        <div class="top2" @click="handleclick(top2)">
          <!-- <router-link :to="'/film/detail?fid=' + top2.id"> -->
          <div class="ranking-top-icon">2</div>
          <img width="160px" height="220px" :src="top2.cover" alt="" />
          <!--  top2电影名称 -->
          <p>{{ top2.name }}</p>
          <p class="top2_hot">热度值：{{ top2.hot }}</p>
          <!-- </router-link> -->
        </div>
        <!-- top3 -->
        <div class="top3" @click="handleclick(top3)">
          <!-- <router-link :to="'/film/detail?fid=' + top3.id"> -->
          <div class="ranking-top-icon">3</div>
          <img width="160px" height="220px" :src="top3.cover" alt="" />
          <!--  top3电影名称 -->
          <p>{{ top3.name }}</p>
          <p class="top3_hot">热度值：{{ top3.hot }}</p>
          <!-- </router-link> -->
        </div>
        <!-- top3之后 -->
        <div class="more_top">
          <ul>
            <li v-for="(item, index) in toplist" :key="index">
              <div @click="handleclick(item)">
                <!-- <router-link :to="'/film/detail?fid=' + item.id"> -->
                <!-- top名次 -->
                <span class="more_index">{{ index + 4 }}</span>
                <!-- top电影名称 -->
                <span class="more_name">{{ item.name }}</span>
                <!-- top电影热度值 -->
                <span class="more_hot">热度值:{{ item.hot }}</span>
                <!-- </router-link> -->
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 中间电影列表 -->
    <div class="main">
      <p class="main_top">热门电影</p>
      <ul v-for="(item, index) in movielist" :key="index">
        <!-- <router-link :to="'/film/detail?fid=' + item.id"> -->
        <li>
          <div @click="handleclick(item)">
            <div>
              <!-- 电影名称 -->
              <div class="main_movie_name">{{ item.name }}</div>
              <!-- 电影图片 -->
              <img class="main_movie_img" :src="item.cover" alt="" />
              <!-- 有几人想看 -->
              <div class="main_movie_wwatch">{{ item.hot }} 人想看</div>
            </div>
            <!-- 上映时间 -->
            <span class="main_movie_releaseTime">{{ item.releaseTime }}</span>
          </div>
        </li>
        <!-- </router-link> -->
      </ul>
    </div>
  </div>
</template>
<script>
import {
  ListAllPoster,
  NowPlayingFilm,
  ListHots,
  ListAllFilm,
} from "@/api/film";
export default {
  data() {
    return {
      status: 1, //参数1上架，参数0下架
      posterList: [], //轮播图
      movielist: [], //热门电影
      toplist: [], //热门榜单
      top1: {}, //top1
      top2: {}, //top2
      top3: {}, //top3
    };
  },
  methods: {
    /*  ----------------轮播图查询---------------------*/
    posterQuery() {
      /* 3.2.4.1 查询轮播图列表 */
      ListAllPoster().then((res) => {
        /* console.log(res); */
        if (res.code == 200) {
          this.posterList = res.data;
          /* for (var i = 0; i < this.posterList.length; i++) {
            this.posterList[i].url =
              config.API_URL + "/upload?id=" + this.posterList[i].url;
          } */
        }
      });
    },
    /*  ----------------热门电影查询---------------------*/
    nowplaying() {
      /* 3.2.1.1 正在热播电影查询 */
      ListAllFilm().then((res) => {
        if (res.code == 200) {
          console.log(res.data);
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].releaseTime = this.FormData(res.data[i].releaseTime);
          }
          this.movielist = res.data;
          console.log(this.movielist);
        }
      });
    },
    FormData(dateint) {
      //日期格式化
      //console.log(dateint)
      var date = new Date(parseInt(dateint));
      var str =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      return str;
    },
    /*  ----------------热门榜单查询---------------------*/
    toplistquery() {
      /* 2.3.3 热门榜单查询 */
      ListHots().then((res) => {
        if (res.code == 200) {
          for (var i = 0; i < res.data.length; i++) {
            //res.data[i].releaseTime=ConvertJSONDateToJSDate("/Date("+res.data[i].releaseTime+")/");
            res.data[i].releaseTime = this.FormData(res.data[i].releaseTime);
          }
          this.top1 = res.data[0];
          this.top2 = res.data[1];
          this.top3 = res.data[2];
          this.toplist.splice(0, 3);
        }
      });
    },
    handleclick(item) {
      localStorage.setItem("fid", item.id);
      this.$router.push("/film/detail?fid=" + item.id);
    },
  },
  mounted() {
    /* 轮播图查询接口 */
    this.posterQuery();
    /* 热门电影查询接口 */
    this.nowplaying();
    /* 热门榜单查询接口 */
    this.toplistquery();
  },
};
</script>

<style lang="scss" scoped>
.home {
  padding: 10px 0;
  .CarImg {
    width: 100%;
    height: 100%;
  }
  .as {
    width: 350px;
    float: right;
    padding-right: 80px;
    margin-top: 20px;
    .top {
      /* 热门榜单top10文字 */
      span {
        font-size: 25px;
        color: orange;
      }
      /* top1电影 */
      .top1 {
        margin: 20px 0;
        border: 1px solid #eee;
        height: 210px;
        .ranking-top-icon {
          position: absolute;
          z-index: 10;
          width: 25px;
          height: 25px;
          background-color: orange;
          text-align: center;
        }
        img {
          float: left;
        }
        .top1_text {
          margin-top: 20px;
          //float: left;
          height: 35px;
          line-height: 35px;
          font-size: 16px;
          color: #91949c;
          //margin-left: 10px;
          list-style: none;
          .li {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
          }
          .hot_text {
            font-size: 14px;
            color: orange;
          }
        }
      }
      /* top2电影 */
      .top2 {
        border: 1px solid #eee;
        width: 160px;
        color: #91949c;
        font-size: 16px;
        float: left;
        margin-right: 20px;
        .ranking-top-icon {
          position: absolute;
          z-index: 10;
          width: 25px;
          height: 25px;
          background-color: orange;
          text-align: center;
        }
        p {
          height: 15px;
          line-height: 15px;
          margin: 10px;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          color: #91949c;
        }
        .top2_hot {
          font-size: 12px;
          color: orange;
        }
      }
      /* top3电影 */
      .top3 {
        border: 1px solid #eee;
        width: 160px;
        color: #91949c;
        font-size: 16px;
        float: left;
        .ranking-top-icon {
          position: absolute;
          z-index: 10;
          width: 25px;
          height: 25px;
          background-color: orange;
          text-align: center;
        }
        p {
          height: 15px;
          line-height: 15px;
          margin: 10px;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          color: #91949c;
        }
        .top3_hot {
          font-size: 12px;
          color: orange;
        }
      }
      /* top3之后电影 */
      .more_top {
        clear: both;
        margin-top: 320px;
        ul {
          list-style: none;
          li {
            span {
              font-size: 16px;
              color: #91949c;
              height: 50px;
              line-height: 50px;
              display: inline-block;
            }
            .more_index {
              float: left;
            }
            .more_name {
              padding-left: 20px;
              width: 250px;
              white-space: nowrap;
              overflow: hidden;
              text-overflow: ellipsis;
            }
            .more_hot {
              float: right;
              font-size: 12px;
              color: orange;
            }
          }
        }
      }
    }
  }
  /* 中间电影列表 */
  .main {
    width: 60%;
    float: left;
    margin-left: 80px;
    margin-top: 20px;
    .main_top {
      font-size: 25px;
      color: orange;
      margin-left: 40px;
    }
    ul {
      list-style: none;
      margin-left: 25px;
      li {
        width: 170px;
        height: 310px;
        float: left;
        margin: 20px 20px 0 20px;
        position: relative;
        div {
          text-decoration: none;
          div {
            height: 265px;
            background-color: #eee;
            .main_movie_img {
              width: 100%;
              height: 230px;
              overflow: hidden;
            }

            .main_movie_wwatch {
              color: orange;
              height: 35px;
              line-height: 35px;
              font-size: 14px;
              padding-left: 10px;
              z-index: 3;
              position: relative;
              top: -5px;
              border: 1px solid lightgray;
            }
          }
          .main_movie_name {
            color: white;
            background-color: black;
            opacity: 0.6;
            position: absolute;
            top: 185px;
            left: 0;
            width: 170px;
            text-align: center;
            height: 45px;
            line-height: 45px;
            z-index: -1;
          }
          .main_movie_releaseTime {
            color: #91949c;
            font-size: 14px;
            margin-top: 10px;
            width: 170px;
            text-align: center;
            display: inline-block;
          }
        }
      }
      /* 鼠标划过显示电影名称 */
      li:hover .main_movie_name {
        z-index: 1;
      }
    }
  }
}
</style>