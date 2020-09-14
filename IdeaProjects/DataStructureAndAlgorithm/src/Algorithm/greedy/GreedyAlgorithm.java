package Algorithm.greedy;

import java.util.*;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        Map<String, HashSet<String>> broadcasts=new HashMap<>();
        HashSet<String> h1=new HashSet<>();
        h1.add( "北京" );
        h1.add( "上海" );
        h1.add( "天津" );
        HashSet<String> h2=new HashSet<>();
        h2.add( "广州" );
        h2.add( "北京" );
        h2.add( "深圳" );
        HashSet<String> h3=new HashSet<>();
        h3.add( "成都" );
        h3.add( "上海" );
        h3.add( "杭州" );
        HashSet<String> h4=new HashSet<>();
        h4.add( "上海" );
        h4.add( "天津" );
        HashSet<String> h5=new HashSet<>();
        h5.add( "杭州" );
        h5.add( "大连" );
        broadcasts.put( "k1", h1 );
        broadcasts.put( "k2", h2 );
        broadcasts.put( "k3", h3 );
        broadcasts.put( "k4", h4 );
        broadcasts.put( "k5", h5 );


//        ArrayList<String> selects=new ArrayList<>();
//        Set<String> tempSet=new HashSet<>();
//        String maxKey=null;
//        while (allAreas.size() != 0) {
//            maxKey=null;
//            for (String key : broadcasts.keySet()) {
//                tempSet.clear();
//                tempSet.addAll( broadcasts.get( key ) );
//                tempSet.retainAll( allAreas );
//                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get( maxKey ).size()))
//                    maxKey=key;
//            }
//            if (maxKey!=null) {
//                selects.add( maxKey );
//                allAreas.removeAll( broadcasts.get( maxKey ) );
//            }
//        }

        System.out.println("结果："+listCover( broadcasts ));

    }
    public static List<String> listCover(Map<String,HashSet<String>> broadcasts){
        HashSet<String> allAreas=new HashSet<>();
        for (String key : broadcasts.keySet()) {
            for (String val : broadcasts.get( key )) {
                allAreas.add( val );//里面是所有不重复的城市
            }
        }
        List<String> selects=new ArrayList<>( );
        Set<String> temp=new HashSet<>();
        String maxKey=null;
        while (allAreas.size()!=0){
                maxKey=null;
            for (String key:broadcasts.keySet()){
                temp.clear();
                temp.addAll( broadcasts.get( key ) );
                temp.retainAll( allAreas );
                if (temp.size()>0&&(maxKey==null||temp.size()>broadcasts.get( maxKey ).size()))
                    maxKey=key;
            }
            if (maxKey!=null){
                selects.add( maxKey );
                allAreas.removeAll( broadcasts.get( maxKey ) );
            }
        }
        return selects;
    }
}
