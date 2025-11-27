package com.github.JoaoPem.computacaodistribuida.grpc;

import com.github.JoaoPem.computacaodistribuida.services.UserAccountService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class UserAccountGrpcService extends UserAccountServiceGrpc.UserAccountServiceImplBase {

    private final UserAccountService userAccountService;

    @Override
    public void listAllUserAccounts(
            EmptyRequest request,
            StreamObserver<UserAccountListResponse> responseObserver) {

        List<com.github.JoaoPem.computacaodistribuida.models.UserAccount> users =
                userAccountService.listAllUserAccount();

        UserAccountListResponse.Builder responseBuilder = UserAccountListResponse.newBuilder();

        users.forEach(user -> responseBuilder.addUsers(
                UserAccount.newBuilder()
                        .setId(user.getId())
                        .setName(user.getName())
                        .build()
        ));

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
