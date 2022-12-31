<template>
  <div>
    <v-row>
      <!-- Sidebar -->
      <v-col cols="2">
        <sidebar_admin></sidebar_admin>
      </v-col>

      <!-- User Table -->
      <v-col cols="10">
        <v-data-table
          :headers="headers"
          :items="userList"
          :items-per-page="5"
          class="elevation-1"
        >
          <template v-slot:item.actions="{ item }">
            <!-- Update User Status Btn -->
            <v-btn
              class="mr-2"
              color="primary"
              fab
              x-small
              elevation="2"
              @click="onClickUpdateUserStatus(item)"
            >
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
          </template>
        </v-data-table>
      </v-col>
    </v-row>

    <!-- Update User Status Dialog -->
    <v-dialog v-model="updateDialog" width="400">
      <v-card>
        <!-- Dialog Heading -->
        <v-toolbar color="primary" dark>
          <div>Change User Status</div>
          <v-spacer></v-spacer>
          <v-btn icon @click="updateDialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>

        <v-card-text class="pa-4">
          <v-form ref="updateForm" v-model="updateForm">
            <!-- User ID -->
            <v-text-field
              v-model="toUpdateUser.id"
              label="User ID"
              disabled
            ></v-text-field>

            <!-- User Name -->
            <v-text-field
              v-model="toUpdateUser.name"
              label="User Name"
              disabled
            ></v-text-field>

            <!-- User Gmail -->
            <v-text-field
              v-model="toUpdateUser.gmail"
              label="User Gmail"
              disabled
            ></v-text-field>

            <!-- User Role -->
            <v-text-field
              v-model="toUpdateUser.role"
              label="User Role"
              disabled
            ></v-text-field>

            <!-- User Start Join Date -->
            <v-text-field
              v-model="toUpdateUser.startJoinDate"
              label="User Start Join Date"
              disabled
            ></v-text-field>

            <!-- User Last Join Date -->
            <v-text-field
              v-model="toUpdateUser.lastJoinDate"
              label="User Last Join Date"
              disabled
            ></v-text-field>

            <!-- User Access Count -->
            <v-text-field
              v-model="toUpdateUser.accessCount"
              label="User Access Count"
              disabled
            ></v-text-field>

            <!-- User Status -->
            <v-select
              v-model="userStatus"
              :items="userStatusList"
              :rules="[(v) => !!v || 'Required']"
              label="User Status"
              required
            >
            </v-select>

            <!-- Error Alert -->
            <v-alert class="mt-3" v-show="errorAlert" dense type="error">
              Change User Status Failed!
            </v-alert>
          </v-form>
        </v-card-text>

        <!-- Update User Status Btn -->
        <v-card-actions class="justify-end">
          <v-btn
            :disabled="!updateForm"
            color="success"
            @click="updateUserStatus"
          >
            <span v-if="!loading">Change</span>
            <v-progress-circular
              v-else
              indeterminate
              color="primary"
            ></v-progress-circular>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import utils from "../utils/utils";
import sidebar_admin from "../components/sidebar_admin.vue";

export default {
  name: "admin_user_list",
  components: { sidebar_admin },

  data() {
    return {
      headers: [
        { text: "ID", value: "id", sortable: true },
        { text: "Name", value: "name", sortable: true },
        { text: "Gmail", value: "gmail", sortable: true },
        { text: "Status", value: "status", sortable: true },
        { text: "Role", value: "role", sortable: true },
        { text: "Start Join Date", value: "startJoinDate", sortable: true },
        { text: "Last Join Date", value: "lastJoinDate", sortable: true },
        { text: "Access Count", value: "accessCount", sortable: true },
        { text: "CreatedAt", value: "createdAt", sortable: true },
        { text: "UpdatedAt", value: "updatedAt", sortable: false },
        { text: "Actions", value: "actions", sortable: false },
      ],
      userList: [],

      updateDialog: false,
      updateForm: false,
      toUpdateUser: {
        id: "",
        status: "",
      },
      errorAlert: false,
      loading: false,

      userStatus: "",
      userStatusList: [],
    };
  },

  async created() {
    await this.fetchUserStatus();
    await this.fetchUsers();
  },

  methods: {
    async fetchUsers() {
      const resp = await utils.http.get("/admin/user");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.userList = data;
        }
      }
    },

    async fetchUserStatus() {
      const resp = await utils.http.get("/admin/user_status");
      if (resp && resp.status === 200) {
        const data = await resp.json();
        if (data) {
          this.userStatusList = data;
        }
      }
    },

    onClickUpdateUserStatus(item) {
      this.updateDialog = true;
      this.toUpdateUser = Object.assign({}, item);
      this.userStatus = this.toUpdateUser.status;
    },

    async updateUserStatus() {
      if (this.$refs.updateForm.validate()) {
        this.errorAlert = false;
        this.loading = true;

        const resp = await utils.http.putFormData("/admin/user/update_status", {
          id: this.toUpdateUser.id,
          status: this.userStatus,
        });
        if (resp && resp.status === 200) {
          // Refresh User List
          await this.fetchUsers();
          this.updateDialog = false;
        } else {
          this.errorAlert = true;
        }

        this.loading = false;
      }
    },
  },
};
</script>
