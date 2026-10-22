package edu.bellevue.cis530.week7.security;

import edu.bellevue.cis530.week7.entity.Member;
import edu.bellevue.cis530.week7.repository.MemberRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
 * This class implements the UserDetailsService interface from Spring Security. 
 *It is responsible for loading user-specific data during authentication. 
 *The loadUserByUsername method retrieves a Member entity from the database using the provided username, 
 *and if found, it constructs a UserDetails object with the member's information (username, password, enabled status, and authorities). 
 *If the member is not found, it throws a UsernameNotFoundException.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Member not found: " + username));


        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .disabled(!member.isEnabled())
                .authorities(member.getRole())
                .build();
    }
}
