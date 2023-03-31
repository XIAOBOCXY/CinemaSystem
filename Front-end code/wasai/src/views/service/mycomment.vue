<template>
  <div class="main">
    <el-table :data="workerevaluate" style="width: 100%" stripe>
      <el-table-column label="日期" width="180px"
        ><template slot-scope="scope"> {{ scope.row.createTime }}</template>
      </el-table-column>
      <el-table-column label="评语">
        <template slot-scope="scope">{{ scope.row.content }}</template>
      </el-table-column>
      <el-table-column width="130px">
        <template slot-scope="scope">
          <el-tag
            v-if="scope.row.type === '满意'"
            :type="'primary'"
            disable-transitions
            >满意
          </el-tag>
          <el-tag
            v-else-if="scope.row.type === '不满意'"
            :type="'warning'"
            disable-transitions
            >不满意
          </el-tag>
          <el-tag
            v-else-if="scope.row.type === '非常满意'"
            :type="'success'"
            disable-transitions
            >非常满意
          </el-tag></template
        >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { MyCritic3 } from "@/api/lmessage";
export default {
  data() {
    return {
      workerevaluate: [], //轮播图
    };
  },
  methods: {
    /*  ----------------我的评价---------------------*/
    workerevaluateQuery() {
      //localStorage.getItem("wid")
      MyCritic3(localStorage.getItem("id")).then((res) => {
        if (res.code == 200) {
          //console.log(res.data);
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].createTime = this.FormData(res.data[i].createTime);
          }
          this.workerevaluate = res.data;
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
  },
  mounted() {
    /* 我的评价查询接口 */
    this.workerevaluateQuery();
  },
};
</script>

<style lang="scss" scope>
.main {
  background-color: #f4f4f4;
  padding: 20px;
}
</style>