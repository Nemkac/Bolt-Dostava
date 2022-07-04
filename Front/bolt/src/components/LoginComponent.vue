<template>
  <html>
  <html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
  </head>
  <body>
  <div class="wrapper">
    <div class="logoLogin">
      <img src="../assets/Bolt-dostava-logo.png" alt="">
    </div>
    <div class="text-center mt-4 name">
      Login
    </div>
    <form class="p-3 mt-3">
      <div class="form-field d-flex align-items-center">
        <span class="far fa-user"></span>
        <input v-model="korisnik.korisnickoIme" type="text" name="korisnickoIme" id="korisnickoIme" placeholder="Korisnicko Ime">
      </div>
      <div class="form-field d-flex align-items-center">
        <span class="fas fa-key"></span>
        <input v-model="korisnik.lozinka" type="password" name="lozinka" id="lozinka" placeholder="Lozinka">
      </div>
      <button v-on:click="this.prijaviSe()" class="btn mt-3">Login</button>
    </form>
    <div class="text-center fs-6">
      <a href="#">Zaboravljena lozinka?</a> ILI <router-link to = "/register"><a href="#">Registracija?</a></router-link>
    </div>
  </div>
  </body>
  </html>
  </html>
</template>

<script>
import axios from "axios"

export default {
  name: "LogiN",
  data: function () {
    return {
      korisnik: {
        korisnickoIme: "",
        lozinka: ""
      },
    };
  },
  methods: {
    prijaviSe: function () {
      console.log(this.korisnik);
      event.preventDefault();
      axios
        .post("http://localhost:8080/api/login",this.korisnik, {credentials: 'include',})
        .then(res =>{
          console.log(res.data);
          sessionStorage.setItem('korisnickoIme', res.data.korisnickoIme)
          sessionStorage.setItem('ulogovaniKorisnikID', res.data.id)
          sessionStorage.setItem('uloga', res.data.uloga)
          alert("Uspesno");

          if (res.data.uloga === 'ADMIN'){
            this.$router.push("/admin");
          }
          else if (res.data.uloga === 'MENADZER'){
            sessionStorage.setItem('idRestorana',res.data.restoran.id)
            this.$router.push("/menadzerPrikazPorudzbina");
          }
          else if (res.data.uloga === 'DOSTAVLJAC'){
            this.$router.push("/dostavljacPrikazPorudzbina");
          }
          else{
            this.$router.push("/pocetna");
          }

        })
        .catch(error =>{
          alert(error.response);
          console.log(error);
          console.log(error.response);
          alert("Neuspesno");
          this.$router.push("/Login");
        });
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');

/* Reseting */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

.wrapper {
  max-width: 350px;
  min-height: 500px;
  margin: 80px auto;
  padding: 40px 30px 30px 30px;
  background-color: #ecf0f3;
  border-radius: 15px;
  box-shadow: 13px 13px 20px #cbced1, -13px -13px 20px #fff;
}

.logoLogin {
  width: 80px;
  margin: auto;
}

.logoLogin img {
  width: 100%;
  height: 80px;
  object-fit: cover;
  border-radius: 50%;
  box-shadow: 0px 0px 3px #5f5f5f,
  0px 0px 0px 5px #ecf0f3,
  8px 8px 15px #a7aaa7,
  -8px -8px 15px #fff;
}

.wrapper .name {
  font-weight: 600;
  font-size: 1.4rem;
  letter-spacing: 1.3px;
  padding-left: 10px;
  color: #01072a;
}

.wrapper .form-field input {
  width: 100%;
  display: block;
  border: none;
  outline: none;
  background: none;
  font-size: 1.2rem;
  color: #666;
  padding: 10px 15px 10px 10px;
  /* border: 1px solid red; */
}

.wrapper .form-field {
  padding-left: 10px;
  margin-bottom: 20px;
  border-radius: 20px;
  box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
}

.wrapper .form-field .fas {
  color: #555;
}

.wrapper .btn {
  box-shadow: none;
  width: 100%;
  height: 40px;
  background-color: #ffd74a;
  color: #fff;
  border-radius: 25px;
  box-shadow: 3px 3px 3px #b1b1b1,
  -3px -3px 3px #fff;
  letter-spacing: 1.3px;
}

.wrapper .btn:hover {
  background-color: #fdbf00;
}

.wrapper a {
  text-decoration: none;
  font-size: 0.8rem;
  color: #00214f;
}

.wrapper a:hover {
  color: #01072a;
}

@media(max-width: 380px) {
  .wrapper {
    margin: 30px 20px;
    padding: 40px 15px 15px 15px;
  }
}
</style>