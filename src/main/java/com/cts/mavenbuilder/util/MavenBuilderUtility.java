package com.cts.mavenbuilder.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import com.cts.mavenbuilder.jaxbclasses.Model;
import com.cts.mavenbuilder.jaxbclasses.ObjectFactory;

public class MavenBuilderUtility {

	public static String format(String format, Map<String, Object> values)
	{
	    StringBuilder formatter = new StringBuilder(format);
	    List<Object> valueList = new ArrayList<Object>();

	    Matcher matcher = Pattern.compile("\\$\\{(\\w+)}").matcher(format);

	    while (matcher.find())
	    {
	        String key = matcher.group(1);

	        String formatKey = String.format("${%s}", key);
	        int index = formatter.indexOf(formatKey);

	        if (index != -1)
	        {
	            formatter.replace(index, index + formatKey.length(), "%s");
	            valueList.add(values.get(key));
	        }
	    }

	    return String.format(formatter.toString(), valueList.toArray());
	}
	
	@SuppressWarnings("unchecked")
	public static Model getRootElement(String path) throws JAXBException{
	    
		//1. We need to create JAXContext instance
	    
		JAXBContext jaxbContext = JAXBContext.newInstance(com.cts.mavenbuilder.jaxbclasses.ObjectFactory.class);

	    //2. Use JAXBContext instance to create the Unmarshaller.
	    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

	    //3. Use the Unmarshaller to unmarshal the XML document to get an instance of JAXBElement.
	    String basedir = System.getProperty("user.dir");
	    File file =  new File(basedir+path);
	    @SuppressWarnings("unchecked")
	    JAXBElement<Model>  unmarshalledObject =null;
	    try {
	    	
	     
	    	unmarshalledObject= (JAXBElement<Model>)unmarshaller.unmarshal(file);
	    	System.out.println("*************************" +unmarshalledObject.getName().getNamespaceURI());
	    }
		catch(JAXBException e) {
			System.out.println("Error in executing the command" + e);
		} 
		
	    //4. Get the instance of the required JAXB Root Class from the JAXBElement.
	    Model model = unmarshalledObject.getValue();

		return model;
	}
	
	public static void marshalModelToPom (Model model, String path) throws JAXBException, FileNotFoundException {
		 JAXBContext contextObj = JAXBContext.newInstance(ObjectFactory.class);  
 	    Marshaller marshallerObj = contextObj.createMarshaller();  
 	    QName _Project_QNAME = new QName("http://maven.apache.org/POM/4.0.0", "project");    
 	    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
		  
		    marshallerObj.marshal(new JAXBElement<Model>(_Project_QNAME, Model.class, null, model), new FileOutputStream(path));  
	}
}
