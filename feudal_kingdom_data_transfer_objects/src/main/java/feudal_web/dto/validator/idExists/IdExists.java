package feudal_web.dto.validator.idExists;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { IdExistsValidator.class })
public @interface IdExists {

	String message() default "{feudal_web.dto.validator.idExists.message}";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
	
}
