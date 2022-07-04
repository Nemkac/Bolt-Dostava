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

  <div class="container" align="center">
    <h2>Lista Porudzbina</h2>
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
            window.location.reload();
            // this.$router.push("/menadzerPrikazPorudzbina");
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
            window.location.reload();
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
            window.location.reload();
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
  padding-top: 7rem;
}

.container table{
  margin-top: 2rem;
  width: 90%;
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

.container .table td .btn{
  width: 80%;
  background-color: #002d69;
  border-radius: 20px;
  color: #fff;
}

.container .table td .btn:hover{
  background-color: #01072a;
  cursor: pointer;
}
</style>