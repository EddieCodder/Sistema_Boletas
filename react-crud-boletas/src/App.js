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
      articles: [],           // Estado para guardar los artículos
      expandedRows: null
    };

    this.boletaService = new BoletaService();
    this.rowExpansionTemplate = this.rowExpansionTemplate.bind(this);
    this.handlePrint = this.handlePrint.bind(this);
  }

  componentDidMount() {
    // Obtener boletas y artículos en paralelo
    Promise.all([
      this.boletaService.getAll(),
      this.boletaService.getArticles()
    ])
    .then(([boletas, articles]) => {
      this.setState({ boletas, articles });
    })
    .catch(error => {
      console.error("Error al obtener datos:", error);
    });
  }

  rowExpansionTemplate(data) {
    // Mapear los IDs de artículos a nombres
    const articleMap = this.state.articles.reduce((map, article) => {
      map[article.id] = article.nombre;
      return map;
    }, {});

    // Agregar nombre del artículo a los detalles
    const detallesConNombre = data.detalles.map(detalle => ({
      ...detalle,
      nombreArticulo: articleMap[detalle.articuloId] || 'Desconocido'
    }));

    return (
      <div>
        <h4>Detalles de Artículos</h4>
        <DataTable value={detallesConNombre} responsiveLayout="scroll">
          <Column field="id" header="ID"></Column>
          <Column field="articuloId" header="ID Artículo"></Column>
          <Column field="nombreArticulo" header="Nombre Artículo"></Column>
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
