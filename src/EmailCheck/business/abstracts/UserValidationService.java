package EmailCheck.business.abstracts;

import EmailCheck.entities.concretes.User;

public interface UserValidationService {
	
	boolean validate(User user);
	
	

}
