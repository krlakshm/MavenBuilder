/**
 * 
 */
package com.cts.mavenbuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import com.cts.mavenbuilder.jaxbclasses.Build;
import com.cts.mavenbuilder.jaxbclasses.Build.Plugins;
import com.cts.mavenbuilder.jaxbclasses.Dependency;
import com.cts.mavenbuilder.jaxbclasses.Exclusion;
import com.cts.mavenbuilder.jaxbclasses.Model;
import com.cts.mavenbuilder.jaxbclasses.Model.Dependencies;
import com.cts.mavenbuilder.jaxbclasses.Model.Properties;
import com.cts.mavenbuilder.jaxbclasses.Parent;
import com.cts.mavenbuilder.jaxbclasses.Plugin;
import com.cts.mavenbuilder.jaxbclasses.Plugin.Executions;
import com.cts.mavenbuilder.jaxbclasses.PluginExecution;
import com.cts.mavenbuilder.jaxbclasses.PluginExecution.Configuration;
import com.cts.mavenbuilder.jaxbclasses.PluginExecution.Goals;
import com.cts.mavenbuilder.jaxbclasses.WsdlOption;
import com.cts.mavenbuilder.jaxbclasses.WsdlOptions;
import com.cts.mavenbuilder.util.IMavenBuilderConstants;
import com.cts.mavenbuilder.util.MavenBuilderUtility;

/**
 * @author 153780
 *
 */
public class MavenBuilder implements /*IMavenBuilder, */IMavenBuilderConstants {

