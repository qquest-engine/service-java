package ua.ithillel.evo.questengine.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.ithillel.evo.questengine.data.dao.AppUserDAO;
import ua.ithillel.evo.questengine.data.entity.AppUser;

import static java.util.Collections.emptyList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final AppUserDAO appUserDAO;

    public JwtUserDetailsService(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = appUserDAO.getByUserName(username);
        return new User(appUser.getUserName(), appUser.getPassword(), emptyList());
    }
}
