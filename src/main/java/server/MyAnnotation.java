package server;

import java.lang.annotation.*;

//
//@Inherited
@Target(ElementType.PARAMETER)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String name() default "Haim";
    int amount() default 30;
}
