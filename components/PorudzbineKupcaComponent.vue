<template>
  Lista porudzbina
  <br><br>
  <div class="container" align="center">
    <table class="table">
      <thead>
      <tr>
        <th>Restoran</th>
        <th>Dostavljac</th>
        <th>Status</th>
        <th>Vreme porudzbine</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="porudzbina in porudzbine" v-bind:key="porudzbina.id">
        <td>{{porudzbina.nazivRestorana}}</td>
        <td>{{porudzbina.dostavljac.ime}}</td>
        <td>{{porudzbina.status}}</td>
        <td>{{porudzbina.vremePorudzbine}}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "PorudzbineKupcaComponent",
  data() {
    return {
      porudzbine: [],
    }
  },
  methods: {
    prikaziPorudzbine() {
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .get("http://localhost:8080/api/kupac/prikazPorudzbina/" + korisnickoIme)
          .then(res => {
            console.log(res);
            this.porudzbine = res.data;
          })
          .catch(error => {
            console.log(error.response);
          });
    }
  },
  created() {
    this.prikaziPorudzbine();
  },
};

</script>

<style scoped>

</style>