<template>
  <div class="seat_main">
    <!-- 上方步骤条 当前处于步骤二 -->
    <el-steps :active="2" align-center>
      <el-step title="步骤1" description="选择电影场次"></el-step>
      <el-step title="步骤2" description="选择座位号"></el-step>
      <el-step title="步骤3" description="加入购物车"></el-step>
      <el-step title="步骤4" description="结算付款"></el-step>
    </el-steps>
    <!-- 页面内容部分 -->
    <div class="seat_content">
      <!-- 侧边栏 -->
      <div class="seat_aside">
        <div style="display: flex">
          <img :src="film.cover" alt="" />
          <div>
            <div class="seat_aside_name">{{ film.name }}</div>
            <div class="seat_aside_text">类型：{{ film.type }}</div>
            <div class="seat_aside_text">地区：{{ film.region }}</div>
            <div class="seat_aside_text">时长：{{ film.duration }} 分钟</div>
          </div>
        </div>
        <div class="seat_aside_content">
          <div class="d1"><span>放映：</span>{{ arrangement.type }}</div>
          <div class="d1 colred">
            <span>开场：</span>{{ arrangement.startTime }}
          </div>
          <div class="d1"><span>散场：</span>{{ arrangement.endTime }}</div>
          <div class="d1"><span>票价：</span>￥{{ arrangement.price }}/张</div>
          <el-divider></el-divider>
          <div class="d1">
            已选座位：
            <el-tag
              class="tag"
              v-for="(item, index) in userSelectSeats"
              :key="index"
              type="danger"
              effect="plain"
              >{{ item }}号
            </el-tag>
          </div>
          <div class="d1 top">
            总计：
            <span class="colred">￥</span>
            <span class="colred pricecss">{{ cart.price }}</span>
          </div>
          <el-divider></el-divider>
          <div class="btn">
            <el-input
              class="add_cart_input"
              v-model="cart.phone"
              placeholder="请输入手机号码"
              clearable
            ></el-input>
            <el-button
              class="add_cart_btn"
              @click="submitSeat"
              type="danger"
              round
              >加入购物车</el-button
            >
          </div>
        </div>
      </div>
      <!-- 页面右侧内容 -->
      <div class="hall seat_select">
        <!-- 上方座位例子 -->
        <div class="seat_example">
          <div class="selecttable_example example">
            <span>可选座位</span>
          </div>
          <div class="sold_example example">
            <span>已售座位</span>
          </div>
          <div class="selected_example example">
            <span>已选座位</span>
          </div>
        </div>
        <div class="seats_block">
          <div class="seats_container">
            <div class="screen_container">
              <!-- 银幕和银幕中央 银幕座位背景图片 -->
              <div class="screen">荧幕中央</div>
              <!-- 中间分割线 -->
              <div class="c-screen-line"></div>
            </div>
            <!-- 座位部分 -->
            <div class="seats_wrapper">
              <div style="seats">
                <span v-for="(item, index) in seats" :key="index">
                  <!-- 不可选 0-->
                  <span v-if="item.status === 0" class="seat sold item"></span>
                  <!-- 可选 1-->
                  <span
                    v-if="item.status === 1"
                    @click="handlleSelect(index)"
                    class="seat selectable item"
                  ></span>
                  <!-- 已选 2-->
                  <span
                    v-if="item.status === 2"
                    @click="handlleDisSelect(index)"
                    class="seat selected item"
                  ></span>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { FindArrangementById, GetArrangementSeats, AddCart } from "@/api/film";