	/* (non-Javadoc)
	 * @see com.cts.mavenbuilder.IMavenBuilder#createJavaProject(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Model createJavaProject(String archetype, String groupid, String artifactId, String version) {
		Model model = null;
		try {
			
			 Map<String, Object> arguments = new HashMap<String, Object>();
			 arguments.put("0", groupid);
			 arguments.put("1", artifactId);
			
			 Process process = Runtime.getRuntime().exec(GOTO_OUTPUT_FOLDER + "&&"+ MavenBuilderUtility.format(MAVEN_PROJECT_CREATE_COMMAND, arguments));
			 BufferedReader in = new BufferedReader( new InputStreamReader(process.getInputStream()) ); 
			 String line = ""; 
			 while ((line = in.readLine()) != null) { 
				 System.out.println(line);
			 }
			 
			 //Get the java object of the POM file.
			 arguments.clear();
			 arguments.put("0", artifactId);
			 
			 String filePath = MavenBuilderUtility.format(POM_FILE_PATH, arguments);
      		model =  MavenBuilderUtility.getRootElement(filePath);
		}
		catch(IOException ex) {
			System.out.println("Error in executing the command" + ex);
		}
		catch(JAXBException e) {
			System.out.println("Error in executing the command" + e);
		} finally {
		return model;
		}

	}
	
	public void executeProject(String artifactId) {
		try {
			
			Map<String, Object> arguments = new HashMap<String, Object>();
			 arguments.clear();
			 arguments.put("0",artifactId );
			 
			// String filePath1 = MavenBuilderUtility.format("/output/${0}/target", arguments);
			 //Process process = Runtime.getRuntime().exec(MavenBuilderUtility.format(GOTO_PROJECT_FOLDER, arguments) + "&&"+ MAVEN_PROJECT_BUILD_COMMAND);
			 Process process = Runtime.getRuntime().exec("cmd /C cd output/"+artifactId + "&& "+ MAVEN_PROJECT_BUILD_COMMAND);
			 BufferedReader in = new BufferedReader( new InputStreamReader(process.getInputStream()) ); 
			 String line = ""; 
			 while ((line = in.readLine()) != null) { 
				 System.out.println(line);
			 }
			 
			 //Get the java object of the POM file.
		}
		catch(IOException ex) {
			System.out.println("Error in executing the command" + ex);
		}

	}

	public void executeJavaDoc(String artifactId) {
		try {
			
			Map<String, Object> arguments = new HashMap<String, Object>();
			 arguments.clear();
			 arguments.put("0",artifactId );
			 
			// String filePath1 = MavenBuilderUtility.format("/output/${0}/target", arguments);
			 //Process process = Runtime.getRuntime().exec(MavenBuilderUtility.format(GOTO_PROJECT_FOLDER, arguments) + "&&"+ MAVEN_PROJECT_BUILD_COMMAND);
			 Process process = Runtime.getRuntime().exec("cmd /C cd output/"+artifactId + "&& "+ MAVEN_PROJECT_JAVADOC_COMMAND);
			 BufferedReader in = new BufferedReader( new InputStreamReader(process.getInputStream()) ); 
			 String line = ""; 
			 while ((line = in.readLine()) != null) { 
				 System.out.println(line);
			 }
			 
			 //Get the java object of the POM file.
		}
		catch(IOException ex) {
			System.out.println("Error in executing the command" + ex);
		}

	}

	/* (non-Javadoc)
	 * @see com.cts.mavenbuilder.IMavenBuilder#addDependencytoPom(com.cts.mavenbuilder.jaxbclasses.Model, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Model addDependencytoPom(Model model, String groupid, String artifactId, String version, String type,
			String classfier, String scope, String systemPath) {
		
		if(model != null) {
			Dependencies dependencies = model.getDependencies();
			Dependency dependency = new Dependency();
			dependency.setArtifactId(artifactId);
			dependency.setGroupId(groupid);
			dependency.setVersion(version);
			dependency.setType(type);
			dependency.setClassifier(classfier);
			dependency.setScope(scope);
			dependency.setSystemPath(systemPath);
			
			if(dependencies.getDependency() != null) {
				dependencies.getDependency().add(dependency);
			}

		}
		return model;
	}
	
	public Model addDependencyToPomWithExclusion(Model model, String groupid, String artifactId, String version, String type, String classifier, String scope, String systemPath, Exclusion exclusion) {
		
		if(model != null) {
			Dependencies dependencies = model.getDependencies();
			Dependency dependency = new Dependency();
			dependency.setArtifactId(artifactId);
			dependency.setGroupId(groupid);
			dependency.setVersion(version);
			dependency.setType(type);
			dependency.setClassifier(classifier);
			dependency.setScope(scope);
			dependency.setSystemPath(systemPath);
			if(exclusion != null) {
				dependency.getExclusions().getExclusion().add(exclusion);
			}

			if(dependencies.getDependency() != null) {
				dependencies.getDependency().add(dependency);
			}
		}
		return model;
	}

	/* (non-Javadoc)
	 * @see com.cts.mavenbuilder.IMavenBuilder#addParent(com.cts.mavenbuilder.jaxbclasses.Model, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Model addParent(Model model, String groupid, String artifactId, String version) {
		
		if(model != null) {
			Parent parent = new Parent();
			parent.setArtifactId(artifactId);
			parent.setGroupId(groupid);
			parent.setVersion(version);
			model.setParent(parent);
		}
		
		return model;
	}

	/* (non-Javadoc)
	 * @see com.cts.mavenbuilder.IMavenBuilder#addPlugin(com.cts.mavenbuilder.jaxbclasses.Build, com.cts.mavenbuilder.jaxbclasses.PluginManagement, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Model addPlugin(Model model, String groupid, String artifactId,
			String version, String id, String phase, String goal, String wsdl, String sourceRoot) {
		
		Plugins plugins = null;
		Plugin plugin = null;
		Executions executions = null;
		PluginExecution pluginExec = null;
		Goals pluginGoals = null;
		Configuration config = null;
		WsdlOptions objwsdlOptions = null;
		WsdlOption objWsdlOption = null;
		if(model != null) {
			Build build = model.getBuild();
			if(build == null) {
				build = new Build();

				if(plugins == null) {
					plugins = new Plugins();
					plugins.getPlugin();
					plugin = new Plugin();
					plugin.setArtifactId(artifactId);
					plugin.setGroupId(groupid);
					plugin.setVersion(version);
					executions = new Executions();
					//executions.getExecution();
					pluginExec = new PluginExecution();
					pluginExec.setId(id);
					pluginExec.setPhase(phase);
					
					config = new Configuration();
					config.setSourceRoot("${project.build.directory}/generated/cxf");
					
					objwsdlOptions = new WsdlOptions();
					
					objWsdlOption = new WsdlOption();
					
					objWsdlOption.setWsdl(wsdl);
					objwsdlOptions.getWsdlOption().add(objWsdlOption);
					config.setWsdlOptions(objwsdlOptions);
					
					//config.getAny();
					
					pluginExec.setConfiguration(config);
					
					pluginGoals = new Goals();
					pluginGoals.getGoal().add(goal);
					
				}
				pluginExec.setGoals(pluginGoals);
				executions.getExecution().add(pluginExec);
				plugin.setExecutions(executions);
				plugins.getPlugin().add(plugin);
				build.setPlugins(plugins);
				model.setBuild(build);
			}
		}
		return model;
	}
	
	
	public Model addPropertiesToModel(Model model,String isREST) {
		
		Properties modelProps = model.getProperties();
		if(modelProps == null){
			modelProps = new Properties();
			}	
		if(isREST.equals("SOAP")) {
			modelProps.setCxfversion("2.2.3");
		} else if(isREST.equals("REST")) {
			modelProps.setPbse("UTF-8");
			modelProps.setProe("UTF-8");
		}
		modelProps.setJavaversion("1.8");
		model.setProperties(modelProps);
	
		return model;
	}
	
	public void installJarToLocalRepository(String groupid, String artifactId, String version) {
		try {
			
			 Map<String, Object> arguments = new HashMap<String, Object>();
			 arguments.put("0", artifactId);
			 arguments.put("1", groupid);
			
			 Process process = Runtime.getRuntime().exec("cmd /C cd output/"+artifactId+"/target" + " && " + MavenBuilderUtility.format(MAVEN_INSTALL_LOCAL_REPOSITORY, arguments));
			 BufferedReader in = new BufferedReader( new InputStreamReader(process.getInputStream()) ); 
			 String line = ""; 
			 while ((line = in.readLine()) != null) { 
				 System.out.println(line);
			 }
			 
			 //Get the java object of the POM file.
			 arguments.clear();
			 arguments.put("0", artifactId);
			 
		}
		catch(IOException ex) {
			System.out.println("Error in executing the command" + ex);
		}

	}
	
	
	public static void main(String args[]) throws FileNotFoundException, JAXBException {
		MavenBuilder mb = new MavenBuilder();
		Model model = mb.createJavaProject("DummyProj", "com.cts", "DummyProj", "");
		model = mb.addDependencytoPom(model, "com.sun.xml.bind", "jaxb-core", "2.2.11", null, null, null, null);
		 Map<String, Object> arguments = new HashMap<String, Object>();
		 arguments.clear();
		 arguments.put("0", "DummyProj");
		 
		 String filePath = MavenBuilderUtility.format(POM_FILE_PATH, arguments);

		 String basedir = System.getProperty("user.dir");
	   // File file =  new File(basedir+filePath);
		model = mb.addPlugin(model, "org.apache.cxf", "cxf-codegen-plugin", "${cxf.version}", "generate-sources", "generate-sources", "wsdl2java", "D:/vipuserservices-query-1.7.wsdl", "");
		 model = mb.addPropertiesToModel(model, "SOAP");
		MavenBuilderUtility.marshalModelToPom(model, basedir+filePath);
		mb.executeProject("DummyProj");
		mb.installJarToLocalRepository( "com.cts","DummyProj", "1.0");
	}

}
