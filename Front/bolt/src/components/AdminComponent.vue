<template>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel = "stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.3/css/all.min.css">
    <title>Document</title>
  </head>
  <!--Pocetak headera-->
  <body>
  <header>
    <a href="#" class="logo"><router-link to = "/pocetna"><img src="../assets/LogotipLanding.png" width="170" height="100"></router-link></a>

    <nav class="navbar">
      <a href=""><router-link to = "/pocetna">Početna</router-link></a>
      <a href=""><router-link to = "/prikaz_restorana">Restorani</router-link></a>
      <a href=""><router-link to = "/profilKupca">Profil</router-link></a>
    </nav>

    <div class = "korpa">
      <a href="#"><router-link to = "/prikaz_restorana"><fa icon="search"/></router-link></a>
      <a href="#"><router-link to="/korpa"><fa icon="cart-shopping"/></router-link></a>
    </div>
  </header>
  </body>
  <div class="content">
    <div class="container">
      <h3>Svi korisnici</h3>

      <div class="container2" align="center">
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


    <div class="container3" align="center">
      <h3>SVI RESTORANI</h3>

      <div class="container4">
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


    <form class="wrapper">
      <h1>Dodavanje menadzera</h1>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
        Ime <input v-model="menadzer.ime" class="form-control" />
      </div>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Prezime</label>-->
        Prezime <input v-model="menadzer.prezime" class="form-control" />
      </div>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Korisničko ime</label>-->
        Korisnicko ime <input v-model="menadzer.korisnickoIme" class="form-control" />
      </div>

      <div class="podaci" align="center">
        <!--      <label for="inputPassword4" class="form-label">Lozinka</label>-->
        Lozinka <input v-model="menadzer.lozinka" type="password" class="form-control" />
      </div>
      <!--    <label for="">Pol:</label> <br>-->
      Pol
      <div class="Pol">
        <input v-model="menadzer.pol" type="radio"  value = "0"  /> Muski
        <input v-model="menadzer.pol" type="radio" value = "1"  /> Zenski
      </div>
      <br>
      <label>Datum rodjenja:</label> <br>
      <div  class="podaci" align="center">
        <input v-model="menadzer.datum" type="date" class="form-control" /> <br>
        Restoran za dodelu menadzeru <input v-model="restoranZaDodeluMenadzeru" class="form-control" />
        <br>
        <button v-on:click="kreirajMenadzera()" class="btn btn-primary">
          Kreiraj menadzera
        </button>
      </div>
    </form>

    <form class="wrapper">
      <h1>Dodavanje dostavljaca</h1>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
        Ime <input v-model="dostavljac.ime" class="form-control" />
      </div>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Prezime</label>-->
        Prezime <input v-model="dostavljac.prezime" class="form-control" />
      </div>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Korisničko ime</label>-->
        Korisnicko ime <input v-model="dostavljac.korisnickoIme" class="form-control" />
      </div>

      <div class="podaci" align="center">
        <!--      <label for="inputPassword4" class="form-label">Lozinka</label>-->
        Lozinka <input v-model="dostavljac.lozinka" type="password" class="form-control" />
      </div>
      <!--    <label for="">Pol:</label> <br>-->
      Pol
      <div class="Pol">
        <input v-model="menadzer.pol" type="radio"  value = "0"  /> Muski
        <input v-model="menadzer.pol" type="radio" value = "1"  /> Zenski
      </div>
      <br>
      <br>
      <label>Datum rodjenja:</label> <br>
      <div class="podaci" align="center">
        <input v-model="dostavljac.datum" type="date" class="form-control" /> <br>
        <br>
        <button v-on:click="kreirajDostavljaca()" class="btn btn-primary">
          Kreiraj dostavljaca
        </button>
      </div>
    </form>

    <form class="wrapper">
      <h1>Dodavanje restorana</h1>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
        Naziv <input v-model="restoran.naziv" class="form-control" />
      </div>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Prezime</label>-->
        Tip <input v-model="restoran.tip" class="form-control" />
      </div>
      <div class="podaci" align="center">
        <!--      <label for="inputEmail4" class="form-label">Korisničko ime</label>-->
        Adresa <input v-model="restoran.adresa" class="form-control" />
      </div>
      <button v-on:click="dodajRestoran()" class="btn btn-primary">
        Dodaj restoran
      </button>

    </form>
  </div>
</template>

<script>
import axios from "axios";
import RestoranService from "@/service/RestoranService";

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
            window.location.reload();
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
            window.location.reload();
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
            window.location.reload();
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
*{
  font-family: 'Poppins', sans-serif;
  margin: 0; padding: 0;
  box-sizing: border-box;
  text-transform: uppercase;
  text-decoration: none;
  outline: none; border: none;
  transition: all .07s linear;

}

html{
  overflow-x: hidden ;
  scroll-behavior: smooth;
  scroll-padding-top: 5.5rem;
  font-size: 62.5%;
}

header{
  position: fixed;
  top:0; left: 0; right: 0;
  background: #fff;
  display: flex;
  padding: .5rem;
  align-items: center;
  justify-content: space-between;
  z-index: 1000;
  box-shadow: 13px 13px 20px #cbced1, -13px -13px 20px #fff;
}

header .logo{
  padding-left: 1.4rem;
}

header .navbar a{
  font-size: 1.2rem;
  font-weight: bold;
  border-radius: .5rem;
  padding: 5rem 1.5rem;
  color: #002d69;
}

header .navbar a:hover{
  color: #01072a;
  cursor: pointer;
}

header .korpa a{
  cursor: pointer;
  margin-left: .5rem;
  height: 4.5rem;
  line-height: 4.5rem;
  width: 4.5rem;
  text-align: center;
  text-decoration: none;
  font-size: 1.4rem;
  color: #002d69;
  padding-right: 1.4rem;
}

header .korpa a:hover{
  color: #01072a;
}

.content{
  padding-top: 6rem;
}

.content .container .container2 table{
  margin-top: 15px;
  background-color: #ecf0f3;
  width: 60%;
  text-align: center;
}

.content .container3 .container4 table{
  margin-top: 15px;
  background-color: #ecf0f3;
  width: 60%;
  text-align: center;
}

.wrapper {
  max-width: 400px;
  min-height: 500px;
  margin: 160px auto;
  padding: 40px 30px 30px 30px;
  background-color: #ecf0f3;
  border-radius: 15px;
  box-shadow: 13px 13px 20px #cbced1, -13px -13px 20px #fff;
}

.wrapper .podaci{
  padding-top: 10px;
}

.wrapper .podaci input{
  border: none;
  padding-left: 10px;
  margin-bottom: 10px;
  border-radius: 20px;
  box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
  width: 100%;
}

.wrapper .podaci .Pol{
  padding-left: 10px;
  margin-bottom: 20px;
  border-radius: 20px;
  box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
}

.wrapper button{
  width: 60%;
  height: 22px;
  background-color: #002d69;
  border-radius: 20px;
  color: #fff;
  margin-top: 10px;
}

.wrapper button:hover{
  background-color: #01072a;
  cursor: pointer;
}

</style>