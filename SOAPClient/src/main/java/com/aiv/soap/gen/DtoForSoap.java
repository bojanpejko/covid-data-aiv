
package com.aiv.soap.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtoForSoap complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtoForSoap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adminID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hospitalized" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="infected" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numOfInhabitants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="regionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://SOAP.covid.aiv.com/}zoneStatus" minOccurs="0"/>
 *         &lt;element name="tested" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtoForSoap", propOrder = {
    "adminID",
    "hospitalized",
    "id",
    "infected",
    "name",
    "numOfInhabitants",
    "regionID",
    "status",
    "tested"
})
public class DtoForSoap {

    protected String adminID;
    protected int hospitalized;
    protected String id;
    protected int infected;
    protected String name;
    protected int numOfInhabitants;
    protected String regionID;
    @XmlSchemaType(name = "string")
    protected ZoneStatus status;
    protected int tested;

    /**
     * Gets the value of the adminID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdminID() {
        return adminID;
    }

    /**
     * Sets the value of the adminID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdminID(String value) {
        this.adminID = value;
    }

    /**
     * Gets the value of the hospitalized property.
     * 
     */
    public int getHospitalized() {
        return hospitalized;
    }

    /**
     * Sets the value of the hospitalized property.
     * 
     */
    public void setHospitalized(int value) {
        this.hospitalized = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the infected property.
     * 
     */
    public int getInfected() {
        return infected;
    }

    /**
     * Sets the value of the infected property.
     * 
     */
    public void setInfected(int value) {
        this.infected = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the numOfInhabitants property.
     * 
     */
    public int getNumOfInhabitants() {
        return numOfInhabitants;
    }

    /**
     * Sets the value of the numOfInhabitants property.
     * 
     */
    public void setNumOfInhabitants(int value) {
        this.numOfInhabitants = value;
    }

    /**
     * Gets the value of the regionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionID() {
        return regionID;
    }

    /**
     * Sets the value of the regionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionID(String value) {
        this.regionID = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link ZoneStatus }
     *     
     */
    public ZoneStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZoneStatus }
     *     
     */
    public void setStatus(ZoneStatus value) {
        this.status = value;
    }

    /**
     * Gets the value of the tested property.
     * 
     */
    public int getTested() {
        return tested;
    }

    /**
     * Sets the value of the tested property.
     * 
     */
    public void setTested(int value) {
        this.tested = value;
    }

}
