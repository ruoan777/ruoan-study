package com.ruoan.study.optional;

import java.util.Optional;

/**
 * @author xh.gao
 * @Description
 * @createTime 2020年09月30日 15:31:00
 */
public class Programmer {

    public void setGirlFriend(GirlFriend girlFriend) {
        this.girlFriend = girlFriend;
    }

    private GirlFriend girlFriend;

    public Programmer() {
    }

    public Optional<GirlFriend> getGirlFriend() {
        return Optional.of(girlFriend);
    }


    public static void main(String[] args) {
        Optional<Programmer> p = Optional.of(new Programmer());
        // TODO: 2020/9/30 注意下面这两种方法的返回值类型的区别
        Optional<Optional<GirlFriend>> girlFriend = p.map(Programmer::getGirlFriend);
        Optional<GirlFriend> girlFriend1 = p.flatMap(Programmer::getGirlFriend);

        Optional<Address> address = girlFriend1.flatMap(x -> Optional.of(x.getAddress()));
        System.out.println(address.map(Address::getCity).orElse("shanghai"));
    }
}


class GirlFriend {
    private String name;
    private int age;
    private Address address;

    public GirlFriend() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "GirlFriend{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}

class Address {
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}