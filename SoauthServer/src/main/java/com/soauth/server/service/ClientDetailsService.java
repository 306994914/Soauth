package com.soauth.server.service;

import com.soauth.core.vo.oauth2.ClientDetails;
import com.soauth.server.model.ClientInfoPage;

import java.util.List;

/**
 *
 * @author zhoujie
 * @date 2017/12/15
 */
public interface ClientDetailsService {


    /**
     *  �����½���Client
     * @param clientDetails
     * @return
     */
 boolean saveClientDetails(ClientDetails clientDetails);

    /**
     * ��ѯ���еĿͻ���.
     * @param clientInfoPage
     * @return
     */
 List<ClientInfoPage> getAllClientDetails(ClientInfoPage clientInfoPage);

}
