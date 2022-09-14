<template>
  <v-card height="600" class="mx-auto">
    <v-navigation-drawer permanent>
      <v-list dense nav>
        <v-list-item
          v-for="item in menuItemList"
          :key="item.title"
          link
          @click="goToRoute(item.path)"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

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
        title: "Admin",
        icon: "mdi-table",
        path: "/admin",
      },
      {
        title: "Create Movie",
        icon: "mdi-movie",
        path: "/create_movie",
      },
    ],
  }),

  created() {},

  methods: {
    goToRoute(path) {
      if (this.$route.path != path) {
        this.$router.push({ path: path });
      }
    },
  },
};
</script>

<style scoped></style>
