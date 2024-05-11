package com.msfb.maju_mundur_application.security;

import com.msfb.maju_mundur_application.dto.request.CustomerRequest;
import com.msfb.maju_mundur_application.entity.Account;
import com.msfb.maju_mundur_application.entity.Customer;
import com.msfb.maju_mundur_application.entity.Merchant;
import com.msfb.maju_mundur_application.service.CustomerService;
import com.msfb.maju_mundur_application.service.MerchantService;
import com.msfb.maju_mundur_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component("authorizeSecurity")
@RequiredArgsConstructor
public class AuthorizationSecurity {
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final UserService userService;

    public boolean checkSameIdAsPrincipalCustomer(CustomerRequest request){
        Customer customer = customerService.getById(request.getId());
        Account userAccount = userService.getByContext();

        if (!userAccount.getId().equals(customer.getAccount().getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "cannot access this resource");
        }
        return true;
    }

    public boolean checkSameIdAsPrincipalMerchant(Merchant request){
        Merchant merchant = merchantService.getMerchantById(request.getId());
        Account userAccount = userService.getByContext();

        if (!userAccount.getId().equals(merchant.getAccount().getId())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "cannot access this resource");
        }
        return true;
    }
}
