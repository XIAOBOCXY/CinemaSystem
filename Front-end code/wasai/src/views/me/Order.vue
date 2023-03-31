<template>
  <!-- 订单超时 1 支付成功2  订单异常3 等待支付0 -->
  <div class="order">
    <h3>我的订单</h3>
    <div class="orderCon">
      <el-card
        v-for="(item, index) in orderList"
        :key="index"
        class="box_card"
        shadow="hover"
      >
        <div>
          <img
            :src="item.film.cover"
            alt=""
            v-if="item != null"
            class="item_film_img"
          />

          <div class="order_info" v-if="item != null">
            <div class="item_film_name">《{{ item.film.name }}》</div>
            <div class="item_film_seat">订购座位：{{ item.order.seats }}</div>
            <div class="item_film_seat">
              下单时间：{{ item.order.createTime }}
            </div>
            <div v-if="item.order.status === 2" class="item_film_time">
              支付时间：{{ item.order.payAt }}
            </div>
          </div>

          <el-button
            type="text"
            @click="handlePay(item)"
            v-if="item.order.status === 0"
            class="o1"
            >等待支付</el-button
          >
          <el-tooltip
            class="item"
            effect="dark"
            content="请联系工作人员查询您的订单"
            placement="top"
          >
            <div
              v-if="item.order.status === 3"
              style="color: #e6a23c"
              class="o1"
            >
              订单异常
            </div></el-tooltip
          >
          <div v-if="item.order.status === 2" style="color: #67c23a" class="o1">
            支付成功
          </div>
          <div v-if="item.order.status === 1" style="color: #f56c6c" class="o1">
            订单超时
          </div>
          <div class="o2">¥{{ item.order.price }}</div>
        </div>
      </el-card>
    </div>
    <el-dialog
      title="扫码支付"
      :visible.sync="centerDialogVisible"
      width="30%"
      :show-close="false"
    >
      <div><img class="c_img" src="../../assets/img/c.jpeg" alt="" /></div>
      <span slot="footer" class="dialog_footer">
        <el-button type="primary" @click="submitPay">支付成功</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { MyOrder, updateorder } from "@/api/personal";
import config from "@/config.js";
export default {
  data() {
    return {
      //loading: false,
      centerDialogVisible: false,
      orderList: [
        // {
        //   cover_show: require("../../assets/filmImg/xiaoxiaodeyuanwang.jpg"),
        //   name: "小小的愿望",
        //   seats: 30,
        //   createAt: "2022-01-02",
        //   status: 0,
        //   payAt: "2022-01-20 - 14:25:23",
        //   price: 30.5,
        // },
        // {
        //   cover_show: require("../../assets/filmImg/xiaoxiaodeyuanwang.jpg"),
        //   name: "小小的愿望",
        //   seats: 30,
        //   createAt: "2022-01-02",
        //   status: 1,
        //   payAt: "2022-01-20 - 14:25:23",
        //   price: 30.5,
        // },
        // {
        //   cover_show: require("../../assets/filmImg/xiaoxiaodeyuanwang.jpg"),
        //   name: "小小的愿望",
        //   seats: 30,
        //   createAt: "2022-01-02",
        //   status: 2,
        //   payAt: "2022-01-20 - 14:25:23",
        //   price: 30.5,
        // },
        // {
        //   cover_show: require("../../assets/filmImg/xiaoxiaodeyuanwang.jpg"),
        //   name: "小小的愿望",
        //   seats: 30,
        //   createAt: "2022-01-02",
        //   status: 3,
        //   payAt: "2022-01-20 - 14:25:23",
        //   price: 30.5,
        // },
      ],
    };
  },
  methods: {
    formatDate(dateint) {
      //日期格式化，
      //console.log(dateint);
      var date = new Date(parseInt(dateint));
      var str = date.getFullYear() + "-";
      if (date.getMonth() < 10) {
        str += "0";
      }
      str += date.getMonth() + 1 + "-";
      if (date.getDate() < 10) {
        str += "0";
      }
      str += date.getDate() + " ";
      if (date.getHours() < 10) {
        str += "0";
      }
      str += date.getHours() + ":";

      if (date.getMinutes() < 10) {
        str += "0";
      }
      str += date.getMinutes() + ":";
      if (date.getSeconds() < 10) {
        str += "0";
      }
      str += date.getSeconds();
      //console.log(str);
      return str;
    },
    QMyOrder() {
      MyOrder(localStorage.getItem("uid")).then((res) => {
        if (res.code == 200) {
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].order.createTime = this.formatDate(
              res.data[i].order.createTime
            );
            res.data[i].film.cover = config.API_URL + res.data[i].film.cover;
          }
          this.orderList = res.data;
        }
        //console.log(this.orderList);
      });
    },
    /* 等待支付按钮 */
    // handlePay(order, index) {
    //   this.open(order, index);
    // },
    /* 支付成功按钮 */
    submitPay() {
      this.centerDialogVisible = false;
    },
    handlePay(item) {
      if (true) {
        this.$confirm(
          "请您仔细确认订单金额为" + item.order.price + "元，是否继续？",
          "提示",
          {
            comfirmButtonText: "确认支付",
            cancelButtonText: "取消支付",
            type: "success",
            center: true,
          }
        ).then(() => {
          // item.order.status = 2;
          let data = {
            status: 2,
            id: item.order.id,
            payAt: new Date(),
          };
          updateorder(data)
            .then((res) => {
              if (res.code == 200) {
                this.$message({
                  type: "success",
                  message: "支付成功!",
                });
              }
              this.centerDialogVisible = true;
            })
            .catch(() => {
              this.$message({
                type: "warning",
                message: "用户已取消支付，请前往我的订单支付",
              });
            });
        });
      }
      //   this.$confirm(
      //     "请您仔细确认订单金额为" + order.price + "元，是否继续？",
      //     "提示",
      //     {
      //       confirmButtonText: "确认支付",
      //       cancelButtonText: "取消支付",
      //       type: "success",
      //       center: true,
      //     }
      //   )
      //     .then(() => {
      //       this.loading = false;
      //       this.centerDialogVisible = true;
      //       this.payOrderId = order.id;
      //     })
      //     .catch(() => {
      //       this.$message({
      //         type: "warning",
      //         message: "用户已取消支付",
      //       });
      //     });
      // },
    },
  },
  mounted() {
    this.QMyOrder();
  },
};
</script>
<style lang="scss" scoped>
.order {
  width: 100%;
  h3 {
    letter-spacing: 1px;
    font-weight: 400;
    padding-bottom: 20px;
  }
  .orderCon {
    .box_card {
      margin-bottom: 10px;
      .item_film_img {
        float: left;
        width: 80px;
        height: 100px;
        padding-bottom: 10px;
        padding-right: 10px;
      }
      .order_info {
        float: left;
        .item_film_name {
          letter-spacing: 2px;
          font-weight: 500;
          font-size: 18px;
          padding-bottom: 15px;
          padding-top: 1px;
        }
        .item_film_seat {
          letter-spacing: 1px;
          font-size: 12px;
          padding-bottom: 5px;
          padding-left: 10px;
          color: #91949c;
        }
        .item_film_time {
          letter-spacing: 1px;
          font-size: 12px;
          padding-left: 10px;
          padding-bottom: 5px;
          color: #91949c;
        }
      }
      .o1 {
        float: right;
        line-height: 100px;
        margin-right: 20px;
      }
      .o2 {
        font-size: 20px;
        float: right;
        line-height: 100px;
        padding-right: 100px;
      }
    }
  }
  .el-dialog {
    .c_img {
      width: 100%;
      height: 100%;
    }
  }
}
</style>