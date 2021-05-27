package serialization_and_proxy;

import serialization_and_proxy.cache_options.Arguments;
import serialization_and_proxy.cache_options.Save;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheAnnotation {
    Save save() default Save.SAVE_TO_DISK;
    Arguments arguments() default Arguments.BOTH_ARGS;
    int limits() default -1;
}
