package com.soauth.server.oauth.validator.client;

import com.soauth.core.utils.SpringBeanUtils;
import com.soauth.core.vo.oauth2.ClientDetails;
import com.soauth.server.oauth.SoauthAuthRequest;
import com.soauth.server.oauth.SoauthTokenRequest;
import com.soauth.server.oauth.message.BaseSoauthResponse;
import com.soauth.server.service.Oauth2Service;
import org.apache.oltu.oauth2.as.request.OAuthRequest;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 *
 * @author zhoujie
 * @date 2017/9/23
 *
 * ��֤�ͻ��˲����Ĺ�����
 * AbstractClientDetailsValidator
 */
public abstract class BaseClientValidator extends BaseSoauthResponse {
     private  static final Logger logger= LoggerFactory.getLogger(BaseClientValidator.class);


     protected transient  Oauth2Service oauth2Service= SpringBeanUtils.getBean(Oauth2Service.class);



     protected  OAuthRequest authRequest;

     private  ClientDetails clientDetails;

    /**
     *��ʼ�� CodeClientValidator TokenClientValidator
     * @param oAuthRequest
     */
     public BaseClientValidator(OAuthRequest oAuthRequest){
         super(oAuthRequest);
         authRequest =oAuthRequest;
     }


    protected  ClientDetails clientDetails( ){
        return  clientDetails == null ?  (clientDetails=oauth2Service.loadClientDetails(authRequest.getClientId())): clientDetails ;
    }

    public  final OAuthResponse validate() throws OAuthSystemException{
         ClientDetails clientDetails = clientDetails();

        return clientDetails ==null ? invalidClientErrorResponse(): validateSelf(clientDetails);
    }

    protected boolean verifyScopes(Set<String> scopes, ClientDetails clientDetails){
        return  false;
    }

    protected  boolean verifypwd(){
        return  true;
    }



    /**
     *  ���෽������ҵ��ʵ���Լ�����֤�߼�
     * @param clientDetails
     * @return
     * @throws OAuthSystemException
     */
    protected abstract OAuthResponse validateSelf(ClientDetails clientDetails) throws OAuthSystemException;

}
