<template>
  <div>
    <v-container>
      <!-- Register Form -->
      <v-form ref="registerForm" v-model="registerForm">
        <!-- Name -->
        <v-text-field
          v-model="name"
          :rules="[
            (v) => !!v || '必須',
            (v) => (v && v.length <= 30) || '最大30文字以下入力してください',
          ]"
          label="名前"
          required
        ></v-text-field>

        <!-- Email -->
        <v-text-field
          v-model="email"
          class="mt-1"
          :rules="[
            (v) => !!v || '必須',
            (v) => (v && v.length <= 100) || '最大100文字以下入力してください',
            (v) =>
              /.+@.+\..+/.test(v) ||
              'メールアドレスのフォマードが正しくありません',
          ]"
          label="メール"
          required
        ></v-text-field>

        <!-- Password -->
        <v-text-field
          v-model="password"
          class="mt-1"
          :rules="[
            (v) => !!v || '必須',
            (v) => (v && v.length <= 100) || '最大100文字以下入力してください',
            (v) => /[A-Z]/.test(v) || '一文字以上大文字を含む入力してください',
          ]"
          label="パスワード"
          required
        ></v-text-field>

        <!-- Password ReEnter -->
        <v-text-field
          v-model="passwordReEnter"
          class="mt-1"
          :rules="[
            (v) => !!v || '必須',
            (v) =>
              v === password || 'パスワードと一致している値を入力してください',
          ]"
          label="パスワード再入力"
          required
        ></v-text-field>

        <!-- Register Btn -->
        <v-btn color="success" class="mt-5 mr-4" @click="register()">
          <span v-if="!loading">登録</span>
          <v-progress-circular
            v-else
            indeterminate
            color="primary"
          ></v-progress-circular>
        </v-btn>

        <!-- Error Alert -->
        <v-alert class="mt-3" v-show="errorAlert" dense type="error">
          登録処理が失敗しました
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

      // name: "",
      // email: "",
      // password: "",
      // passwordReEnter: "",
      name: "Mg Mg",
      email: "test@gmail.com",
      password: "A1111",
      passwordReEnter: "A1111",

      errorAlert: false,
      loading: false,
    };
  },

  async created() {},

  methods: {
    async register() {
      if (!this.$refs.registerForm.validate()) return;
      try {
        this.errorAlert = false;
        this.loading = true;
        // API Call
        const resp = await utils.http.post("/user/register", {
          name: this.name,
          gmail: this.email,
          password: this.password,
        });
        if (!resp) {
          this.errorAlert = true;
          return;
        }
        if (resp.status !== 200) {
          this.errorAlert = true;
          return;
        }
        const data = await resp.json();
        if (!data) {
          this.errorAlert = true;
          return;
        }
        // Store Login Info in Vuex
        this.$store.commit("setLoginUser", data);
        // If Admin -> Go to Admin
        // If User -> Go to Home
        if (data.role == "admin") {
          this.$router.push({ path: "/admin" });
          return;
        }
        this.$router.push({ path: "/" });
      } catch (error) {
        console.log(error);
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>
