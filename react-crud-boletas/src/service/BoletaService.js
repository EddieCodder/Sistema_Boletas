import axios from 'axios';

export class BoletaService {
    baseUrl = 'http://localhost:8080/api/boletas';

    // Para consumir nuestra api
    getAll() {
        return axios.get(this.baseUrl)  // Cambié a "all" como en tu ejemplo
            .then(res => res.data)  // Aquí corregí el acceso a `res.data`
            .catch(error => {
                console.error('Error al consumir el API:', error);
                throw error;
            });
    }
}