package com.soauth.api.client.oauth.service;

/**
 *
 * @author zhoujie
 * @date 2017/11/16
 */
public interface ConfigurationService {

    /**
     *  ��ȡaccessToken��issuer��ַ
     * @param accessToken
     * @return
     */
    String getissuerUrl(String accessToken);

}
