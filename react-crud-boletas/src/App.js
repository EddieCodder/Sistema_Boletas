import React, { Component } from 'react';
import './App.css';
import { BoletaService } from './service/BoletaService';

export default class App extends Component {
  constructor() {
    super();
    this.state = {
      boletas: []  // Inicializamos el estado correctamente
    };

    this.boletaService = new BoletaService();
  }

  componentDidMount() {
    this.boletaService.getAll().then(data => {
      console.log(data);
      this.setState({ boletas: data });  // Guardar los datos en el estado
    }).catch(error => {
      console.error("Error al obtener boletas:", error);
    });
  }

  render() {
    return (
      <div>
        <h1>Lista de Boletas</h1>
        <ul>
          {this.state.boletas.map(boleta => (
            <li key={boleta.id}>
              Cliente: {boleta.cliente}, Total: {boleta.total}
            </li>
          ))}
        </ul>
      </div>
    );
  }
}
