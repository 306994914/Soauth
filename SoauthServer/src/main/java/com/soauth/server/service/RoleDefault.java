package com.soauth.server.service;

import java.util.Set;

/**
 *
 * @author zhoujie
 * @date 2017/11/1
 *
 * �û�Ȩ����ص�crud�������ڴ˽ӿ�
 */
public interface RoleDefault {
    /**
     * �����û���ɫ
     * @param userid
     * @return
     */
    Set<String> loadUserRole(String userid);
}
