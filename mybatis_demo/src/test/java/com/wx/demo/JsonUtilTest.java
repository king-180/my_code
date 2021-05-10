package com.wx.demo;

import com.wx.demo.util.JSONUtilsEx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxing
 * @date 2021/4/28 10:52
 */
public class JsonUtilTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        System.out.println(list.toString());
        System.out.println(JSONUtilsEx.serialize(list));
    }
}
