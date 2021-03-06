package rest.car_repair.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rest.car_repair.domain.Member;
import rest.car_repair.repositories.MemberRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Member> persistentMember = memberRepository.findByEmail(email);

        if (!persistentMember.isPresent()) {
            throw new UsernameNotFoundException ("Member with email " + email + " not found.");
        }

        Member member = persistentMember.get();

        return User.withUsername(email)
                .password(member.getPassword())
                .authorities(member.getUserType())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
