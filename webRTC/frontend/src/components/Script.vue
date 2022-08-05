<template>  
<!-- Script 생성 버튼 -->
<a class="btn btn-primary" data-bs-toggle="modal" href="#SurveyModal" role="button">Script 생성</a>
<!-- script card -->
<ScriptCard ></ScriptCard>
<SurveyModal></SurveyModal>
</template>
<script>
import ScriptCard from './ScriptCard.vue';
import SurveyModal from './Modal/SurveyModal.vue';
export default {
    name: "Script",
    data() {
    return {
      scriptList:'',
    };
  },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        }
    },
    mounted() {
        if (!this.currentUser) {
            this.$router.push("/");
        }
    },
    components: { ScriptCard, SurveyModal }
};
</script>