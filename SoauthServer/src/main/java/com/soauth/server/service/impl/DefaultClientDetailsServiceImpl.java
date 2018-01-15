package com.soauth.server.service.impl;

import com.soauth.core.vo.oauth2.AbstractOIDC;
import com.soauth.core.vo.oauth2.ClientDetails;
import com.soauth.server.dao.ClientDetailsdao;
import com.soauth.server.model.ClientInfoPage;
import com.soauth.server.oauth.authorize.ServerConfig;
import com.soauth.server.service.ClientDetailsService;
import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author zhoujie
 * @date 2017/12/15
 */
@Service
public class DefaultClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    ClientDetailsdao clientDetailsdao;

    @Autowired
    ServerConfig serverConfig;

    /**
     *  ���client, ��Ϊÿ��client��ӻ���api����(�����¼, ������Ȩ, ����ˢ��)
     * @param clientDetails
     * @return
     */
    @Override
    public  boolean saveClientDetails(ClientDetails clientDetails) {

        addToken(clientDetails);

        checkandAddJwk(clientDetails);

        checkGrantTypes(clientDetails);

        clientDetails.setGrantType(Arrays.toString(new Set[]{clientDetails.getGrantTypes()}));
        clientDetails.setScopeString(Arrays.toString(new Set[]{clientDetails.getScope()}));

       int rows= clientDetailsdao.saveClientDetails(clientDetails);
        return rows>0;
    }

    @Override
    public List<ClientInfoPage> getAllClientDetails(ClientInfoPage clientInfoPage) {

        return clientDetailsdao.getAllClientDetails(clientInfoPage);
    }

    /**
     *  ע��Ŀͻ��˲���ͬʱӵ��jwkurl �� jwkValue
     * @param clientDetails
     */
    private  void checkandAddJwk(ClientDetails clientDetails){

        if(clientDetails.getJwk()!=null && !StringUtils.isNotEmpty(clientDetails.getJwksUri().trim())){
            throw  new IllegalArgumentException("ע��Ŀͻ��˲���ͬʱӵ��jwkurl �� jwkValue");
        }

        //�������Աû��ָ��client jwkurl��Ϊ�����Ĭ�ϵ�jwk��ȡ��ַ
        if(!StringUtils.isNotEmpty(clientDetails.getJwksUri())){
            String jwkUri=serverConfig.getJwkUri();
            clientDetails.setJwksUri(jwkUri);
        }
    }

    private void addToken(ClientDetails clientDetails){
        Set<String>  set = new LinkedHashSet<>();


        if(!clientDetails.getGrantTypes().contains(OAuth.OAUTH_REFRESH_TOKEN) && !clientDetails.getGrantTypes().contains(GrantType.CLIENT_CREDENTIALS) &&!clientDetails.getGrantTypes().contains(AbstractOIDC.OAUTH_Implicit))
        {
            set.add(OAuth.OAUTH_REFRESH_TOKEN);
        clientDetails.setGrantTypes(set);
            clientDetails.setScope(set);
        }

        //������Ȩ
        if(!clientDetails.getGrantTypes().contains(OAuth.OAUTH_ACCESS_TOKEN)){
            set.add(OAuth.OAUTH_ACCESS_TOKEN);
        }

        //�����¼
        if(!clientDetails.getGrantTypes().contains(AbstractOIDC.ID_TOKEN)){
            set.add(AbstractOIDC.ID_TOKEN);
        }
    }

    /**
     * ��ֹʹ��passwordģʽ, ���redirectUri, ���ͻ��˼�����, ��鲢���ClientAuthentication
     * @param clientDetails
     */
    private  void  checkGrantTypes(ClientDetails clientDetails){


        if(!StringUtils.isNotEmpty(clientDetails.getRedirectUri())){
            throw  new IllegalArgumentException("�ͻ���û��ָ���ص��õ�ַ");
        }

        if(clientDetails.getTokenEndpointmethod()==null){

            clientDetails.setTokenEndpointmethod(ClientDetails.Authentication.SECRET_BASIC);
        }

        if(clientDetails.getGrantTypes().contains(GrantType.AUTHORIZATION_CODE)){

            if(clientDetails.getGrantTypes().contains(GrantType.CLIENT_CREDENTIALS) ||clientDetails.getGrantTypes().contains(AbstractOIDC.OAUTH_Implicit)){
                throw  new IllegalArgumentException("��������ע��authorization_codeģʽ��ͬʱ��ע��client_credentialsģʽ �� implicit ģʽ");
            }
        }

        if(clientDetails.getGrantTypes().contains(GrantType.CLIENT_CREDENTIALS)){

            if(clientDetails.getGrantTypes().contains(GrantType.AUTHORIZATION_CODE) ||clientDetails.getGrantTypes().contains(AbstractOIDC.OAUTH_Implicit)){
                throw  new IllegalArgumentException("��������ע��client_credentialsģʽ��ͬʱ��ע��authorization_codeģʽ �� implicit ģʽ");
            }
        }

        if(clientDetails.getGrantTypes().contains(AbstractOIDC.OAUTH_Implicit)){

            if(clientDetails.getGrantTypes().contains(GrantType.AUTHORIZATION_CODE) ||clientDetails.getGrantTypes().contains(GrantType.CLIENT_CREDENTIALS)){
                throw  new IllegalArgumentException("��������ע��implicitģʽ��ͬʱ��ע��authorization_codeģʽ ��client_credentialsģʽ");
            }
        }


        if(clientDetails.getGrantTypes().contains(GrantType.PASSWORD)){
            throw  new IllegalArgumentException("��Ȩ��������֧��OAuth2.0��password��Ȩģʽ");
        }

    }

}
