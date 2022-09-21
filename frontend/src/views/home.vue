<template>
  <div>
    <v-row>
      <v-col cols="2">
        <v-card height="600" class="mx-auto">
          <v-navigation-drawer permanent>
            <v-list dense nav>
              <v-list-item
                v-for="(cat, index) in movieCategoryList"
                link
                @click="onClickCategory(cat)"
              >
                <v-list-item-content>
                  <v-list-item-title>{{ cat.name }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-navigation-drawer>
        </v-card>
      </v-col>

      <v-col cols="10">
        <v-row>
          <v-col cols="3" v-for="(movie, index) in movieList">
            <v-card @click="goToMovieDetails(movie)">
              <v-card-text>
                <v-img
                  :src="localDomain + movie.posterPath"
                  max-height="250"
                  contain
                ></v-img>
                <div>{{ movie.title }}</div>
                <div>{{ movie.budget }}</div>
                <div>{{ movie.adult }}</div>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import utils from "../utils/utils";

export default {
  name: "home",

  components: {},

  data() {
    return {
      localDomain: utils.constant.localDomain,

      catList: [],
      movieList: [],

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

    goToMovieDetails(movie) {
      this.$router.push({
        path: "/movie_details/" + movie.id,
      });
    },

    async onClickCategory(cat) {
      const resp = await utils.http.get("/movie/category/" + cat.id);
      if (resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.movieList = data;
        }
      }
    },
  },
};
</script>
