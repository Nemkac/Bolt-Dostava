<template >

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

  <div class="container" align="center">
    <div class="container">
      <h3>Artikli restorana</h3>
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

      <button v-if="this.restoranOtvoren" style="margin: 10px" v-on:click="this.otvoriRestoran(false)" className="btn btn-primary" class="btn1"> Zatvori restoran </button>
      <button v-else  style="margin: 10px" v-on:click="this.otvoriRestoran(true)" className="btn btn-primary" class="btn1"> Otvori restoran </button>

      <button style="margin: 10px" v-on:click="this.pokreniDodavanje()" className="btn btn-primary" class="btn1"> Dodaj novi artikal </button>
      <div v-if="dodavanjeAktivno==true" class="dodajArtikal">

        <div class="Unos">
          <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
          Naziv artikla: <input v-model="artikal.naziv" class="form-control" />
        </div >
        <div class="Unos">
          Cena: <input v-model="artikal.cena" class="form-control" />
        </div>
        <div class="Unos">
          Kolicina <input v-model="artikal.kolicina" class="form-control" />
        </div>

        <div class="Unos">
          Opis:  <input v-model="artikal.opis" class="form-control" />
        </div>
        <div class="Unos">
          Tip artikla:
          <select style="margin: 10px" v-model="artikal.tip" name="tip" id="tip">
            <option value="0">Jelo</option>
            <option value="1">Pice</option>
          </select>
        </div>

        <button v-on:click="this.sacuvajArtikal(artikal.naziv)" className="btn btn-primary" class="btn11"> Sacuvaj artikal </button>
      </div>
    </div>
  </div>

</template>

<script>

import axios from "axios";
import RestoranService from "@/service/RestoranService";

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

.container{
  padding-top: 4rem;
}

.container table{
  margin-top: 2rem;
  width: 60%;
  height: 40%;
  background-color: #ecf0f3;
}

.container .table tr{
  height: 2rem;
  text-align: center;
}

.container .table tr{
  border-style: solid;
  border-width: 2px;
}

.container .btn{
  width: 60%;
  height: 22px;
  background-color: #002d69;
  border-radius: 20px;
  color: #fff;
  margin-top: 10px;
}

.container .btn:hover{
  background-color: #01072a;
  cursor: pointer;
}
.container-big .container .table td{
  border: #01072a;
}

.container .table input{
  border: none;
  padding-left: 10px;
  margin-bottom: 10px;
  border-radius: 20px;
  box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
  width: 20%;
}

.container .btn1{
  width: 20%;
  height: 22px;
  background-color: #002d69;
  border-radius: 20px;
  color: #fff;
  margin-top: 10px;
}

.container .btn1:hover{
  background-color: #01072a;
  cursor: pointer;
}

.container .btn11{
  width: 50%;
  height: 22px;
  background-color: #002d69;
  border-radius: 20px;
  color: #fff;
  margin-top: 10px;
}

.container .btn11:hover{
  background-color: #01072a;
  cursor: pointer;
}

.container .dodajArtikal{
  width: 20%;
  background-color: #ecf0f3;
  display: block;
  text-align: left;
}

.container .dodajArtikal .Unos input{
  border: none;
  padding-left: 10px;
  margin-bottom: 10px;
  border-radius: 20px;
  box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
  width: 40%;
}

</style>
