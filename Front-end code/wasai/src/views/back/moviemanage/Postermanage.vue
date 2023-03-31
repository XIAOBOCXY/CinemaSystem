<template>
  <div class="main">
    <p class="title">轮播海报管理</p>
    <el-carousel indicator-position="outside" height="400px">
      <el-carousel-item v-for="(item, index) in posterList1" :key="index">
        <img class="CarImg" :src="item.url" alt="" />
      </el-carousel-item>
    </el-carousel>
    <div class="button">
      <el-button type="primary" @click="uploadposter">上传轮播海报</el-button>
      <el-button type="danger" @click="deleteall">删除所有海报</el-button>
    </div>
    <el-table :data="posterList" style="width: 100%" stripe>
      <el-table-column label="上传时间" width="170px"
        ><template slot-scope="scope"> {{ scope.row.updateTime }}</template>
      </el-table-column>
      <el-table-column label="标题" width="130px">
        <template slot-scope="scope">{{ scope.row.title }}</template>
      </el-table-column>
      <el-table-column label="地址浏览">
        <template slot-scope="scope">{{ scope.row.url }}</template>
      </el-table-column>
      <el-table-column label="上架状态" width="100px">
        <template slot-scope="scope">
          <el-tag
            v-if="scope.row.status === 1"
            :type="'success'"
            disable-transitions
            >上架
          </el-tag>
          <el-tag
            v-else-if="scope.row.status === 0"
            :type="'warning'"
            disable-transitions
            >下架
          </el-tag></template
        >
      </el-table-column>
      <el-table-column width="130px">
        <template slot-scope="scope">
          <div v-if="scope.row.status === 0" class="upfont">上架</div>
          <div v-else-if="scope.row.status === 1" class="unupfont">
            下架
          </div></template
        >
      </el-table-column>
      <el-table-column width="130px">
        <template slot-scope="scope">
          <el-link type="danger" @click="handleDelete(scope.$index, scope.row)"
            >删除</el-link
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="轮播海报" :visible.sync="dialogFormVisible">
      <el-form :model="poster">
        <el-form-item label="海报标题" :label-width="formLabelWidth">
          <el-input v-model="poster.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="上架状态" :label-width="formLabelWidth">
          <el-select v-model="poster.status" placeholder="请选择上架状态">
            <el-option label="上架" value="1"></el-option>
            <el-option label="下架" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="选择图片" :label-width="formLabelWidth">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:8888/api/poster/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="subimt">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  ListAllPoster2,
  addposter,
  ListAllPoster,
  deleteposterbyid,
} from "@/api/film";
export default {
  inject: ["reload"],
  data() {
    return {
      posterList: [], //轮播图
      posterList1: [], //轮播图
      poster: {
        title: "",
        url: "",
        status: "",
      },
      dialogFormVisible: false,
      imageUrl: "",
      formLabelWidth: "120px",
      PosterName: "",
    };
  },
  methods: {
    /*  ----------------轮播图查询---------------------*/
    posterQuery() {
      /* 3.2.4.1 查询轮播图列表 */
      ListAllPoster2().then((res) => {
        /* console.log(res); */
        if (res.code == 200) {
          this.posterList = res.data;
          for (var i = 0; i < this.posterList.length; i++) {
            // this.posterList[i].url =
            //   config.API_URL + "/upload?id=" + this.posterList[i].url;
            res.data[i].updateTime = this.FormData(res.data[i].updateTime);
          }
        }
      });
    },
    posterQuery1() {
      /* 3.2.4.1 查询轮播图列表 */
      ListAllPoster().then((res) => {
        /* console.log(res); */
        if (res.code == 200) {
          this.posterList1 = res.data;
          // console.log(this.posterList1);
          for (var i = 0; i < this.posterList1.length; i++) {
            // this.posterList[i].url =
            //   config.API_URL + "/upload?id=" + this.posterList[i].url;
            res.data[i].updateTime = this.FormData(res.data[i].updateTime);
          }
        }
      });
    },
    FormData(dateint) {
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
    handleDelete(index, row) {
      console.log(index, row);
      deleteposterbyid(row.id).then((res) => {
        if (res.code == 200) {
          this.reload();
        }
      });
    },
    uploadposter() {
      this.dialogFormVisible = true;
    },
    handleAvatarSuccess(res, file) {
      this.PosterName = res.data.PosterName;
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
    subimt() {
      this.dialogFormVisible = false;
      let data = {
        fid: 10,
        url: this.PosterName,
        title: this.poster.name,
        status: this.poster.status,
        createTime: new Date(),
        updateTime: new Date(),
      };
      console.log(data);
      addposter(data).then((res) => {
        if (res.code == 200) {
          console.log(res.data);
          this.$message({
            type: "success",
            message: "新增轮播图成功!",
          });
          this.reload();
          this.posterQuery1();
          // console.log("1111111111");
          // console.log(this.posterList);
          // console.log("222222222222");
          // console, log(this.posterList1);
        }
      });
    },
    deleteall() {
      for (var i = 0; i < this.posterList1.length; i++) {
        console.log(this.posterList1);
        console.log(i);
        deleteposterbyid(this.posterList1[i].id).then((res) => {
          if (res.code == 200) {
            this.reload();
          }
        });
      }
    },
  },
  mounted() {
    /* 轮播图查询接口 */
    this.posterQuery();
    this.posterQuery1();
  },
};
</script>

<style lang="scss" scope>
.main {
  background-color: #f4f4f4;
  padding: 20px;
  .title {
    font-size: 18px;
    color: gray;
  }
  .el-carousel {
    padding-top: 20px;
    width: 1250px;
    margin: 0 auto;
    .CarImg {
      width: 100%;
      height: 100%;
    }
  }
  .avatar {
    width: 200px;
  }
  .button {
    padding-left: 30px;
    padding-bottom: 20px;
    .el-button {
      margin-right: 50px;
    }
  }
  .upfont {
    color: #00cc33;
  }
  .unupfont {
    color: #ff6633;
  }
}
</style>