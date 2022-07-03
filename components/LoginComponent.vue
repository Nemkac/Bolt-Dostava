<template>
  <form>
    <h1>Prijava</h1>
    <div>
<!--      <label htmlFor="inputEmail4" className="form-label">Korisničko ime</label>-->
      Korisnicko ime <input v-model="korisnik.korisnickoIme" className="form-control"/>
    </div>

    <div>
<!--      <label htmlFor="inputPassword4" className="form-label">Lozinka</label>-->
      Lozinka <input v-model="korisnik.lozinka" type="password" className="form-control"/>
    </div>

    <div className="col-12">
      <button v-on:click="this.prijaviSe()" className="btn btn-primary">Prijava</button>
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
      console.log(this.korisnik);
      event.preventDefault();
      axios
          .post("http://localhost:8080/api/login", this.korisnik, {
            credentials: 'include',
           // withCredentials: 'True',
           // mode: 'no-cors'
          })
          .then(res => {
            //this.$session.start()
            console.log(res.data);
            sessionStorage.setItem('korisnickoIme', res.data.korisnickoIme)
            sessionStorage.setItem('ulogovaniKorisnikID', res.data.id)
            sessionStorage.setItem('uloga', res.data.uloga)
            sessionStorage.setItem('idRestorana',res.data.restoran.id)
            alert("Uspesno");
            if (res.data.uloga === 'ADMIN'){
              this.$router.push("/admin");
            } else{
              this.$router.push("/listaRestorana");
            }

          })
          .catch(error => {
            alert(error.response);
            console.log(error);
            console.log(error.response);
            alert("Neuspesno");
          });
    },
    prikazi_korisnika: function (){
      let korisnickoIme = sessionStorage.getItem('korisnickoIme');
      axios
          .get("http://localhost:8080/api/prikaz_podataka/" + korisnickoIme, {
            credentials: 'include'
          })
          .then(res => {
            // this.$session.start()
            alert("Uspesno");
            console.log(res);
          })
          .catch(error => {
            alert("Neuspesno");
            console.log(error.response);
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
body {background-color: #c9de59;}
</style>
