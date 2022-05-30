package RegisterLogin.RegisterLoginTutorial.registration.service;

import RegisterLogin.RegisterLoginTutorial.appuser.entity.AppUser;
import RegisterLogin.RegisterLoginTutorial.appuser.entity.AppUserRole;
import RegisterLogin.RegisterLoginTutorial.appuser.service.AppUserService;
import RegisterLogin.RegisterLoginTutorial.registration.utilities.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
