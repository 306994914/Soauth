package com.soauth.api.client.web.authcontroller;


import com.soauth.api.client.openid.connect.service.AuthenticationTokenService;
import com.soauth.api.client.openid.connect.service.ConnectAuthEndpointService;
import com.soauth.core.vo.oauth2.AbstractOIDC;
import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author zhoujie
 * @date 2017/11/23
 *
 * �������е�������¼��Ȩ����.
 */
@Controller
@RequestMapping(value = "oidc")
public class AuthentControl {

   private Logger  log= LoggerFactory.getLogger(AuthentControl.class);

    @Autowired
    ConnectAuthEndpointService connectAuthEndPoint;

    @Autowired
    AuthenticationTokenService tokenService;


    /**
     * step 1:
     * ��վ��,�ƶ������ȷ����������¼����(ģ�����������partlogin.js��)
     *
     * step 2:
     * ��ȡ��ǰ�����¼����վ����Ϣ
     *
     * step 3:
     * ��װ��ǰ�����¼����վ��Ϣ
     *
     * step 4:
     *��op������֤��Ȩ����
     *
     *
     */
    @CrossOrigin
    @RequestMapping(value = "authorize", method = RequestMethod.GET,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void  signin(@RequestParam(value = "client_id") String clientid, @RequestParam(value = "scope") String scope,
                         @RequestParam(value = "redirect_uri") String redirectUri, @RequestParam(value = "response_type") String responseType,
                         @RequestParam(value = "client_secret") String clientsecret,
                         Device device, HttpServletRequest request, HttpServletResponse response){

        Map<String,String> options = new HashMap<>(3);

        /**
         * �������˵���ҳ��ѡ��
         */
        if(device.isNormal()){
            options.put(AbstractOIDC.OIDC_DISPLAY,AbstractOIDC.Display.PAGE.getDisplay());
        }else if(device.isMobile()){
            options.put(AbstractOIDC.OIDC_DISPLAY,AbstractOIDC.Display.WAP.getDisplay());
        }

        HttpSession session= request.getSession();
        /**
         * ƴ�Ӳ�ѯurl
         */
        String url= connectAuthEndPoint.connectauthuri(session,scope,clientsecret,clientid,responseType,redirectUri,options);


        log.debug("hold da redirect: {}",url);

        try {
            response.sendRedirect(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * step 1: ����˻ص�����code
     *
     * step 2: ִ����֤
     *
     * step 3: ͨ��code �õ�token
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "authorize_callback_code")
    public void  codecallback(HttpServletRequest request, HttpServletResponse response){
        //error request
        if(StringUtils.isNotEmpty(request.getParameter(OAuthError.OAUTH_ERROR)) || StringUtils.isNotEmpty(request.getParameter(OAuthError.OAUTH_ERROR_DESCRIPTION))){
            log.debug("op �ص���������.");

        }else if(StringUtils.isNotEmpty(request.getParameter(OAuth.OAUTH_CODE))){
            log.debug("op �ص�code.");
            tokenService.getCodeAuthentToken(request,response);
        }

        log.debug("op �ص�token{}",request.getParameter("access_token"));

        tokenService.getImplicitToken(request,response);
    }




}
