package com.linked.matched.service.user;

import com.linked.matched.config.jwt.UserPrincipal;
import com.linked.matched.request.jwt.DeleteTokenDto;
import com.linked.matched.request.jwt.TokenRequestDto;
import com.linked.matched.request.user.*;
import com.linked.matched.response.jwt.TokenDto;
import com.linked.matched.response.user.UserMail;
import com.linked.matched.response.user.UserProfile;

import java.security.Principal;

public interface UserService {
    void join(UserJoin userJoin) throws Exception;

    TokenDto login(UserLogin userLogin);

    TokenDto reissue(TokenRequestDto tokenRequestDto);


    void refreshTokenDelete(DeleteTokenDto deleteTokenDto);

    void deleteUser(Long id);

    void edit(Long id, UserEdit userEdit);

    void passwordEdit(Long id, PwdEdit pwdEdit);

    void passwordChange(PwdChange pwdChange);

    UserMail findUserEmail(Long applicantId);

     UserProfile viewUser(Long id);
}
