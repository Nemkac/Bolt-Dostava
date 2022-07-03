<template >
  <div class="container" align="center">
    <h3>Artikli restorana</h3>

    <div class="container">
      <table class="table">
        <thead>
        <tr>
          <th>Naziv artikla</th>
          <th>ID artikla</th>
          <th>Opis</th>
          <th>Tip artikla</th>
          <th>Cena</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="artikal in artikli" v-bind:key="artikal.id">
          <td>{{artikal.naziv}}</td>
          <td>{{artikal.id}}</td>
          <td>{{artikal.opis}}</td>
          <td>{{artikal.tip}}</td>
          <td>{{artikal.cena}}</td>
          <td><button v-on:click="this.obrisiArtikal(artikal.naziv)" className="btn btn-primary"> Obrisi </button></td>
        </tr>
        </tbody>
      </table>

      <button v-if="this.restoranOtvoren" style="margin: 10px" v-on:click="this.otvoriRestoran(false)" className="btn btn-primary"> Zatvori restoran </button>
      <button v-else  style="margin: 10px" v-on:click="this.otvoriRestoran(true)" className="btn btn-primary"> Otvori restoran </button>

      <button style="margin: 10px" v-on:click="this.pokreniDodavanje()" className="btn btn-primary"> Dodaj novi artikal </button>
      <div v-if="dodavanjeAktivno==true">

          <div>
            <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
            Naziv artikla: <input v-model="artikal.naziv" class="form-control" />
          </div>
          <div>
            Cena: <input v-model="artikal.cena" class="form-control" />
          </div>
          <div>
            Kolicina <input v-model="artikal.kolicina" class="form-control" />
          </div>

          <div>
            Opis:  <input v-model="artikal.opis" class="form-control" />
          </div>
        <div>
          Tip artikla:
          <select style="margin: 10px" v-model="artikal.tip" name="tip" id="tip">
          <option value="0">Jelo</option>
          <option value="1">Pice</option>
          </select>
        </div>

        <button v-on:click="this.sacuvajArtikal(artikal.naziv)" className="btn btn-primary"> Sacuvaj artikal </button>
      </div>
    </div>
  </div>

</template>

<script>

import axios from "axios";
import RestoranService from "@/services/RestoranService";

export default {
  name: "profilMenadzera",
  data() {
    return {
      artikal: {
        naziv: "",
        tip:0,
        cena: 0,
        kolicina: 0.0,
        opis: "",
      },
      restoranOtvoren: Boolean,
      artikli: [],
      dodavanjeAktivno : Boolean ,
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
          let idRestorana = sessionStorage.getItem('idRestorana');
          RestoranService.izlistajArtikle(idRestorana)
          .then(response => {
            this.artikli = response.data;
          });
          console.log(this.artikli)
    },
    obrisiArtikal(nazivArtikla) {
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .delete("http://localhost:8080/api/menadzer/obrisiArtikal/"+ nazivArtikla +"/" + korisnickoIme)
          .then(res => {
            console.log(res);
            this.artikli = res.data.artikli;
            window.location.reload();
          })
          .catch(error => {
            console.log(error.response);
          });
},
    otvoriRestoran(otvori) {
      let idRestorana = sessionStorage.getItem('idRestorana');
      axios
          .post("http://localhost:8080/api/menadzer/otvori_restoran/" +idRestorana+ "/" + otvori)
          .then(res => {
            console.log(res);
            window.location.reload();
          })
          .catch(error => {
            console.log(error.response);
          });
    },
    getRestoran() {
      let idRestorana = sessionStorage.getItem('idRestorana');
      axios
          .get("http://localhost:8080/api/menadzer/getRestoran/" +idRestorana)
          .then(res => {
            console.log(res);
            console.log(res.data.radi)
            this.restoranOtvoren = res.data.radi;
          })
          .catch(error => {
            console.log(error.response);
          });
    },
    pokreniDodavanje() {
        this.dodavanjeAktivno = true;
    },
    sacuvajArtikal() {
      console.log(this.artikal);
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .post("http://localhost:8080/api/menadzer/dodaj_artikal/" + korisnickoIme, this.artikal)
          .then(res => {
            console.log(res);
            window.location.reload();
          })
          .catch(error => {
            console.log(error.response);
          });

    }
  },


    created() {
      this.prikaziArtikleRestorana();
      this.getRestoran();
    }

}
</script>

<style scoped>

</style>