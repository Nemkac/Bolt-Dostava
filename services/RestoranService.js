import axios from "axios";

class RestoranService {
    findAll(){
        return axios.get(`http://localhost:8080/api/lista_restorana`);
    }

    findByNaziv(){
        return axios.post(`http://localhost:8080/api/restoran/pretraga/poImenu`);
    }

    izlistajArtikle(idRestorana){
        return axios.get(`http://localhost:8080/api/pregledArtikalaRestorana/`+idRestorana);
    }

}

export default new RestoranService();