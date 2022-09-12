<template>
  <v-app-bar app color="deep-purple lighten-1" dense dark>
    <a class="navtitle" href="/">Movie Theater</a>
    <v-spacer></v-spacer>
    <router-link class="mx-2 navlink" to="/">Home</router-link> |
    <router-link class="mx-2 navlink" to="/register">Register</router-link> |
    <router-link class="mx-2 navlink" to="/login">Login</router-link>
  </v-app-bar>
</template>

<script>
export default {
  name: "heading",

  data: () => ({}),
};
</script>

<style scoped>
.navlink {
  color: white !important;
  text-decoration: none;
}

.navlink:hover {
  cursor: pointer;
  text-decoration: underline;
}

.navtitle {
  color: white !important;
  text-decoration: none;
  cursor: pointer !important;
}
</style>
