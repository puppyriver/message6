package com.alcatelsbell.nms.util;

import com.alcatelsbell.nms.valueobject.odn.Rack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 12-9-12
 * Time: 下午1:25
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class SortUtil {
    public static void sort(List list,CompareAdapter compareAdapter) {
        ArrayList<ObjectWrapper> temp = new ArrayList();

        for (Object o : list) {
            temp.add(new ObjectWrapper(compareAdapter,o));
        }
        Collections.sort(temp);

        list.clear();
        for (ObjectWrapper wrapper : temp)
            list.add(wrapper.getObject());
    }

    static class ObjectWrapper implements Comparable {
        private CompareAdapter compareAdapter;
        private Object object;

        public CompareAdapter getCompareAdapter() {
            return compareAdapter;
        }

        public void setCompareAdapter(CompareAdapter compareAdapter) {
            this.compareAdapter = compareAdapter;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }

        ObjectWrapper(CompareAdapter compareAdapter, Object object) {
            this.compareAdapter = compareAdapter;
            this.object = object;
        }


        @Override
        public int compareTo(Object o) {
            return compareAdapter.compare(this.getObject(),((ObjectWrapper)o).getObject());
        }
    }

    // 若从小到大，则return o1-o2
    public interface CompareAdapter {
        public int compare(Object o1, Object o2) ;
    }


    public static void main(String[] args) {
        List<Rack> l = new ArrayList();
        Rack rack = new Rack();
        rack.setLocationIndex(3);
        l.add(rack);

        Rack rack2 = new Rack();
        rack2.setLocationIndex(1);
        l.add(rack2);

        Rack rack3 = new Rack();
        rack3.setLocationIndex(2);
        l.add(rack3);

        Rack rack4 = new Rack();
        rack4.setLocationIndex(4);
        l.add(rack4);

        SortUtil.sort(l,new CompareAdapter() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Rack)o2).getLocationIndex() -((Rack)o1).getLocationIndex() ;
            }
        });

        System.out.println(l);
        for (Rack r : l)
            System.out.println(r.getLocationIndex());

    }
}
