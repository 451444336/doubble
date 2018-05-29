package com.born.config.generator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * 生成数据库字段注释
 * 类名前加自定义注释
 * 注释增加数据字段是否为必填项
 * 注释增加数据字段是否有默认值
 * 注释增加属性是否为主健
 */
public class RemarksCommentGenerator extends DefaultCommentGenerator {

	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * @数表名称 " + introspectedTable.getFullyQualifiedTable());
		topLevelClass.addJavaDocLine(" * @开发日期 " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		topLevelClass.addJavaDocLine(" */");

	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		String remark = introspectedColumn.getRemarks();
		String columnName = introspectedColumn.getActualColumnName();
		List<IntrospectedColumn> primaryKey = introspectedTable.getPrimaryKeyColumns();
		for (IntrospectedColumn pk : primaryKey) {
			if (columnName.equals(pk.getActualColumnName())) {
				field.addJavaDocLine("");
				remark += " (主健ID)";
				continue;
			}
		}
		String defaultValue = introspectedColumn.getDefaultValue();
		if (!StringUtils.isEmpty(defaultValue)) {
			remark += null != defaultValue ? "  (默认值为: " + defaultValue + ")" : "";
		}
		field.addJavaDocLine("/** " + remark + " */");
		field.addAnnotation("@Column(name=\"" + introspectedColumn.getActualColumnName() + "\")");

	}
}