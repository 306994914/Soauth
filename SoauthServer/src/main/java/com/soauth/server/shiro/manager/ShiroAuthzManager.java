package com.soauth.server.shiro.manager;

/**
 * Created by zhoujie on 2017/9/27.
 */
public interface ShiroAuthzManager {

    /**
     * ���ع���������Ϣ
     * @return
     */
    public String loadauthzFilesinits();

    /**
     * ���¹���Ȩ�޹�����
     * һ�����޸����û���ɫ���û�����Ϣʱ����Ҫ�ٴε��ø÷���
     */
    public void reCreateFilterChains();

}
