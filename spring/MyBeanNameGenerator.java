package com.best.web.htyt.util.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * spring通过Component自动装载bean，但给bean取的名字都带上了Impl后缀，不爽，自己写一个，把Impl后缀去掉
 * 配置在applicationContext-dao.xml中：
 *  <context:component-scan base-package="com.best.eLogistics" name-generator="com.best.eLogistics.util.spring.MyBeanNameGenerator"/>
 * @author BL00064
 *
 */
public class MyBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String result = super.generateBeanName(definition, registry);
        result = result.replaceAll("Impl$", "");
        return result;
    }

}
