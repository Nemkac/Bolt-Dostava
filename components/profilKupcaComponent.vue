<template >
  <form>
    <h1>Podaci o korisniku</h1>
    <div>
      Broj bodova {{korisnik.brBodova}}
      <br>
      Tip: {{tipKorisnika}}
    </div>
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
      Lozinka <input v-model="korisnik.lozinka" type="password" class="form-control" />
    </div>

    <br>
    <div class="col-12">
      <button  v-on:click="izmeniKorisnika()" class="btn btn-primary">
        Izmeni podatke
      </button>
    </div>
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
            this.$router.push("/prijava");
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

</style>