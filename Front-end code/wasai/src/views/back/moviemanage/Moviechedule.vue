<template>
  <div>
    <div>
      <el-table
        :data="
          film.filter(
            (data) =>
              !search ||
              data.name.toLowerCase().includes(search.toLowerCase()) ||
              data.type.toLowerCase().includes(search.toLowerCase())
          )
        "
        style="width: 100%"
        stripe
        height="600px"
        :default-sort="{ prop: 'date', order: 'descending' }"
      >
        <el-table-column label="电影名" sortable width="130px"
          ><template slot-scope="scope">
            <div class="tablename">{{ scope.row.name }}</div></template
          >
          /></el-table-column
        >
        <el-table-column label="放映时间" sortable width="160px">
          <template slot-scope="scope">{{ scope.row.startTime }}</template>
        </el-table-column>
        <el-table-column label="开放座位" sortable width="80px">
          <template slot-scope="scope">{{ scope.row.seatNumber }}</template>
        </el-table-column>
        <el-table-column
          prop="type"
          label="类型"
          width="130px"
          sortable
          :filters="[
            { text: '2D', value: '2D' },
            { text: '3D', value: '3D' },
          ]"
          :filter-method="filtertype"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope" width="130px">
            <el-tag
              v-if="scope.row.type === '2D'"
              :type="'success'"
              disable-transitions
              >{{ scope.row.type }}
            </el-tag>
            <el-tag
              v-else-if="scope.row.type === '3D'"
              :type="'warning'"
              disable-transitions
              >{{ scope.row.type }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="票房统计"
          prop="boxOffice"
          sortable
          width="130px"
        >
        </el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="160px" sortable>
        </el-table-column>
        <el-table-column label="本场票价" prop="price" sortable width="130px"
          ><template slot-scope="scope">
            <el-tag effect="plain">{{ scope.row.price }}</el-tag>
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
              type="success"
              @click="handlecount(scope.$index, scope.row)"
              >统计票房</el-button
            >
            <el-button
              size="mini"
              type="primary"
              @click="handleEdit(scope.$index, scope.row)"
              >修改</el-button
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
    </div>
    <el-dialog title="提示" :visible.sync="dialogFormVisible">
      <el-form :model="arrangement">
        <el-form-item label="请输入此场次的票房" :label-width="formLabelWidth">
          <el-input
            v-model="arrangement.boxOffice"
            autocomplete="off"
            placeholder="请输入"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit1">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改电影排片" :visible.sync="dialogFormVisible1"
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
          <el-date-picker
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
          </el-date-picker>
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
          ><el-button @click="dialogFormVisible1 = false">取消</el-button>
        </div>
      </div></el-dialog
    >
  </div>
</template>

<style>
</style>

<script>
import { ListAllArrangementFilm, updatearrange } from "@/api/film";
export default {
  inject: ["reload"],
  data() {
    return {
      inject: ["reload"],
      film: [],
      search: "",
      dialogFormVisible: false,
      dialogFormVisible1: false,
      arrangement: {},
      formLabelWidth: "150px",
      updatefid: "",
      name: "",
      fid: "",
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
    };
  },
  methods: {
    filtertype(value, row) {
      return row.type === value;
    },
    handleEdit(index, row) {
      console.log(index, row);
      this.dialogFormVisible1 = true;
      this.updatefid = row.id;
      this.fid = this.film[index].id;
      this.name = this.film[index].name;
    },
    handleDelete(index, row) {
      console.log(index, row);
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
    handlecount(index, row) {
      this.dialogFormVisible = true;
      this.updatefid = row.id;
      this.fid = this.film[index].id;
      this.name = this.film[index].name;
    },
    submit1() {
      let data = {
        id: this.updatefid,
        fid: this.fid,
        name: this.name,
        seatNumber: this.arrangement.seatNumber,
        boxOffice: this.arrangement.boxOffice,
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
      updatearrange(data).then((res) => {
        if (res.code == 200) {
          this.$message({
            type: "success",
            message: "票房统计成功",
          });
          console.log(res.data);
          this.reload();
          this.dialogFormVisible = false;
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
        id: this.updatefid,
        fid: this.fid,
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
      updatearrange(data).then((res) => {
        if (res.code == 200) {
          this.$message({
            type: "success",
            message: "修改排片成功",
          });
          console.log(res.data);
          this.reload();
          this.dialogFormVisible1 = false;
        }
      });
    },
    handleChange(value) {
      // console.log(value);
    },
  },
  mounted() {
    /*查看排片列表 */
    ListAllArrangementFilm().then((res) => {
      if (res.code == 200) {
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].startTime = this.formatDate(res.data[i].startTime);
          res.data[i].endTime = this.formatDate(res.data[i].endTime);
          this.film = res.data;
        }
      } else {
      }
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

