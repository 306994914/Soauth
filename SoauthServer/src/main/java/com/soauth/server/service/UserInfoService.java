package com.soauth.server.service;

import com.soauth.core.model.UserInfo;
import java.util.List;
import java.util.Map;
/**
 *
 * @author zhoujie
 * @date 2017/11/1
 *
 * ���û� crud ����ȫ�����ڴ˽ӿ���
 */
public interface UserInfoService {

    /**
     * ��ȡ�û���Ϣ
     * @param username
     * @return
     */
    UserInfo getUserInfo(String username);

    /**
     * ��¼��֤�˺�����
     * @param username
     * @param password
     * @return
     */
    UserInfo login(String username,String password);


}
