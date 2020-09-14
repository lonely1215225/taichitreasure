package edu.hunau.huffmantree;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
//        String context="i love my country, China";
//        //String context="10101010";
//        byte[] bytes=context.getBytes();
//        byte[] huffmanZip=huffmanZip( bytes );
//        System.out.println( Arrays.toString( huffmanZip ) );
//
//        System.out.println( "��ʼ����......" );
//        byte[] decode=decode( map, huffmanZip );
//        System.out.println( Arrays.toString( decode ) );
//        System.out.println( new String( decode ) );

        zipFile("C:\\Users\\Administrator\\Desktop/linux�������Լ���.png","C:\\Users\\Administrator\\Desktop/picture.zip");
//        System.out.println(bytes.length);
//       System.out.println(Arrays.toString( bytes ));
//        List<Node1> huffman=getNodes( bytes );
//       Node1 node1=createHuffman( huffman );
//        StringBuilder stringBuilder=new StringBuilder();
//        Map<Byte, String> codes=getCodes( node1, "", stringBuilder );
//       byte[] zip=zip( bytes, codes );
//        decode(  codes,zip);
//        System.out.println( codes.toString() );
        //       System.out.println( Arrays.toString( zip ) );
        //System.out.println( (byte)Integer.parseInt( context, 2 ) );//context:10101010�����ʮ����ֵ-86

    }

    public static byte[] huffmanZip(byte[] bytes) {
        System.out.println( "�ַ���Ӧ��Ascii��Ϊ��" );
        System.out.println( Arrays.toString( bytes ) );
        //�õ�KV��ֵ����ʽ�ļ���list��(105��2)
        List<Node1> nodes=getNodes( bytes );
        //ͨ��list�õ���������
        Node1 huffman=createHuffman( nodes );

        Map<Byte, String> codes=getCodes( huffman, "0", new StringBuilder() );
//        System.out.println( "Ascii���Ӧ�Ĺ��������룺" );
//        codes.forEach( (key, value) -> {
//            System.out.println( key + ":" + value );
//        } );
        byte[] zip=zip( bytes, codes );
        return zip;
    }

    //��һ�����Ȱ��ַ���Ӧ��Ascii�루byte[]�����ŵ�map�У�����map��keyΪÿ��byte�ֽڣ�105��i����valueΪ�����ֽڵĸ�����
    //����map.put(105,1)��ʾi��Ӧ��ascii�룬������1��.  Ȼ��Ϊÿһ��map��ֵ��newһ��node�ڵ㣬���node�ڵ����list�����С�
    public static List<Node1> getNodes(byte[] bytes) {
        List<Node1> nodes=new ArrayList<>();
        Map<Byte, Integer> map=new HashMap<>();
        for (byte b : bytes) {
            Integer count=map.get( b );
            if (count == null)
                map.put( b, 1 );
            else
                map.put( b, count + 1 );
        }
//        map.forEach((key,value)->{
//            System.out.println(key+":"+value);
//        });
        //Set<Map.Entry<Byte, Integer>> entries=map.entrySet();
        for (Map.Entry<Byte, Integer> entries : map.entrySet()) {
            nodes.add( new Node1( entries.getKey(), entries.getValue() ) );
        }
        return nodes;
    }

    //ͨ�����淽�����list���Ϻ�ͨ��list���������������������õ�һ�������
    public static Node1 createHuffman(List<Node1> list) {
        while (list.size() > 1) {
            Collections.sort( list );//�����ִ���(����)��С��������
            Node1 leftNode=list.get( 0 );//
            Node1 rightNode=list.get( 1 );//��ó��ִ�����С���������
            Node1 parent=new Node1( null, leftNode.weight + rightNode.weight );//������������Ȩֵ��Ӳ�����һ���½�㡣
            parent.left=leftNode;//
            parent.right=rightNode;//��������
            list.remove( leftNode );//
            list.remove( rightNode );//�Ƴ�Ȩֵ��С���������
            list.add( parent );//���������뼯��
        }
        return list.get( 0 );
    }

    //ͨ�������Ѿ��õ��Ĺ�������������㣬�����б���
    //������δ��룬�Ǹ������룬��0��1�����������õ���map����keyΪ�����ֽڣ�valueΪ�����ֽ�ƴ�ӵ�01����������,��(105,1010)
    private static Map<Byte, String> map=new HashMap<>();

    public static Map<Byte, String> getCodes(Node1 node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1=new StringBuilder( stringBuilder );
        stringBuilder1.append( code );
        if (node.data == null) {
            getCodes( node.left, "0", stringBuilder1 );
            getCodes( node.right, "1", stringBuilder1 );
        } else
            map.put( node.data, stringBuilder1.toString() );
        return map;
    }

    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder=new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append( huffmanCodes.get( b ) );
        }
       // System.out.println( stringBuilder.toString() );
        int len=(stringBuilder.length() + 7) / 8;
        byte[] huffmanCodeBytes=new byte[len];
        int index=0;
        for (int i=0; i < stringBuilder.length(); i+=8) {
            String temp;
            if (i + 8 > stringBuilder.length())
                temp=stringBuilder.substring( i );
            else
                temp=stringBuilder.substring( i, i + 8 );
            huffmanCodeBytes[index]=(byte) Integer.parseInt( temp, 2 );
            index++;
        }
        return huffmanCodeBytes;
    }

    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanCodeBytes) {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0; i < huffmanCodeBytes.length; i++) {
            byte b=huffmanCodeBytes[i];
            boolean flag=(i == huffmanCodeBytes.length - 1);
            stringBuilder.append( byteToBitString( !flag, b ) );
        }
       // System.out.println( stringBuilder );
        Map<String, Byte> hashMap=new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            hashMap.put( entry.getValue(), entry.getKey() );
        }

        List<Byte> list=new ArrayList<>();
        int count;
        String code;
        Byte aByte;
        for (int i=0; i < stringBuilder.length(); ) {
            count=1;
            while (true) {
                code=stringBuilder.substring( i, i + count );
                aByte=hashMap.get( code );
                if (aByte == null)
                    count++;
                else
                    break;
            }
            i+=count;
            list.add( aByte );
        }

        byte[] b=new byte[list.size()];
        for (int i=0; i < list.size(); i++)
            b[i]=list.get( i );

        return b;
    }

    public static String byteToBitString(boolean flag, byte b) {
        int temp=b;
        if (flag)
            temp|=256;//����8λ����ȫ1 0000 0000 | 1010 =1 0000 1010
        String s=Integer.toBinaryString( temp );
        if (flag | temp < 0)
            return s.substring( s.length() - 8 );//ȡ��8λ
        else
            return s;//�����������ֱ�ӷ���
    }

    public static void zipFile(String srcPath, String destPath) {
        InputStream in=null;
        OutputStream out=null;
        ObjectOutputStream oout=null;

        try {
            in=new FileInputStream( srcPath );
            byte[] b=new byte[in.available()];
            in.read( b );
            byte[] huffmanZip=huffmanZip( b );
            out=new FileOutputStream( destPath );
            out.write( huffmanZip );
           // oout=new ObjectOutputStream( out );
            //oout.writeObject( huffmanZip );
            //oout.writeObject( map );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
//                oout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

class Node1 implements Comparable<Node1> {
    Byte data;
    int weight;
    Node1 left;
    Node1 right;


    public Node1(Byte data, int weight) {
        this.data=data;
        this.weight=weight;
    }

    public void preOrder( ) {
        System.out.println( this );
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    @Override
    public String toString( ) {
        return "Node1{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node1 node1) {
        return this.weight - node1.weight;
    }
}
