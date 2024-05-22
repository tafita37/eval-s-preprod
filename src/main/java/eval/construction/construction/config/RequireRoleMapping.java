package eval.construction.construction.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // Spécifie que l'annotation peut être utilisée sur des types (classes)
@Retention(RetentionPolicy.RUNTIME) 

public @interface RequireRoleMapping {
    String[] value();
}
