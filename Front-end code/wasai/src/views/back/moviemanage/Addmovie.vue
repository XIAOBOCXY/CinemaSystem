<template>
  <div class="main">
    <p class="title">新增电影</p>
    <div class="filmcover">
      <p>电影封面</p>
      <el-upload
        class="avatar-uploader"
        action="http://localhost:8888/api/user/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </div>
    <div class="filmname">
      <p>电影名称</p>
      <el-input v-model="film.name" placeholder="请输入内容"></el-input>
    </div>
    <div class="filmregion">
      <p>上映地区</p>
      <el-select v-model="film.region" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select>
    </div>
    <div class="filmtime">
      <p>全片时长</p>
      <el-input-number
        v-model="film.duration"
        @change="handleChange"
        :min="60"
        :max="500"
        label="描述文字"
      ></el-input-number>
    </div>
    <div class="filmstart">
      <p>上映时间</p>
      <el-date-picker
        v-model="film.releaseTime"
        align="right"
        type="date"
        placeholder="选择日期"
      >
      </el-date-picker>
    </div>
    <div class="filmon">
      <p>即时上架</p>
      <el-switch
        v-model="shangjia"
        active-color="#13ce66"
        inactive-color="#ff4949"
      >
      </el-switch>
    </div>
    <div class="filmselect">
      <p>电影类型</p>
      <el-radio-group v-model="radio">
        <el-radio :label="1">爱情</el-radio>
        <el-radio :label="2">喜剧</el-radio>
        <el-radio :label="3">科幻</el-radio>
        <el-radio :label="4">动画</el-radio>
        <el-radio :label="5">恐怖</el-radio>
        <el-radio :label="6">悬疑</el-radio>
        <el-radio :label="7">冒险</el-radio>
        <el-radio :label="8">动作</el-radio>
        <el-radio :label="9">纪录</el-radio>
        <el-radio :label="10">家庭</el-radio>
        <el-radio :label="11">传记</el-radio>
        <el-radio :label="12">武侠</el-radio>
        <el-radio :label="13">犯罪</el-radio>
        <el-radio :label="14">历史</el-radio>
        <el-radio :label="15">古装</el-radio>
        <el-radio :label="16">战争</el-radio>
        <el-radio :label="17">儿童</el-radio>
        <el-radio :label="18">短片</el-radio>
        <el-radio :label="19">其他</el-radio>
      </el-radio-group>
    </div>
    <div class="filminfo">
      <p>内容介绍</p>

      <div>
        <el-input
          type="textarea"
          :rows="5"
          placeholder="请输入内容"
          v-model="film.introduction"
        >
        </el-input>
      </div>
    </div>
    <div class="button">
      <el-button type="primary" @click="submitaddfilm(film, fileName)"
        >确认提交</el-button
      ><el-button>取消</el-button>
    </div>
  </div>
</template>

<script>
import { AddFilm } from "@/api/film.js";
import { upload } from "@/api/user.js";
export default {
  data() {
    return {
      input: "",
      options: [
        {
          value: "中国大陆",
          label: "中国大陆",
        },
        {
          value: "内地",
          label: "内地",
        },
        {
          value: "美国",
          label: "美国",
        },
        {
          value: "韩国",
          label: "韩国",
        },
      ],
      film: {
        cover: "",
        name: "",
        region: "",
        duration: "",
        releaseTime: "",
        status: "",
        type: "",
        introduction: "",
      },
      shangjia: "",
      radio: "",
      imageUrl: "",
      fileName: "",
    };
  },
  methods: {
    formatDate(dateint) {
      //日期格式化
      //console.log(dateint)
      var date = new Date(parseInt(dateint));
      var str =
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1) +
        "-" +
        date.getDate() +
        "  " +
        date.getHours() +
        ":" +
        date.getMinutes() +
        ":" +
        date.getSeconds();
      return str;
    },
    handleChange(value) {
      // console.log(value);
    },
    submitaddfilm(item, fileName) {
      console.log(item);
      if (this.shangjia === 0) {
        item.status = 0;
      } else {
        item.status = 1;
      }
      if (this.radio === 1) {
        item.type = "爱情";
      } else if (this.radio === 2) {
        item.type = "喜剧";
      } else if (this.radio === 3) {
        item.type = "科幻";
      } else if (this.radio === 4) {
        item.type = "动画";
      } else if (this.radio === 5) {
        item.type = "恐怖";
      } else if (this.radio === 6) {
        item.type = "悬疑";
      } else if (this.radio === 7) {
        item.type = "冒险";
      } else if (this.radio === 8) {
        item.type = "动作";
      } else if (this.radio === 9) {
        item.type = "纪录";
      } else if (this.radio === 10) {
        item.type = "家庭";
      } else if (this.radio === 11) {
        item.type = "传记";
      } else if (this.radio === 12) {
        item.type = "武侠";
      } else if (this.radio === 13) {
        item.type = "犯罪";
      } else if (this.radio === 14) {
        item.type = "历史";
      } else if (this.radio === 15) {
        item.type = "古装";
      } else if (this.radio === 16) {
        item.type = "战争";
      } else if (this.radio === 17) {
        item.type = "儿童";
      } else if (this.radio === 18) {
        item.type = "短片";
      } else if (this.radio === 19) {
        item.type = "其他";
      }
      // item.releaseTime = this.formatDate(this.releaseTime);
      console.log(item);
      console.log("fileName");
      console.log(fileName);
      let data = {
        cover: this.fileName,
        name: this.film.name,
        duration: this.film.duration,
        releaseTime: this.film.releaseTime,
        introduction: this.film.introduction,
        status: 1,
        type: this.film.type,
        region: this.film.region,
        hot: 0,
        createTime: new Date(),
        updateTime: new Date(),
        isDelete: 0,
      };
      console.log(data);
      AddFilm(data).then((res) => {
        if (res.code == 200) {
          console.log(res.data);
          this.$message({
            type: "success",
            message: "新增电影成功!",
          });
        }
      });
    },
    handleAvatarSuccess(res, file) {
      this.fileName = res.data.fileName;
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    submiteditfilm() {},
  },
};
</script>

<style lang="scss" scoped>
.main {
  height: 820px;
  background-color: #f4f4f4;
  padding: 20px;
  .title {
    font-size: 18px;
    color: gray;
  }
  .filmcover {
    margin-top: 20px;
    p {
      float: left;
      padding-right: 20px;
    }
    .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #409eff;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      line-height: 178px;
      text-align: center;
    }
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }
  }
  .filmname {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
    .el-input {
      width: 360px;
    }
  }
  .filmregion {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
    .el-select {
      width: 360px;
    }
  }
  .filmtime {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
  }
  .filmstart {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
  }
  .filmon {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
  }
  .filmselect {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
    .el-radio-group {
      width: 1150px;
      .el-radio {
        padding: 5px;
      }
    }
  }
  .filminfo {
    margin-top: 20px;
    line-height: 40px;
    p {
      float: left;
      padding-right: 20px;
    }
    div {
      float: left;
      width: 400px;
    }
  }
  .button {
    clear: both;
    padding-left: 80px;
    margin-top: 150px;
    .el-button {
      width: 100px;
    }
  }
}
</style>