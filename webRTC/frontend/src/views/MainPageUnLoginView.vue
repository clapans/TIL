<template>
<nav class="navbar" style="background-color:#0742F2;">
  <div class="container-fluid">
    <a href="/" class="navbar-brand">
      <img src="../assets/logo.png" alt="" style="width:150px">
    </a>
    <router-link to="/login" class="nav-link">
      <button class="btn" style="color:white; background-color:#F2CB05;">Login</button>
    </router-link>
  </div>
</nav>
<SiteIntroduce></SiteIntroduce>
</template>
<script>
import SiteIntroduce from "../components/SiteIntroduce.vue"
export default {
    data() {
        return {
            isHidden: false,
        };
    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    methods: {
        logOut() {
            this.$store.dispatch("auth/logout");
            this.$router.push("/login");
        },
    },
    components : { SiteIntroduce }
};
</script>