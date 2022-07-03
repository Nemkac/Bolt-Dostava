<template>
Lista porudzbina

  <div class="container" align="center">
    <table class="table">
      <thead>
      <tr>
        <th>ID poruzbine</th>
        <th>Restoran</th>
        <th>Cena</th>
        <th>Status</th>
        <th>Kupac</th>
        <th>Artikli</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="porudzbina in porudzbine" v-bind:key="porudzbina.id">
        <td>{{porudzbina.id}}</td>
        <td>{{porudzbina.restoran.naziv}}</td>
        <td>{{porudzbina.cena}}</td>
        <td>{{porudzbina.status}}</td>
        <td>{{porudzbina.kupac.korisnickoIme}}</td>
        <td>
          <table>
            <tr v-for="artikal in porudzbina.poruceniArtikli" v-bind:key="artikal.id">
              <td>{{artikal.naziv}}</td>
              <td>{{artikal.cena}} RSD</td>
            </tr>
            </table>
        </td>
        <td v-if="porudzbina.status=='OBRADA'">
          <button v-on:click="this.promeniStatus(porudzbina.id)" className="btn btn-primary"> Promeni status u "U PRIPREMI" </button>
        </td>
        <td v-if="porudzbina.status=='UPRIPREMI'">
          <button v-on:click="this.promeniStatusUCekaDostavljaca(porudzbina.id)" className="btn btn-primary"> Promeni status u "CEKA DOSTAVLJACA" </button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>


</template>

<script>
import axios from "axios";

export default {
  name: "MenadzerPrikazPorudzbinaComponent",
  data() {
    return {
      porudzbine: [],
    }
  },
  methods: {
    prikaziKorisnikovuKorpu() {
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .get("http://localhost:8080/api/menadzer/pregledPorudzbina/" + korisnickoIme)
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
    promeniStatusUCekaDostavljaca(idPorudzbine) {
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .put("http://localhost:8080/api/menadzer/promeniStatusUCekaDostavljaca/"+ idPorudzbine +"/" + korisnickoIme)
          .then(res => {
            console.log(res);
            this.$router.push("/menadzerPrikazPorudzbina");
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