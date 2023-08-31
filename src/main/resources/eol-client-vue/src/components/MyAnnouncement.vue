<!-- 文件路径：src\components\MyAnnouncement.vue -->


<template>
  <div>
    <el-card class="latest-announcements">
      <h3>最新公告</h3>
      <el-timeline>
        <el-timeline-item
          v-for="announcement in announcements"
          :key="announcement.id"
        >
          <h4>{{ announcement.title }}</h4>
          <p>{{ announcement.content }}</p>
          <p>{{ formatDate(announcement.createTime) }}</p>
        </el-timeline-item>
      </el-timeline>
    </el-card>
  </div>
</template>

<script>
import { getLatestAnnouncement } from "@/api/announcement";

export default {
  name: "LatestAnnouncements",
  data() {
    return {
      announcements: []
    };
  },
  methods: {
    fetchData() {
      getLatestAnnouncement().then(response => {
        this.announcements = response.data;
      });
    },
    formatDate(date) {
      const d = new Date(date);
      const year = d.getFullYear();
      const month = (d.getMonth() + 1).toString().padStart(2, "0");
      const day = d.getDate().toString().padStart(2, "0");
      const hour = d.getHours().toString().padStart(2, "0");
      const minute = d.getMinutes().toString().padStart(2, "0");
      const second = d.getSeconds().toString().padStart(2, "0");
      return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
    }
  },
  mounted() {
    this.fetchData();
  }
};
</script>

<style scoped>
.latest-announcements {
  max-width: 100%;
}
</style>
