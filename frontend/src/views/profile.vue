<template>
  <div>           
<v-card
  class="mx-auto"
  max-width="344"
>
  <v-card-text>
    <div>Weclome</div>
    <p class="text-h4 text--primary">
      {{loginUser.name}}
    </p>
    <p>Data </p>
    <div class="text--primary">
      <div v-if="loginUser.role == 'admin'">Admin</div>
      <div>  {{loginUser.gmail}}</div>
      <div> Joining Date: {{loginUser.startJoinDate}}</div>
      <div> Visited times: {{loginUser.accessCount}}</div>        
    </div>
  </v-card-text>
  <v-card-actions>
    <v-btn
      text
      color="teal accent-4"
      @click="changePwd"
    >
      Change Password
    </v-btn>
  </v-card-actions>   
</v-card>


  
  </div>
</template>

<script>
export default {
  name: "profile",
  data() {
    return {
      loginUser: {},
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
    changePwd(){
      this.$router.push({ path: "/changePwd" });
    },


  }
};
</script>