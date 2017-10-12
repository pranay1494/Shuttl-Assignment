package com.example.pranaybansal.shuttl.comparator;

import com.example.pranaybansal.shuttl.model.pojo.Dummy;

import java.util.Comparator;

/**
 * Created by Pranay Bansal on 10/12/2017.
 */

public class DateComparator implements Comparator<Dummy>{
    @Override
    public int compare(Dummy o1, Dummy o2) {
        String s1 = o1.getTime();
        String s2 = o2.getTime();
        return s1.compareTo(s2);
    }
}
