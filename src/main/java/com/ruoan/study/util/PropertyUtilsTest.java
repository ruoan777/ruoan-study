package com.ruoan.study.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年10月28日 10:35:00
 */
public class PropertyUtilsTest {

    public static class Address {
        private String email;

        public Address(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class UserInfo {
        private String userName;
        private int age;
        private Address address;
        private List<String> friendsNames;
        private Map<String, String> tag;

        UserInfo(String userName, int age, Address address, List<String> friendsNames, Map<String, String> tag) {
            this.userName = userName;
            this.age = age;
            this.address = address;
            this.friendsNames = friendsNames;
            this.tag = tag;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<String> getFriendsNames() {
            return friendsNames;
        }

        public void setFriendsNames(List<String> friendsNames) {
            this.friendsNames = friendsNames;
        }

        public Map<String, String> getTag() {
            return tag;
        }

        public void setTag(Map<String, String> tag) {
            this.tag = tag;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }
    }

    public void test() {
        List<String> friendList = Lists.newArrayList("张三", "李四");
        Map<String, String> tag = Maps.newHashMap();
        tag.put("key1", "value1");
        tag.put("key2", "value2");
        UserInfo userInfo = new UserInfo("tuacy", 18, new Address("1@qq.com"), friendList, tag);
        try {
            Map<String, Object> propertyMap = PropertyUtils.describe(userInfo);
            Object friend0 = PropertyUtils.getIndexedProperty(userInfo, "friendsNames[0]"); // 张三
            Object friend0Temp = PropertyUtils.getIndexedProperty(userInfo, "friendsNames", 0); // 张三
            Object mapperProperty = PropertyUtils.getMappedProperty(userInfo, "tag(key1)"); // value1
            Object mapperPropertyTemp = PropertyUtils.getMappedProperty(userInfo, "tag", "key1"); // value1
            Object nestedProperty = PropertyUtils.getNestedProperty(userInfo, "address.email"); // 1@qq.com
            Object name = PropertyUtils.getProperty(userInfo, "userName"); // tuacy
            PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(userInfo, "userName"); // PropertyDescriptor是对属性的一个封装
            PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(userInfo); // 获取所有的属性
            Class<?> classType = PropertyUtils.getPropertyType(userInfo, "address"); // 获取属性的类型Address
            Method getMethod = PropertyUtils.getReadMethod(PropertyUtils.getPropertyDescriptor(userInfo, "userName")); // getUserName()方法
            Object simpleProperty = PropertyUtils.getSimpleProperty(userInfo, "userName");
            Method setMethod = PropertyUtils.getWriteMethod(PropertyUtils.getPropertyDescriptor(userInfo, "userName")); // setUserName()方法
            boolean isReadable = PropertyUtils.isReadable(userInfo, "userName"); // true
            boolean isWriteable = PropertyUtils.isWriteable(userInfo, "userName"); // true
            PropertyUtils.setIndexedProperty(userInfo, "friendsNames[0]", "张三第一次修改"); // 修改friendsNames第一个元素
            PropertyUtils.setIndexedProperty(userInfo, "friendsNames", 0, "张三第二次修改"); // 修改friendsNames第一个元素
            PropertyUtils.setMappedProperty(userInfo, "tag(key1)", "value1第一次修改"); // 修改tag key为key1的值
            PropertyUtils.setMappedProperty(userInfo, "tag", "key1", "value1第二次修改"); // 修改tag key为key1的值
            PropertyUtils.setNestedProperty(userInfo, "address.email", "1007@qq.com"); // 修改address属性对象对应的email属性
            PropertyUtils.setProperty(userInfo, "userName", "tuacy0"); // 修改userName对应的属性值
            PropertyUtils.setSimpleProperty(userInfo, "age", 19); // 修改age对应的属性值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Long aa= 1234567890L;
        String l = String.valueOf(aa%10);
    }
}
