package com.cts.mavenbuilder.jaxbclasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsdlOption", propOrder = {
   "wsdl"
})
public class WsdlOption {
	
	protected String wsdl;

	/**
	 * @return the wsdl
	 */
	public String getWsdl() {
		return wsdl;
	}

	/**
	 * @param wsdl the wsdl to set
	 */
	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}

}
