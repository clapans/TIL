<template>
<MainPageNavbar></MainPageNavbar>
<Script v-if="role==='student'" ></Script>
<Consultant v-else></Consultant>
</template>
<script>
import Script from '@/components/Script.vue';
import MainPageNavbar from '../components/MainPageNavbar.vue';
import Consultant from '@/components/Consultant.vue';
export default {
    data(){
        return{
            role:this.$store.state.auth.user.role
        }
    },
    methods: {
        logOut() {
            this.$store.dispatch("auth/logout");
            this.$router.push("/login");
        }
    },
    components: { Script, MainPageNavbar, Consultant }
};
</script>