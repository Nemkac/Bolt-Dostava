<template>
  <form>
    <h1>Prijava</h1>
    <div>
      <label htmlFor="inputEmail4" className="form-label">Korisničko ime</label>
      <input v-model="korisnik.korisnickoIme" className="form-control"/>
    </div>

    <div>
      <label htmlFor="inputPassword4" className="form-label">Lozinka</label>
      <input v-model="korisnik.lozinka" type="password" className="form-control"/>
    </div>

    <div className="col-12">
      <button v-on:click="prijaviSe()" className="btn btn-primary">Prijava</button>
    </div>
  </form>
</template>

<script>
import axios from "axios";

export default {
  name: "LoginView",
  data: function () {
    return {
      korisnik: {
        korisnickoIme: "",
        lozinka: ""
      }
    };
  },
  methods: {
    prijaviSe: function () {
      axios
          .post("http://localhost:8080/api/login", this.korisnik, {
            withCredentials: true
          })
          .then(res => {
            console.log(res);
            alert("Uspesno");
            this.$router.push("/admin");
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
  color: cornflowerblue;
  margin-top: 10%;
}

form {
  margin: auto;
}

input {
  width: 30%;
  margin: 5px auto;
}
</style>
