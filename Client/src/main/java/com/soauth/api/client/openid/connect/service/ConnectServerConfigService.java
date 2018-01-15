package com.soauth.api.client.openid.connect.service;

import com.soauth.api.client.model.ServerConfig;

/**
 *
 * @author zhoujie
 * @date 2017/11/6
 *
 * ����(��)Openid���з�
 */
public interface ConnectServerConfigService {

    /**
     *  ��ȡOIDC����������
     * @param issuer  key
     * @return
     */
    ServerConfig loadServerConfig(String issuer);
}
