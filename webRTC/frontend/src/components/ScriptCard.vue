<template>
<div class="card" style="width: 16rem; height: 12rem;">
  <div class="card-body">
    <svg data-bs-toggle="modal" data-bs-target="#delete" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
      <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
      <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
    </svg>
    <div @click="scriptDetail" style="background-color:#0742F2;">
      <p>2022.08.01</p>
      <p>주제:공원가기</p>
    </div>
  </div>
</div>
<ScriptDeleteModal></ScriptDeleteModal>
</template>

<script>
import ScriptDeleteModal from './Modal/ScriptDeleteModal.vue';
export default {
    name: "ScriptCard",
    setup() {
    },
    components: { ScriptDeleteModal },
    methods: {
        scriptDetail() {
            this.$router.push("/scriptdetail");
        },
        
    },
}
</script>

<style scoped>
p {
  color : white;
}
</style>