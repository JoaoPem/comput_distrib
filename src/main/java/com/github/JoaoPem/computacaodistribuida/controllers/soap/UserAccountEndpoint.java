package com.github.JoaoPem.computacaodistribuida.controllers.soap;

import com.example.users.soap.GetAllUsersRequest;
import com.example.users.soap.GetAllUsersResponse;
import com.example.users.soap.UserAccount;
import com.github.JoaoPem.computacaodistribuida.services.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class UserAccountEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/users/soap";

    private final UserAccountService userAccountService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest request) {
        List<com.github.JoaoPem.computacaodistribuida.models.UserAccount> users = userAccountService.listAllUserAccount();

        GetAllUsersResponse response = new GetAllUsersResponse();

        users.forEach(userEntity -> {
            UserAccount soapUser = new UserAccount();
            soapUser.setId(userEntity.getId());
            soapUser.setName(userEntity.getName());
            response.getUsers().add(soapUser);
        });

        return response;
    }
}
