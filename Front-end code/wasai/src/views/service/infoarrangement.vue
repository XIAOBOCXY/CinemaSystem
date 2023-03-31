<template>
  <div>
    <el-table :data="order" style="width: 100%" stripe border height="600px">
      <el-table-column type="expand" flex>
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="订单ID">
              <span>{{ props.row.order.id }}</span>
            </el-form-item>
            <el-form-item label="用户ID">
              <span>{{ props.row.order.uid }}</span>
            </el-form-item>
            <el-form-item label="电影ID">
              <span>{{ props.row.film.id }}</span>
            </el-form-item>
            <el-form-item label="场次ID">
              <span>{{ props.row.order.aid }}</span>
            </el-form-item>
            <el-form-item label="电影名">
              <span>{{ props.row.film.name }}</span>
            </el-form-item>
            <el-form-item label="座位号">
              <span>{{ props.row.order.seats }}</span>
            </el-form-item>
            <el-form-item label="订单金额">
              <span>{{ props.row.order.price }}</span>
            </el-form-item>
            <el-form-item label="下单时间">
              <span>{{ props.row.order.createTime }}</span>
            </el-form-item>
            <el-form-item label="支付时间">
              <span>{{ props.row.order.payAt }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="订单ID" prop="order.id" width="150px">
      </el-table-column>
      <el-table-column label="电影名" prop="film.name"> </el-table-column>
      <el-table-column label="订购座位" prop="order.seats"></el-table-column>
      <el-table-column label="订单金额" prop="order.price"></el-table-column>
      <el-table-column label="订单状态">
        <template slot-scope="scope" width="130px">
          <el-tag v-if="scope.row.order.status === 0" disable-transitions
            >等待支付
          </el-tag>
          <el-tag
            v-else-if="scope.row.order.status === 1"
            :type="'danger'"
            disable-transitions
            >支付失败
          </el-tag>
          <el-tag
            v-else-if="scope.row.order.status === 2"
            :type="'success'"
            disable-transitions
            >支付成功
          </el-tag>
          <el-tag
            v-else-if="scope.row.order.status === 3"
            :type="'warning'"
            disable-transitions
            >已被撤销
          </el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>



<script>
import { AllOrder } from "@/api/customer.js";
export default {
  data() {
    return {
      order: [],
    };
  },
  methods: {
    handleDelete(index, row) {
      //console.log(index, row);
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
    // statusFormat(row) {
    //   if (row.order.status === 0) {
    //     return "等待支付";
    //   } else if (row.order.status === 1) {
    //     return "支付失败";
    //   } else if (row.order.status === 2) {
    //     return "支付成功";
    //   } else if (row.order.status === 3) {
    //     return "已被撤销";
    //   }
    // },
  },
  mounted() {
    /*查询订单列表 */
    AllOrder().then((res) => {
      if (res.code == 200) {
        console.log(res);
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].order.createTime = this.formatDate(
            res.data[i].order.createTime
          );
          res.data[i].order.payAt = this.formatDate(res.data[i].order.payAt);
          this.order = res.data;
        }
      } else {
      }
    });
  },
};
</script>

<style>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 200px;
  color: #99a9bf;
  padding-left: 40px;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
