import { createRouter, createWebHistory } from 'vue-router'
import RegisterComponent from "@/components/RegisterComponent.vue";
import LoginComponent from "@/components/LoginComponent.vue";
import LandingPage from "@/components/LandingPage";
import RestoraniComponent from "@/components/RestoraniComponent";
import PocetnaKomponent from "@/components/PocetnaKomponent";
import PrikaziRestoranComponent from "@/components/PrikaziRestoranComponent";
import KorpaComponent from "@/components/KorpaComponent";
import PorudzbineKupcaComponent from "@/components/PorudzbineKupcaComponent";
import MenadzerPrikazPorudzbinaComponent from "@/components/MenadzerPrikazPorudzbinaComponent";
import profilMenadzera from "@/components/profilMenadzera";
import DostavljacPrikazPorudzbineComponent from "@/components/DostavljacPrikazPorudzbineComponent";
import profilKupcaComponent from "@/components/profilKupcaComponent";
import AdminComponent from "@/components/AdminComponent";

const routes = [
    {
      path:'/',
      name: 'landingpage',
      component: LandingPage
    },
    {
        path: '/register',
        name: 'register',
        component: RegisterComponent
    },
    {
        path: '/login',
        name: 'login',
        component: LoginComponent
    },
    {
        path:'/prikaz_restorana',
        name:'prikaz_restorana',
        component: RestoraniComponent
    },
    {
        path: '/pocetna',
        name: 'pocetna',
        component: PocetnaKomponent
    },
    {
        path: '/restoran/:id',
        name: 'restoran',
        component: PrikaziRestoranComponent
    },
    {
        path: '/korpa',
        name: 'korpa',
        component: KorpaComponent
    },
    {
        path: '/porudzbine_kupca',
        name: 'porudzbine_kupca',
        component: PorudzbineKupcaComponent
    },
    {
        path: '/menadzerPrikazPorudzbina',
        name: 'MenadzerPrikazPorudzbina',
        component: MenadzerPrikazPorudzbinaComponent
    },
    {
        path: '/menadzerProfil',
        name: 'ProfilMenadzera',
        component: profilMenadzera
    },
    {
        path: '/dostavljacPrikazPorudzbina',
        name: 'DostavljacPrikazPorudzbinaComponent',
        component: DostavljacPrikazPorudzbineComponent
    },
    {
        path: '/profilKupca',
        name: 'profilKupca',
        component: profilKupcaComponent
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