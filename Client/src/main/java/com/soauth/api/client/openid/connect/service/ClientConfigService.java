package com.soauth.api.client.openid.connect.service;

import com.soauth.core.vo.oauth2.ClientDetails;

/**
 *
 * @author zhoujie
 * @date 2017/12/3
 */
public interface ClientConfigService {

    /**
     * ��ȡ�ͻ�������(��̬��,�Լ�Զ�̴�OP��ȡ��)
     * @param issuer  OP�˵�
     * @return
     */
   ClientDetails getClientConfig(String  issuer);
}
