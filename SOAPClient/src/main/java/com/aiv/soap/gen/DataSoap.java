
package com.aiv.soap.gen;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DataSoap", targetNamespace = "http://SOAP.covid.aiv.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DataSoap {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.DeleteResponse")
    public void delete(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getByID", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.GetByID")
    @ResponseWrapper(localName = "getByIDResponse", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.GetByIDResponse")
    public String getByID(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns com.aiv.soap.gen.DtoForSoap
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "create", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.CreateResponse")
    public DtoForSoap create(
        @WebParam(name = "arg0", targetNamespace = "")
        DtoForSoap arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns com.aiv.soap.gen.DtoForSoap
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "update", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.UpdateResponse")
    public DtoForSoap update(
        @WebParam(name = "arg0", targetNamespace = "")
        DtoForSoap arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @return
     *     returns java.util.List<com.aiv.soap.gen.DtoForSoap>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAll", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.GetAll")
    @ResponseWrapper(localName = "getAllResponse", targetNamespace = "http://SOAP.covid.aiv.com/", className = "com.aiv.soap.gen.GetAllResponse")
    public List<DtoForSoap> getAll();

}
