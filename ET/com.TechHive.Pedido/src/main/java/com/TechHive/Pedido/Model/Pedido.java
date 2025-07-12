package com.TechHive.Pedido.Model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroPedido;
    private String rutCliente;
    private String nombreVendedor;
    private String nombreCliente;
    private String direccionEntrega;
    private String telefono;
    private LocalDate fechaPedido;
    private String estado;
    private String metodoPago;

    @Size(max = 255, message = "La descripción del pedido no debe superar los 255 caracteres")
    private String detallesPedido;

    private double totalPedido;

    // Constructores
    public Pedido() {}

    public Pedido(String numeroPedido, String rutCliente, String nombreVendedor, String nombreCliente,
                String direccionEntrega, String telefono, LocalDate fechaPedido, String estado,
                String metodoPago, String detallesPedido, double totalPedido) {
        this.numeroPedido = numeroPedido;
        this.rutCliente = rutCliente;
        this.nombreVendedor = nombreVendedor;
        this.nombreCliente = nombreCliente;
        this.direccionEntrega = direccionEntrega;
        this.telefono = telefono;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.detallesPedido = detallesPedido;
        this.totalPedido = totalPedido;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String getDetallesPedido() {
        return detallesPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setDetallesPedido(String detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }
}
