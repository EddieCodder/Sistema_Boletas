import React from 'react';
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';

const BoletaDetails = ({ boleta, onHide }) => {
  // Realiza los cálculos necesarios
  const calcularDescuento = (valorDeVenta, descuento) => {
    return valorDeVenta - descuento;
  };

  const calcularIgv = (valorDeVenta) => {
    return valorDeVenta * 0.18; // Suponiendo que el IGV es 18%
  };

  const calcularPrecioDeVenta = (valorDeVenta, descuento, igv) => {
    return valorDeVenta - descuento + igv;
  };

  const valorDeVenta = boleta.valorDeVenta;
  const descuento = boleta.descuento;
  const igv = calcularIgv(valorDeVenta);
  const precioDeVenta = calcularPrecioDeVenta(valorDeVenta, descuento, igv);

  return (
    <Dialog header={`Detalles de la Boleta ${boleta.id}`} visible={true} onHide={onHide} style={{ width: '50vw' }}>
      <div>
        <h3>Detalles de la Boleta</h3>
        <p><strong>Cliente:</strong> {boleta.cliente}</p>
        <p><strong>Dirección:</strong> {boleta.direccion}</p>
        <p><strong>RUC:</strong> {boleta.ruc}</p>
        <p><strong>Fecha de Emisión:</strong> {boleta.fechaEmision}</p>
        <p><strong>Fecha de Vencimiento:</strong> {boleta.fechaVencimiento}</p>
        <p><strong>Localidad:</strong> {boleta.localidad}</p>
        <p><strong>Guía de Remisión:</strong> {boleta.guiaRemision}</p>
        <p><strong>Vendedor:</strong> {boleta.vendedor}</p>
        <p><strong>Forma de Pago:</strong> {boleta.formaDePago}</p>
        <p><strong>Descuento:</strong> {descuento.toFixed(2)}</p>
        <p><strong>Valor de Venta:</strong> {valorDeVenta.toFixed(2)}</p>
        <p><strong>IGV:</strong> {igv.toFixed(2)}</p>
        <p><strong>Precio de Venta:</strong> {precioDeVenta.toFixed(2)}</p>
        <h4>Detalles de los Artículos</h4>
        <DataTable value={boleta.detalles} paginator rows={10}>
          <Column field="articuloId" header="ID Artículo"></Column>
          <Column field="cantidad" header="Cantidad"></Column>
        </DataTable>
        <Button label="Cerrar" icon="pi pi-times" onClick={onHide} />
      </div>
    </Dialog>
  );
};

export default BoletaDetails;
