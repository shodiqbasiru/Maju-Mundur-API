package com.msfb.maju_mundur_application.service.impl;

import com.msfb.maju_mundur_application.constant.UserRole;
import com.msfb.maju_mundur_application.dto.request.LoginRequest;
import com.msfb.maju_mundur_application.dto.request.RegisterCustomerRequest;
import com.msfb.maju_mundur_application.dto.request.RegisterMerchantRequest;
import com.msfb.maju_mundur_application.dto.response.LoginResponse;
import com.msfb.maju_mundur_application.dto.response.RegisterCustomerResponse;
import com.msfb.maju_mundur_application.dto.response.RegisterMerchantResponse;
import com.msfb.maju_mundur_application.entity.Account;
import com.msfb.maju_mundur_application.entity.Customer;
import com.msfb.maju_mundur_application.entity.Merchant;
import com.msfb.maju_mundur_application.entity.Role;
import com.msfb.maju_mundur_application.repository.AccountRepository;
import com.msfb.maju_mundur_application.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final RoleService roleService;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterCustomerResponse registerCustomer(RegisterCustomerRequest request) {

        Role roleCustomer = roleService.getOrSave(UserRole.ROLE_CUSTOMER);
        String hashPassword = encoder.encode(request.getPassword());

        Account account = Account.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .roles(List.of(roleCustomer))
                .isEnable(true)
                .build();
        accountRepository.saveAndFlush(account);

        Customer customer = Customer.builder()
                .customerName(request.getCustomerName())
                .account(account)
                .build();
        customerService.createCustomer(customer);

        List<String> roles = account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return RegisterCustomerResponse.builder()
                .customerName(customer.getCustomerName())
                .username(account.getUsername())
                .roles(roles)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterMerchantResponse registerMerchant(RegisterMerchantRequest request) {
        Role roleMerchant = roleService.getOrSave(UserRole.ROLE_MERCHANT);
        String hashPassword = encoder.encode(request.getPassword());

        Account account = Account.builder()
                .username(request.getUsername())
                .password(hashPassword)
                .roles(List.of(roleMerchant))
                .isEnable(true)
                .build();
        accountRepository.saveAndFlush(account);

        Merchant merchant = Merchant.builder()
                .merchantName(request.getMerchantName())
                .account(account)
                .build();
        merchantService.createMerchant(merchant);

        List<String> roles = account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return RegisterMerchantResponse.builder()
                .merchantName(merchant.getMerchantName())
                .username(account.getUsername())
                .roles(roles)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account = (Account) authenticate.getPrincipal();
        if (!account.getIsEnable()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Account is not active");
        }
        String token = jwtService.generateToken(account);
        return LoginResponse.builder()
                .username(account.getUsername())
                .roles(account.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .token(token)
                .build();
    }
}
