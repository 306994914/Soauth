package com.soauth.server.service;

import com.soauth.core.vo.oauth2.ClientDetails;
import com.soauth.core.vo.oauth2.Oauth2Code;

/**
 * Created by zhoujie on 2017/9/25.
 */
public interface Oauth2Service {

    /**
     * ����client id���� client��Ϣ
     * @param clientId
     * @return
     */
    ClientDetails loadClientDetails(String clientId);

    /**
     *  ����clientid �� code ����Code��Ϣ
     * @param code
     * @param clientDetails
     * @return
     */
    Oauth2Code  loadOauthCode(String code, ClientDetails clientDetails);

    /**
     *  ����code
     * @param code
     * @return
     */
    int saveOauthcode (Oauth2Code code);

}
