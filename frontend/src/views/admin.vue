<template>
  <div>
    <v-row>
      <v-col cols="2">
        <sidebar_admin></sidebar_admin>
      </v-col>
      <v-col cols="10">
        <v-data-table
          :headers="headers"
          :items="movieList"
          :items-per-page="5"
          class="elevation-1"
        >
          <template v-slot:item.posterPath="{ item }">
            <v-img
              :src="localDomain + item.posterPath"
              width="200"
              height="150"
              contain
            ></v-img>
          </template>
          <template v-slot:item.actions="{ item }">
            <v-btn
              class="mr-2"
              color="primary"
              fab
              x-small
              elevation="2"
              @click="onClickUpdateMovie(item)"
            >
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn
              color="red"
              fab
              dark
              x-small
              elevation="2"
              @click="
                deleteDialog = true;
                toDeleteMovie = item;
              "
            >
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </template>
        </v-data-table>
      </v-col>
    </v-row>

    <v-dialog v-model="deleteDialog" width="400">
      <v-card>
        <v-toolbar color="primary" dark>
          <div>Delete This Movie?</div>
          <v-spacer></v-spacer>
          <v-btn icon @click="deleteDialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="pa-4">
          <v-row dense>
            <v-col cols="3" class="font-weight-bold">ID</v-col>
            <v-col cols="7">{{ toDeleteMovie.id }}</v-col>
            <v-col cols="3" class="font-weight-bold">Title</v-col>
            <v-col cols="7">{{ toDeleteMovie.title }}</v-col>
          </v-row>
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn color="red" dark @click="deleteMovie()">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="updateDialog" width="500">
      <v-card>
        <v-toolbar color="primary" dark>
          <div>Update This Movie?</div>
          <v-spacer></v-spacer>
          <v-btn
            icon
            @click="
              updateDialog = false;
              toUpdateMovie.poster = null;
            "
          >
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="pa-4">
          <v-form ref="movieForm" v-model="movieForm">
            <v-text-field
              v-model="toUpdateMovie.title"
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
              v-model="toUpdateMovie.overview"
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
              v-model="toUpdateMovie.budget"
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
              v-model="toUpdateMovie.category"
              :items="movieCategoryList"
              item-text="name"
              item-value="id"
              :rules="[(v) => !!v || 'Required']"
              label="Category"
              required
            >
            </v-select>

            <v-checkbox
              v-model="toUpdateMovie.adult"
              label="Adult"
            ></v-checkbox>

            <v-file-input
              v-model="toUpdateMovie.poster"
              label="Poster"
              show-size
              prepend-icon="mdi-camera"
              placeholder="Choose Poster Image"
              accept="image/png, image/jpeg"
              :rules="[
                (v) =>
                  !v ||
                  v.size < 10000000 ||
                  'Image size should be less than 10 MB!',
              ]"
              @change="onChangePoster"
            ></v-file-input>

            <v-img
              v-if="posterPreviewPath == null"
              :src="localDomain + toUpdateMovie.posterPath"
              width="200"
              height="150"
              contain
            >
            </v-img>

            <v-img
              v-if="posterPreviewPath != null"
              :src="posterPreviewPath"
              width="200"
              height="150"
              contain
            >
            </v-img>

            <v-file-input
              v-model="toUpdateMovie.trailer"
              label="Trailer"
              show-size
              prepend-icon="mdi-camera"
              placeholder="Choose Trailer"
              accept="video/mp4"
              :rules="[
                (v) =>
                  !v ||
                  v.size < 100000000 ||
                  'Image size should be less than 100 MB!',
              ]"
              @change="onChangeTrailer"
            ></v-file-input>

            <video
              v-if="trailerPreviewPath == null"
              class="mb-2"
              width="100%"
              :src="localDomain + toUpdateMovie.trailerPath"
              controls
            ></video>

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
              class="mr-4"
              @click="updateMovie"
            >
              <span v-if="!loading">Update</span>
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
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import utils from "../utils/utils";
import sidebar_admin from "../components/sidebar_admin.vue";

