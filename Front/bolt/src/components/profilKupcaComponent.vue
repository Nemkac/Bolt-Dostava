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
      <a href=""><router-link to = "/pocetna">POČETNA</router-link></a>
      <a href=""><router-link to = "/prikaz_restorana">RESTORANI</router-link></a>
    </nav>

    <div class = "korpa">
      <a href="#"><router-link to = "/prikaz_restorana"><fa icon="search"/></router-link></a>
      <a href="#"><router-link to="/korpa"><fa icon="cart-shopping"/></router-link></a>
    </div>
  </header>
  </body>

  <form class="wrapper">
    <h2>PODACI O KORISNIKU</h2>
    <div class="podaci">
      Broj bodova {{korisnik.brBodova}}
      <br>
      Tip: {{tipKorisnika}}
    </div>
    <div class="podaci" align="left">
      <!--      <label for="inputEmail4" class="form-label">Ime</label>-->
      IME <input v-model="korisnik.ime" class="form-control" />
    </div>
    <div class="podaci" align="left">
      <!--      <label for="inputEmail4" class="form-label">Prezime</label>-->
      PREZIME <input v-model="korisnik.prezime" class="form-control" />
    </div>
    <div class="podaci" align="left">
      <!--      <label for="inputEmail4" class="form-label">Korisničko ime</label>-->
      KORISNICKO IME <input v-model="korisnik.korisnickoIme" class="form-control" />
    </div>

    <div class="podaci" align="left">
      LOZINKA <input v-model="korisnik.lozinka" type="password" class="form-control" />
    </div>

    <br>
    <button v-on:click="izmeniKorisnika()">IZMENI PODATKE</button>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "profilKupcaComponent",

  data: function() {
    return {
      korisnik: {
        ime: "",
        prezime: "",
        korisnickoIme: "",
        lozinka: "",
        brBodova: 0
      },
      tipKorisnika: ""
    };
  },
  methods: {
    prikazi_korisnika: function (){
      // event.preventDefault();
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .get("http://localhost:8080/api/prikaz_podataka/" + korisnickoIme, {
            credentials: 'include'
          })
          .then(res => {
            this.korisnik.ime = res.data.ime
            this.korisnik.prezime = res.data.prezime
            this.korisnik.korisnickoIme = res.data.korisnickoIme
            this.korisnik.lozinka = res.data.lozinka
            this.korisnik.brBodova = res.data.brBodova

            if (this.korisnik.brBodova < 500){
              this.tipKorisnika = 'BRONZANI';
            } else if (this.korisnik.brBodova >= 500 && this.korisnik.brBodova < 2000){
              this.tipKorisnika = 'SREBRNI';
            } else {
              this.tipKorisnika = 'ZLATNI';
            }
            console.log(res);
          })
          .catch(error => {
            console.log(error.response);
          });
    },
    izmeniKorisnika: function() {
      event.preventDefault()
      console.log(this.korisnik);
      axios
          .put("http://localhost:8080/api/izmena_podataka/" + sessionStorage.getItem("korisnickoIme"), this.korisnik)
          .then(res => {
            console.log(res);
            alert("Uspesno");
            this.$router.push("/login");
          })
          .catch(error => {
            console.log(error.response);
            alert("Neuspesno");
          });
    }
  },

  created() {
    console.log('radi')
    this.prikazi_korisnika();
  }
}
</script>

<style scoped>
*{
  font-family: 'Poppins', sans-serif;
  margin: 0; padding: 0;
  box-sizing: border-box;
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