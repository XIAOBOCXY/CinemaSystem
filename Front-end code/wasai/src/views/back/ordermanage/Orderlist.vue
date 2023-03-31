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
      <el-table-column label="订单金额" prop="order.price"> </el-table-column>
      <el-table-column label="订单状态" :formatter="statusFormat">
        <template slot-scope="scope">
          {{ statusFormat(scope.row) }}
        </template></el-table-column
      >
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="warning" @click="handleDelete(scope.row)"
            >撤销订单</el-button
          >
          <el-button
            size="mini"
            type="danger"
            @click="handleupload(scope.$index, scope.row)"
            >上报异常</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="上报异常表单" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="上报人" :label-width="formLabelWidth">
          <el-input
            v-model="OrderException.reviewer"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="上报原因" :label-width="formLabelWidth">
          <el-input
            v-model="OrderException.reason"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="orderexupdate">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>



<script>
import { AllOrder, addorderex } from "@/api/customer.js";
import { updateorder } from "@/api/personal.js";
export default {
  inject: ["reload"],
  data() {
    return {
      order: [],
      dialogFormVisible: false,
      OrderException: [],
      formLabelWidth: "120px",
      updatefid: "",
      oid: "",
    };
  },
  methods: {
    handleDelete(row) {
      console.log(row.order);
      this.$confirm("确定要撤销此订单？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log(row.order);
          let data = {
            status: 3,
            id: row.order.id,
          };
          updateorder(data).then((res) => {
            console.log(row.order);
            if (res.code == 200) {
              this.$message({
                type: "success",
                message: "撤销成功!",
              });
            }
          });
          // deleteorder(row.order.id).then((res) => {
          //   if (res.code == 200) {
          //     // console.log(res.data);
          //     this.$message({
          //       type: "success",
          //       message: "删除成功!",
          //     });
          //   }
          // });
          // this.listallorder();
          this.reload();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //上报异常
    handleupload(index, row) {
      this.dialogFormVisible = true;
      this.updatefid = row.order.id;
      // this.oid = row.order.oid;
      console.log(this.updatefid);
      // console.log(this.oid);
    },
    orderexupdate() {
      console.log(this.updatefid);
      let data = {
        oid: this.updatefid,
        reason: this.OrderException.reason,
        status: 0,
        reviewer: this.OrderException.reviewer,
      };
      console.log(data);
      addorderex(data).then((res) => {
        if (res.code == 200) {
          this.$message({
            type: "success",
            message: "上报异常",
          });
          console.log(res.data);
          this.reload();
          this.dialogFormVisible = false;
        }
      });
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
    statusFormat(row) {
      if (row.order.status === 0) {
        return "等待支付";
      } else if (row.order.status === 1) {
        return "支付失败";
      } else if (row.order.status === 2) {
        return "支付成功";
      } else if (row.order.status === 3) {
        return "已被撤销";
      }
    },
    listallorder() {
      AllOrder().then((res) => {
        if (res.code == 200) {
          //console.log(res);
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].order.createTime = this.formatDate(
              res.data[i].order.createTime
            );
            res.data[i].order.payAt = this.formatDate(res.data[i].order.payAt);
            this.order = res.data;
          }
        } else {
        }
        console.log(this.order);
      });
    },
  },
  mounted() {
    /*查询订单列表 */
    this.listallorder();
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
