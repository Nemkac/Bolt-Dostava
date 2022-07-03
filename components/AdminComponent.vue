<template>
Admin component
  <div class="container">
    <h3>Svi korisnici</h3>

    <div class="container" align="center">
      <table class="table">
        <thead>
        <tr>
          <th>Ime</th>
          <th>Prezime</th>
          <th>Korisnicko ime</th>
          <th>Uloga</th>
          <th>Broj bodova</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="korisnik in korisnici" v-bind:key="korisnik.id">
          <td>{{korisnik.ime}}</td>
          <td>{{korisnik.prezime}}</td>
          <td>{{korisnik.korisnickoIme}}</td>
          <td>{{korisnik.uloga}}</td>
          <td>{{korisnik.brBodova}}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
  <br>
  <br>


  <div class="container" align="center">
    <h3>SVI RESTORANI</h3>

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
<!--          <td>-->
<!--            <button v-on:click="this.obrisiRestoran(restoran.id)" className="btn btn-primary"> Obrisi restoran </button>-->
<!--          </td>-->
        </tr>
        </tbody>
      </table>
    </div>
  </div>


  <form>
    <h1>Dodavanje menadzera</h1>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
      Ime <input v-model="menadzer.ime" class="form-control" />
    </div>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Prezime</label>-->
      Prezime <input v-model="menadzer.prezime" class="form-control" />
    </div>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Korisničko ime</label>-->
      Korisnicko ime <input v-model="menadzer.korisnickoIme" class="form-control" />
    </div>

    <div>
      <!--      <label for="inputPassword4" class="form-label">Lozinka</label>-->
      Lozinka <input v-model="menadzer.lozinka" type="password" class="form-control" />
    </div>
    <!--    <label for="">Pol:</label> <br>-->
    Pol
    <form>
      <input v-model="menadzer.pol" type="radio"  value = "0"  /> Muski
      <br>
      <input v-model="menadzer.pol" type="radio" value = "1"  /> Zenski

    </form>
    <br>
    <label>Datum rodjenja:</label> <br>
    <div class="col-12">
      <input v-model="menadzer.datum" type="date" class="form-control" /> <br>
      Restoran za dodelu menadzeru <input v-model="restoranZaDodeluMenadzeru" class="form-control" />
      <br>
      <button v-on:click="kreirajMenadzera()" class="btn btn-primary">
        Kreiraj menadzera
      </button>
    </div>
  </form>

  <form>
    <h1>Dodavanje dostavljaca</h1>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
      Ime <input v-model="dostavljac.ime" class="form-control" />
    </div>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Prezime</label>-->
      Prezime <input v-model="dostavljac.prezime" class="form-control" />
    </div>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Korisničko ime</label>-->
      Korisnicko ime <input v-model="dostavljac.korisnickoIme" class="form-control" />
    </div>

    <div>
      <!--      <label for="inputPassword4" class="form-label">Lozinka</label>-->
      Lozinka <input v-model="dostavljac.lozinka" type="password" class="form-control" />
    </div>
    <!--    <label for="">Pol:</label> <br>-->
    Pol
    <form>
      <input v-model="dostavljac.pol" type="radio"  value = "0"  /> Muski
      <br>
      <input v-model="dostavljac.pol" type="radio" value = "1"  /> Zenski

    </form>
    <br>
    <label>Datum rodjenja:</label> <br>
    <div class="col-12">
      <input v-model="dostavljac.datum" type="date" class="form-control" /> <br>
      <br>
      <button v-on:click="kreirajDostavljaca()" class="btn btn-primary">
        Kreiraj dostavljaca
      </button>
    </div>
  </form>

  <form>
    <h1>Dodavanje restorana</h1>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
      Naziv <input v-model="restoran.naziv" class="form-control" />
    </div>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Prezime</label>-->
      Tip <input v-model="restoran.tip" class="form-control" />
    </div>
    <div>
      <!--      <label for="inputEmail4" class="form-label">Korisničko ime</label>-->
      Adresa <input v-model="restoran.adresa" class="form-control" />
    </div>
      <button v-on:click="dodajRestoran()" class="btn btn-primary">
        Dodaj restoran
      </button>

  </form>
</template>

<script>
import axios from "axios";
import RestoranService from "@/services/RestoranService";

export default {
  name: "AdminComponent",
  data() {
    return {
      korisnici: [],
      restorani: [],
      naziv: undefined,
      id : undefined,
      menadzer:{
        ime: "",
        prezime: "",
        korisnickoIme: "",
        lozinka: "",
        datum: "",
        pol: ""
      },
      dostavljac: {
        ime: "",
        prezime: "",
        korisnickoIme: "",
        lozinka: "",
        datum: "",
        pol: ""
      },
      restoranZaDodeluMenadzeru: "",
      restoran:{
        naziv: "",
        tip: "",
        adresa: ""
      },
      selektovani_artikal:{
        nazivArtikla: "",
        kolicina: 1
      }
    };
  },
  methods: {
    prikaziSveKorisnike() {
      // let dostavljacId = sessionStorage.getItem('ulogovaniKorisnikID');
      axios
          .get("http://localhost:8080/api/listaKorisnika")
          .then(res => {
            console.log(res);
            this.korisnici = res.data;
          })
          .catch(error => {
            console.log(error.response);
          });
    },
    prikaziSveRestorane(){
      RestoranService.findAll()
          .then(response => {
            this.restorani = response.data;
          });
    },
    kreirajMenadzera: function() {
      event.preventDefault()
      console.log(this.menadzer);
      axios
          .post("http://localhost:8080/api/kreiraj_menadzera/" + sessionStorage.getItem('korisnickoIme') + "/"+ this.restoranZaDodeluMenadzeru, this.menadzer)
          .then(res => {
            console.log(res);
            alert("Uspesno");
          })
          .catch(error => {
            console.log(error.response);
            alert("Neuspesno");
          });
    },
    kreirajDostavljaca: function() {
      event.preventDefault()
      console.log(this.dostavljac);
      axios
          .post("http://localhost:8080/api/kreiraj_dostavljaca/" + sessionStorage.getItem('korisnickoIme'), this.dostavljac)
          .then(res => {
            console.log(res);
            alert("Uspesno");
          })
          .catch(error => {
            console.log(error.response);
            alert("Neuspesno");
          });
    },
    dodajRestoran: function() {
      event.preventDefault()
      console.log(this.menadzer);
      axios
          .post("http://localhost:8080/api/kreiraj_restoran/" + sessionStorage.getItem('korisnickoIme'), this.restoran)
          .then(res => {
            console.log(res);
            alert("Uspesno");
          })
          .catch(error => {
            console.log(error.response);
            alert("Neuspesno");
          });
    }
  },
  created() {
    console.log('kreirano')
    console.log(this.$route.params.id)
    this.prikaziSveKorisnike();
    this.prikaziSveRestorane();
  },

};

</script>

<style scoped>

</style>