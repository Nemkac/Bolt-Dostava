import { createRouter, createWebHistory } from 'vue-router'
import LoginComponent from "@/components/LoginComponent";
import RegisterComponent from "@/components/RegisterComponent";
import HomeComponent from "@/components/HomeComponent";
import IzlistaniRestorani from "@/components/IzlistaniRestorani";
import RestoranComponent from "@/components/RestoranComponent";
import MenadzerPrikazPorudzbinaComponent from "@/components/MenadzerPrikazPorudzbinaComponent";
import KorpaComponent from "@/components/KorpaComponent";
import DostavljacPrikazPorudzbinaComponent from "@/components/DostavljacPrikazPorudzbinaComponent";
import ProfilMenadzera from "@/components/profilMenadzera";
import ProfilKupcaComponent from "@/components/profilKupcaComponent";
import AdminComponent from "@/components/AdminComponent";
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
  },

  {
    path: '/listaRestorana',
    name: 'listaRestorana',
    component: IzlistaniRestorani
  },

  {
    path: '/pregledArtikalaRestorana/:id',
    name: 'pregledArtikalaRestorana',
    component: RestoranComponent
  },

  {
    path: '/menadzerPrikazPorudzbina',
    name: 'MenadzerPrikazPorudzbinaComponent',
    component: MenadzerPrikazPorudzbinaComponent
  },

  {
    path: '/korpa',
    name: 'KorpaComponent',
    component: KorpaComponent
  },

  {
    path: '/dostavljacPrikazPorudzbina',
    name: 'DostavljacPrikazPorudzbinaComponent',
    component: DostavljacPrikazPorudzbinaComponent
  },
  {
    path: '/menadzerProfil',
    name: 'ProfilMenadzera',
    component: ProfilMenadzera
  },
  {
    path: '/profilKupca',
    name: 'profilKupca',
    component: ProfilKupcaComponent
  },
  {
    path: '/admin',
    name: 'admin',
    component: AdminComponent
  }



]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
