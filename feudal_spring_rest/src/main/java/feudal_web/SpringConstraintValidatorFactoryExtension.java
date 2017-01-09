package feudal_web;

import javax.validation.ConstraintValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

@Component
public class SpringConstraintValidatorFactoryExtension extends SpringConstraintValidatorFactory {

	private final Logger logger = LoggerFactory.getLogger(SpringConstraintValidatorFactoryExtension.class);

	private final AutowireCapableBeanFactory beanFactory;

	public SpringConstraintValidatorFactoryExtension(AutowireCapableBeanFactory beanFactory) {	
		super(beanFactory);
		this.beanFactory = beanFactory;
	}
	
	@Override
	public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
		T bean = null;
		
		try {
			logger.debug("Trying to find a validator bean of class " + key.getSimpleName());
			bean = beanFactory.getBean(key);
		} catch (BeansException exc) {
			logger.debug("Failed to find a bean of class " + key.getSimpleName());
		}

		if (bean == null) {
			try {
				logger.debug("Creating a new validator bean of class " + key.getSimpleName());
				bean = beanFactory.createBean(key);
			} catch (BeansException exc) {
				logger.debug("Failed to create a validator of class " + key.getSimpleName());
			}
		}

		if (bean == null) {
			logger.warn("Failed to get validator of class " + key.getSimpleName());
		}

		return bean;
	}

}