import React, { Component } from 'react';
import './App.css';
import { BoletaService } from './service/BoletaService';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Panel } from 'primereact/panel';
import { Button } from 'primereact/button';
import 'primereact/resources/themes/saga-blue/theme.css';  // Importa el tema
import 'primereact/resources/primereact.min.css';          // Importa los estilos de PrimeReact
import 'primeicons/primeicons.css';                        // Importa los iconos de PrimeIcons

export default class App extends Component {
  constructor() {
    super();
    this.state = {
      boletas: [],
      expandedRows: null
    };

    this.boletaService = new BoletaService();
    this.rowExpansionTemplate = this.rowExpansionTemplate.bind(this);
    this.handlePrint = this.handlePrint.bind(this);
  }

  componentDidMount() {
    this.boletaService.getAll().then(data => {
      console.log(data);
      this.setState({ boletas: data });
    }).catch(error => {
      console.error("Error al obtener boletas:", error);
    });
  }

  rowExpansionTemplate(data) {
    return (
      <div>
        <h4>Detalles de Artículos</h4>
        <DataTable value={data.detalles} responsiveLayout="scroll">
          <Column field="id" header="ID"></Column>
          <Column field="articuloId" header="ID Artículo"></Column>
          <Column field="cantidad" header="Cantidad"></Column>
        </DataTable>
      </div>
    );
  }

  handlePrint(id) {
    // Redirige a la página de impresión
    window.location.href = `/print/${id}`;
  }

  render() {
    const { boletas } = this.state;

    return (
      <div>
        <Panel header="Lista de Boletas" style={{ marginBottom: '20px' }}>
          <DataTable
            value={boletas}
            responsiveLayout="scroll"
            expandedRows={this.state.expandedRows}
            onRowToggle={(e) => this.setState({ expandedRows: e.data })}
            rowExpansionTemplate={this.rowExpansionTemplate}
            dataKey="id"
          >
            <Column expander style={{ width: '3em' }} />
            <Column field="id" header="ID Boleta"></Column>
            <Column field="cliente" header="Cliente"></Column>
            <Column field="direccion" header="Dirección"></Column>
            <Column field="ruc" header="RUC"></Column>
            <Column field="total" header="Total"></Column>
            <Column field="fechaEmision" header="Fecha de Emisión"></Column>
            <Column field="fechaVencimiento" header="Fecha de Vencimiento"></Column>
            <Column body={(rowData) => (
              <Button label="Imprimir" icon="pi pi-print" onClick={() => this.handlePrint(rowData.id)} />
            )} header="Acciones" style={{ textAlign: 'center' }}></Column>
          </DataTable>
        </Panel>
      </div>
    );
  }
}
