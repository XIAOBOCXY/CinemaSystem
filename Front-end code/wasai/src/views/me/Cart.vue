<template>
  <div class="carShopping">
    <h3>购物车</h3>
    <div class="box" v-loading="loading">
      <el-card
        v-for="(item, index) in cartList"
        :key="index"
        class="box_card"
        shadow="hover"
      >
        <div>
          <div class="shop_car_check">
            <input
              type="checkbox"
              @change="handleCheck(item.cart.id)"
              :checked="isExists(item.cart.id)"
            />
          </div>
          <img class="item_film_img" :src="item.film.cover" alt="" />
          <div class="shop_car_info">
            <div class="item_film_name">《{{ item.film.name }}》</div>
            <div class="item_film_seat">座位：{{ item.cart.seats }}</div>
            <div class="item_film_seat">手机号码：{{ item.cart.phone }}</div>
            <div class="item_film_time">
              开场时间：
              <span>{{ item.arrangement.startTime }}</span>
            </div>
          </div>

          <div class="shop_car_price">
            ¥{{ item.cart.price }}
            <el-button
              @click="handledelete(index)"
              type="text"
              icon="el-icon-delete"
              >删除</el-button
            >
          </div>
        </div>
      </el-card>

      <div v-if="cartList.length !== 0" class="isbottom">
        <div>
          <div class="all_checked">
            <input
              type="checkbox"
              @change="handleCheckAll"
              :checked="this.selectList.length === this.cartList.length"
              全选
            />
          </div>
          <el-button @click="submitCart" type="text" class="sub_btn"
            >全部结算</el-button
          >
          <div class="order_footer1">合计：¥{{ totalall }}</div>
        </div>
      </div>
    </div>

    <el-dialog
      title="扫码支付"
      :visible.sync="centerDialogVisible"
      width="30%"
      :show-close="false"
    >
      <div><img class="c-img" src="../../assets/img/c.jpeg" alt="" /></div>
      <span slot="footer" class="dialog_footer">
        <el-button type="primary" @click="submitPay">支付成功</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { MyCart, AddMyOrder } from "@/api/personal";
