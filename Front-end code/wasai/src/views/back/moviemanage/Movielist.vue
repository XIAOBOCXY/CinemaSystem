<template>
  <div>
    <el-table
      :data="
        Film.filter(
          (data) =>
            !search ||
            data.name.toLowerCase().includes(search.toLowerCase()) ||
            data.type.toLowerCase().includes(search.toLowerCase()) ||
            data.region.toLowerCase().includes(search.toLowerCase())
        )
      "
      style="width: 100%"
      stripe
      height="600px"
      :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column label="电影名" sortable width="130px"
        ><template slot-scope="scope">
          <img class="tableimg" :src="scope.row.cover" alt="" />
          <div class="tablename">{{ scope.row.name }}</div></template
        >
        /></el-table-column
      >
      <el-table-column label="上映时间" sortable width="160px">
        <template slot-scope="scope">{{ scope.row.releaseTime }}</template>
      </el-table-column>
      <el-table-column
        prop="type"
        label="类型"
        width="130px"
        sortable
        :filters="[
          { text: '爱情', value: '爱情' },
          { text: '喜剧', value: '喜剧' },
          { text: '科幻', value: '科幻' },
          { text: '动画', value: '动画' },
          { text: '恐怖', value: '恐怖' },
          { text: '悬疑', value: '悬疑' },
          { text: '冒险', value: '冒险' },
          { text: '动作', value: '动作' },
          { text: '纪录片', value: '纪录片' },
          { text: '家庭', value: '家庭' },
          { text: '传记', value: '传记' },
          { text: '武侠', value: '武侠' },
          { text: '犯罪', value: '犯罪' },
          { text: '历史', value: '历史' },
          { text: '古装', value: '古装' },
          { text: '战争', value: '战争' },
          { text: '儿童', value: '儿童' },
          { text: '短片', value: '短片' },
          { text: '其他', value: '其他' },
        ]"
        :filter-method="filtertype"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope" width="130px">
          <el-tag
            v-if="
              scope.row.type === '爱情' ||
              scope.row.type === '喜剧' ||
              scope.row.type === '家庭'
            "
            :type="'warning'"
            disable-transitions
            >{{ scope.row.type }}
          </el-tag>
          <el-tag
            v-else-if="
              scope.row.type === '科幻' ||
              scope.row.type === '动画' ||
              scope.row.type === '儿童'
            "
            :type="'success'"
            disable-transitions
            >{{ scope.row.type }}
          </el-tag>
          <el-tag
            v-else-if="
              scope.row.type === '恐怖' ||
              scope.row.type === '悬疑' ||
              scope.row.type === '冒险' ||
              scope.row.type === '动作' ||
              scope.row.type === '犯罪' ||
              scope.row.type === '战争'
            "
            :type="'danger'"
            disable-transitions
            >{{ scope.row.type }}
          </el-tag>
          <el-tag
            v-else-if="
              scope.row.type === '历史' ||
              scope.row.type === '古装' ||
              scope.row.type === '传记' ||
              scope.row.type === '纪录片' ||
              scope.row.type === '武侠'
            "
            :type="'info'"
            disable-transitions
            >{{ scope.row.type }}
          </el-tag>
          <el-tag
            v-else-if="scope.row.type === '短片' || scope.row.type === '短片'"
            disable-transitions
            >{{ scope.row.type }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="上架状态"
        prop="status"
        sortable
        :formatter="statusFormat"
        width="130px"
      >
        <template slot-scope="scope">
          {{ statusFormat(scope.row) }}
        </template>
      </el-table-column>
      <el-table-column
        prop="region"
        label="地区"
        width="130px"
        sortable
        :filters="[
          { text: '中国大陆', value: '中国大陆' },
          { text: '内地', value: '内地' },
          { text: '美国', value: '美国' },
          { text: '韩国', value: '韩国' },
        ]"
        :filter-method="filterregion"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          {{ scope.row.region }}
        </template>
      </el-table-column>
      <el-table-column label="电影时长" prop="duration" sortable width="130px"
        ><template slot-scope="scope">
          <el-tag effect="plain">{{ scope.row.duration }}分钟</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <el-input placeholder="请输入内容" v-model="search">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </template>
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleEdit(scope.$index, scope.row)"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="success"
            @click="handlefilm(scope.$index, scope.row)"
            >排片</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="电影信息修改" :visible.sync="dialogFormVisible"
      ><div class="main">
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
          <el-button type="primary" @click="submiteditfilm()"
            >确认提交</el-button
          ><el-button @click="quxiao()">取消</el-button>
        </div>
      </div></el-dialog
    >

    <!-- ////////////////////////////////////////////////////////// -->
    <el-dialog title="新增电影排片" :visible.sync="dialogFormVisible1"
      ><div class="main">
        <div class="filmname">
          <p>电影名称</p>
          <el-input
            v-model="this.name"
            placeholder="请输入内容"
            disabled
          ></el-input>
        </div>
        <div class="filmregion">
          <p>开放座位</p>
          <el-input-number
            v-model="arrangement.seatNumber"
            @change="handleChange"
            :min="1"
            :max="50"
            label="描述文字"
          ></el-input-number>
        </div>
        <div class="filmtime">
          <p>放映类型</p>
          <el-select v-model="arrangement.type" placeholder="请选择">
            <el-option
              v-for="item in options1"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </div>
        <div class="filmstart">
          <p>放映日期</p>
          <el-date-picker
            v-model="arrangement.date"
            align="right"
            type="date"
            placeholder="选择日期"
          >
          </el-date-picker>
        </div>
        <div class="filmon">
          <p>放映时间</p>
          <div class="block">
            <span class="demonstration"></span>
            <el-date-picker
              v-model="arrangement.startTime"
              type="datetime"
              placeholder="选择日期时间"
            >
            </el-date-picker>
          </div>
          <p>结束时间</p>
          <div class="block">
            <span class="demonstration"></span>
            <el-date-picker
              v-model="arrangement.endTime"
              type="datetime"
              placeholder="选择日期时间"
            >
            </el-date-picker>
          </div>

          <!-- <el-date-picker
            v-model="arrangement.startTime"
            align="right"
            type="date"
            placeholder="开始时间"
          >
          </el-date-picker>
          --
          <el-date-picker
            v-model="arrangement.endTime"
            align="right"
            type="date"
            placeholder="结束时间"
          >
          </el-date-picker> -->
        </div>

        <div class="filmregion">
          <p>电影票价</p>
          <el-input-number
            v-model="arrangement.price"
            @change="handleChange"
            :min="1"
            :max="200"
          ></el-input-number>
        </div>

        <div class="filminfo">
          <p>排片人</p>

          <div>
            <el-input
              v-model="arrangement.founder"
              placeholder="请输入内容"
            ></el-input>
          </div>
        </div>
        <div class="button">
          <el-button type="primary" @click="submiteditfilm1()"
            >确认提交</el-button
          ><el-button @click="quxiao()">取消</el-button>
        </div>
      </div></el-dialog
    >
  </div>
