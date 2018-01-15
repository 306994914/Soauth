package com.soauth.api.client.openid.connect.service;

import com.soauth.api.client.model.AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zhoujie
 * @date 2017/12/12
 */
public interface AuthenticationTokenService {

    /**
     * ��֤������������, ���������������Token
     * @return
     */
    AuthenticationToken getCodeAuthentToken(HttpServletRequest request, HttpServletResponse response);

    /**
     * ��ģʽ�»�ȡToken
     */
    AuthenticationToken getImplicitToken(HttpServletRequest request, HttpServletResponse response);
}
