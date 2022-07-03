import Vue from 'vue'
import { createApp } from 'vue'
import { FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import App from './App.vue'
import VueRouter from "vue-router";
import router from './router'
import LandingPage from "@/components/LandingPage";

const app = createApp(App).use(router).component('fa', FontAwesomeIcon).mount('#app')
library.add(fas);
app.use(VueRouter);



new Vue({
    render: h => h(LandingPage),
}).$mount('#app')