import config from "@/config.js";
export default {
  data() {
    return {
      //购物车列表
      cartList: [
        // {
        //   name: "小小的愿望",
        //   seats: "23",
        //   phone: 13569984562,
        //   cover_show: require("../../assets/filmImg/xiaoxiaodeyuanwang.jpg"),
        //   data: "2022-01-20",
        //   starttime: "19:00",
        //   price: 40,
        // },
        // {
        //   name: "小小的愿望",
        //   seats: "23",
        //   phone: 13569984562,
        //   cover_show: require("../../assets/filmImg/xiaoxiaodeyuanwang.jpg"),
        //   data: "2022-01-20",
        //   starttime: "19:00",
        //   price: 40,
        // },
      ],
      selectList: [],
      loading: false,
      checkAll: false,
      price: "",
      centerDialogVisible: false,
      //isIndeterminate: false,
      /* success: false,
      i: 0, */
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
      str += date.getMinutes();
      //console.log(str);
      return str;
    },
    isExists(id) {
      let idIndex = -1;
      for (var i = 0; i < this.selectList.length; i++) {
        if (id == this.selectList[i]) {
          idIndex = i;
          break;
        }
      }
      //console.log(idIndex);
      if (idIndex == -1) {
        return false;
      } else {
        return true;
      }
    },
    handleCheck(id) {
      //console.log("handleCheck");
      //拿到对应下标
      //let idIndex = this.mainlistIds.indexOf(id);
      //console.log(id);
      let idIndex = -1;
      for (var i = 0; i < this.selectList.length; i++) {
        if (id == this.selectList[i]) {
          idIndex = i;
          break;
        }
      }

      if (idIndex >= 0) {
        // 如果已经包含了该id, 则去除(单选按钮由选中变为非选中状态)
        this.selectList.splice(idIndex, 1);
      } else {
        // 选中该checkbox
        this.selectList.push(id);
      }
      //console.log(this.selectList);
    },
    /* 复选框change事件 */
    // handleCheck() {
    //   console.log("handleCheck");
    //   this.checkOut();
    // },
    /* 删除按钮 */
    handledelete(index) {
      for (var i = 0; i < this.cartList.length; i++) {
        var indexid = this.selectList.indexOf(this.cartList[index].cart.id);
        if (indexid >= 0) {
          this.selectList.splice(indexid, 1);
        }
      }
      this.cartList.splice(index, 1);
      console.log(this.cartList);
      // this.cartList.splice(index, 1);
      // this.loadCarts();
      //数据库里没删掉
    },
    /* 复选框全选事件 */
    handleCheckAll() {
      //console.log("handleCheckAll");
      this.checkAll = !this.checkAll;
      if (this.checkAll) {
        // 全选时
        this.selectList = [];
        this.cartList.forEach(function (item) {
          this.selectList.push(item.cart.id);
        }, this);
      } else {
        this.selectList = [];
      }
      //console.log(this.selectList);
      // if (this.checkAll) {
      //   this.changeAllChecked(true);
      //   this.checkOut();
      // } else {
      //   this.changeAllChecked(false);
      //   this.price = 0;
      // }
    },
    // changeAllChecked(status) {
    //   for (let i = 0; i < this.selectList.length; i++) {
    //     this.selectList[i].checked = status;
    //   }
    // },
    /* 全部结算 */
    submitCart() {
      if (this.selectList.length > 0) {
        this.$confirm(
          "请您仔细确认订单金额为" + this.price + "元，是否继续？",
          "提示",
          {
            comfirmButtonText: "确认支付",
            cancelButtonText: "取消支付",
            type: "success",
            center: true,
          }
        )
          .then(() => {
            //调用后端代码，使用批处理效果
            let data = {
              ids: this.selectList,
              uid: localStorage.getItem("uid"),
              phone: this.cartList[0].cart.phone,
              aid: this.cartList[0].arrangement.id,
              price: this.price,
              status: 0,
            };
            AddMyOrder(data).then((res) => {
              //获得数据库的返回
              console.log(res);
              console.log(this.selectList);
              for (let i = 0; i < this.selectList.length; i++) {
                for (let j = 0; j < this.cartList.length; j++) {
                  if (this.cartList[j].cart.id == this.selectList[i]) {
                    //什么意思
                    //0，表示正常的购物车项，没有加入到订单中
                    //2，表示已经加入到订单中，不用再显示了
                    //3，表示异常的购物车项，
                    console.log(this.cartList[j]);
                    this.cartList[j].cart.status = 2;
                    this.handledelete(j); //删除选中的内容
                    this.centerDialogVisible = true;
                  }
                }
              }
            });
          })
          .catch(() => {
            for (let i = 0; i < this.selectList.length; i++) {
              for (let j = 0; j < this.cartList.length; j++) {
                if (this.cartList[j].cart.id == this.selectList[i]) {
                  this.cartList[j].cart.status = 3;
                  this.handledelete(j);
                }
              }
            }
            this.$message({
              type: "warning",
              message: "用户已取消支付，请前往我的订单支付",
            });
          });
      }
      // let result = this.selectList.filter((item) => {
      //   return item.checked;
      // });
      // if (result.length > 0) {
      //   this.$confirm(
      //     "请您仔细确认订单金额为" + this.price + "元，是否继续？",
      //     "提示",
      //     {
      //       confirmButtonText: "确认支付",
      //       cancelButtonText: "取消支付",
      //       type: "success",
      //       center: true,
      //     }
      //   )
      //     .then(() => {
      //       for (let i = 0; i < this.selectList.length; i++) {
      //         if (this.selectList[i].checked) {
      //           this.selectList[i].cart.status = 2;
      //           this.centerDialogVisible = true;
      //           this.i = i;
      //         }
      //       }
      //     })
      //     .catch(() => {
      //       for (let i = 0; i < this.selectList.length; i++) {
      //         this.selectList[i].cart.status = 0;
      //         this.$message({
      //           type: "warning",
      //           message: "用户已取消支付，请您前往我的订单进行支付",
      //         });
      //       }
      //     });
      // } else {
      //   this.$message({
      //     type: "warning",
      //     message: "请您勾选要结算的电影",
      //   });
      // }
    },
    submitPay() {
      this.centerDialogVisible = false;
      location.reload();
    },
    /* 查询购物车数据调后台接口 */
    loadCarts() {
      MyCart(localStorage.getItem("uid")).then((res) => {
        if (res.code == 200) {
          //console.log(res.data)
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].film.cover = config.API_URL + res.data[i].film.cover;
            res.data[i].arrangement.startTime = this.formatDate(
              res.data[i].arrangement.startTime
            );
            //数字转字符串
            //res.data[i].cart.id=res.data[i].cart.id+"";
          }
          this.cartList = res.data;
        }
      });
      /* 循环加字段 */
      // this.selectList = this.cartList.map((item) => {
      //   return {
      //     checked: false,
      //     cart: Object.assign({}, item), //每一项作为目标文件复制，放到cart属性里
      //   };
      // });

      /* this.loading = true;
      //查询我购物车接口
      ListCarts(this, uid).then((res) => {
      setTimeout(() => {
        this.cartList = res.data;
        for (let i = 0; i < this.cartList.length; i++) {
          this.selectList[i] = {
            checked: false,
            cart: this.cartList[i],
          };
          this.cartList[i].film.cover_show =
            config.API_URL + "/upload?id=" + this.cartList[i].film.cover;
        }
        this.loading = false;
      }, 1000);
      }); */
    },
    /* 封装选择购物车方法 */
    //   checkOut() {
    //     this.price = 0;
    //     for (let i = 0; i < this.selectList.length; i++) {
    //       if (this.selectList[i].checked) {
    //         this.price += this.selectList[i].cart.price;
    //       }
    //     }
    //     /* 如果每个复选框都手动勾选,则全选框自动勾选 */
    //     var checkedCount = 0;
    //     for (let i = 0; i < this.selectList.length; i++) {
    //       if (this.selectList[i].checked == true) {
    //         checkedCount++;
    //       }
    //     }
    //     if (checkedCount == this.selectList.length) {
    //       this.checkAll = true;
    //     } else {
    //       this.checkAll = false;
    //     }
    //     this.isIndeterminate =
    //       checkedCount > 0 && checkedCount < this.selectList.length;
    //   },
    // },
  },
  created() {
    this.loadCarts(); //页面初始化的时候数据已经加载好
  },
  mounted() {},
  computed: {
    totalall() {
      let total = 0;
      for (var i = 0; i < this.cartList.length; i++) {
        if (this.selectList.indexOf(this.cartList[i].cart.id) >= 0) {
          total += Number(this.cartList[i].cart.price);
        }
      }
      this.price = total;
      return total;
    },
  },
};
</script>
<style lang="scss" scoped>
div {
  .carShopping {
    width: 100%;
    h3 {
      letter-spacing: 1px;
      font-weight: 400;
      padding-bottom: 20px;
    }
    .box {
      .box_card {
        margin-bottom: 5px;
        .shop_car_check {
          float: left;
          line-height: 100px;
          padding-right: 20px;
        }
        .item_film_img {
          float: left;
          width: 80px;
          height: 100px;
          padding-bottom: 20px;
          padding-right: 10px;
        }
        .shop_car_info {
          float: left;
          .item_film_name {
            letter-spacing: 2px;
            font-weight: 500;
            font-size: 18px;
            padding-bottom: 13px;
          }
          .item_film_seat {
            letter-spacing: 1px;
            font-size: 12px;
            padding-bottom: 8px;
            padding-left: 10px;
            color: #91949c;
          }
          .item_film_time {
            font-size: 12px;
            padding-left: 10px;
            letter-spacing: 1px;
            color: #91949c;
            span {
              color: red;
            }
          }
        }
        .shop_car_price {
          float: right;
          color: #f34d41;
          letter-spacing: 2px;
          line-height: 100px;
          padding-right: 5px;
          .el-button {
            color: red;
            padding-left: 50px;
          }
        }
      }
      .isbottom {
        height: 60px;
        background: #e3e5e9;
        color: rgb(119, 126, 128);
        .all_checked {
          float: left;
          line-height: 60px;
          padding: 0 20px;
          font-size: 14px;
        }
        .sub_btn {
          float: right;
          /* color: #000000; */
          height: 60px;
          width: 120px;
          background: #f34d41;
          letter-spacing: 2px;
          line-height: 33px;
          text-align: center;
          font-weight: 500;
          font-size: 18px;
          color: white;
        }
        .order_footer1 {
          float: right;
          line-height: 60px;
          padding-right: 40px;
        }
      }
    }
    .el-dialog {
      .c-img {
        width: 100%;
        height: 100%;
      }
    }
  }
}
</style>