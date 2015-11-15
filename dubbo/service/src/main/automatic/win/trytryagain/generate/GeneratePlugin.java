package com.diy.generate;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import java.util.List;

/**
 * Created by zeal on 15-11-13.
 */
public class GeneratePlugin extends PluginAdapter implements Plugin {
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        super.initialized(introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    private void addSerialVersionUID(InnerClass clazz) {
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setStatic(true);
        field.setFinal(true);
        field.setName("serialVersionUID");
        field.setType(new FullyQualifiedJavaType("long"));
        field.setInitializationString("1L");
        List<Field> fields = clazz.getFields();
        for (int i = 0; i < fields.size(); i++) { //将序列化id放第一个属性上
            Field currField = fields.get(i);
            fields.set(i, field);
            field = currField;
        }
        fields.add(field);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        addSerialVersionUID(topLevelClass);
        return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        // 实体类添加统一父接口
        FullyQualifiedJavaType ptype = new FullyQualifiedJavaType("java.io.Serializable");
        topLevelClass.addImportedType(ptype);
        topLevelClass.addSuperInterface(ptype);
        addSerialVersionUID(topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 字段修改，添加注释
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass,
                                       IntrospectedColumn introspectedColumn,
                                       IntrospectedTable introspectedTable,
                                       Plugin.ModelClassType modelClassType) {
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
                introspectedTable, modelClassType);
    }

    @Override
    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        return true;
    }

    /**
     * 修改 select 查询xml 对象， 添加自动分页功能
     */
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {
        XmlElement choose = new XmlElement("choose");
        XmlElement rangeLimitWhen = new XmlElement("when");
        rangeLimitWhen.addAttribute(new Attribute("test", "limitStart != -1 and limitEnd != -1"));
        rangeLimitWhen.addElement(new TextElement("limit ${limitStart} , ${limitEnd}"));
        XmlElement limitStartWhen = new XmlElement("when");
        limitStartWhen.addAttribute(new Attribute("test", "limitStart != -1"));
        limitStartWhen.addElement(new TextElement("limit ${limitStart}"));
        choose.addElement(rangeLimitWhen);
        choose.addElement(limitStartWhen);
        element.addElement(choose);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /** example 中添加分页信息 */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {
        // 所有类条件类添加统一父接口
        FullyQualifiedJavaType ptype = new FullyQualifiedJavaType("java.io.Serializable");
        topLevelClass.addImportedType(ptype);
        topLevelClass.addSuperInterface(ptype);
        //添加实体的序列化号
        addSerialVersionUID(topLevelClass);
        //添加内部类

        //GeneratedCriteria, Criterion
        andGeneratedCriteriaImplSerialable(topLevelClass, "GeneratedCriteria");
        andGeneratedCriteriaImplSerialable(topLevelClass, "Criterion");
        andGeneratedCriteriaImplSerialable(topLevelClass, "Criteria");

        addLimit(topLevelClass, introspectedTable, "limitStart");
        addLimit(topLevelClass, introspectedTable, "limitEnd");

        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    private void andGeneratedCriteriaImplSerialable(TopLevelClass topLevelClass, String innerClassName) {
        if(innerClassName == null){
            return;
        }
        InnerClass generatedCriteria = null;
        for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
            if (innerClassName.equals(innerClass.getType().getShortName())) {
                generatedCriteria = innerClass;
                break;
            }
        }
        if(generatedCriteria == null){
            return;
        }
        FullyQualifiedJavaType ptype = new FullyQualifiedJavaType("java.io.Serializable");
        topLevelClass.addImportedType(ptype);
        generatedCriteria.addSuperInterface(ptype);
        addSerialVersionUID(generatedCriteria);
    }

    private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable,
                          String name) {
        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(FullyQualifiedJavaType.getIntInstance());
        field.setName(name);
        field.setInitializationString("-1");
        commentGenerator.addFieldComment(field, introspectedTable);

        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);

        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }
}
