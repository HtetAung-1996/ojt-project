<template>
  <v-card class="mx-auto">
    <v-navigation-drawer permanent>
      <v-list dense nav>
        <v-list-item
          v-for="item in menuItemList"
          :key="item.title"
          link
          @click="goToRoute(item.path)"
        >
          <!-- Icon -->
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <!-- Title -->
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  name: "sidebar_admin",

  data: () => ({
    menuItemList: [
      {
        title: "ムービー一覧",
        icon: "mdi-table",
        path: "/admin",
      },
      {
        title: "ムービー作成",
        icon: "mdi-movie",
        path: "/admin/create_movie",
      },
      {
        title: "ユーザー一覧",
        icon: "mdi-account-supervisor ",
        path: "/admin/user_list",
      },
      {
        title: "ムービー履歴",
        icon: "mdi-account-supervisor ",
        path: "/admin/record_list",
      },
    ],
  }),

  created() {},

  methods: {
    goToRoute(path) {
      // If Current Path is same with Clicked Path, No Go to Route
      if (this.$route.path != path) {
        this.$router.push({ path: path });
      }
    },
  },
};
</script>

<style scoped></style>
