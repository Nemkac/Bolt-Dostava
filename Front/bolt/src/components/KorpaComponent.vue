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
    <h1>Korpa</h1>
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
    <button v-on:click="this.zavrsiPorudzbinu()" className="btn btn-primary" class="btn"> Zavrsi porudzbinu </button>
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
            this.$router.push("/prikaz_restorana");
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
  padding-top: 6rem;
}

.container table{
  margin-top: 2rem;
  width: 40%;
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
  width: 10%;
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
</style>