export default {
  name: "admin",

  components: { sidebar_admin },

  data() {
    return {
      localDomain: utils.constant.localDomain,

      headers: [
        { text: "ID", value: "id", sortable: true },
        { text: "Poster", value: "posterPath", sortable: false },
        { text: "Title", value: "title", sortable: true },
        { text: "Budget", value: "budget", sortable: true },
        { text: "Overview", value: "overview", sortable: false },
        { text: "Category", value: "category.name", sortable: true },
        { text: "Adult", value: "adult" },
        { text: "CreatedAt", value: "createdAt", sortable: true },
        { text: "UpdatedAt", value: "updatedAt", sortable: false },
        { text: "Actions", value: "actions", sortable: false },
      ],
      movieList: [],

      deleteDialog: false,
      toDeleteMovie: {},

      updateDialog: false,
      movieForm: false,
      toUpdateMovie: {
        title: "",
        overview: "",
        budget: 0,
        category: 1,
        adult: false,
        posterPath: "",
        poster: null,
        trailerPath: "",
        trailer: null,
      },
      posterPreviewPath: null,
      trailerPreviewPath: null,

      errorAlert: false,
      loading: false,

      movieCategoryList: [],
    };
  },

  async created() {
    await this.fetchMovieCategories();
    await this.fetchMovies();
  },

  methods: {
    async fetchMovies() {
      const resp = await utils.http.get("/movie");
      if (resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.movieList = data;
        }
      }
    },

    async fetchMovieCategories() {
      const resp = await utils.http.get("/admin/category");
      if (resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.movieCategoryList = data;
        }
      }
    },

    async deleteMovie() {
      const resp = await utils.http.del(
        "/admin/movie/delete/" + this.toDeleteMovie.id
      );
      if (resp.status === 200) {
        this.deleteDialog = false;
        await this.fetchMovies();
      } else {
        this.errorAlert = true;
      }
    },

    onClickUpdateMovie(item) {
      this.updateDialog = true;
      this.toUpdateMovie = Object.assign({}, item);
      this.toUpdateMovie.poster = null;
      this.toUpdateMovie.trailer = null;
      this.posterPreviewPath = null;
      this.trailerPreviewPath = null;
    },

    async updateMovie() {
      if (this.$refs.movieForm.validate()) {
        this.errorAlert = false;
        this.loading = true;

        let posterPath = this.toUpdateMovie.posterPath;
        let trailerPath = this.toUpdateMovie.trailerPath;

        if (this.toUpdateMovie.poster != null) {
          const respPoster = await utils.http.putMedia(
            "/admin/file/update",
            this.toUpdateMovie.poster,
            this.toUpdateMovie.poster.type,
            this.toUpdateMovie.posterPath
          );
          if (respPoster.status === 200) {
            posterPath = await respPoster.text();
          } else {
            console.debug("Poster Update Failed");
          }
        }

        if (this.toUpdateMovie.trailer != null) {
          const respTrailer = await utils.http.putMedia(
            "/admin/file/update",
            this.toUpdateMovie.trailer,
            this.toUpdateMovie.trailer.type,
            this.toUpdateMovie.trailerPath
          );
          if (respTrailer.status === 200) {
            trailerPath = await respTrailer.text();
          } else {
            console.debug("Trailer Update Failed");
          }
        }

        const respMovie = await utils.http.put(
          "/admin/movie/update/" + this.toUpdateMovie.id,
          {
            title: this.toUpdateMovie.title,
            overview: this.toUpdateMovie.overview,
            budget: this.toUpdateMovie.budget,
            category: this.toUpdateMovie.category,
            adult: this.toUpdateMovie.adult,
            posterPath: posterPath,
            trailerPath: trailerPath,
          }
        );
        if (respMovie.status === 200) {
          this.toUpdateMovie.poster = null;
          this.updateDialog = false;
          await this.fetchMovies();
        } else {
          this.errorAlert = true;
        }

        this.loading = false;
      }
    },

    onChangePoster(poster) {
      this.posterPreviewPath = URL.createObjectURL(poster);
    },

    onChangeTrailer(trailer) {
      console.log(trailer);
      this.trailerPreviewPath = URL.createObjectURL(trailer);
    },
  },
};
</script>
