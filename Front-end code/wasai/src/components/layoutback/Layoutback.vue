<template>
  <div>
    <div class="layout">
      <!-- 左侧导航区域 -->
      <Aside class="aside" :isCollapse="isCollapse" />
      <!-- 右侧内容区域 -->
      <Content
        class="content"
        :class="{ isActive: isCollapse }"
        @changeCollapse="changeCollapse"
        :isCollapse="isCollapse"
      />
    </div>
  </div>
</template>

<script>
import Content from "@/components/layoutback/Content.vue";
import Aside from "@/components/layoutback/Aside.vue";
export default {
  name: "Layoutback",
  components: {
    Content,
    Aside,
  },
  data() {
    return {
      isCollapse: false,
    };
  },
  methods: {
    changeCollapse() {
      this.isCollapse = !this.isCollapse;
    },
  },
};
</script>

<style  lang="scss" scoped>
.layout {
  .aside {
    position: fixed;
    top: 0;
    bottom: 0;
    background-color: #34495e;
  }
  .content {
    margin-left: 200px;
  }
  .isActive {
    margin-left: 64px;
  }
}
</style>