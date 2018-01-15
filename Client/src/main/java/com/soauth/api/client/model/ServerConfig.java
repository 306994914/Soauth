package com.soauth.api.client.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author zhoujie
 * @date 2017/11/16
 *
 *
 * �����оٵĲ���ֻ������ϵͳClient����Ҫ�Ĳ���,����Ĳ��������Openid Connect �����ĵ�
 *
 * http://openid.net/specs/openid-connect-core-1_0.html
 *
 */
public class ServerConfig {

    /**
     * Op�ĵ�ַ
     */
    @Getter
    @Setter
    private String issuer;

    /**
     * ��ѡ����, OP �������֤����Ȩ�Ķ˵�uri
     */
    @Getter
    @Setter
    private String authorizationEndpointUri;

    /**
     * �����,  rpͨ��uri��ȡop��jwks
     */
    @Getter
    @Setter
    private String jwksUri;

    /**
     * ��ѡ����, op�����ƻ�ȡuri
     */
    @Getter
    @Setter
    private String tokenEndpointUri;

    @Getter
    @Setter
    private String userInfoUri;

}
