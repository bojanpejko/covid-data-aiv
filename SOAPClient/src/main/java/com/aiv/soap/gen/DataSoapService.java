
package com.aiv.soap.gen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DataSoapService", targetNamespace = "http://SOAP.covid.aiv.com/", wsdlLocation = "http://localhost:8080/covid-1.0-SNAPSHOT/DataSoap?wsdl")
public class DataSoapService
    extends Service
{

    private final static URL DATASOAPSERVICE_WSDL_LOCATION;
    private final static WebServiceException DATASOAPSERVICE_EXCEPTION;
    private final static QName DATASOAPSERVICE_QNAME = new QName("http://SOAP.covid.aiv.com/", "DataSoapService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/covid-1.0-SNAPSHOT/DataSoap?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DATASOAPSERVICE_WSDL_LOCATION = url;
        DATASOAPSERVICE_EXCEPTION = e;
    }

    public DataSoapService() {
        super(__getWsdlLocation(), DATASOAPSERVICE_QNAME);
    }

    public DataSoapService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DATASOAPSERVICE_QNAME, features);
    }

    public DataSoapService(URL wsdlLocation) {
        super(wsdlLocation, DATASOAPSERVICE_QNAME);
    }

    public DataSoapService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DATASOAPSERVICE_QNAME, features);
    }

    public DataSoapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DataSoapService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DataSoap
     */
    @WebEndpoint(name = "DataSoapPort")
    public DataSoap getDataSoapPort() {
        return super.getPort(new QName("http://SOAP.covid.aiv.com/", "DataSoapPort"), DataSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DataSoap
     */
    @WebEndpoint(name = "DataSoapPort")
    public DataSoap getDataSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://SOAP.covid.aiv.com/", "DataSoapPort"), DataSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DATASOAPSERVICE_EXCEPTION!= null) {
            throw DATASOAPSERVICE_EXCEPTION;
        }
        return DATASOAPSERVICE_WSDL_LOCATION;
    }

}
