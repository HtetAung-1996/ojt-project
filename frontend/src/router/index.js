import Vue from "vue";
import VueRouter from "vue-router";
import store from "../store";

import home from "../views/home.vue";
import login from "../views/login.vue";
import register from "../views/register.vue";
import admin from "../views/admin.vue";
import create_movie from "../views/create_movie.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: home,
  },
  {
    path: "/login",
    name: "login",
    component: login,
  },
  {
    path: "/register",
    name: "register",
    component: register,
  },
  {
    path: "/admin",
    name: "admin",
    component: admin,
    meta: {
      requiresAuth: true,
      requiresAdmin: true,
    },
  },
  {
    path: "/create_movie",
    name: "create_movie",
    component: create_movie,
    meta: {
      requiresAuth: true,
      requiresAdmin: true,
    },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  let loginUser = router.app.$store.getters.loginUser;
  let isLogin = router.app.$store.getters.isLogin;
  if (to.meta.requiresAuth == true && !isLogin) {
    next({ path: "/" });
  } else if (
    to.meta.requiresAuth == true &&
    to.meta.requiresAdmin == true &&
    loginUser.role != "admin"
  ) {
    next({ path: "/" });
  } else {
    next();
  }
});

export default router;
