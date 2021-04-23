
package com.aiv.soap.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.aiv.soap.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Delete_QNAME = new QName("http://SOAP.covid.aiv.com/", "delete");
    private final static QName _UpdateResponse_QNAME = new QName("http://SOAP.covid.aiv.com/", "updateResponse");
    private final static QName _Create_QNAME = new QName("http://SOAP.covid.aiv.com/", "create");
    private final static QName _CreateResponse_QNAME = new QName("http://SOAP.covid.aiv.com/", "createResponse");
    private final static QName _Update_QNAME = new QName("http://SOAP.covid.aiv.com/", "update");
    private final static QName _DeleteResponse_QNAME = new QName("http://SOAP.covid.aiv.com/", "deleteResponse");
    private final static QName _GetByID_QNAME = new QName("http://SOAP.covid.aiv.com/", "getByID");
    private final static QName _GetByIDResponse_QNAME = new QName("http://SOAP.covid.aiv.com/", "getByIDResponse");
    private final static QName _GetAllResponse_QNAME = new QName("http://SOAP.covid.aiv.com/", "getAllResponse");
    private final static QName _GetAll_QNAME = new QName("http://SOAP.covid.aiv.com/", "getAll");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.aiv.soap.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAll }
     * 
     */
    public GetAll createGetAll() {
        return new GetAll();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link GetByID }
     * 
     */
    public GetByID createGetByID() {
        return new GetByID();
    }

    /**
     * Create an instance of {@link GetByIDResponse }
     * 
     */
    public GetByIDResponse createGetByIDResponse() {
        return new GetByIDResponse();
    }

    /**
     * Create an instance of {@link GetAllResponse }
     * 
     */
    public GetAllResponse createGetAllResponse() {
        return new GetAllResponse();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link Delete }
     * 
     */
    public Delete createDelete() {
        return new Delete();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link DtoForSoap }
     * 
     */
    public DtoForSoap createDtoForSoap() {
        return new DtoForSoap();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Delete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "delete")
    public JAXBElement<Delete> createDelete(Delete value) {
        return new JAXBElement<Delete>(_Delete_QNAME, Delete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "deleteResponse")
    public JAXBElement<DeleteResponse> createDeleteResponse(DeleteResponse value) {
        return new JAXBElement<DeleteResponse>(_DeleteResponse_QNAME, DeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByID }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "getByID")
    public JAXBElement<GetByID> createGetByID(GetByID value) {
        return new JAXBElement<GetByID>(_GetByID_QNAME, GetByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByIDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "getByIDResponse")
    public JAXBElement<GetByIDResponse> createGetByIDResponse(GetByIDResponse value) {
        return new JAXBElement<GetByIDResponse>(_GetByIDResponse_QNAME, GetByIDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "getAllResponse")
    public JAXBElement<GetAllResponse> createGetAllResponse(GetAllResponse value) {
        return new JAXBElement<GetAllResponse>(_GetAllResponse_QNAME, GetAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SOAP.covid.aiv.com/", name = "getAll")
    public JAXBElement<GetAll> createGetAll(GetAll value) {
        return new JAXBElement<GetAll>(_GetAll_QNAME, GetAll.class, null, value);
    }

}
