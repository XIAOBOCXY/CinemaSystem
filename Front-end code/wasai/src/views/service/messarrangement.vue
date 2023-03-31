<template>
  <!-- 留言框右侧 -->
  <el-main>
    <!-- 留言列表 -->
    <div
      class="cinema_item"
      v-for="(item, index) in leavingMessageList"
      :key="index"
    >
      <div style="display: flex">
        <img v-if="item.user != null" :src="item.user.avatar" alt="null" />
        <img v-else src="../../assets/img/nim.png" alt="" />
        <div>
          <div class="d2" v-if="item.user">
            {{ item.user.nickname }}
          </div>
          <div v-else class="d2">匿名用户</div>
          <div class="d3">{{ item.leavingMessage.content }}</div>
          <div class="d2">Time: {{ item.leavingMessage.createTime }}</div>
        </div>
      </div>
      <div v-if="item.leavingMessage.reply != 'null'" class="reply">
        <div class="d2">客服回复：</div>
        <div class="d3">{{ item.leavingMessage.reply }}</div>

        <el-link
          type="success"
          :disabled="isLogin == false"
          @click="handleReply(item)"
          :underline="false"
          >回复留言reply</el-link
        >
      </div>
      <el-dialog title="回复留言" :visible.sync="dialogTableVisible">
        <el-form>
          <el-form-item :label-width="formLabelWidth">
            <el-input
              v-model="reply"
              autocomplete="off"
              type="textarea"
              :rows="8"
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisibleClick()"
            >确认提交</el-button
          >
        </div>
      </el-dialog>
    </div>
  </el-main>
</template>
<script>
import { LMessage, AddLMessage } from "@/api/lmessage";
import config from "@/config.js";
export default {
  data() {
    return {
      isLogin: true,
      leavingMessageList: [], //留言列表
      dialogTableVisible: false,
      formLabelWidth: "120px",
      reply: "",
      l: {},
    };
  },
  methods: {
    /*  ----------------回复留言---------------------*/
    handleReply(item) {
      this.l = item;
      this.dialogTableVisible = true;
    },
    dialogVisibleClick() {
      // console.log(this.l);
      this.dialogTableVisible = false;
      let data = {
        id: this.l.id,
        uid: localStorage.getItem("uid"),
        reply: this.reply,
      };
      console.log("------");
      console.log(data);
      AddLMessage(data).then((res) => {
        if (res.code == 200) {
          //提交成功后，清理上一个输入的内容
          this.reply = "";
          this.QLMessage();
        }
      });
    },
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

    /*  ----------------查询客服列表---------------------*/
    QLMessage() {
      /* 4.2.1 查询留言列表 */
      LMessage().then((res) => {
        if (res.code == 200) {
          //console.log(res.data);
          for (var i = 0; i < res.data.length; i++) {
            // console.log(res.data[i]);
            console.log(res.data[i].id);
            res.data[i].user.avatar = config.API_URL + res.data[i].user.avatar;
            res.data[i].leavingMessage.createTime = this.formatDate(
              res.data[i].leavingMessage.createTime
            );
          }
          this.leavingMessageList = res.data;
          console.log(this.leavingMessageList);
        }
      });
    },
  },
  mounted() {
    this.QLMessage(); //留言消息
  },
};
</script>

<style lang="scss" scoped>
/* 留言框右侧 */
.el-main {
  width: 100%;
  background-color: #f4f4f4;
  color: #333;
  padding: 20px 70px 50px;
  min-height: 500px;
  /* 留言列表 */
  .cinema_item {
    padding-bottom: 60px;
    /* 客人头像 */
    img {
      width: 60px;
      height: 60px;
      border-radius: 30px;
      margin-right: 20px;
      margin-top: 10px;
    }
    div {
      /* 客人头像右侧文字 */
      .d2 {
        color: #9c9c9c;
        letter-spacing: 2px;
        font-size: 14px;
        line-height: 30px;
      }
      .d3 {
        letter-spacing: 2px;
        font-size: 15px;
        font-weight: 400;
        line-height: 30px;
        color: black;
      }
    }
    .reply {
      padding-left: 80px;
      padding-top: 15px;
      .d2 {
        color: #9c9c9c;
        letter-spacing: 2px;
        font-size: 14px;
        line-height: 30px;
      }
      .d3 {
        letter-spacing: 2px;
        font-size: 15px;
        font-weight: 400;
        line-height: 30px;
        color: black;
      }
    }
  }
}
</style>