<template>
  <v-app-bar app color="deep-purple lighten-1" dense dark>
    <a class="navtitle" href="/">ムービー</a>

    <v-spacer></v-spacer>

    <!-- Home -->
    <router-link class="mx-2 navlink" to="/">ホーム</router-link>

    <!-- Register -->
    <span v-if="!isLogin">|</span>
    <router-link v-if="!isLogin" class="mx-2 navlink" to="/register"
      >アカウント登録</router-link
    >

    <!-- Login -->
    <span v-if="!isLogin">|</span>
    <router-link v-if="!isLogin" class="mx-2 navlink" to="/login"
      >ログイン</router-link
    >

    <!-- Profile -->
    <span v-if="isLogin && loginUser.role !== 'admin'">|</span>
    <router-link
      v-if="isLogin && loginUser.role !== 'admin'"
      class="mx-2 navlink"
      to="/profile"
      >プロフィール</router-link
    >

    <!-- Admin -->
    <span v-if="isLogin && loginUser.role === 'admin'">|</span>
    <router-link
      v-if="isLogin && loginUser.role === 'admin'"
      class="mx-2 navlink"
      to="/admin"
      >管理画面</router-link
    >

    <!-- Login out -->
    <span v-if="isLogin">|</span>
    <a v-if="isLogin" class="mx-2 navlink" @click="logout()">ログアウト</a>
  </v-app-bar>
</template>

<script>
export default {
  name: "heading",

  data: () => ({
    loginUser: {},
    isLogin: false,
  }),

  created() {
    // Login User
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

    // IsLogin
    this.isLogin = this.$store.state.isLogin;
    this.$store.watch(
      () => {
        return this.$store.state.isLogin;
      },
      (newVal, oldVal) => {
        this.isLogin = newVal;
      },
      {
        deep: true,
      }
    );
  },

  methods: {
    logout() {
      this.$store.commit("logout");
      // If current path is not home page, Go to home page
      if (this.$route.path != "/") {
        this.$router.push({ path: "/" });
      }
    },
  },
};
</script>

<style scoped>
.navlink {
  color: white !important;
  text-decoration: none;
}

.navlink:hover {
  cursor: pointer;
  text-decoration: underline;
}

.navtitle {
  color: white !important;
  text-decoration: none;
  cursor: pointer !important;
}
</style>
