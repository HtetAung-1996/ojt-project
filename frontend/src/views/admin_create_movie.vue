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

          <v-select
            v-model="category"
            :items="movieCategoryList"
            item-text="name"
            item-value="id"
            :rules="[(v) => !!v || 'Required']"
            label="Category"
            required
          >
          </v-select>

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
            @change="onChangePoster"
          ></v-file-input>

          <v-img
            v-if="posterPreviewPath != null"
            :src="posterPreviewPath"
            width="200"
            height="150"
            contain
          >
          </v-img>

          <v-file-input
            v-model="trailer"
            label="Trailer"
            show-size
            prepend-icon="mdi-camera"
            placeholder="Choose Trailer"
            accept="video/mp4"
            :rules="[
              (v) => !!v || 'Required',
              (v) =>
                !v ||
                v.size < 100000000 ||
                'Image size should be less than 100 MB!',
            ]"
            @change="onChangeTrailer"
          ></v-file-input>

          <video
            v-if="trailerPreviewPath != null"
            class="mb-2"
            width="100%"
            :src="trailerPreviewPath"
            controls
          ></video>

          <v-btn
            :disabled="!movieForm"
            color="success"
            class="mt-4 mr-4"
            @click="createMovie"
          >
            <span v-if="!loading">Save</span>
            <v-progress-circular
              v-else
              indeterminate
              color="primary"
            ></v-progress-circular>
          </v-btn>

          <v-alert class="mt-3" v-show="errorAlert" dense type="error">
            Create Movie Failed!
          </v-alert>
          <v-alert class="mt-3" v-show="errorAlert2" dense type="error">
            Movie with same title exists!
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
  name: "admin_create_movie",
  components: { sidebar_admin },

  data() {
    return {
      movieForm: false,
      title: "Test",
      overview: "Overview",
      budget: 1000,
      category: 1,
      adult: false,
      errorAlert: false,
      errorAlert2: false,
      loading: false,
      poster: null,
      posterPreviewPath: null,
      trailer: null,
      trailerPreviewPath: null,

      movieCategoryList: [],
    };
  },

  async created() {
    await this.fetchMovieCategories();
  },

  methods: {
    async fetchMovieCategories() {
      const resp = await utils.http.get("/admin/category");
      if (resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.movieCategoryList = data;
        }
      }
    },

    async createMovie() {
      if (this.$refs.movieForm.validate()) {
        this.errorAlert = false;
        this.loading = true;

        let titleCheckOK = false;
        const respTitleCheck = await utils.http.get(
          "/admin/movie/title/" + this.title
        );
        if (respTitleCheck.status === 200) {
          const data = await respTitleCheck.json();
          if (data) {
            this.errorAlert2 = true;
          } else {
            this.errorAlert2 = false;
            titleCheckOK = true;
          }
        } else {
          this.errorAlert2 = true;
        }

        if (titleCheckOK) {
          let respPosterData = null;
          const respPoster = await utils.http.postMedia(
            "/admin/file/create",
            this.poster,
            this.poster.type
          );
          if (respPoster.status === 200) {
            respPosterData = await respPoster.text();
          } else {
            this.errorAlert = true;
          }

          let respTrailerData = null;
          const respTrailer = await utils.http.postMedia(
            "/admin/file/create",
            this.trailer,
            this.trailer.type
          );
          if (respTrailer.status === 200) {
            respTrailerData = await respTrailer.text();
          } else {
            this.errorAlert = true;
          }

          if (respPosterData && respTrailerData) {
            const respMovie = await utils.http.post("/admin/movie/create", {
              title: this.title,
              overview: this.overview,
              budget: this.budget,
              category: { id: this.category },
              adult: this.adult,
              posterPath: respPosterData,
              trailerPath: respTrailerData,
            });
            if (respMovie.status === 200) {
              this.$router.push({ path: "/admin" });
            } else {
              this.errorAlert = true;
            }
          }
        }

        this.loading = false;
      }
    },

    onChangePoster(poster) {
      this.posterPreviewPath = URL.createObjectURL(poster);
    },

    onChangeTrailer(trailer) {
      this.trailerPreviewPath = URL.createObjectURL(trailer);
    },
  },
};
</script>
