<template>
  <!-- Modal -->
<div class="modal" id="Logout" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        로그아웃하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary"  @click.prevent="logOut">Yes</button>
      </div>
    </div>
  </div>
</div>
</template>

<script>
export default {
  setup() {
    
  },
  methods: {
        logOut() {
            this.$store.dispatch("auth/logout");
            this.$router.push("/");
        },
    },
}
</script>