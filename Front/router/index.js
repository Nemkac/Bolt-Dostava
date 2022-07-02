import { createRouter, createWebHistory } from 'vue-router'
import LoginComponent from "@/components/LoginComponent";
import RegisterComponent from "@/components/RegisterComponent";
import HomeComponent from "@/components/HomeComponent";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeComponent
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/prijava',
    name: 'prijava',
    component: LoginComponent
  },
  {
    path: '/registracija',
    name: 'registracija',
    component: RegisterComponent
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
