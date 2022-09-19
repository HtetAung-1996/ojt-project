<template>
  <div>
    <v-container>
      <v-form ref="registerForm" v-model="registerForm">
        <v-text-field
          v-model="name"
          :counter="10"
          :rules="[
            (v) => !!v || 'Required',
            (v) =>
              (v && v.length <= 10) || 'Name must be less than 10 characters',
          ]"
          label="Name"
          required
        ></v-text-field>

        <v-text-field
          v-model="email"
          :rules="[
            (v) => !!v || 'Required',
            (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid',
          ]"
          label="E-mail"
          required
        ></v-text-field>

        <v-text-field
          v-model="password"
          :counter="10"
          :rules="[
            (v) => !!v || 'Required',
            (v) =>
              (v && v.length <= 10) ||
              'Password must be less than 10 characters',
          ]"
          label="Password"
          required
        ></v-text-field>

        <v-btn
          :disabled="!registerForm"
          color="success"
          class="mr-4"
          @click="register"
        >
          <span v-if="!loading">Register</span>
          <v-progress-circular
            v-else
            indeterminate
            color="primary"
          ></v-progress-circular>
        </v-btn>

        <v-alert class="mt-3" v-show="errorAlert" dense type="error">
          Register Failed!
        </v-alert>
      </v-form>
    </v-container>
  </div>
</template>

<script>
import utils from "../utils/utils";

export default {
  name: "register",

  components: {},

  data() {
    return {
      registerForm: false,
      name: "Mg Mg",
      email: "test@gmail.com",
      password: "1111",
      errorAlert: false,
      loading: false,
    };
  },

  async created() {},

  methods: {
    async register() {
      if (this.$refs.registerForm.validate()) {
        this.errorAlert = false;
        this.loading = true;
        const resp = await utils.http.post("/user/register", {
          name: this.name,
          gmail: this.email,
          password: this.password,
        });
        if (resp.status === 200) {
          const data = await resp.json();
          if (data) {
            this.$store.commit("setLoginUser", data);
            if (data.role == "admin") {
              this.$router.push({ path: "/admin" });
            } else {
              this.$router.push({ path: "/" });
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
