package org.xmlportletfactory.templates;

import java.io.File;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.xmlportletfactory.xml.xmlportletfactory.DefinitionDocument.Definition.Applications;
import org.xmlportletfactory.xml.xmlportletfactory.DefinitionDocument.Definition.CommonData;
import org.xmlportletfactory.xml.xmlportletfactory.DefinitionDocument.Definition.Applications.Application;

public class Variables {
	
	String fileSep = File.separator;
	
	protected StringHelper stringHelper = new StringHelper();
	
	private CommonData commonData;
	private Application application;
	private String applicationClassName;
	private Applications applications;
	
	public Variables(CommonData commonData, Application application, String applicationClassName, Applications applications) {
		this.commonData = commonData;
		this.application = application;
		this.applicationClassName = applicationClassName;
		this.applications = applications;
	}
	
	public void populate(VelocityContext context) {

		context.put("fieldHelper", new FieldHelper());
		context.put("stringHelper", new StringHelper());
		
		context.put("normalizedProjectName", getKebabCaseProjectName());
		context.put("kebabCaseProjectName", getKebabCaseProjectName());
		context.put("projectName", getProjectName());
		context.put("upperCamelCaseProjectName", getUpperCamelCaseProjectName());
		
		context.put("webModulePath", getWebModulePath());
		context.put("apiModulePath", getApiModulePath());
		context.put("serviceModulePath", getServiceModulePath());
		
		context.put("basePackage", getBasePackage());
		
		context.put("webModuleBasePackagePath", getWebModuleBasePackagePath());
		context.put("apiModuleBasePackagePath", getApiModuleBasePackagePath());
		context.put("serviceModuleBasePackagePath", getServiceModuleBasePackagePath());
	
		context.put("webResourcesPath", getWebResourcesPath());
		
		context.put("modulesParentDirPath", getModulesParentDirPath());
		
		context.put("portletName", getPortletName(this.application));
		context.put("portletDisplayName", this.application.getClassDef().getTitle());
			
		context.put("EntityName", getEntityName());
		context.put("EntityNamePlural", getEntityNamePlural());
		
		context.put("snakeCaseEntityName", getSnakeCaseEntityName());
		context.put("snakeCaseEntityNamePlural", getSnakeCaseEntityNamePlural());
		
		context.put("snakeCaseEntitiesName", getSnakeCaseEntityNamePlural()); // Deprecated
	
		context.put("helper", this);
		
		loadVariablesTxt(context);
	}

	public String getPortletName(Application entity) {
		
		String portletName = getBasePackage().replaceAll("\\.", "_");
		
		portletName += "_" + getEntityName(entity) + "AdminPortlet";
		
		return portletName;
	}
	
	private String getSnakeCaseEntityName() {
		
		String name = getSnakeCaseEntityName(this.application);
		
		return name;
	}
	
	public String getSnakeCaseEntityName(Application entity) {
		
		String name = stringHelper.toSnakeCase(entity.getFileDef().getName());
		
		return name;
	}
	
	private String getSnakeCaseEntityNamePlural() {
		
		String name = getSnakeCaseEntityName(this.application);
		
		return name;
	}
	
	public String getSnakeCaseEntityNamePlural(Application entity) {
		
		String name = getSnakeCaseEntityName(entity) + "s";
		
		return name;
	}
	
	public String getEntityName(Application entity) {
		
		String name = entity.getClassDef().getName();
		
		return name;
	}
	
	public String getEntityNamePlural(Application entity) {
		
		// TODO Deal with plural
		
		String name = getEntityName(entity) + "s";
		
		return name;
	}
	
	
	private String getEntityName() {
		
		String name = getEntityName(this.application);
		
		return name;
	}
	
	private String getEntityNamePlural() {
		
		String name = getEntityNamePlural(this.application);
		
		return name;
	}

	private String getWebResourcesPath() {

		String webResourcePath = getWebModulePath()
						
		+ "/src/main/resources/META-INF/resources".replaceAll("/", fileSep);				
						
		return webResourcePath;
	}

	private String getServiceModulePath() {

		return getModulePath("service");
	}

	private String getApiModulePath() {

		return getModulePath("api");
	}

	private String getWebModulePath() {

		return getModulePath("web");
	}

	private String getWebModuleBasePackagePath() {
		
		return getModuleBasePackagePath("web");
	}
	
	protected String getApiModuleBasePackagePath() {

		return getModuleBasePackagePath("api");
	}
	
	protected String getServiceModuleBasePackagePath() {

		return getModuleBasePackagePath("service");
	}

	protected String getBasePackage() {

		String basePackage = commonData.getClient().toLowerCase() + "."
						+ commonData.getProjectName().toLowerCase();

		return basePackage;
	}

	private void loadVariablesTxt(VelocityContext vc) {
		 Template template = Velocity.getTemplate( "/Resources/VelocityTemplates_common/PortletFiles/variables.txt", "UTF-8" );            
         StringWriter sw = new StringWriter(); 	
         template.merge(vc, sw);
	}

	protected String getModulesParentDirPath() {
		
		String project = getKebabCaseProjectName();
		
		String path = "/" + project;
		
		return path;
	}
	
	protected String getModulePath(String moduleType) {
		
		String project = getKebabCaseProjectName();
		
		String path = "/" + project + "/" + project + "-" + moduleType;
		
		return path;
	}
	
	protected String getModuleBasePackagePath(String moduleType) {
	
		String path = getModulePath(moduleType) 
						+ fileSep + "src" + fileSep + "main" + fileSep + "java" 
						+ fileSep + getBasePackage().replaceAll("\\.", fileSep);

		return path;
	}
		
	protected String getProjectName() {
		
		return commonData.getProjectName();
	}
	
	protected String getKebabCaseProjectName() {
		
		String basename = stringHelper
			.toLowerCaseHyphen(getProjectName());

		return basename;
	}
	
	protected String getUpperCamelCaseProjectName() {
		
		char[] pn = getProjectName().toCharArray();
		
		pn[0] = Character.toUpperCase(pn[0]);
		
		String result = new String(pn);
		
		return result;
	}
}
