
package com.aiv.soap.gen;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zoneStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="zoneStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GREEN"/>
 *     &lt;enumeration value="YELLOW"/>
 *     &lt;enumeration value="ORANGE"/>
 *     &lt;enumeration value="RED"/>
 *     &lt;enumeration value="BLACK"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "zoneStatus")
@XmlEnum
public enum ZoneStatus {

    GREEN,
    YELLOW,
    ORANGE,
    RED,
    BLACK;

    public String value() {
        return name();
    }

    public static ZoneStatus fromValue(String v) {
        return valueOf(v);
    }

}
