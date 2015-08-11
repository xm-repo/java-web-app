package cmc.ps.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=OwnerPropertyValidator.class)
public @interface OwnerConstraint {

	String message() default "OWNER ERROR";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
