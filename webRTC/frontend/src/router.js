import { createWebHistory, createRouter } from "vue-router";
import MainPageUnLoginView from "./views/MainPageUnLoginView.vue";
import Mainpage from "./views/MainPageView.vue";
import Login from "./components/Login.vue";
import ScriptDetail from "./components/ScriptDetail.vue";
import Register from "./components/Register.vue";
import Feedback from "./components/Feedback.vue";
import FeedbackDetail from "./components/FeedbackDetail.vue";
import WebrtcStudentView from "./views/WebrtcStudentView.vue";

const BoardUser = () => import("./components/BoardUser.vue")
const routes = [
  {
    path: "/",
    name: "mainpageunlogin",
    component: MainPageUnLoginView,
  },
  {
    path: "/mainpageunlogin",
    component: MainPageUnLoginView,
  },
  {
    path: "/login",
    component: Login,
  },
 
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/mainpage",
    name: "mainpage",
    // lazy-loaded
    component: Mainpage,
  },
   {
    path: "/scriptdetail",
    name:"scriptdetail",
    component: ScriptDetail,
  },
  {
    path: "/feedback",
    name: "feedback",
    // lazy-loaded
    component: Feedback,
  },
  {
    path: "/feedbackdetail",
    name:"feedbackdetail",
    component: FeedbackDetail,
  },
  {
    path: "/webrtcstudent",
    name: "webrtcstudent",
    // lazy-loaded
    component: WebrtcStudentView,
  },
  {
    path: "/user",
    name: "user",
    // lazy-loaded
    component: BoardUser,
  },
];
const router = createRouter({
  history: createWebHistory(),
  routes,
});
export default router;