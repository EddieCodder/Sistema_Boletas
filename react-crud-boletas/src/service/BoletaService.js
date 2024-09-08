// BoletaService.js
import axios from 'axios';

export class BoletaService {
    baseUrl = 'http://localhost:8080/api/boletas';
    articlesUrl = 'http://localhost:8080/api/articulos';  // URL para obtener los artículos

    getAll() {
        return axios.get(this.baseUrl)
            .then(res => res.data)
            .catch(error => {
                console.error('Error al consumir el API:', error);
                throw error;
            });
    }

    getArticles() {
        return axios.get(this.articlesUrl)
            .then(res => res.data)
            .catch(error => {
                console.error('Error al consumir el API de artículos:', error);
                throw error;
            });
    }
}
