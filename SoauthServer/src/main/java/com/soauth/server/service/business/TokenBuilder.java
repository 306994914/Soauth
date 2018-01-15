package com.soauth.server.service.business;

import com.soauth.core.vo.oauth2.AccessToken;
import com.soauth.core.vo.oauth2.ClientDetails;
import com.soauth.server.oauth.SoauthAuthRequest;
import org.apache.oltu.oauth2.as.request.OAuthRequest;
import org.jose4j.jwt.JwtClaims;

/**
 *
 * @author JZ9523
 * @date 2017/12/4
 *
 * �����������շ��ظ�client�˵�token.
 */
public interface TokenBuilder{


    /**
     * �÷���һ����accessToken����,Ȼ�����accessToken����refreshToken��idToken
     * @param clientDetails
     * @param claims
     * @param request
     * @param issuer
     * @return
     */
    AccessToken enhance(ClientDetails clientDetails, JwtClaims claims, OAuthRequest request, String issuer);
}

