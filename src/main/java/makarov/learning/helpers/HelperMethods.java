package makarov.learning.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class HelperMethods {
    @Autowired
    ApplicationContext applicationContext;

    public void printSpringContextBeans() {
        System.out.println("number of existing beans in app.context: "+ applicationContext.getBeanDefinitionCount());
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .forEach(name -> System.out.printf("%2$100s - %1$s\n",name,applicationContext.getBean(name).getClass().getSimpleName()));
    }
}
