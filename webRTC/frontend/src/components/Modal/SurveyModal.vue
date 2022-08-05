<template>
  <!-- Survey 모달 -->
<div class="modal" id="SurveyModal" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
  <div class="modal-dialog modal-xl modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalToggleLabel">Survey</h5>
        <!-- select level -->
        <div class="d-flex flex-column">
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          <span></span>
          <select v-model="level" class="form-select" aria-label="Default select example" style="width:200px">
            <option disabled value="">Open this select Level</option>
            <option value="AL">AL</option>
            <option value="IH">IH</option>
            <option value="IM">IM</option>
            <option value="IL">IL</option>
          </select>
        </div>
      </div>
      <p>Topic : {{ topic }} / Level : {{ level }}</p>
      <div class="modal-body">
        <div class="leisure-activities">
          <h1>여가활동</h1>
          <div class="form-check form-check-inline">
            <input v-model="topic" class="form-check-input" type="radio" name="inlineRadioOptions" id="leisure1" value="영화보기">
            <label class="form-check-label" for="영화보기">영화보기</label>
          </div>
          <div class="form-check form-check-inline">
            <input v-model="topic" class="form-check-input" type="radio" name="inlineRadioOptions" id="leisure2" value="나이트클럽 가기">
            <label class="form-check-label" for="나이트클럽 가기">나이트클럽 가기</label>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button @click="getQuestion(topic,level),level='',topic=''" class="btn btn-primary" data-bs-target="#SurveyModal2" data-bs-toggle="modal">START</button>
      </div>
    </div>
  </div>
</div>
<div class="modal" id="SurveyModal2" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalToggleLabel2">문제듣기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- 듣기 -->
        <svg @click.prevent.once="playSound(audioUrl)" xmlns="http://www.w3.org/2000/svg" width="160" height="160" fill="currentColor" class="bi bi-volume-up-fill" viewBox="0 0 16 16">
          <path d="M11.536 14.01A8.473 8.473 0 0 0 14.026 8a8.473 8.473 0 0 0-2.49-6.01l-.708.707A7.476 7.476 0 0 1 13.025 8c0 2.071-.84 3.946-2.197 5.303l.708.707z"/>
          <path d="M10.121 12.596A6.48 6.48 0 0 0 12.025 8a6.48 6.48 0 0 0-1.904-4.596l-.707.707A5.483 5.483 0 0 1 11.025 8a5.483 5.483 0 0 1-1.61 3.89l.706.706z"/>
          <path d="M8.707 11.182A4.486 4.486 0 0 0 10.025 8a4.486 4.486 0 0 0-1.318-3.182L8 5.525A3.489 3.489 0 0 1 9.025 8 3.49 3.49 0 0 1 8 10.475l.707.707zM6.717 3.55A.5.5 0 0 1 7 4v8a.5.5 0 0 1-.812.39L3.825 10.5H1.5A.5.5 0 0 1 1 10V6a.5.5 0 0 1 .5-.5h2.325l2.363-1.89a.5.5 0 0 1 .529-.06z"/>
        </svg>
        <!-- 녹음 -->
        <h1>
          <svg class="bi bi-record-circle" data-bs-target="#SurveyModal3" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          </svg>
        </h1>
        <h2> Start recording</h2>
      </div>
    </div>
  </div>
</div>
<div class="modal" id="SurveyModal3" data-bs-backdrop="static" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel3" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <h5 class="modal-title" id="exampleModalToggleLabel3">
         Q. {{ questionInfo.questionContent }}
        </h5>
        <!-- soundwave -->
        <svg xmlns="http://www.w3.org/2000/svg" width="300" height="300" fill="currentColor" class="bi bi-soundwave" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M8.5 2a.5.5 0 0 1 .5.5v11a.5.5 0 0 1-1 0v-11a.5.5 0 0 1 .5-.5zm-2 2a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm4 0a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm-6 1.5A.5.5 0 0 1 5 6v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm8 0a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm-10 1A.5.5 0 0 1 3 7v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5zm12 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5z"/>
        </svg>
        <!-- 일시정지 -->
        <p>
          <svg data-bs-target="#SurveyModal4" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-pause-circle" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M5 6.25a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5zm3.5 0a1.25 1.25 0 1 1 2.5 0v3.5a1.25 1.25 0 1 1-2.5 0v-3.5z"/>
          </svg>
        </p>
      </div>
    </div>
  </div>
</div>
<div class="modal" id="SurveyModal4" data-bs-backdrop="false" style="background-color: rgba(0, 0, 0, 0.5);" data-bs-keyboard="false" aria-hidden="true" aria-labelledby="exampleModalToggleLabel4" tabindex="-1">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <h5 class="modal-title" id="exampleModalToggleLabel4">
          Q. {{ questionInfo.questionContent }}
        </h5>
        <!-- soundwave -->
        <svg xmlns="http://www.w3.org/2000/svg" width="300" height="300" fill="currentColor" class="bi bi-soundwave" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M8.5 2a.5.5 0 0 1 .5.5v11a.5.5 0 0 1-1 0v-11a.5.5 0 0 1 .5-.5zm-2 2a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm4 0a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zm-6 1.5A.5.5 0 0 1 5 6v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm8 0a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm-10 1A.5.5 0 0 1 3 7v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5zm12 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0V7a.5.5 0 0 1 .5-.5z"/>
        </svg>
        <div>
          <!-- 녹음 -->
          <svg class="bi bi-record-circle" data-bs-target="#SurveyModal2" data-bs-toggle="modal" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" viewBox="0 0 16 16">
            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
            <path d="M11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
          </svg>
          <p>Re-Start</p>
          <!-- save -->
          <svg @click="saveScript" data-bs-dismiss="modal" aria-label="Close" xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-download" viewBox="0 0 16 16">
            <path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
            <path d="M7.646 11.854a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V1.5a.5.5 0 0 0-1 0v8.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3z"/>
          </svg>
          <p>Script save</p>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from 'axios';
const API_URL = 'http://localhost:8080/api/v1';

export default {
  data(){
    return{
      topic : '',
      level : '',
      questionInfo : {},
      audioUrl : '',
      userId:this.$store.state.auth.user.id,
    }
  },
  methods : {
     getQuestion(topic,level) {
    axios.get(API_URL + '/question/random', {
      params: {
        topic: topic,
        level: level
      }
    })
      .then(response => {
        console.log(response)
        this.questionInfo = response.data
        this.audioUrl = response.data.audioUrl
        console.log(this.questionInfo)
      });
  },

  saveScript() {
    axios.post(API_URL + '/script', {
      
        userId: this.userId,
        questionId: this.questionInfo.id,
        scriptContent: "Oh, my home. I live in a normal looking apartment in Seoul. Capital city in Korea. My apartment is standard. There are three rooms, two bathrooms, a kitchen, and a balcony. You know, the best place in my apartment is the bedroom. That is the place where I spend most of my time. The bedroom is so spacious than other apartments. There is a queen size bed and a nice mood light. I usually spend my time on the bed with my smartphone. It is literally comfortable. I think I am satisfied with my apartment.",
        audioURL: "www.naver.com"

    }).then(res=>{
      console.log(res)
    })
  },

  playSound (sound) {
      if(sound) {
        var audio = new Audio(sound);
        audio.play();
      }
    }
  }
}
</script>

<style scoped>
.modal-content {
  background-color: #E3F2FD;
}
</style>