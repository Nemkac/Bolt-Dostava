import { createRouter, createWebHistory } from 'vue-router'
import UserComponent from "@/components/UserComponent.vue";
import RegisterComponent from "@/components/RegisterComponent.vue";
import LoginComponent from "@/components/LoginComponent.vue";
import LandingPage from "@/components/LandingPage";
import PretragaRestoranaComponent from "@/components/PretragaRestoranaComponent";
import RestoraniComponent from "@/components/RestoraniComponent";
import ProfilComponent from "@/components/ProfilComponent";
import PocetnaKomponent from "@/components/PocetnaKomponent";

const routes = [
    {
      path:'/',
      name: 'landingpage',
      component: LandingPage
    },
    {
        path: '/users',
        name: 'users',
        component: UserComponent
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
        path: '/pretraga_restorana',
        name: 'pretraga_restorana',
        component: PretragaRestoranaComponent
    },
    {
        path:'/prikaz_restorana',
        name:'prikaz_restorana',
        component: RestoraniComponent
    },
    {
        path:'/profil',
        name:'profil',
        component: ProfilComponent
    },
    {
        path: '/pocetna',
        name: 'pocetna',
        component: PocetnaKomponent
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router