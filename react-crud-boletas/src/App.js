// src/App.js
import React, { Component } from 'react';
import './App.css';
import { BoletaService } from './service/BoletaService';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Panel } from 'primereact/panel';
import { Link } from 'react-router-dom'; // Importa el componente Link de React Router
import 'primereact/resources/themes/saga-blue/theme.css';  // Importa el tema
import 'primereact/resources/primereact.min.css';          // Importa los estilos de PrimeReact
import 'primeicons/primeicons.css';                        // Importa los iconos de PrimeIcons

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
      this.setState({ boletas: data });  // Guardar los datos en el estado
    }).catch(error => {
      console.error("Error al obtener boletas:", error);
    });
  }

  render() {
    return (
      <div>
        <Panel header="Lista de Boletas" style={{ margin: '20px' }}>
          <DataTable value={this.state.boletas} paginator rows={10} responsiveLayout="scroll">
            <Column field="id" header="ID Boleta"></Column>
            <Column field="cliente" header="Cliente"></Column>
            <Column field="direccion" header="Dirección"></Column>
            <Column field="ruc" header="RUC"></Column>
            <Column field="total" header="Total"></Column>
            <Column field="fechaEmision" header="Fecha de Emisión"></Column>
            <Column field="fechaVencimiento" header="Fecha de Vencimiento"></Column>
            <Column body={(rowData) => (
              <Link to={`/print/${rowData.id}`}>
                <button className="p-button p-component p-button-outlined">
                  Imprimir
                </button>
              </Link>
            )} header="Acciones" style={{ textAlign: 'center' }}></Column>
          </DataTable>
        </Panel>
      </div>
    );
  }
}
