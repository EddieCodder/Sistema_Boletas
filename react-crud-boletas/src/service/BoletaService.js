// BoletaService.js
import axios from 'axios';

export class BoletaService {
    baseUrl = 'http://localhost:8080/api/boletas';
    articlesUrl = 'http://localhost:8080/api/articulos';  // URL para obtener los artículos

    // Método para obtener todas las boletas
    getAll() {
        return axios.get(this.baseUrl)
            .then(res => res.data)
            .catch(error => {
                console.error('Error al consumir el API de boletas:', error);
                throw error;
            });
    }

    // Método para obtener todos los artículos
    getArticles() {
        return axios.get(this.articlesUrl)
            .then(res => res.data)
            .catch(error => {
                console.error('Error al consumir el API de artículos:', error);
                throw error;
            });
    }
}
