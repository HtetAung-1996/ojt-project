<template>
  <div>{{ loginUser }}</div>
</template>

<script>
export default {
  name: "profile",
  data() {
    return {
      loginUser: {},
    };
  },
  created() {
    this.loginUser = this.$store.state.loginUser;
    this.$store.watch(
      () => {
        return this.$store.state.loginUser;
      },
      (newVal, oldVal) => {
        this.loginUser = newVal;
      },
      {
        deep: true,
      }
    );
  },
  methods: {},
};
</script>
