package com.soauth.server.service;

import com.soauth.core.vo.oauth2.AccessToken;
import com.soauth.core.vo.oauth2.Oauth2Code;
import com.soauth.core.vo.oauth2.RefreshToken;

/**
 *
 * @author zhoujie
 * @date 2017/9/25
 */
public interface TokenService {
    /**
     * ����accessToken
     * @param accessToken
     * @return
     */
    AccessToken saveAccessToken(AccessToken accessToken);

    /**
     * ����refreshToken
     * @param refreshToken
     * @return
     */
    RefreshToken saveRefreshToken(RefreshToken refreshToken);


    /**
     *  ���� clientid, code ��ѯ
     * @param clientid
     * @param code
     * @return
     */
    Oauth2Code findOauth2Code(String clientid, String code);

    /**
     *  ����clientid , username ��ѯ
     * @param username
     * @param clientid
     * @return
     */
    Oauth2Code findOauth2CodeUserAndClientId(String username, String clientid);

    /**
     *  ɾ��code
     * @param code
     * @return
     */
    int deleteOauth2Code(Oauth2Code code);

    /**
     * ����code
     * @param code
     * @return
     */
    int saveOauth2Code(Oauth2Code code);

}
