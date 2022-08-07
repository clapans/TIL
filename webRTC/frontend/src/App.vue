<template>
  <div class="container" >
    <router-view />
  </div>
<!-- <MainPageUnLoginView v-if="!currentUser"></MainPageUnLoginView>
<MainPageView  v-if="currentUser"></MainPageView> -->
</template>
<script>
import MainPageView from './views/MainPageView.vue';
import MainPageUnLoginView from './views/MainPageUnLoginView.vue';
export default {
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    methods: {
        logOut() {
            this.$store.dispatch("auth/logout");
            this.$router.push("/login");
        }
    },
    components: { MainPageUnLoginView,MainPageView }
};
</script>