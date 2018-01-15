package com.soauth.server.dao;

import com.soauth.core.vo.oauth2.ClientDetails;
import com.soauth.server.model.ClientInfoPage;

import java.util.List;

/**
 *
 * @author zhoujie
 * @date 2017/12/15
 */
public interface ClientDetailsdao {

    /**
     *  �����½���Client
     * @param clientDetails
     * @return
     */
    int  saveClientDetails(ClientDetails clientDetails);

    /**
     * ��ѯ���еĿͻ���.
     * @param clientInfoPage
     * @return
     */
    List<ClientInfoPage> getAllClientDetails(ClientInfoPage clientInfoPage);

}
