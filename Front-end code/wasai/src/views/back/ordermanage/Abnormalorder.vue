<template>
  <div>
    <el-table :data="orderex" style="width: 100%" stripe border height="600px">
      <el-table-column type="expand" flex>
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="ID">
              <span>{{ props.row.orderex.id }}</span>
            </el-form-item>
            <el-form-item label="订单ID">
              <span>{{ props.row.orderex.oid }}</span>
            </el-form-item>
            <el-form-item label="异常原因">
              <span>{{ props.row.orderex.reason }}</span>
            </el-form-item>
            <el-form-item label="上报人">
              <span>{{ props.row.orderex.reviewer }}</span>
            </el-form-item>
            <el-form-item label="是否已处理完成">
              <span>{{ props.row.orderex.status }}</span>
            </el-form-item>
            <el-form-item label="处理结果">
              <span>{{ props.row.orderex.result }}</span>
            </el-form-item>
            <el-form-item label="上报时间">
              <span>{{ props.row.orderex.createAt }}</span>
            </el-form-item>
            <el-form-item label="处理时间">
              <span>{{ props.row.orderex.endAt }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="订单ID" prop="orderex.id" width="150px">
      </el-table-column>
      <el-table-column label="上报人" prop="orderex.reviewer">
      </el-table-column>
      <el-table-column label="异常处理状态" :formatter="statusFormat">
        <template slot-scope="scope">
          {{ statusFormat(scope.row) }}
        </template></el-table-column
      >
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleedit(scope.$index, scope.row)"
            >处理异常</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="提示" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="处理结果" :label-width="formLabelWidth">
          <el-input
            v-model="result"
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
  </div>
</template>



<script>
import { AllOrderex, updateorderex } from "@/api/customer.js";
export default {
  inject: ["reload"],
  data() {
    return {
      orderex: [],
      dialogFormVisible: false,
      formLabelWidth: "150px",
      updatefid: "",
      oid: "",
      orderex1: {},
      result: "",
    };
  },
  methods: {
    submit1() {
      console.log(this.updatefid);
      let data = {
        id: this.updatefid,
        oid: this.oid,
        reason: this.orderex1.orderex.reason,
        status: 1,
        result: this.result,
        reviewer: "admin",
        // createAt: this.orderex1.orderex.createAt,
        // endAt: this.orderex1.orderex.endAt,
      };
      console.log(data);
      updateorderex(data).then((res) => {
        if (res.code == 200) {
          this.$message({
            type: "success",
            message: "异常订单处理成功",
          });
          console.log(res.data);
          this.reload();
          this.dialogFormVisible = false;
        }
      });
    },
    handleedit(index, row) {
      this.dialogFormVisible = true;
      console.log(row.orderex.id);
      this.updatefid = row.orderex.id;
      this.oid = row.orderex.oid;
      console.log("----------");
      console.log(index, row);
      console.log(this.updatefid);
      this.orderex1 = this.orderex[index];
      console.log("11111111");
      console.log(this.orderex1);
      // this.name = this.film[index].name;
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
    statusFormat(row) {
      if (row.orderex.status === 0) {
        return "等待处理";
      } else if (row.orderex.status === 1) {
        return "处理成功";
      }
    },
  },
  mounted() {
    /*查询订单列表 */
    AllOrderex().then((res) => {
      if (res.code == 200) {
        //console.log(res);
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].orderex.createTime = this.formatDate(
            res.data[i].orderex.createTime
          );
          res.data[i].orderex.payAt = this.formatDate(
            res.data[i].orderex.payAt
          );
          res.data[i].orderex.createAt = this.formatDate(
            res.data[i].orderex.createAt
          );
          res.data[i].orderex.endAt = this.formatDate(
            res.data[i].orderex.endAt
          );
          this.orderex = res.data;
          console.log(this.orderex);
        }
      }
      console.log(this.orderex);
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
