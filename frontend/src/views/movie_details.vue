<template>
  <div>{{ movie_id }}</div>
</template>

<script>
export default {
  name: "movie_details",
  data() {
    return {
      movie_id: this.$route.params.id,
    };
  },
  created() {},
  methods: {},
};
</script>
