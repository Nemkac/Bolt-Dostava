import axios from "axios";

class KorisnikService{

    prikaziKorisnike(){
        return axios.get(`http://localhost:8080/api/listaKorisnika`)
    }
}

export default new KorisnikService();