<template>
  <!-- Modal -->
<div class="modal" id="Feedback" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        대기중인 강사와 연결하시겠습니까?
      </div>
      <div class="modal-footer">
        <!-- <a href="https://i7b202.p.ssafy.io:8443/?">
          <button type="button" class="btn btn-primary"  @click="feedBack">Yes</button>
        </a> -->
        <button type="button" class="btn btn-primary"  @click="feedBack">Yes</button>
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
        feedBack() {
            this.$router.push("/webrtcstudent");
        },
    },
}
</script>
