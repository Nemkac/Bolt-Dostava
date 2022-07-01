import axios from "axios";

class AdminService {
    napraviMenadzera(){
        return axios.post(`http://localhost:8080/api/kreiraj_menadzera`, {withCredentials: true});
    }

    napraviDostavljaca(){
        return axios.post(`http://localhost:8080/api/kreiraj_dostavljaca`,{withCredentials: true});
    }

    napraviRestoran(){
        return axios.post(`http://localhost:8080/api/kreiraj_restoran`,{withCredentials: true});
    }

    listaSvihKorisnika(){
        return axios.get(`http://localhost:8080/api/pregledSvihKorisnika`,{withCredentials: true});
    }
}

export default new AdminService();