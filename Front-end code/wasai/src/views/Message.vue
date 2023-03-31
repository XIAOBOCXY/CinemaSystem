<template>
  <!-- 整个界面 -->
  <div class="message_box">
    <!--  留言框 -->
    <el-container>
      <!-- 留言框左侧 -->
      <el-aside width="400px">
        <h2>客服人员</h2>
        <!-- 客服列表 -->
        <div
          class="message_item"
          v-for="(item, index) in isWorkList"
          :key="index"
        >
          <!-- 客服头像 -->
          <img :src="item.avatar" alt="" />
          <!-- 客服头像右侧文字 -->
          <div class="left_topright">
            <p>名字：{{ item.nickname }}</p>
            <p>性别：{{ item.gender }}</p>
            <p>ID：{{ item.id }}</p>
          </div>
          <!-- 客服头像下方文字链接 -->
          <div class="left_bottom">
            <el-button type="text" @click="handleListEvaluate(item)"
              >我的评价</el-button
            >
            <el-button
              type="text"
              :disabled="isLogin == false"
              @click="handleEvaluate(item)"
              >评价客服</el-button
            >
          </div>
          <el-dialog title="客服评价" :visible.sync="dialogTableVisible2">
            <el-form :model="evaluate"
              ><el-form-item label="满意程度" :label-width="formLabelWidth">
                <el-select v-model="evaluate.type" placeholder="请选择满意程度">
                  <el-option label="满意" value="满意"></el-option>
                  <el-option label="不满意" value="不满意"></el-option>
                  <el-option label="非常满意" value="非常满意"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="评价内容" :label-width="formLabelWidth">
                <el-input
                  v-model="evaluate.content"
                  autocomplete="off"
                  type="textarea"
                  :rows="8"
                ></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogTableVisible2 = false">取 消</el-button>
              <el-button type="primary" @click="dialogVisible2Click(item)"
                >确 定</el-button
              >
            </div>
          </el-dialog>
        </div>
      </el-aside>

      <!-- 留言框右侧 -->
      <el-main>
        <h2>影院留言</h2>
        <!-- 留言列表 -->
        <div
          class="cinema_item"
          v-for="(item, index) in leavingMessageList"
          :key="index"
        >
          <div style="display: flex">
            <img v-if="item.user != null" :src="item.user.avatar" alt="null" />
            <img v-else src="../assets/img/nim.png" alt="" />
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
          </div>
        </div>

        <!-- 右下方添加留言按钮 -->
        <div class="right_bottom">
          <el-button
            type="success"
            @click="handleLeaving"
            :disabled="isLogin == false"
            icon="el-icon-share"
            >添加留言</el-button
          >
        </div>
      </el-main>
    </el-container>
    <!-- 我的评价弹出框 -->
    <el-dialog title="我的评价" :visible.sync="dialogTableVisible1">
      <el-table :data="viewpointTable">
        <el-table-column
          property="createTime"
          label="日期"
          width="250"
        ></el-table-column>
        <el-table-column
          property="content"
          label="评价"
          width="320"
        ></el-table-column>
        <el-table-column property="type" width="150">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.type === '非常满意'" type="success"
              >非常满意</el-tag
            >
            <el-tag v-if="scope.row.type === '满意'" type="info">满意</el-tag>
            <el-tag v-if="scope.row.type === '不满意'" type="danger"
              >不满意</el-tag
            >
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 客服评价弹出框 -->
    <!-- <el-dialog title="客服评价" :visible.sync="dialogTableVisible2">
      <el-form :model="evaluate"
        ><el-form-item label="满意程度" :label-width="formLabelWidth">
          <el-select v-model="evaluate.type" placeholder="请选择满意程度">
            <el-option label="满意" value="满意"></el-option>
            <el-option label="不满意" value="不满意"></el-option>
            <el-option label="非常满意" value="非常满意"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评价内容" :label-width="formLabelWidth">
          <el-input
            v-model="evaluate.content"
            autocomplete="off"
            type="textarea"
            :rows="8"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible2Click(item)"
          >确 定</el-button
        >
      </div>
    </el-dialog> -->

    <!-- 添加留言弹出框 -->
    <el-dialog title="添加留言" :visible.sync="dialogTableVisible3">
      <el-form :model="evaluate">
        <el-form-item :label-width="formLabelWidth">
          <el-input
            v-model="evaluate.content"
            autocomplete="off"
            type="textarea"
            :rows="8"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible3 = false">取 消</el-button>
        <el-button type="primary" @click="dialogTableVisible3 = false"
          >提交留言</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
