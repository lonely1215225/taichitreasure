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
//        System.out.println( "开始解码......" );
//        byte[] decode=decode( map, huffmanZip );
//        System.out.println( Arrays.toString( decode ) );
//        System.out.println( new String( decode ) );

        zipFile("C:\\Users\\Administrator\\Desktop/linux进程属性简析.png","C:\\Users\\Administrator\\Desktop/picture.zip");
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
        //System.out.println( (byte)Integer.parseInt( context, 2 ) );//context:10101010补码的十进制值-86

    }

    public static byte[] huffmanZip(byte[] bytes) {
        System.out.println( "字符对应的Ascii码为：" );
        System.out.println( Arrays.toString( bytes ) );
        //得到KV键值对形式的集合list，(105：2)
        List<Node1> nodes=getNodes( bytes );
        //通过list得到哈夫曼树
        Node1 huffman=createHuffman( nodes );

        Map<Byte, String> codes=getCodes( huffman, "0", new StringBuilder() );
//        System.out.println( "Ascii码对应的哈夫曼编码：" );
//        codes.forEach( (key, value) -> {
//            System.out.println( key + ":" + value );
//        } );
        byte[] zip=zip( bytes, codes );
        return zip;
    }

    //第一步：先把字符对应的Ascii码（byte[]），放到map中（其中map的key为每个byte字节：105（i）；value为各个字节的个数）
    //比如map.put(105,1)表示i对应的ascii码，出现了1次.  然后为每一个map键值对new一个node节点，最后将node节点放入list集合中。
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

    //通过上面方法获得list集合后，通过list集合来创建哈夫曼树。得到一个根结点
    public static Node1 createHuffman(List<Node1> list) {
        while (list.size() > 1) {
            Collections.sort( list );//按出现次数(概率)从小到大排序
            Node1 leftNode=list.get( 0 );//
            Node1 rightNode=list.get( 1 );//获得出现次数最小的两个结点
            Node1 parent=new Node1( null, leftNode.weight + rightNode.weight );//将这两个结点的权值相加并放入一个新结点。
            parent.left=leftNode;//
            parent.right=rightNode;//进行树化
            list.remove( leftNode );//
            list.remove( rightNode );//移除权值最小的两个结点
            list.add( parent );//将新树加入集合
        }
        return list.get( 0 );
    }

    //通过上面已经得到的哈夫曼树，根结点，来进行编码
    //下面这段代码，是给树编码，左0右1。。。。。得到的map集合key为结点的字节；value为对于字节拼接的01哈夫曼编码,如(105,1010)
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
            temp|=256;//不够8位，则补全1 0000 0000 | 1010 =1 0000 1010
        String s=Integer.toBinaryString( temp );
        if (flag | temp < 0)
            return s.substring( s.length() - 8 );//取后8位
        else
            return s;//如果是正数就直接返回
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
