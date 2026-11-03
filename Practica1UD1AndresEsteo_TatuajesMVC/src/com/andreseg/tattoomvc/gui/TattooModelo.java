package com.andreseg.tattoomvc.gui;

import com.andreseg.tattoomvc.base.Piercing;
import com.andreseg.tattoomvc.base.Servicio;
import com.andreseg.tattoomvc.base.Tatuaje;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TattooModelo {
    public ArrayList<Servicio> listaServicios;

    public TattooModelo() {
        listaServicios = new ArrayList<>();
    }

    public ArrayList<Servicio> obtenerServicios() {
        return listaServicios;
    }

    public void altaTatuaje(String cliente, String telefono, double precio, LocalDate fechaCita, String estilo, int tamanoCm) {
        Tatuaje nuevoTatuaje = new Tatuaje(cliente, telefono, precio, fechaCita, estilo, tamanoCm);
        listaServicios.add(nuevoTatuaje);
    }

    public void altaPiercing(String cliente, String telefono, double precio, LocalDate fechaCita, String zonaCuerpo, String material) {
        Piercing nuevoPiercing = new Piercing(cliente, telefono, precio, fechaCita, zonaCuerpo, material);
        listaServicios.add(nuevoPiercing);
    }

    public boolean existeCliente(String cliente) {
        for (Servicio unServicio : listaServicios) {
            if (unServicio.getCliente().equals(cliente)) {
                return true;
            }
        }
        return false;
    }

    public void bajaServicio(Servicio servicio) {
        listaServicios.remove(servicio);
    }

    public void exportar(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("Servicios");
        documento.getDocumentElement().appendChild(raiz);

        for (Servicio unServicio : listaServicios) {
            Element nodoServicio = (unServicio instanceof Tatuaje) ?
                    documento.createElement("Tatuaje") :
                    documento.createElement("Piercing");
            raiz.appendChild(nodoServicio);

            crearNodo(documento, nodoServicio, "cliente", unServicio.getCliente());
            crearNodo(documento, nodoServicio, "telefono", unServicio.getTelefono());
            crearNodo(documento, nodoServicio, "precio", String.valueOf(unServicio.getPrecio()));
            crearNodo(documento, nodoServicio, "fechaCita", unServicio.getFechaCita().toString());

            if (unServicio instanceof Tatuaje) {
                Tatuaje t = (Tatuaje) unServicio;
                crearNodo(documento, nodoServicio, "estilo", t.getEstilo());
                crearNodo(documento, nodoServicio, "tamanoCm", String.valueOf(t.getTamanoCm()));
            } else {
                Piercing p = (Piercing) unServicio;
                crearNodo(documento, nodoServicio, "zonaCuerpo", p.getZonaCuerpo());
                crearNodo(documento, nodoServicio, "material", p.getMaterial());
            }
        }

        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, resultado);
    }

    private void crearNodo(Document documento, Element padre, String nombre, String valor) {
        Element nodo = documento.createElement(nombre);
        nodo.appendChild(documento.createTextNode(valor));
        padre.appendChild(nodo);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaServicios.clear();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaTatuajes = documento.getElementsByTagName("Tatuaje");
        for (int i = 0; i < listaTatuajes.getLength(); i++) {
            Element nodo = (Element) listaTatuajes.item(i);
            Tatuaje t = new Tatuaje();
            t.setCliente(nodo.getElementsByTagName("cliente").item(0).getTextContent());
            t.setTelefono(nodo.getElementsByTagName("telefono").item(0).getTextContent());
            t.setPrecio(Double.parseDouble(nodo.getElementsByTagName("precio").item(0).getTextContent()));
            t.setFechaCita(LocalDate.parse(nodo.getElementsByTagName("fechaCita").item(0).getTextContent()));
            t.setEstilo(nodo.getElementsByTagName("estilo").item(0).getTextContent());
            t.setTamanoCm(Integer.parseInt(nodo.getElementsByTagName("tamanoCm").item(0).getTextContent()));
            listaServicios.add(t);
        }

        NodeList listaPiercings = documento.getElementsByTagName("Piercing");
        for (int i = 0; i < listaPiercings.getLength(); i++) {
            Element nodo = (Element) listaPiercings.item(i);
            Piercing p = new Piercing();
            p.setCliente(nodo.getElementsByTagName("cliente").item(0).getTextContent());
            p.setTelefono(nodo.getElementsByTagName("telefono").item(0).getTextContent());
            p.setPrecio(Double.parseDouble(nodo.getElementsByTagName("precio").item(0).getTextContent()));
            p.setFechaCita(LocalDate.parse(nodo.getElementsByTagName("fechaCita").item(0).getTextContent()));
            p.setZonaCuerpo(nodo.getElementsByTagName("zonaCuerpo").item(0).getTextContent());
            p.setMaterial(nodo.getElementsByTagName("material").item(0).getTextContent());
            listaServicios.add(p);
        }
    }
}