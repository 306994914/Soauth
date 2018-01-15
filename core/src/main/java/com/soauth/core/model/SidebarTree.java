package com.soauth.core.model;
import java.util.List;
import com.google.common.collect.Lists;
import net.sf.json.JSONObject;
import lombok.Data;


/**
 *  
 * @author zhoujie
 * ������װ��������
 *
 */

@Data
public class SidebarTree {
      private String  url;
      private String urlname;
      private String id;
      private String parent;
      private String cssname;
      private String urltype;
      private JSONObject attributes = new JSONObject();
    /**
     * ����ӽڵ�
     */
    private List<SidebarTree> children= Lists.newArrayList();
    




      
}