// import { SearchServicesWorkers } from "@/api/worker";
// import { SearchMessages } from "@/api/ser";
import {
  WorkList,
  MyCritic,
  LMessage,
  MyCritic2,
  AddEvaluate,
} from "@/api/lmessage";
import config from "@/config.js";
export default {
  data() {
    return {
      isLogin: true,
      isWorkList: [], //客服列表
      leavingMessageList: [], //留言列表
      //isUser: [],
      // leavingMessage: {
      //   uid: localStorage.getItem("uid"),
      //   reply: "null",
      //   content: "",
      //   createAt: "",
      // },
      viewpointTable: [
        // {
        //   createAt: "2022-06-05 16:26:56",
        //   content: "XXXX",
        //   type: "非常满意",
        // },
        // {
        //   createAt: "2022-05-23 15:25:36",
        //   content: "XXXX",
        //   type: "满意",
        // },
        // {
        //   createAt: "2022-06-05 16:26:56",
        //   content: "XXXX",
        //   type: "不满意",
        // },
      ],
      dialogTableVisible1: false,
      dialogTableVisible2: false,
      dialogTableVisible3: false,
      evaluate: {
        type: "",
        content: "",
        /*         date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "", */
      },
      formLabelWidth: "120px",
      a: {},
    };
  },
  /* mounted() {
    if (localStorage.getItem("uid")) {
      this.isLogin = true;
    }
    this.queryLeavingMessage();
    this.queryWorker();
  }, */
  methods: {
    dialogVisible2Click(item) {
      console.log(item);
      //console.log("dialogVisible2Click(item)");
      this.dialogTableVisible2 = false;
      item.dialogVisible2 = false; //关闭窗口
      //console.log(item.id);
      let data = {
        wid: this.a.id,
        uid: localStorage.getItem("uid"),
        content: this.evaluate.content,
        type: this.evaluate.type,
        updateTime: new Date(),
      };
      console.log(data);
      AddEvaluate(data).then((res) => {
        if (res.code == 200) {
          //提交成功后，清理上一个输入的内容
          this.evaluate.type = "";
          this.evaluate.content = "";
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
    /*  ----------------我的评价---------------------*/
    handleListEvaluate(item) {
      this.dialogTableVisible1 = true;
      //item.id;
      MyCritic2(localStorage.getItem("uid"), item.id).then((res) => {
        if (res.code == 200) {
          //console.log(res.data);
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].createTime = this.formatDate(res.data[i].createTime);
          }
          this.viewpointTable = res.data;
        }
      });
    },
    /*  ----------------评价客服---------------------*/
    handleEvaluate(item) {
      item.id = item.id;
      this.a = item;
      //console.log("handleEvaluate(item)");
      //console.log(item);
      this.dialogTableVisible2 = true;
    },
    /*  ----------------添加留言---------------------*/
    handleLeaving() {
      this.dialogTableVisible3 = true;
    },
    /* submitLeaving() {
      CreatingLeavingMessage(this.leavingMessage).then((res) => {
        if (res.success) {
          this.dialogFormVisible2 = false;
          this.$message({
            message: "感谢您对哇塞电影留下的宝贵意见",
            type: "success",
          });
          this.queryLeavingMessage();
        }
      });
    }, */
    /*  ----------------查询客服列表---------------------*/
    QWorkList() {
      /* 3.5.1.1 查询员工列表 */
      WorkList().then((res) => {
        if (res.code == 200) {
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].avatar = config.API_URL + res.data[i].avatar;
          }
          this.isWorkList = res.data;
        }
        console.log(this.isWorkList);
      });
    },
    /*  ----------------查询我的评论列表---------------------*/
    QMyCritic() {
      MyCritic(localStorage.getItem("uid")).then((res) => {
        if (res.code == 200) {
          this.viewpointTable = res.data;
          //console.log(res.data);
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].createTime = this.formatDate(res.data[i].createTime);
          }
          //console.log(res.data);
        }
      });
    },
    /*  ----------------查询客服列表---------------------*/
    QLMessage() {
      /* 4.2.1 查询留言列表 */
      LMessage().then((res) => {
        if (res.code == 200) {
          //console.log(res.data);
          for (var i = 0; i < res.data.length; i++) {
            res.data[i].user.avatar = config.API_URL + res.data[i].user.avatar;
            res.data[i].leavingMessage.createTime = this.formatDate(
              res.data[i].leavingMessage.createTime
            );
          }
          this.leavingMessageList = res.data;
        }
      });
    },
  },
  mounted() {
    // /* 查询客服列表接口 */
    // this.queryserviceworkers();
    // /* 查询留言列表接口 */
    // this.querymessage();
    this.QWorkList(); //客服人员列表
    this.QMyCritic(); //我的评论
    this.QLMessage(); //留言消息
  },
};
</script>

