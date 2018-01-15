package com.soauth.core.utils;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import net.sf.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


/**
 *  datatables ��װ����
 * @param <T>
 */
@Data
public class DataTablePageUtil<T> {

    /**
     *  �ڼ�������
     */
    private int draw;
    /**
     * ��һ�����ݵ���ʼλ��
     */
    private int start = 0;

    /**
     * ������ÿҳ��ʾ������.
     */
    private int length = 100;

    /**
     * ȫ�ֵ���������
     */
    private String search;

    /**
     * ���Ϊ true����ȫ��������ֵ����Ϊ������ʽ����
     *
     */
    private boolean isSearch=false;

    /**
     * ��Ҫ�������
     */
    private int[] order;

    /**
     * ���ߺ�̨������ķ�ʽ�� desc ���� asc����
     */
    private String order_dir;

    /**
     * columns �󶨵�����Դ���� columns.dataOption ���塣
     */
    private String columns_data;

    /**
     * columns �����֣��� columns.nameOption ���塣
     */
    private String columns_name;

    /**
     * ������Ƿ��ܱ�����,Ϊtrue������ԣ����򲻿��ԣ�������� columns.searchableOption ����
     */
    private String columns_searchable;

    /**
     * ������Ƿ�������,Ϊ true������ԣ����򲻿��ԣ�������� columns.orderableOption ����
     */
    private boolean is_orderable;

    /**
     * ��Ǿ����е���������
     */
    private String columns_search_value;

    /**
     * �ض��е����������Ƿ���Ϊ������ʽ�� ���Ϊ true����������ֵ����Ϊ������ʽ����Ϊ false���ǡ�
     * ע�⣺ͨ���ڷ�����ģʽ�¶��ڴ����ݲ�ִ��������������ʽ�����ⶼ���Լ�������
     */
    private boolean is_search_regex;

	/*
     * ��Ҫ�������ᵽ�ˣ�Datatables���͵�draw�Ƕ�����ô�������ͷ��ض��١�
	 * ����ע�⣬���߳��ڰ�ȫ�Ŀ��ǣ�ǿ��Ҫ������ת��Ϊ���Σ������ֺ��ٷ��أ������Ǵ���Ľ���Ȼ�󷵻أ����� Ϊ�˷�ֹ��վ�ű���XSS��������
	 */
    // private int draw;

    /**
     * ��Ҫ.��û�й��˵ļ�¼�������ݿ����ܹ���¼����
     */
    private Long recordsTotal;

    /**
     * ��Ҫ.���˺�ļ�¼��������н��յ�ǰ̨�Ĺ����������򷵻ص��ǹ��˺�ļ�¼����
     */
    private Long  recordsFiltered;

    /**
     * ��Ҫ����������Ҫ��ʾ�����ݡ�����һ���������飬Ҳ����ֻ�����飬 �������� ������ǰ̨�Ͳ���Ҫ�� columns�����ݣ����Զ�����˳��ȥ��ʾ
     * ����������������Ҫʹ�� columns�����ݲ���������ʾ�� ע����� data�����ƿ����� ajaxOption ��
     * ajax.dataSrcOption ����
     */
    private List<T> data;

    /**
     * ��ѡ. ����Զ���һ�����������������������������Ѻ���ʾ
     */
    private String error;


    /**
     * �Զ��󶨵� tr�ڵ���
     */
    private String dt_rowId;

    /**
     * �Զ������������ӵ� tr
     */
    private String dt_rowClass;

    /**
     * ʹ�� jQuery.data() ���������ݰ󶨵�row�У�����֮�������������������һ������¼���
     */
    private Object dt_rowData;

    /**
     * �Զ������ݵ� tr�ϣ�ʹ�� jQuery.attr() ����������ļ��������ԣ�ֵ�������Ե�ֵ�� ע����� ��Ҫ Datatables
     * 1.10.5+�İ汾��֧��
     */
    private Object dt_rowAttr;

    /**
     * ��ǰҳ��
     */
    private int page_num = 1;

    /**
     * ÿҳ����
     */
    private int page_size = 10;


    public DataTablePageUtil() {

    }

    /**
     *
     * @param dataTable ��װ��ǰ�˲���
     * @param list ��װ�����ݿ��ҳ���ز���
     * @return
     */
     public  static <T> String pageTable(DataTablePageUtil<T> dataTable, List<T> list){
        PageInfo page = new PageInfo(list);
        long  total=page.getTotal();

        dataTable.setRecordsTotal(total);
        dataTable.setData(list);
        dataTable.setDraw(dataTable.getDraw());
        dataTable.setRecordsFiltered(total);
        String json= JSONObject.fromObject(dataTable).toString();

        return json;
    }

    public DataTablePageUtil(HttpServletRequest request, String startIndex, String pageSize) {
        //��ʼ����������
        String start =startIndex;
        //ÿҳ��������
        String length = pageSize;

        setPage_size(Integer.parseInt(pageSize));

        //DT���ݵ�draw:
        String draw ="4";

        this.setStart(Integer.parseInt(start));
        this.setLength(Integer.parseInt(length));
        this.setDraw(Integer.parseInt(draw));
        //����ҳ��
        this.page_num = (Integer.parseInt(start) / Integer.parseInt(length)) + 1;

    }


}