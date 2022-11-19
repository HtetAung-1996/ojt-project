<template>
  <div>
    <v-card class="mx-auto pa-3" max-width="344">
      <v-card-text>
        <div>Weclome</div>
        <p class="text-h4 text--primary">
          {{ loginUser.name }}
        </p>
        <p>Data</p>
        <div class="text--primary">
          <div v-if="loginUser.role == 'admin'">Admin</div>
          <div>{{ loginUser.gmail }}</div>
          <div>Joining Date: {{ loginUser.startJoinDate }}</div>
          <div>Visited times: {{ loginUser.accessCount }}</div>
        </div>
      </v-card-text>
      <v-btn text color="teal accent-4" @click="changePwd">
        Change Password
      </v-btn>
      <v-btn text color="teal accent-4" @click="openRecordDialog">
        See Your Movie Record
      </v-btn>
    </v-card>

    <v-dialog v-model="recordDialog" max-width="800">
      <v-card>
        <v-toolbar color="primary" dark>
          <div>Your Movie Record</div>
          <v-spacer></v-spacer>
          <v-btn icon @click="recordDialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-card-text class="pa-4">
          <v-data-table
            :headers="headers"
            :items="recordList"
            :items-per-page="5"
            class="elevation-1"
          >
          </v-data-table>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import utils from "../utils/utils";

export default {
  name: "profile",
  data() {
    return {
      loginUser: {},
      recordDialog: false,

      headers: [
        { text: "Movie ID", value: "movie.id", sortable: true },
        { text: "Movie Title", value: "movie.title", sortable: true },
        { text: "Watched At", value: "createdAt", sortable: true },
      ],
      recordList: [],
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
  methods: {
    changePwd() {
      this.$router.push({ path: "/changePwd" });
    },

    async openRecordDialog() {
      const resp = await utils.http.get("/record?userID=" + this.loginUser.id);
      if (resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.recordList = data;
        }
      }
      this.recordDialog = true;
    },
  },
};
</script>
