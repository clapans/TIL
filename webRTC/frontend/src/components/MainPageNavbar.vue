<template>
<nav class="navbar" style="background-color:#0742F2;">
  <div class="container-fluid">
    <a href="/mainpage" class="navbar-brand ">
      <img src="../assets/logo.png" alt="" style="width:150px">
    </a>
    <button class="btn" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#Logout">Logout</button>
  </div>
</nav>
<div v-if="role==='student'" class="menu" style="background-color:white;">
  <a href="/mainpage">Script</a>
  <a href="/feedback">Feedback</a>
</div>
<div v-else class="container text-center" style="background-color:white;">
  <div class="row">
    <p class="col">Topic</p>
    <p class="col-6">Question</p>
    <p class="col">State</p>
  </div>
</div>
<LogoutModal></LogoutModal>
</template>
<script scope>
import LogoutModal from './Modal/LogoutModal.vue';
export default {
  data(){
        return{
            role:this.$store.state.auth.user.role
        }
    },
    components: { LogoutModal }
};
</script>
