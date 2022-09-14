<template>
  <div>
    <v-row>
      <v-col cols="2">
        <sidebar></sidebar>
      </v-col>
      <v-col cols="10">
        <v-data-table
          :headers="headers"
          :items="movieList"
          :items-per-page="5"
          class="elevation-1"
        >
          <template v-slot:item.posterPath="{ item }">
            <v-img :src="localDomain + item.posterPath" max-width="150"></v-img>
          </template>
          <template v-slot:item.actions="{ item }">
            <v-btn class="mb-2" color="primary" fab x-small elevation="2">
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <br />
            <v-btn color="red" fab dark x-small elevation="2">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import utils from "../utils/utils";
import sidebar from "../components/sidebar.vue";

export default {
  name: "admin",

  components: { sidebar },

  data() {
    return {
      headers: [
        { text: "ID", value: "id", sortable: true },
        { text: "Poster", value: "posterPath", sortable: false },
        { text: "Title", value: "title", sortable: true },
        { text: "Budget", value: "budget", sortable: true },
        { text: "Overview", value: "overview", sortable: false },
        { text: "Type", value: "type", sortable: true },
        { text: "Adult", value: "adult" },
        { text: "CreatedAt", value: "createdAt", sortable: true },
        { text: "UpdatedAt", value: "updatedAt", sortable: false },
        { text: "Actions", value: "actions", sortable: false },
      ],
      movieList: [],
      localDomain: utils.constant.localDomain,
    };
  },

  async created() {
    const resp = await utils.http.get("/movies");
    if (resp.status === 200) {
      const data = await resp.json();
      if (data) {
        console.log(data);
        this.movieList = data;
      }
    }
  },

  methods: {},
};
</script>
