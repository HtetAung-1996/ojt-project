<template>
  <v-app>
    <v-main>
      <heading></heading>
      <router-view class="mx-3 my-2" />
    </v-main>
  </v-app>
</template>

<script>
import heading from "./components/heading.vue";

export default {
  name: "App",

  components: {
    heading,
  },

  data: () => ({}),
};
</script>