<style lang="scss" scoped>
/* 整个界面 */
.message_box {
  color: #ffffff;
  /*  padding: 40px; */
  overflow: auto;
  width: 1480px;
  margin: 30px 0;
  float: right;
  /* 留言框 */
  .el-container {
    /*  留言框左侧 */
    .el-aside {
      width: 800px;
      background-color: rgb(43, 49, 54);
      color: #333;
      padding: 20px 50px 40px;
      min-height: 500px;
      /* 客服人员文字 */
      h2 {
        color: #eee;
        padding-bottom: 40px;
        letter-spacing: 1px;
      }
      /* 客服列表 */
      .message_item {
        margin-bottom: 40px;
        padding-bottom: 10px;
        border-bottom: 1px dashed rgba(255, 255, 255, 0.3);
        img {
          width: 76px;
          height: 76px;
          border-radius: 50%;
          margin-right: 20px;
          float: left;
        }
        /* 客服头像右侧文字 */
        .left_topright {
          /* float: left; */
          p {
            letter-spacing: 2px;
            line-height: 30px;
            color: #afbac4;
            font-size: 14px;
          }
        }
        .left_bottom {
          clear: both;
          .el-button {
            padding: 10px 30px 10px 0;
            margin-left: 20px;
            color: rgb(111, 174, 233);
          }
        }
      }
    }
    /* 留言框右侧 */
    .el-main {
      background-color: #353b43;
      color: #333;
      padding: 20px 70px 50px;
      min-height: 500px;
      /* 影院留言文字 */
      h2 {
        color: #eee;
        padding-bottom: 40px;
        letter-spacing: 1px;
      }
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
            color: #afbac4;
            letter-spacing: 2px;
            font-size: 14px;
            line-height: 30px;
          }
          .d3 {
            letter-spacing: 2px;
            font-size: 15px;
            font-weight: 400;
            line-height: 30px;
            color: #ffffff;
          }
        }
        .reply {
          padding-left: 80px;
          padding-top: 15px;
          .d2 {
            color: #afbac4;
            letter-spacing: 2px;
            font-size: 14px;
            line-height: 30px;
          }
          .d3 {
            letter-spacing: 2px;
            font-size: 15px;
            font-weight: 400;
            line-height: 30px;
            color: #ffffff;
          }
        }
      }
      .right_bottom {
        float: right;
      }
    }
  }
}
</style>