import config from "@/config.js";
export default {
  data() {
    return {
      film: {}, //电影详情
      arrangement: {}, //电影排片详情
      userSelectSeats: [], //已选的座位
      cart: {
        uid: localStorage.getItem("uid"),
        aid: localStorage.getItem("arrangementId"),

        seats: "", //提交时选择的座位
        phone: "", //提交的电话号码
        price: 0, //提交的价格
      }, //购物车
      seats: [], //原始座位
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
    /*  ----------------加入购物车---------------------*/
    submitSeat() {
      if (this.checkPhoneAnSeats()) {
        let mySeats = [];
        for (let i = 0; i < this.userSelectSeats.length; i++) {
          this.cart.seats += this.userSelectSeats[i] + "号";
          mySeats[i] = this.userSelectSeats[i];
        }
        console.log(mySeats);
        this.$message({
          message: "付款",
          type: "warning",
        });
        //加到购物车中
        //加到购物车中，座位出现重复不好判断
        //购物车中一行，座位项中只有一个座位，如果同时购买多个座位，生成多条记录
        //批量新增的
        //https://blog.csdn.net/chang100111/article/details/115664432
        //(1)阿凡达2,狮子王，1号厅 18号
        this.cart.seats = mySeats + "";
        this.cart.status = 0;
        console.log(this.cart);
        AddCart(this.cart).then((res) => {
          //代码
          if (res.code == 200) {
            console.log(res.data);
          }
        });

        //this.$router.go(-1);
        //this.$router.go(-1);
        // for (let i = 0; i < this.userSelectSeats.length; i++) {
        //   this.cart.seats += this.userSelectSeats[i] + "号";
        // }
        /* console.log(this.cart.seats); */
        /* 加入购物车接口 */
        /* CreatCart(this.cart).then((res) => {
          this.$message({
            message: "已为您添加购物车成功，请尽快付款吧",
            type: "success",
          });
          this.$router.go(-1);
        }); */
      }
    },
    /*  ----------------点击可选座位---------------------*/
    handlleSelect(index) {
      if (this.userSelectSeats.length >= 4) {
        var d = this.userSelectSeats[0] - 1;
        this.seats[d].status = 1;
        this.userSelectSeats.splice(0, 1); //删除一个，返回删除元素的数组
      }
      this.userSelectSeats.push(index + 1);
      this.seats[index].status = 2;
      this.cart.price = this.arrangement.price * this.userSelectSeats.length;
    },
    /*  ----------------点击已选座位---------------------*/
    handlleDisSelect(index) {
      this.seats[index].status = 1;
      this.userSelectSeats.splice(this.userSelectSeats.indexOf(index + 1), 1);
      this.cart.price = this.arrangement.price * this.userSelectSeats.length;
    },
    /*  ----------------校验手机号和座位---------------------*/
    checkPhoneAnSeats() {
      if (this.userSelectSeats.length == 0) {
        this.$message({
          message: "请选择要订购的座位",
          type: "warning",
        });
        return false;
      }
      if (this.cart.phone.length != 11) {
        this.$message({
          message: "请输入11位的电话号码",
          type: "warning",
        });
        return false;
      }
      return true;
    },
    /*  ----------------查询排片详情、座位情况---------------------*/
    queryInfo() {
      FindArrangementById(localStorage.getItem("arrangementId")).then((res) => {
        if (res.code == 200) {
          //console.log(res.data);
          this.film = res.data.film; //获得电影信息
          this.film.cover = config.API_URL + this.film.cover; //修改图片地址
          this.arrangement = res.data.arrangement;
          //alert(this.film);
          //修改时间显示
          this.arrangement.startTime = this.formatDate(
            this.arrangement.startTime
          );
          this.arrangement.endTime = this.formatDate(this.arrangement.endTime);
        }
        //获得对应的座位信息
        // http://localhost:8888/api/arrangement/getSeats
        GetArrangementSeats(localStorage.getItem("arrangementId")).then(
          (res) => {
            if (res.code == 200) {
              this.userselectSeats = res.data; //这是一个集合
              //console.log(res.data);
              //显示座位
              this.loadSeats();
            }
          }
        );
      });
      // /* 2.4.6 查询排片详情 */
      // FindArrangementById(this.$route.query.id).then((res) => {
      //   this.film = res.data.film;
      //   this.arrangement = res.data.arrangement;
      //   /* 2.4.7 查询排片座位情况 */
      //   GetArrangementSeats(this.$route.query.id).then((res) => {
      //     this.userselectSeats = res.data;
      //     this.loadSeats();
      //   });
      // });
    },
    /*  ----------------加载座位颜色情况---------------------*/
    loadSeats() {
      const n = this.arrangement.seatNumber; //作为总数
      let arr = new Array(n);
      for (var i = 0; i < n; i++) {
        arr[i] = { status: 1 };
        // if (this.userselectSeats.indexOf(i + 1) == -1) {
        //   arr[i] = { seat: i + 1, status: 1 }; //可选
        // } else {
        //   arr[i] = { seat: i + 1, status: 0 }; //不可选
        // }
        for (let i = 0; i < this.userselectSeats.length; i++) {
          arr[this.userselectSeats[i].seat] = { status: 0 };
        }
        this.seats = arr;
      }
    },
  },
  mounted() {
    /* 查询排片详情、座位情况接口*/
    this.queryInfo();
  },
};
</script>

<style lang="scss" scoped>
.seat_main {
  padding: 80px;
  width: 1200px;
  .el-step {
    padding-bottom: 40px;
  }
  .seat_content {
    height: 850px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    .seat_aside {
      width: 30%;
      height: 100%;
      float: left;
      background-color: #f5f6f7;
      img {
        width: 140px;
        height: 200px;
        padding: 30px;
      }
      .seat_aside_name {
        padding-top: 40px;
        padding-bottom: 10px;
        font-size: 25px;
        letter-spacing: 2px;
      }
      .seat_aside_text {
        padding-top: 8px;
        font-size: 13px;
        letter-spacing: 1px;
        color: #666666;
      }
      .seat_aside_content {
        padding: 5px 30px;
        .tag {
          margin-right: 5px;
        }
        .top {
          padding-top: 10px;
        }
        .d1 {
          font-size: 16px;
          letter-spacing: 2px;
          padding-bottom: 15px;
          span {
            color: #91949c;
          }
          .colred {
            color: #f56c6c;
          }
          .pricecss {
            font-size: 25px;
            font-weight: bold;
          }
        }
        .colred {
          color: #f56c6c;
        }

        .btn {
          .add_cart_input {
            padding-top: 20px;
            padding-bottom: 30px;
          }
          .add_cart_btn {
            width: 100%;
            height: 60px;
            border-radius: 50px;
          }
        }
      }
    }
    .seat_select {
      padding-top: 50px;
      padding-left: 8%;
    }
    .hall {
      width: 700px;
      display: inline-block;
      vertical-align: top;
      position: relative;
      .seat_example {
        margin: 30px 0 30px 118px;
        padding-left: 30px;
        .selecttable_example {
          background: url("../assets/img/seat3.png") no-repeat;
        }
        .sold_example {
          background: url("../assets/img/seat1.png") no-repeat;
        }
        .selected_example {
          background: url("../assets/img/seat2.png") no-repeat;
        }
        .example {
          display: inline-block;
          font-size: 16px;
          color: #666;
          height: 26px;
          line-height: 26px;
          padding-left: 38px;
          background-repeat: no-repeat;
          margin-right: 50px;
        }
      }
      .seats_block {
        font-size: 0;
        overflow: hidden;
        margin-left: 20px;
        .seats_container {
          margin-left: 50px;
          overflow: auto;
          position: relative;
          padding-top: 112px;
          .screen_container {
            left: 5px;
            display: inline-block;
            position: absolute;
            top: 0;
            bottom: 0;
            z-index: -1;
            .screen {
              width: 550px;
              padding-top: 50px;
              text-align: center;
              font-size: 16px;
              color: #666;
              background: url("../assets/img/seat4.png") no-repeat;
              background-position-x: center;
              position: relative;
              margin-bottom: 40px;
            }
            .c-screen-line {
              width: 0;
              border-left: 1px dashed #e5e5e5;
              position: absolute;
              top: 90px;
              bottom: 0;
              left: 50%;
            }
          }
          .seats_wrapper {
            display: inline-block;
            min-width: 550px;
            .seats {
              padding: 0 40px;
              width: 500px;
              height: 200px;
            }
            .seat {
              display: inline-block;
              font-size: 0;
              width: 30px;
              height: 26px;
              margin: 0 5px;
              background: url("../assets/img/seat3.png") no-repeat;
              background-position: 0 1px;
            }
            .sold {
              background: url("../assets/img/seat1.png") no-repeat;
            }
            .selected {
              background: url("../assets/img/seat2.png") no-repeat;
            }
            .item {
              padding-bottom: 20px;
            }
          }
        }
      }
    }
  }
}
</style>