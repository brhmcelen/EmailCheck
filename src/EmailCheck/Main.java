package EmailCheck;

import EmailCheck.business.abstracts.UserService;
import EmailCheck.business.concretes.AuthManager;
import EmailCheck.business.concretes.EmailManager;
import EmailCheck.business.concretes.UserManager;
import EmailCheck.business.concretes.UserValiditionManager;
import EmailCheck.core.AuthService;
import EmailCheck.core.GoogleAuthManagerAdapter;
import EmailCheck.dataAccess.concretes.InMemoryUserDao;



public class Main {

	public static void main(String[] args) {
		
		
		UserService userService = new UserManager(new InMemoryUserDao());
		
		AuthService authService = new AuthManager(userService, new UserValiditionManager(), new EmailManager());
		
		
		authService.register(1, "brhmcelen@gmail.com", "123ibrahim789", "İbrahim", "Çelen"); // Başarılı
		authService.register(2, "dasfakjdgavdjkcj..", "þifrevalid", "İbrahim", "Çelen"); // Başarısız eposta invalid
		authService.register(3, "brhmcelen2@gmail.com", "s", "Halit Enes", "Kalaycı"); // Başarısız şifre invalid
		authService.register(4, "brhmcelen1@gmail.com", "s", "H", "K"); // Başarısız ad-soyad invalid
		authService.register(5, "brhmcelen@gmail.com", "123ibrahim1234", "İbrahim", "Çelen"); // Başarısız e-posta mevcut


		authService.login("brhmcelen@gmail.com", "123ibrahim789"); // Başarşsşz üye doşrulama yapmamşş.
		userService.verifyUser(1); // 
		authService.login("brhmcelen@gmail.com", "123ibrahim7891"); // Başarısız şifre yanlış.
		authService.login("brhmcelen@gmail.com", "123ibrahim789"); // Başarşlş.
		authService.login("", ""); // Başarşsşz e-posta parola zorunlu

		authService.login("brhmcelen@gmail.com", "123ibrahim789"); // Başarısız üye doğrulama yapmamış.
		userService.verifyUser(1); // Kullanıcıyı doğrulayalım.
		authService.login("brhmcelen@gmail.com", "123ibrahim7819"); // Başarısız şifre yanlış.
		authService.login("brhmcelen@gmail.com", "123ibrahim789"); // Başarılı.
		authService.login("", ""); // Başarısız e-posta parola zorunlu

		
		AuthService googleAuthService = new GoogleAuthManagerAdapter();
		googleAuthService.register(6, "klyyc7@gmail.com", "123halit1234", "Halit Enes", "Kalaycı");
		googleAuthService.login("klyyc7@gmail.com", "123halit1234");

	}

}
