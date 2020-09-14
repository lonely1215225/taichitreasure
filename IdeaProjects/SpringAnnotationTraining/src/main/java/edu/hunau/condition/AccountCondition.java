package edu.hunau.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

//被标注此类得Condition注解标注的类，决定是否注册beandefinition，返回true表示注册该类
public class AccountCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String[] names = conditionContext.getBeanFactory().getBeanDefinitionNames();
        for (String name:names) System.out.println(name);
        String property = conditionContext.getEnvironment().getProperty("os.name");
        if (property.contains("Windows")) return true;
        return false;
    }
}
