<template>
Korpa

  <div class="container" align="center">
    <table class="table">
      <thead>
      <tr>
        <th>Naziv artikla</th>
        <th>Naziv restorana</th>
        <th>Kolicina</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="artikal in artikli" v-bind:key="artikal.id">
        <td>{{artikal.nazivArtikla}}</td>
        <td>{{artikal.nazivRestorana}}</td>
        <input type="number" v-model="artikal.kolicina" />
      </tr>
      </tbody>
    </table>
    <button v-on:click="this.zavrsiPorudzbinu()" className="btn btn-primary"> Zavrsi porudzbinu </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "KorpaKomponent",

  data() {
    return {
      artikli: [],
    }
  },
  methods: {
    prikaziKorisnikovuKorpu() {
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .get("http://localhost:8080/api/korpa/pregledKorpe/" + korisnickoIme)
          .then(res => {
            console.log(res);
            this.artikli = res.data.artikli;
          })
          .catch(error => {
            console.log(error.response);
          });
    },
    zavrsiPorudzbinu() {
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .put("http://localhost:8080/api/korpa/kreirajPorudzbinu/" + korisnickoIme)
          .then(res => {
            console.log(res);
            this.artikli = res.data.artikli;
            this.$router.push("/listaRestorana");
          })
          .catch(error => {
            console.log(error.response);
          });
    }

  },
  created() {
    this.prikaziKorisnikovuKorpu();
  },

};
</script>

<style scoped>

</style>