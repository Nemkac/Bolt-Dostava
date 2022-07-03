<template>
  Lista porudzbina

  <div class="container">
    <table class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Datum</th>
        <th>Ime</th>
        <th>Prezime</th>
        <th>Vreme porudzbine</th>
        <th>Status</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="porudzbina in porudzbine" v-bind:key="porudzbina.id">
        <td>{{porudzbina.id}}</td>
        <td>{{porudzbina.datum}}</td>
        <td>{{porudzbina.ime}}</td>
        <td>{{porudzbina.prezime}}</td>
        <td>{{porudzbina.vremePorudzbine}}</td>
        <td>{{porudzbina.status}}</td>
        <td v-if="porudzbina.status=='CEKADOSTAVLJACA'">
          <button v-on:click="this.promeniStatusUPreuzeto(porudzbina.id)" className="btn btn-primary"> Promeni status u "PREUZETO" </button>
        </td>
        <td v-if="porudzbina.status=='UTRANSPORTU'">
          <button v-on:click="this.promeniStatusUIsporuceno(porudzbina.id)" className="btn btn-primary"> Promeni status u "ISPORUCENO" </button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>


</template>

<script>
import axios from "axios";

export default {
  name: "DostavljacPrikazPorudzbinaComponent",
  data() {
    return {
      porudzbine: [],
    }
  },
  methods: {
    prikaziKorisnikovuKorpu() {
      let dostavljacId = sessionStorage.getItem('ulogovaniKorisnikID');
      axios
          .get("http://localhost:8080/api/dostavljac/" + dostavljacId)
          .then(res => {
            console.log(res);
            this.porudzbine = res.data;
          })
          .catch(error => {
            console.log(error.response);
          });
    },
    promeniStatus(idPorudzbine) {
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .put("http://localhost:8080/api/menadzer/promeniStatusUPripremi/"+ idPorudzbine +"/" + korisnickoIme)
          .then(res => {
            console.log(res);
            this.$router.push("/menadzerPrikazPorudzbina");
          })
          .catch(error => {
            console.log(error.response);
          });
    },
    promeniStatusUPreuzeto(idPorudzbine) {
      let korisnickiID = sessionStorage.getItem('ulogovaniKorisnikID');
      axios
          .put("http://localhost:8080/api/dostavljac/promeniStatusUTransportu/"+ idPorudzbine +"/" + korisnickiID)
          .then(res => {
            console.log(res);
          })
          .catch(error => {
            console.log(error.response);
          });
    },
  promeniStatusUIsporuceno(idPorudzbine) {
    let korisnickiID = sessionStorage.getItem('ulogovaniKorisnikID');
    axios
        .put("http://localhost:8080/api/dostavljac/promeniStatusUDostavljena/"+ idPorudzbine +"/" + korisnickiID)
        .then(res => {
          console.log(res);
        })
        .catch(error => {
          console.log(error.response);
        });
  },
  },
  created() {
    this.prikaziKorisnikovuKorpu();
  },
};
</script>

<style scoped>

</style>