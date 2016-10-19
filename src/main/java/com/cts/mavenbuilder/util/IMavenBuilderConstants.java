package com.cts.mavenbuilder.util;

public interface IMavenBuilderConstants {

	/**
	 * ${0} - Group id
	 * ${1} - Artifact id
	 */
	public final String MAVEN_PROJECT_CREATE_COMMAND = "mvn archetype:generate -DgroupId=${0} -DartifactId=${1} -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false";
	
	public final String GOTO_OUTPUT_FOLDER = "cmd /C cd output";
	
	public final String GOTO_PROJECT_FOLDER = "cmd /C cd output/{0}";
	
	public final String POM_FILE_PATH = "/output/${0}/pom.xml";
	
	public final String MAVEN_PROJECT_BUILD_COMMAND = "mvn clean install package";
	
	public final String MAVEN_PROJECT_JAVADOC_COMMAND = "mvn javadoc:javadoc";
	
	public final String MAVEN_INSTALL_LOCAL_REPOSITORY = "mvn install:install-file -Dfile=${0}-1.0-SNAPSHOT.jar -DgroupId=${1} -DartifactId=${0} -Dversion=1.0 -Dpackaging=jar";
}