</template>

<style>
</style>

<script>
import { AddFilm, updatefilm, AddArrangement } from "@/api/film.js";
import { ListAllFilm, deletefilm } from "@/api/film";
export default {
  inject: ["reload"],
  data() {
    return {
      arrangement: [],
      Film: [],
      search: "",
      dialogFormVisible: false,
      dialogFormVisible1: false,
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
      options1: [
        {
          value: "2D放映",
          label: "2D放映",
        },
        {
          value: "3D放映",
          label: "3D放映",
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
      updatefid: "",
      img: "",
      name: "",
      region: "",
      type: "",
    };
  },
  methods: {
    quxiao() {
      this.dialogFormVisible = false;
      this.dialogFormVisible1 = false;
    },
    filtertype(value, row) {
      return row.type === value;
    },
    filterregion(value, row) {
      return row.region === value;
    },
    handleEdit(index, row) {
      //console.log(index, row);
      this.updatefid = row.id;
      console.log(this.updatefid);
      this.dialogFormVisible = true;
      this.img = this.Film[index].cover;
      this.name = this.Film[index].name;
      this.region = this.Film[index].region;
      this.type = this.Film[index].type;
      console.log(index);
      console.log(this.name);
    },
    handleDelete(index, row) {
      //console.log("----");
      //console.log(row);
      this.$confirm("确定要删除此电影？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deletefilm(row.id).then((res) => {
            if (res.code == 200) {
              this.$message({
                type: "success",
                message: "删除电影成功",
              });
              this.reload();
            }
          });
          this.reload();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    handlefilm(index, row) {
      //console.log(index, row);
      this.dialogFormVisible1 = true;
      //console.log(index, row);
      this.updatefid = row.id;
      //console.log(this.updatefid);

      console.log(this.updatefid);
      this.img = this.Film[index].cover;
      this.name = this.Film[index].name;
      this.region = this.Film[index].region;
      this.type = this.Film[index].type;
      console.log(index);
      console.log(this.name);
    },
    statusFormat(row) {
      if (row.status === 1) {
        return "上架";
      } else if (row.status === 0) {
        return "下架";
      }
    },
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
    submitaddfilm(item) {
      //console.log(item);
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
      //console.log(item);

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
      };
      AddFilm(data).then((res) => {
        if (res.code == 200) {
          console.log(res.data);
          this.$message({
            type: "success",
            message: "新增电影成功!",
          });
          this.reload();
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
    submiteditfilm() {
      if (this.fileName == "") {
        this.fileName = this.img.replace("http://localhost:8888", "");
      }
      // if (this.film.name == "") {
      //   this.film.name = this.name;
      // }
      // if (this.film.region == "") {
      //   this.film.region = this.region;
      // }
      // if (this.film.type == "") {
      //   this.film.type = this.type;
      // }
      //console.log(this.film);
      let data = {
        id: this.updatefid,
        cover: this.fileName,
        name: this.film.name,
        region: this.film.region,
        duration: this.film.duration,
        releaseTime: this.film.releaseTime,
        status: this.film.status,
        type: this.film.type,
        introduction: this.film.introduction,
      };
      if (this.radio === 1) {
        data.type = "爱情";
      } else if (this.radio === 2) {
        data.type = "喜剧";
      } else if (this.radio === 3) {
        data.type = "科幻";
      } else if (this.radio === 4) {
        data.type = "动画";
      } else if (this.radio === 5) {
        data.type = "恐怖";
      } else if (this.radio === 6) {
        data.type = "悬疑";
      } else if (this.radio === 7) {
        data.type = "冒险";
      } else if (this.radio === 8) {
        data.type = "动作";
      } else if (this.radio === 9) {
        data.type = "纪录";
      } else if (this.radio === 10) {
        data.type = "家庭";
      } else if (this.radio === 11) {
        data.type = "传记";
      } else if (this.radio === 12) {
        data.type = "武侠";
      } else if (this.radio === 13) {
        data.type = "犯罪";
      } else if (this.radio === 14) {
        data.type = "历史";
      } else if (this.radio === 15) {
        data.type = "古装";
      } else if (this.radio === 16) {
        data.type = "战争";
      } else if (this.radio === 17) {
        data.type = "儿童";
      } else if (this.radio === 18) {
        data.type = "短片";
      } else if (this.radio === 19) {
        data.type = "其他";
      }
      console.log(data);
      updatefilm(data).then((res) => {
        if (res.code == 200) {
          this.$message({
            type: "success",
            message: "修改成功",
          });
          console.log(res.data);
          this.reload();
        }
      });
    },
    submiteditfilm1() {
      console.log("--------");
      console.log(this.arrangement.type);
      if (this.arrangement.type === "2D放映") {
        this.arrangement.type = "2D";
      } else {
        this.arrangement.type = "3D";
      }
      let data = {
        fid: this.updatefid,
        name: this.name,
        seatNumber: this.arrangement.seatNumber,
        boxOffice: 0,
        price: this.arrangement.price,
        type: this.arrangement.type,
        date: this.arrangement.date,
        startTime: this.arrangement.startTime,
        endTime: this.arrangement.endTime,
        founder: this.arrangement.founder,
        createTime: new Date(),
        updateTime: new Date(),
      };
      console.log(data);
      AddArrangement(data).then((res) => {
        if (res.code == 200) {
          console.log(res.data);
          this.$message({
            type: "success",
            message: "新增排片成功!",
          });
          this.reload();
        }
      });
    },
  },
  mounted() {
    /*查询电影列表 */
    ListAllFilm().then((res) => {
      if (res.code == 200) {
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].releaseTime = this.formatDate(res.data[i].releaseTime);
          this.Film = res.data;
        }
      } else {
      }
      console.log(this.Film);
    });
  },
};
</script>
<style lang="scss" scoped>
.tableimg {
  width: 92px;
  height: 132px;
  padding: 8px;
  display: block;
}
.tablename {
  text-align: center;
}
.main {
  height: 890px;
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
      width: 700px;
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

