package com.soauth.server.utils;



import com.google.common.collect.Maps;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhoujie on 2017/9/27.
 *����Ķ�ȡini�ļ�.
 */
public class LoadINIutis {
     private  final LinkedHashMap<String, LinkedHashMap<String,String>> coreMap = Maps.newLinkedHashMap();

    /**
     * ��ǰSection������
     */
    String currentSection = null;

    /**
     * ��ȡ
     * @param file �ļ�
     * @throws FileNotFoundException
     */
    public LoadINIutis(File file) throws FileNotFoundException {
        this.init(new BufferedReader(new FileReader(file)));
    }
    /***
     * ���ض�ȡ
     * @param path ���ļ�·��
     * @throws FileNotFoundException
     */
    public LoadINIutis(String path) throws FileNotFoundException {
        this.init(new BufferedReader(new FileReader(path)));
    }
    /***
     * ���ض�ȡ
     * @param source ClassPathResource �ļ�������ļ���resource ���ôֱ�� new ClassPathResource("file name");
     * @throws IOException
     */
    public LoadINIutis(ClassPathResource source) throws IOException {
        this(source.getFile());
    }

    void init(BufferedReader bufferedReader){
        try {
            read(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO Exception:" + e);
        }
    }
    /**
     * ��ȡ�ļ�
     * @param reader
     * @throws IOException
     */
    void read(BufferedReader reader) throws IOException {
        String line = null;
        while((line=reader.readLine())!=null) {
            parseLine(line);
        }
    }

    /**
     * ת��
     * @param line
     */
    void parseLine(String line) {
        line = line.trim();
        // �˲���Ϊע��
        if(line.matches("^\\#.*$")) {
            return;
        }else if (line.matches("^\\[\\S+\\]$")) {
            // section
            String section = line.replaceFirst("^\\[(\\S+)\\]$","$1");
            addSection(section);
        }else if (line.matches("^\\S+=.*$")) {
            // key ,value
            int i = line.indexOf("=");
            String key = line.substring(0, i).trim();
            String value =line.substring(i + 1).trim();
            addKeyValue(currentSection,key,value);
        }
    }


    /**
     * �����µ�Key��Value
     * @param currentSection
     * @param key
     * @param value
     */
    void addKeyValue(String currentSection,String key, String value) {
        if(!coreMap.containsKey(currentSection)) {
            return;
        }
        Map<String, String> childMap = coreMap.get(currentSection);
        childMap.put(key, value);
    }


    /**
     * ����Section
     * @param section
     */
    void addSection(String section) {
        if (!coreMap.containsKey(section)) {
            currentSection = section;
            LinkedHashMap<String,String> childMap = new LinkedHashMap<String,String>();
            coreMap.put(section, childMap);
        }
    }

    /**
     * ��ȡ�����ļ�ָ��Section��ָ���Ӽ���ֵ
     * @param section
     * @param key
     * @return
     */
    public String get(String section,String key){
        if(coreMap.containsKey(section)) {
            return  get(section).containsKey(key) ?  get(section).get(key): null;
        }
        return null;
    }



    /**
     * ��ȡ�����ļ�ָ��Section���Ӽ���ֵ
     * @param section
     * @return
     */
    public Map<String, String> get(String section){
        return  coreMap.containsKey(section) ? coreMap.get(section) : null;
    }

    /**
     * ��ȡ��������ļ��Ľڵ��ֵ
     * @return
     */
    public LinkedHashMap<String, LinkedHashMap<String, String>> get(){
        return coreMap;
    }


}

