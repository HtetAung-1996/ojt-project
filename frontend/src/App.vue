<template>
  <v-app>
    <v-main>
      <heading></heading>
      <v-container>
        <router-view />
      </v-container>
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

  data: () => ({
    //
  }),
};
</script>
