package firenze.project.tiny.spring.di;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
