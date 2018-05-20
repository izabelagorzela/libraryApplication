package org.gorzela.library.server.util;

import org.gorzela.library.common.Reader;
import org.gorzela.library.server.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

    @Autowired
    private ReaderRepository readerRepository;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

       Reader reader = readerRepository.findByLogin(s);

        if(reader == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        UserDetails user = User.withUsername(reader.getLogin()).password(reader.getPassword()).roles(reader.getRole()).build();

        return user;
    }
}
