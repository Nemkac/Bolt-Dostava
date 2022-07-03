<template>
  <div class="container">
    <h3>Artikli restorana</h3>

    <div class="container">
      <table class="table">
        <thead>
        <tr>
          <th>Naziv artikla</th>
          <th>ID artikla</th>
          <th>Kolicina</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="artikal in artikli" v-bind:key="artikal.id">
          <td>{{artikal.naziv}}</td>
          <td>{{artikal.id}}</td>
          <input type="number" v-model="artikal.kolicina" />
          <td>
            <button v-on:click="this.dodajUKorpu(artikal.naziv, artikal.kolicina)" className="btn btn-primary"> Dodaj u korpu </button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>


</template>

<script>
import RestoranService from "@/services/RestoranService";
import Axios from "axios";
// import axios from "axios";

export default {
  name: "RestoranComponent",

  data() {
    return {
      artikli: [],
      naziv: undefined,
      id : undefined,
      selektovani_artikal:{
        nazivArtikla: "",
        kolicina: 1
      }
    };
  },
  methods: {
    prikaziArtikleRestorana() {
      RestoranService.izlistajArtikle(this.$route.params.id)
        .then(response => {
             this.artikli = response.data;
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
        "id":this.id
      }
      Axios.get("http://localhost:8080/api/pregledArtikalaRestorana" + this.$route.params.id, temp)
      this.prikaziArtikleRestorana();
    }
  },
  dodajUKorpu(naziv, kolicina){
    // /api/restoran/{idRestorana}/korpa/dodajUKorpu/{korisnickoIme}
    console.log(naziv)
    console.log(kolicina)
    var dukdto={
      "nazivArtikla" : naziv,
      "kolicina" : kolicina
    }
    let korisnickoIme = sessionStorage.getItem('korisnickoIme');
    Axios.post("http://localhost:8080/api/restoran/"+ this.$route.params.id+ "/korpa/dodajUKorpu/"+ korisnickoIme , dukdto)
  },
  },
  created() {
    console.log('kreirano')
    console.log(this.$route.params.id)
    this.prikaziArtikleRestorana();
  },

};
</script>
}

<style scoped>

</style>