/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaarchivosdelaalianzarebelde;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 *
 * @author jfabr
 */
@XmlRootElement(name="Personaje")
@XmlType(propOrder={"nombre","faccion","rango"})
public class Personaje 
{
    private String id;
    private String nombre;
    private String faccion;
    private String rango;

    public Personaje() {
    }

    public Personaje(String id, String nombre, String faccion, String rango) {
        this.id = id;
        this.nombre = nombre;
        this.faccion = faccion;
        this.rango = rango;
    }
    
    @XmlAttribute(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @XmlElement(name="nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @XmlElement(name="faccion")
    public String getFaccion() {
        return faccion;
    }

    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }
    @XmlElement(name="rango")
    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    @Override
    public String toString() {
        return "Personaje{" + "id=" + id + ", nombre=" + nombre + ", faccion=" + faccion + ", rango=" + rango + '}';
    }
    
    


}
