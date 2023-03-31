<template>
  <!-- 电影界面 -->
  <div class="films">
    <!-- 上方tag框 -->
    <div class="tags_panel">
      <!-- 每行为一个li -->
      <ul class="tags_lines">
        <li class="tags_line">
          <div class="tags_title">类型:</div>
          <!-- 每个选项为li -->
          <ul class="tags">
            <!-- 当前选择的类型改为active 点击返回他的selectType -->
            <li
              v-for="(item, index) in typeList"
              :key="index"
              :class="selectType === item ? 'active' : ''"
              @click="handleTypeSelect(item)"
            >
              <router-link
                :to="'/films?region=' + selectRegion + '&type=' + item"
                >{{ item }}</router-link
              >
            </li>
          </ul>
        </li>
        <li class="tags_line">
          <div class="tags_title">地区:</div>
          <ul class="tags">
            <li
              v-for="(item, index) in regionList"
              :key="index"
              :class="selectRegion === item ? 'active' : ''"
              @click="handleRegionSelect(item)"
            >
              <router-link
                :to="'/films?region' + selectRegion + '&type=' + item"
                >{{ item }}</router-link
              >
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <!-- 下方的电影列表 -->
    <el-row :gutter="20" class="films_el-row">
      <el-col
        class="films_el_col"
        v-for="(item, index) in filmList"
        :key="index"
        :span="4"
      >
        <div @click="handleclick(item)">
          <!-- <router-link :to="'/films?info?fid=' + item.id"> -->
          <el-card shadow="hover" style="padding: 0">
            <!-- 电影图片 -->
            <img class="films_img" :src="item.cover" alt="" />
            <!-- 电影名字 -->
            <span class="films_span">{{ item.name }}</span>
          </el-card>
          <!-- </router-link> -->
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ListAllFilm, FindFilmByRegionAndType } from "@/api/film";
/* import { config } from "vue/types/umd"; */
export default {
  data() {
    return {
      selectType: "全部", //默认选择的类型是全部
      selectRegion: "全部", //默认选择的地区是全部
      typeList: [
        "全部",
        "家庭",
        "惊悚",
        "科幻",
        "爱情",
        "动作",
        "喜剧",
        "恐怖",
        "悬疑",
        "犯罪",
        "冒险",
        "战争",
        "历史",
        "武侠",
        "传记",
        "古装",
        "其他",
      ], //类型
      regionList: [
        "全部",
        "内地",
        "香港",
        "台湾",
        "美国",
        "韩国",
        "日本",
        "泰国",
        "印度",
        "法国",
        "英国",
        "德国",
        "其他",
      ], //地区
      filmList: [], //查询到的电影列表
    };
  },
  methods: {
    /*  ----------------选择类型---------------------*/
    handleTypeSelect(item) {
      this.selectType = item;
      //console.log(this.selectType);
      this.reloadFilmList();
    },
    /*  ----------------选择地区---------------------*/
    handleRegionSelect(item) {
      this.selectRegion = item;
      //console.log(this.selectRegion);
      this.reloadFilmList();
    },
    /*  ----------------查询电影列表，传入地区和类型---------------------*/
    reloadFilmList() {
      /* FindFilmByRegionAndType(this.selectRegion, this.selectType).then(
        (res) => {
          this.filmList = res.data;
          for (var i = 0; i < this.filmList.length; i++) {
            this.filmList[i].cover_show =
              config.API_URL + "/upload?id=" + this.filmList[i].cover;
          }
        }
      ); */
      FindFilmByRegionAndType(this.selectRegion, this.selectType).then(
        (res) => {
          if (res.code == 200) {
            this.filmList = res.data;
            // for(var i=0;i<this.filmList.length;i++){
            //   this.filmList[i].cover=
            //   config.API_URL+"/upload?id="+this.filmList[i].cover
            // }
          }
        }
      );
    },
    handleclick(item) {
      localStorage.setItem("fid", item.id);
      this.$router.push("/film/detail?fid=" + item.id);
    },
  },
  mounted() {
    /* 3.2.1.1 查询电影列表 */
    ListAllFilm().then((res) => {
      if (res.code == 200) {
        this.filmList = res.data;
      } else {
      }
    });
  },
};
</script>

<style lang="scss" scoped>
/* 电影界面 */
.films {
  width: 1300px;
  padding: 20px 100px;
  /* margin: 80px 0; */
  /* 上方tag框 */
  .tags_panel {
    border: 1px solid #e5e5e5;
    padding: 10px 20px;
    margin-top: 40px;
    /* ul 每行为一个li */
    .tags_lines {
      list-style: none;
      /* li 每行为一个li */
      .tags_line {
        padding: 10px 0;
        /* 类型 */
        .tags_title {
          height: 24px;
          line-height: 24px;
          float: left;
          color: #999;
          font-size: 14px;
        }
        /* ul 每个选项为li */
        .tags {
          list-style: none;
          /* li 每个选项为li */
          li {
            border-radius: 14px;
            padding: 2px 9px;
            display: inline-block;
            margin-left: 12px;
            a {
              color: black;
              text-decoration: none;
            }
          }
          /* 点击后改变的样式 */
          li.active {
            background-color: #f34d41;
            a {
              color: #fff;
            }
          }
        }
      }
    }
  }
  /* 下方的电影列表 */
  .films_el-row {
    padding-top: 40px;
    .films_el_col {
      padding-bottom: 40px;
      text-align: center;
    }
    div {
      text-decoration: none;
      .el-card__body {
        padding: 0 0 10px;
        /* 电影图片 */
        .films_img {
          width: 100%;
          height: 200px;
          padding-bottom: 10px;
        }
        /* 电影名字 */
        .films_span {
          display: block; /* 转换成块，否则不显示省略号 */
          margin-bottom: 10px;
          padding: 0 8px;
          letter-spacing: 1px;
          color: orange;
          text-align: center;
          overflow: hidden; /* 超出部分不显示 */
          white-space: nowrap; /* 名字长不换行 */
          text-overflow: ellipsis; /* 名字长显示省略号 */
        }
      }
    }
  }
}
</style>