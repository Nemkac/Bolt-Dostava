<template>
  <div class="wrapperRegister">
    <div class="logo">
      <img src="../assets/Bolt-dostava-logo.png" alt="">
    </div>
    <div class="text-center mt-4 name">
      Register
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
      <div class="form-field d-flex align-items-center">
        <span class="far fa-user"></span>
        <input v-model="korisnik.ime" type="text" name="ime" id="ime" placeholder="Ime">
      </div>
      <div class="form-field d-flex align-items-center">
        <span class="far fa-user"></span>
        <input v-model="korisnik.prezime" type="text" name="prezime" id="prezime" placeholder="Prezime">
      </div>
      <p>Pol</p>
      <div class="Pol">
        <span class="far fa-user"></span>
        <input v-model="korisnik.pol" type="radio" name="polm" id="PolM" value="0"/>Musko
        <input v-model="korisnik.pol" type="radio" name="polz" id="PolZ" value="1"/>Zensko
      </div>
      <div class="Datum">
        <p>Datum Rodjenja</p>
        <input v-model="korisnik.datum" type="date" class="form-control" name="datum" id="datum">
      </div>
      <br>
      <button v-on:click="this.registrujSe()" class="btn mt-3">Register</button>
    </form>
    <div class="text-center fs-6">
      <router-link to = "/login"><a href="#">Vec imate profil? Ulogujte se</a></router-link>
    </div>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: "RegisterComponent",
  data: function () {
    return {
      korisnik: {
        korisnickoIme: "",
        lozinka: "",
        ime: "",
        prezime: "",
        pol: "",
        datum: ""
      }
    };
  },
  methods: {
    registrujSe: function() {
      console.log(this.korisnik);
      axios
          .post("http://localhost:8080/api/registracija", this.korisnik)
          .then(res => {
            console.log(res);
            alert("Uspesno")
            this.$router.push("/login");
          })
          .catch(error => {
            console.log(error.response);
            alert("Neuspesno");
          });
    }
  }
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

.wrapperRegister {
  max-width: 350px;
  min-height: 500px;
  margin: 80px auto;
  padding: 40px 30px 30px 30px;
  background-color: #ecf0f3;
  border-radius: 15px;
  box-shadow: 13px 13px 20px #cbced1, -13px -13px 20px #fff;
}

.logo {
  width: 80px;
  margin: auto;
}

.logo img {
  width: 100%;
  height: 80px;
  object-fit: cover;
  border-radius: 50%;
  box-shadow: 0px 0px 3px #5f5f5f,
  0px 0px 0px 5px #ecf0f3,
  8px 8px 15px #a7aaa7,
  -8px -8px 15px #fff;
}

.wrapperRegister .name {
  font-weight: 600;
  font-size: 1.4rem;
  letter-spacing: 1.3px;
  padding-left: 10px;
  color: #01072a;
}

.wrapperRegister .form-field input {
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

.wrapperRegister .form-field {
  padding-left: 10px;
  margin-bottom: 20px;
  border-radius: 20px;
  box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
}

.wrapperRegister .form-field .fas {
  color: #555;
}

.wrapperRegister .btn {
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

.wrapperRegister .btn:hover {
  background-color: #fdbf00;
}

.wrapperRegister a {
  text-decoration: none;
  font-size: 0.8rem;
  color: #00214f;
}

.wrapperRegister a:hover {
  color: #01072a;
}

.wrapperRegister .Pol{
  padding-left: 10px;
  margin-bottom: 20px;
  border-radius: 20px;
  box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
}

@media(max-width: 380px) {
  .wrapper {
    margin: 30px 20px;
    padding: 40px 15px 15px 15px;
  }
}
</style>