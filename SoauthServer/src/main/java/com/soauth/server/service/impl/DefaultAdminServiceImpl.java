package com.soauth.server.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.soauth.core.model.SidebarTree;
import com.soauth.server.dao.Admindao;
import com.soauth.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhoujie
 * @date 2017/12/14
 */

@Service
public class DefaultAdminServiceImpl implements AdminService {

    @Autowired
    Admindao admindao;

    @Override
    public Map<String, List<Object>> adminSidebar(Long id, HttpServletRequest request, HttpServletResponse response) {
        if(id==null){

        }

        Map<String,List<Object>> result= Maps.newHashMap();
        List<SidebarTree> side=admindao.adminSidebar(id);
        List<SidebarTree> transform= Lists.newArrayList();
        if(side.isEmpty()) {
            result.put("Ufunc", Lists.newArrayList());
        }
        else{
            HttpServletRequest httpRequest=request;
            String basePath=httpRequest.getContextPath();
            for (SidebarTree sidebarTree : side) {
                sidebarTree.setUrl(basePath+sidebarTree.getUrl());

                transform.add(sidebarTree);
            }
            result.put("Ufunc", formatTree(transform));
        }
        // list Ϊ����json����
        List<Object> Uinfo=Lists.newArrayList();
        Uinfo.add(id);
        result.put("UInfo",Uinfo);

        return result;
    }




    public static List<Object> formatTree(List<SidebarTree> list ){
        SidebarTree root=new SidebarTree();
        // ������и��ڵ�
        List<SidebarTree> roots=Lists.newArrayList();
        SidebarTree node=new SidebarTree();
        //ƴ�պõ�Json����
        List<Object> treelist=Lists.newArrayList();
        // ������и��ڵ�
        List<SidebarTree> parentNodes=Lists.newArrayList();
        if(list!=null && list.size()>0){

            for (SidebarTree sidebarTree : list) {
                if(sidebarTree.getParent().equals("0")){
                    roots.add(sidebarTree);
                }
            }

            for(int j=0; j<roots.size(); j++) {
                //��ȡ���ڵ�
                root=roots.get(j);

                for(int i=roots.size(); i<list.size(); i++){
                    node=list.get(i);

                    //�Ӹ��ڵ㿪ʼ�����ǲ����ӽڵ�
                    if(node.getParent().equals(root.getId())){

                        parentNodes.add(node);
                        root.getChildren().add(node);

                    }else{ //��ȡroot�ӽڵ�ĺ��ӽڵ�
                        getChildrenNodes(parentNodes, node);
                        parentNodes.add(node);
                    }
                }

                treelist.add(root);
            }
        }

        return treelist;
    }


    private static void getChildrenNodes(List<SidebarTree> parentNodes , SidebarTree node){
        for(int i=parentNodes.size()-1; i>=0; i--){
            SidebarTree pnode=parentNodes.get(i);
            if(pnode.getId().equals(node.getParent())){
                pnode.getChildren().add(node);
                pnode.setChildren(removeDuplicate(pnode.getChildren()));
                return;
            }


        }
    }


    public static List<SidebarTree> removeDuplicate(List<? super SidebarTree> list) {
        List<SidebarTree> listTemp = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add((SidebarTree) list.get(i));
            }
        }
        return listTemp;
    }


}
