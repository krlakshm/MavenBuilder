package com.cts.mavenbuilder.jaxbclasses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "wsdlOption"
})
public class WsdlOptions {

	protected List<WsdlOption> wsdlOption;
	
	/**
     * Gets the value of the developer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the developer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeveloper().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Developer }
     * 
     * 
     */
    public List<WsdlOption> getWsdlOption() {
        if (wsdlOption == null) {
        	wsdlOption = new ArrayList<WsdlOption>();
        }
        return this.wsdlOption;
    }


}
