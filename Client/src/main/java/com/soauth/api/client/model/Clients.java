package com.soauth.api.client.model;

import lombok.Data;

/**
 *
 * @author zhoujie
 * @date 2017/11/16
 *
 * ���յ�������¼����,�Լ����͸���֤����˵Ĳ���
 */
@Data
public class Clients {

     private String clientid;

     private String responseType;

     private String redirectUri;

     private String scope;

     private String state;

     private String display;

     /**
      * ��ֹ�طŹ���
      */
     private  String nonce;

}
