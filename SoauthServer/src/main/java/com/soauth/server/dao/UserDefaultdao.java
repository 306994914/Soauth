package com.soauth.server.dao;
import com.soauth.core.model.SidebarTree;
import com.soauth.core.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     *  ��֤�˺�����
     * @param username
     * @param password
     * @return
     */
   UserInfo verifyUser(String username, String password);


}
