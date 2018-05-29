package com.born.config.generator;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 
 * @ClassName: GenPlugin
 * @Description:
 * @author 张永胜
 * @date 2018年5月29日 下午2:57:05
 * @version 1.0
 */
public class GenPlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> arg0) {
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addImportedType("lombok.Data");
		topLevelClass.addImportedType("javax.persistence.Table");
		topLevelClass.addImportedType("javax.persistence.Column");

		topLevelClass.addAnnotation("@Data");
		topLevelClass.addAnnotation("@Table(name=\"" + introspectedTable.getFullyQualifiedTable() + "\")");
		return true;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		return false;
	}

}
