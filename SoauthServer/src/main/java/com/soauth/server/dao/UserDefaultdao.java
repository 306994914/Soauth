package com.soauth.server.dao;
import com.soauth.core.model.UserInfo;

/**
 *
 * @author zhoujie
 * @date 2017/11/1
 */
public interface UserDefaultdao {

    /**
     * �����ݿ��л�ȡ�û���Ϣ
     * @param username
     * @return
     */
   UserInfo getUserInfo(String username);

   UserInfo verifyUser(String username, String password);
}
