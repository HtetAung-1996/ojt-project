<template>
  <div>
    <v-row>
      <v-col cols="2">
        <sidebar_admin></sidebar_admin>
      </v-col>

      <v-col cols="10">
        <v-form class="px-10" ref="movieForm" v-model="movieForm">
          <v-text-field
            v-model="title"
            :counter="50"
            :rules="[
              (v) => !!v || 'Required',
              (v) =>
                (v && v.length <= 50) ||
                'Title must be less than 50 characters',
            ]"
            label="Title"
            required
          ></v-text-field>

          <v-text-field
            v-model="overview"
            :counter="1000"
            :rules="[
              (v) => !!v || 'Required',
              (v) =>
                (v && v.length <= 1000) ||
                'Overview must be less than 1000 characters',
            ]"
            label="Overview"
            required
          ></v-text-field>

          <v-text-field
            v-model="budget"
            type="number"
            suffix="MMK"
            max="999999"
            min="1"
            :rules="[
              (v) => !!v || 'Required',
              (v) =>
                (v && v > 0 && v <= 999999) ||
                'Buget must be between 0 and 999999 MMK',
            ]"
            label="Budget"
            required
          ></v-text-field>

          <v-text-field
            v-model="type"
            :rules="[(v) => !!v || 'Required']"
            label="Type"
            required
          ></v-text-field>

          <v-checkbox v-model="adult" label="Adult"></v-checkbox>

          <v-file-input
            v-model="poster"
            label="Poster"
            show-size
            prepend-icon="mdi-camera"
            placeholder="Choose Poster Image"
            accept="image/png, image/jpeg"
            :rules="[
              (v) => !!v || 'Required',
              (v) =>
                !v ||
                v.size < 10000000 ||
                'Image size should be less than 10 MB!',
            ]"
          ></v-file-input>

          <v-btn
            :disabled="!movieForm"
            color="success"
            class="mr-4"
            @click="createMovie"
          >
            <span v-if="!loading">Login</span>
            <v-progress-circular
              v-else
              indeterminate
              color="primary"
            ></v-progress-circular>
          </v-btn>

          <v-alert class="mt-3" v-show="errorAlert" dense type="error">
            Create Movie Failed!
          </v-alert>
        </v-form>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import sidebar_admin from "../components/sidebar_admin.vue";
import utils from "../utils/utils";

export default {
  name: "create_movie",
  components: { sidebar_admin },

  data() {
    return {
      movieForm: false,
      title: "Test",
      overview: "Overview",
      budget: 1000,
      type: "Adventure",
      adult: false,
      errorAlert: false,
      loading: false,
      poster: null,
    };
  },

  methods: {
    async createMovie() {
      if (this.$refs.movieForm.validate()) {
        this.errorAlert = false;
        this.loading = true;

        const respPoster = await utils.http.postImg(
          "/admin/movie/create/poster",
          this.poster,
          this.poster.type
        );
        if (respPoster.status === 200) {
          const respPosterData = await respPoster.text();
          if (respPosterData) {
            const respMovie = await utils.http.post("/admin/movie/create", {
              title: this.title,
              overview: this.overview,
              budget: this.budget,
              type: this.type,
              adult: this.adult,
              posterPath: respPosterData,
            });
            if (respMovie.status === 200) {
              this.$router.push({ path: "/admin" });
            } else {
              this.errorAlert = true;
            }
          }
        } else {
          this.errorAlert = true;
        }

        this.loading = false;
      }
    },
  },
};
</script>
