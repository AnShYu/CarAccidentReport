package ru.andrey.caraccidentreport.testsandexperiments;

import ru.andrey.caraccidentreport.model.Address;

import java.util.ArrayList;
import java.util.List;

public class TestsApp {

    public static void main(String[] args) {

        List<Address> myList = new ArrayList<>();

        Address a1 = new Address("1", "1", "1", "1", "1");

        for(int i = 2; i<5; i++) {
            String a = Integer.toString(i);
            a1.setProvince(a);
            a1.setCity(a);
            a1.setDistrict(a);
            a1.setStreet(a);
            a1.setBuilding(a);
            myList.add(a1);
            System.out.println(myList.get(i-2));
        }
        System.out.println("first element:" + myList.get(0) + "\n second element:" + myList.get(1));

        for (Address address: myList) {
            System.out.println(address);
        }
    }


}
