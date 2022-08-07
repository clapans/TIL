<template>
<MainPageNavbar></MainPageNavbar>
<!-- 나가기 -->
<svg @click="back" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-backspace" viewBox="0 0 16 16">
  <path d="M5.83 5.146a.5.5 0 0 0 0 .708L7.975 8l-2.147 2.146a.5.5 0 0 0 .707.708l2.147-2.147 2.146 2.147a.5.5 0 0 0 .707-.708L9.39 8l2.146-2.146a.5.5 0 0 0-.707-.708L8.683 7.293 6.536 5.146a.5.5 0 0 0-.707 0z"/>
  <path d="M13.683 1a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2h-7.08a2 2 0 0 1-1.519-.698L.241 8.65a1 1 0 0 1 0-1.302L5.084 1.7A2 2 0 0 1 6.603 1h7.08zm-7.08 1a1 1 0 0 0-.76.35L1 8l4.844 5.65a1 1 0 0 0 .759.35h7.08a1 1 0 0 0 1-1V3a1 1 0 0 0-1-1h-7.08z"/>
</svg>
<h1>2022.08.01</h1>
<p>공원가기</p>
<h5>Q. Tell me about what do you usually do at the park. What is your typical day at the park from beginning to end?</h5>
<p>[Script]</p>
<p>You see, as I told you, I like to go to the Han River Park. And I usually walk along
  the beautiful walking path. After walking for a while, I order some delivery food at
  the park. I usually order fried chicken by one of delivery app on my smartphone.
  We can order all kind of delivery food at the park in Korea. This is so convenient.
  You know, there are so many people. I enjoy looking that people. All they are
  different. It's fun. Yeah, that's all about my typical day at the park.
</p>
</template>

<script>
import MainPageNavbar from './MainPageNavbar.vue';
export default {
    name: "FeedbackDetail",
    setup() {
    },
    methods: {
        back() {
            this.$router.push("/feedback");
        },
    },
    components: { MainPageNavbar }
}
</script>