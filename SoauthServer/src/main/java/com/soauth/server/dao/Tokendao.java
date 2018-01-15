package com.soauth.server.dao;

import com.soauth.core.vo.oauth2.AccessToken;
import com.soauth.core.vo.oauth2.Oauth2Code;
import com.soauth.core.vo.oauth2.RefreshToken;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author zhoujie
 * @date 2017/12/6
 */
public interface Tokendao {

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
    Oauth2Code findOauth2Code(@Param("clientid") String clientid, @Param("code") String code);

    /**
     *  ����clientid , username ��ѯ
     * @param username
     * @param clientid
     * @return
     */
    Oauth2Code findOauth2CodeUserAndClientId( @Param("username") String username, @Param("clientid") String clientid);

    /**
     * ����code
     * @param code
     * @return
     */
    int saveOauth2Code(Oauth2Code code);

    /**
     *   ɾ��code
     * @param code
     * @return
     */
    int deleteOauth2Code(Oauth2Code code);

}
