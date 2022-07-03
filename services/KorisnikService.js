import axios from "axios";

class KorisnikService{

    prikaziKorisnike(){
        return axios.get(`http://localhost:8080/api/listaKorisnika`)
    }

    registracija(){
        return axios.post(`http://localhost:8080/api/registacija`)
    }

    login(){
        return axios.post(`htpp://localthost:8080/api/login`)
    }




}

export default new KorisnikService();