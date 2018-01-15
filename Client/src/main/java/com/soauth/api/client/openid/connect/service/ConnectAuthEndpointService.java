package com.soauth.api.client.openid.connect.service;

import com.soauth.api.client.model.ServerConfig;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 * @author zhoujie
 * @date 2017/11/29
 */
public interface ConnectAuthEndpointService {

    /**
     *  ����ƴװ��ɵ�url����
     * @param session
     * @param scope
     * @param clientid
     * @param responseType
     * @param redirectUri
     * @param options
     * @return
     */
 String connectauthuri(HttpSession session, String scope,String clientsecret, String clientid, String responseType, String redirectUri, Map<String,String> options);

    /**
     *
     * @return ����һ������������
     */
 ServerConfig getServerConfig();


}
