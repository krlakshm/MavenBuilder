/**
 * 
 */
package com.cts.mavenbuilder;

import com.cts.mavenbuilder.jaxbclasses.Build;
import com.cts.mavenbuilder.jaxbclasses.Model;
import com.cts.mavenbuilder.jaxbclasses.PluginManagement;


/**
 * @author 153780
 *
 */
public interface IMavenBuilder {
	/**
	 * create the maven project with the basic archetype
	 */
	Model createJavaProject(String archetype, String groupid, String artifactId, String version);
	
	/**
	 * add a dependency artifact
	 */
	Model addDependencytoPom(Model model, String groupid, String artifactId, String version, String type, String classfier, String scope, String systemPath);
	
	/**
	 * add a parent
	 */
	Model addParent(Model model, String groupid, String artifactId, String version);
	
	/**
	 * add a plugin
	 */
	Model addPlugin(Build build, PluginManagement pluginMgmt, String groupid, String artifactId, String version );
}
