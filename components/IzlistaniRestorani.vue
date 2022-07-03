<!--<template>-->
<!--  <h2> Izlistani restorani </h2>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "RestoranComponent"-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
import axios from "axios";
Vue.use(Vuelidate);
<template>
  <div class="container">
    <h3>SVI RESTORANI</h3>

     <input v-model="naziv"  />
    <button v-on:click="this.pretraziPoNazivu()" className="btn btn-primary">Pretrazi po nazivu</button>
    <br>
    <input v-model="tip"  />
    <button v-on:click="this.pretraziPoTipu()" className="btn btn-primary">Pretrazi po tipu</button>
    <br>
    <input v-model="adresa"  />
    <button v-on:click="this.pretraziPoAdresi()" className="btn btn-primary">Pretrazi po adresi</button>
  <div class="container">
      <table class="table">
        <thead>
        <tr>
          <th>naziv</th>
          <th>tip</th>
          <th>adresa</th>
          <th>radi</th>

        </tr>
        </thead>
        <tbody>
        <tr v-for="restoran in restorani" v-bind:key="restoran.naziv">
          <td>{{restoran.naziv}}</td>
          <td>{{restoran.tip}}</td>
          <td>{{restoran.lokacija.adresa}}</td>
          <td>{{restoran.radi}}</td>
          <td>
            <button v-on:click="this.prikazRestorana(restoran.id)" className="btn btn-primary"> Prikazi restoran </button>
<!--            <button class="btn btn-success" v-on:click="prikazRestorana(restoran.id)">-->

          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>


</template>

<script>
import RestoranService from "@/services/RestoranService";
import Axios from 'axios';
import axios from "axios";
export default {
  name: "ListaRestorana",
  data() {
    return {
      restorani: [],
      naziv: undefined,
      tip: undefined,
      adresa: undefined,
      status: undefined
    };
  },
  methods: {
    prikaziRestorane() {
      RestoranService.findAll()
          .then(response => {
            this.restorani = response.data;
          });
    },
    validateAndSubmit(e) {
      e.preventDefault();
      this.errors = [];
      if (!this.name) {
        this.errors.push("Enter valid values");
      } else if (this.adress.length < 1 ) {
        this.errors.push("Enter atleast 5 characters in Adress");
      }
      if (this.errors.length === 0) {
        var temp={
          "naziv":this.naziv,
          "adresa":this.adresa,
          "status":this.status,
          "tip":this.tip
        }
        this.name = ""
        this.adress = ""
        this.description = ""
        Axios.post("http://localhost:8080/api/list_restorana", temp)
        this.prikaziRestorane();
      }
    },
    pretraziPoAdresi() {
      ///api/restoran/pretraga/poImenu/
      axios.get(`http://localhost:8080/api/restoran/pretraga/poLokaciji/` + this.adresa)
          .then(response => {
            this.restorani = response.data;
          });
    },
    pretraziPoNazivu() {
      ///api/restoran/pretraga/poImenu/
      axios.get(`http://localhost:8080/api/restoran/pretraga/poImenu/` + this.naziv)
          .then(response => {
        this.restorani = response.data;
      });
    },

    pretraziPoTipu() {
      axios.get(`http://localhost:8080/api/restoran/pretraga/poTipu/` + this.tip)
        .then(response => {
          this.restorani = response.data;
        });
    },
    prikazRestorana(id){
      this.$router.push("/pregledArtikalaRestorana/"+id);
}
  },
  created() {
    console.log('kreirano')
    this.prikaziRestorane();
  },

};
</script>

<style>

table, th, td {
  border: 2px solid;
}


</style>