package Algorithm.greedy;

import java.util.*;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        Map<String, HashSet<String>> broadcasts=new HashMap<>();
        HashSet<String> h1=new HashSet<>();
        h1.add( "����" );
        h1.add( "�Ϻ�" );
        h1.add( "���" );
        HashSet<String> h2=new HashSet<>();
        h2.add( "����" );
        h2.add( "����" );
        h2.add( "����" );
        HashSet<String> h3=new HashSet<>();
        h3.add( "�ɶ�" );
        h3.add( "�Ϻ�" );
        h3.add( "����" );
        HashSet<String> h4=new HashSet<>();
        h4.add( "�Ϻ�" );
        h4.add( "���" );
        HashSet<String> h5=new HashSet<>();
        h5.add( "����" );
        h5.add( "����" );
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

        System.out.println("�����"+listCover( broadcasts ));

    }
    public static List<String> listCover(Map<String,HashSet<String>> broadcasts){
        HashSet<String> allAreas=new HashSet<>();
        for (String key : broadcasts.keySet()) {
            for (String val : broadcasts.get( key )) {
                allAreas.add( val );//���������в��ظ��ĳ���
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
