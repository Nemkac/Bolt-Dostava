<template>
  <form>
    <h1>Registracija</h1>
    <div>
<!--      <label for="inputEmail4" class="form-label">Ime</label>-->
      Ime <input v-model="korisnik.ime" class="form-control" />
    </div>
    <div>
<!--      <label for="inputEmail4" class="form-label">Prezime</label>-->
      Prezime <input v-model="korisnik.prezime" class="form-control" />
    </div>
    <div>
<!--      <label for="inputEmail4" class="form-label">Korisničko ime</label>-->
      Korisnicko ime <input v-model="korisnik.korisnickoIme" class="form-control" />
    </div>

    <div>
<!--      <label for="inputPassword4" class="form-label">Lozinka</label>-->
      Lozinka <input v-model="korisnik.lozinka" type="password" class="form-control" />
    </div>
<!--    <label for="">Pol:</label> <br>-->
    Pol
    <form>
      <input v-model="korisnik.pol" type="radio"  value = "0"  /> Muski
      <br>
      <input v-model="korisnik.pol" type="radio" value = "1"  /> Zenski

    </form>
    <br>
    <label>Datum rodjenja:</label> <br>
    <div class="col-12">
      <input v-model="korisnik.datum" type="date" class="form-control" />
      <button v-on:click="registrujSe()" class="btn btn-primary">
        Prijava
      </button>
    </div>
  </form>
</template>

<script>
import axios from "axios";
export default {
  name: "RegisterView",
  data: function() {
    return {
      korisnik: {
        ime: "",
        prezime: "",
        korisnickoIme: "",
        lozinka: "",
        datum: "",
        pol: ""
      }
    };
  },
  methods: {
    registrujSe: function() {
      event.preventDefault()
      console.log(this.korisnik);
      axios
          .post("http://localhost:8080/api/registracija", this.korisnik)
          .then(res => {
            console.log(res);
            alert("Uspesno");
            this.$router.push("/prijava");
          })
          .catch(error => {
            console.log(error.response);
            alert("Neuspesno");
          });
    }
  }
};
</script>

<style>
h1 {
  color: #000000;
}
form {
  margin: 5% auto;
  width: 30%;
}
input {
  width: 30%;
  margin: 15px auto;
}
body {background-color: #f8d08b;}
</style>