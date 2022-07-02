import Vue from 'vue'
import { createApp } from 'vue'
import App from './App.vue'
import VueRouter from "vue-router";
import HelloWorld from "@/components/HelloWorld";
import router from './router'


const app = createApp(App).use(router).mount('#app')
app.use(VueRouter);


new Vue({
    render: h => h(HelloWorld),
}).$mount('#app')