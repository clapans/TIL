<template>  
<FeedbackCard></FeedbackCard>
</template>
<script>
import FeedbackCard from './FeedbackCard.vue';
export default {
    name: "Feedback",
    data() {
        return {
            feedbackList: "",
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
    components: { FeedbackCard }
};
</script>