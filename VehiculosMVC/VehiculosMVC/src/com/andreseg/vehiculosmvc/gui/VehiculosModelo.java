package com.andreseg.vehiculosmvc.gui;

import com.andreseg.vehiculosmvc.base.Coche;
import com.andreseg.vehiculosmvc.base.Moto;
import com.andreseg.vehiculosmvc.base.Vehiculo;

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

public class VehiculosModelo {
    public ArrayList<Vehiculo> listaVehiculos;

    public VehiculosModelo() {
        listaVehiculos= new ArrayList<Vehiculo>();
    }

    public ArrayList<Vehiculo> obtenerVehiculos() {
        return listaVehiculos;
    }

    //altaCoche
    public void altaCoche(String matricula, String marca, String modelo,
                          LocalDate fechaMatriculacion,int numPlazas) {
        Coche nuevoCoche = new Coche(matricula,marca,modelo,fechaMatriculacion,numPlazas);
        listaVehiculos.add(nuevoCoche);
    }

    //altaMoto
    public void altaMoto(String matricula, String marca, String modelo,
                         LocalDate fechaMatriculacion,double kms) {
        Moto nuevaMoto = new Moto(matricula,marca,modelo,fechaMatriculacion,kms);
        listaVehiculos.add(nuevaMoto);
    }

    //existeMatricula
    public boolean existeMatricula(String matricula) {
        for (Vehiculo unVehiculo: listaVehiculos) {
            if (unVehiculo.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    //exportar
    public void exportar(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null,"xml",null);

        //añado el nodo raiz Vehiculos
        Element raiz = documento.createElement("Vehiculos");
        documento.getDocumentElement().appendChild(raiz);

        //otros elementos
        Element nodoVehiculo=null;
        Element nodoDatos=null;
        Text texto=null;

        for (Vehiculo unVehiculo: listaVehiculos) {
            //añado dentro de la etiqueta raiz vehiculos (coche o moto)
            if (unVehiculo instanceof Coche) {
                nodoVehiculo=documento.createElement("Coche");
            } else {
                nodoVehiculo=documento.createElement("Moto");
            }
            raiz.appendChild(nodoVehiculo);

            //dentro de cada coche, moto tengo que poner las etiquetas matricula, marca,...
            nodoDatos=documento.createElement("matricula");
            nodoVehiculo.appendChild(nodoDatos);

            texto=documento.createTextNode(unVehiculo.getMatricula());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("marca");
            nodoVehiculo.appendChild(nodoDatos);

            texto=documento.createTextNode(unVehiculo.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("modelo");
            nodoVehiculo.appendChild(nodoDatos);

            texto=documento.createTextNode(unVehiculo.getModelo());
            nodoDatos.appendChild(texto);

            nodoDatos=documento.createElement("fechaMatriculacion");
            nodoVehiculo.appendChild(nodoDatos);

            texto=documento.createTextNode(String.valueOf(unVehiculo.getFechaMatriculacion().toString()));
            nodoDatos.appendChild(texto);

            //nos falta el dato diferente (numeroPlazas, kms)
            if (unVehiculo instanceof Coche) {
                nodoDatos=documento.createElement("numeroPlazas");
                nodoVehiculo.appendChild(nodoDatos);

                texto=documento.createTextNode(String.valueOf(((Coche) unVehiculo).getNumPlazas()));
                nodoDatos.appendChild(texto);
            } else {
                nodoDatos=documento.createElement("kms");
                nodoVehiculo.appendChild(nodoDatos);

                texto=documento.createTextNode(String.valueOf(((Moto) unVehiculo).getKms()));
                nodoDatos.appendChild(texto);
            }

        }
        //guardo en fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);
    }

    //importar
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaVehiculos= new ArrayList<Vehiculo>();
        Coche nuevoCoche=null;
        Moto nuevaMoto=null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i=0;i<listaElementos.getLength();i++) {
            Element nodoVehiculo = (Element) listaElementos.item(i);
            if (nodoVehiculo.getTagName().equals("Coche")) {
                nuevoCoche = new Coche();
                nuevoCoche.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
                nuevoCoche.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
                nuevoCoche.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
                nuevoCoche.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
                nuevoCoche.setNumPlazas(Integer.parseInt(nodoVehiculo.getChildNodes().item(4).getTextContent()));
                listaVehiculos.add(nuevoCoche);
            } else {
                nuevaMoto = new Moto();
                nuevaMoto.setMatricula(nodoVehiculo.getChildNodes().item(0).getTextContent());
                nuevaMoto.setMarca(nodoVehiculo.getChildNodes().item(1).getTextContent());
                nuevaMoto.setModelo(nodoVehiculo.getChildNodes().item(2).getTextContent());
                nuevaMoto.setFechaMatriculacion(LocalDate.parse(nodoVehiculo.getChildNodes().item(3).getTextContent()));
                nuevaMoto.setKms(Double.parseDouble(nodoVehiculo.getChildNodes().item(4).getTextContent()));
                listaVehiculos.add(nuevaMoto);
            }

        }

    }

}
