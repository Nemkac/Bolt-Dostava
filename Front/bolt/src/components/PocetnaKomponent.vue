<template>
  <html>
  <html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel = "stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.3/css/all.min.css">
    <title>Document</title>
  </head>
  <body>
  <!--Pocetak headera-->
  <header>
    <a href="#" class="logo"><router-link to = "/"><img src="../assets/LogotipLanding.png" width="170" height="100"></router-link></a>

    <nav class="navbar">
      <a href="">Početna</a>
      <a href="">Restorani</a>
      <a href="">Kontakt</a>
      <a href=""><router-link to = "/profil">Profil</router-link></a>
      <a href="" v-on:click="this.odjaviSe()"><router-link to = "/">Logout</router-link></a>
    </nav>

    <div class = "korpa">
      <a href="#"><router-link to = "/pretraga_restorana"><fa icon="search"/></router-link></a>
      <a href="#"><fa icon="cart-shopping"/></a>
    </div>

  </header>
  <!--Kraj headerea-->

  <!--Main sekcija-->
  <div class="landing">
    <div class="text">
      <h1>Gladan si?</h1>
      <h2>Bez brige!</h2>
      <p>Bolt dostava napravljena je kako bi mogao, bilo kada,<br> bilo gde, da uživas u svojoj omiljenoj hrani.<br>
        Poruči hranu iz omiljenog restorana i ona će<br> u najkraćem mogućem roku biti na tvojoj adresi.</p>
      <router-link to = "/prikaz_restorana"><button type="button" class="button">
        <span class="button-text">Poruci Odmah</span>
      </button></router-link>
    </div>
  </div>
  <!--Kraj main sekcije-->

  </body>
  </html>
  </html>
</template>


<script>
import axios from "axios";
export default {
  name: 'PocetnaPage',
  data: function() {
  },
  methods:{
    odjaviSe: function() {
      console.log(this.korisnik);
      axios
        .post("http://localhost:8080/api/logout")
        .then(res => {
          console.log(res)
          alert("Uspesno ste se izlogovali")
          this.$router.push("/")
        })
        .catch(error => {
          console.log(error.response);
          alert("Greska")
        });
    }
  }
};
</script>

<style scoped>
:root{
  --SvetloZuta: #ffd74a;
  --TamnoZuta: #fdbf00;
  --TamnoPlava: #01072a;
  --SvetloPlava: #003072;
}

*{
  font-family: 'Poppins', sans-serif;
  margin: 0; padding: 0;
  box-sizing: border-box;
  text-transform: uppercase;
  text-decoration: none;
  outline: none; border: none;
  transition: all .07s linear;
}
body{
  background: #ecf0f3;
  text-align: left;
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
  box-shadow: #000000;
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

.landing{
  background-image: url("../assets/landingBG4.png");
  background-size: cover;
  padding: 10.16rem 2.5rem;
}

.landing .text h1{
  font-size: 3.5rem;
  text-align: left;
  color: #01072a;
}

.landing .text h2{
  font-size: 4rem;
  color: #01072a;
  text-align: left;
}

.landing .text p{
  font-size: 1rem;
}

.landing .text .button{
  margin-top: 1rem;
  padding: .5rem 2.5rem;
  background: #002d69;
  color: #fff;
  display: inline-flex;
  border: none;
  border-radius: 5px;
  overflow: hidden;
}

.landing .text .button:hover{
  background: #01072a;
  cursor: pointer;

}

</style>
