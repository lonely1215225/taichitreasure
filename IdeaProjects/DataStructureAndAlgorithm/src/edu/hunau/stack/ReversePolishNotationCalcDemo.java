package edu.hunau.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author ��׿��
 */
public class ReversePolishNotationCalcDemo {
    List<Integer> minus;//���ڼ�¼���ų��ֵ�����

    public static void main(String[] args) {
        String expression="-7.5*6/(-2+(-6.5- (-5.22)))";// 7.5 - 6.5 5.22 - - *
		//expression="7.5+-3*8/(7+2)";
        ReversePolishNotationCalcDemo rpn=new ReversePolishNotationCalcDemo();
        System.out.print( "��׺���ʽΪ��" );

        rpn.getList( expression ).forEach( System.out::print );

//		for (String s : rpn.getList(expression)) {
//			System.out.printf("%s  ", s);
//		}
        System.out.println();
        System.out.print( "��׺���ʽΪ��" );
        for (String s : rpn.toRPN( expression )) {
            System.out.printf( "%s  ", s );
        }
        System.out.println();
        double result=rpn.calcRPN( rpn.toRPN( expression ) );
        System.out.println( expression.replace( " ", "" ) + " = " + result );
    }

    // ��׺���ʽlist���м���
    public double calcRPN(List<String> rPN) {
        Stack<Double> numStack=new Stack<Double>();
        boolean flag=false;
        for (int i=0; i < rPN.size(); i++) {
            if (isOper( rPN.get( i ) )) {
                if (rPN.get( i ).equals( "-" )) {
                    for (int j=0; j < minus.size(); j++) {
                        if (i == minus.get( j )) {
                            String min=rPN.get( i );
                            Double num=numStack.pop();
                            numStack.push( Double.parseDouble( min + num ) );
                            flag=true;
							continue;
                        }
                    }
                    if (flag) {
                    	flag=false;
						continue;
					}
                }
                double num1=numStack.pop();
                double num2=numStack.pop();
                numStack.push( calc( num1, num2, rPN.get( i ).charAt( 0 ) ) );
			} else {
                numStack.push( Double.valueOf( rPN.get( i ) ) );
			}
        }
        return numStack.pop();
    }

    // ��׺���ʽת��׺���ʽ
    public List<String> toRPN(String expression) {
        List<String> list=getList( expression );
        List<String> rPN=new ArrayList<String>();// ���ڴ�ź�׺���ʽ
        Stack<String> exp=new Stack<String>();// ���ڲ����жϷ���
        minus=new ArrayList<>();
        for (String s : list) {// ����׺���ʽÿ��Ԫ��list����
            if (isOper( s )) {// ����ַ�����+-*/
                if (exp.isEmpty())// ���ջ�ǿգ���ô��ֱ����ջ
                    exp.push( s );//-7.5*6/(-2+(-6.5- -5.22))
                else if (priority( s ) <= priority( exp.peek() )) {// ���ջ���������������ô�ͺ�ջ��������Ƚ����ȼ�
                    rPN.add( exp.pop() );// ��ǰ��������ȼ�С��ջ����������Ͱ�ջ��������������뵽��׺���ʽrPN�б���
                    exp.push( s );// Ȼ��ѹ�뵱ǰ�����
                } else
                    exp.push( s );

            } else if (s.equals( ")" )) {// �����ǰ�ַ����ǡ�)����ô�ͽ�ջ���ַ�������add��List rPN�У�ֱ��ջ���ַ���Ϊ"("
                while (!exp.peek().equals( "(" )) {
                    rPN.add( exp.pop() );
                }
                exp.pop();// һ��Ҫ�ǵý�������(����ȥ������һ��С������ɼ���
            } else if (s.equals( "(" ))
                exp.push( s );// ����������ž�ֱ����ջ
            else {// ����������֣�ֱ�Ӽ���rPN��
                if (s.charAt( 0 ) == '-') {
                    rPN.add( s.substring( 1, s.length() ) );
                    rPN.add( "" + s.charAt( 0 ) );
                    minus.add( rPN.size() - 1 );
                } else
                    rPN.add( s );
            }
        }
        while (!exp.isEmpty()) {// ���ɨ����list�󣬽�ջexp�е��ַ������μ��뵽rPN��׺���ʽ�б���
            rPN.add( exp.pop() );
        }
        System.out.println( "���ų��ֵ�����:" + Arrays.toString( minus.toArray() ) );
        return rPN;
    }

    // ���ڽ���-�ŵĺ�׺���ʽ�ϲ�Ϊ���֣����ڼ���
    public List<String> toRPN2(String expression) {
        List<String> list=getList( expression );
        List<String> rPN=new ArrayList<String>();// ���ڴ�ź�׺���ʽ
        Stack<String> exp=new Stack<String>();// ���ڲ����жϷ���
        for (String s : list) {// ����׺���ʽÿ��Ԫ��list����
            if (isOper( s )) {// ����ַ�����+-*/
                if (exp.isEmpty())// ���ջ�ǿգ���ô��ֱ����ջ
                    exp.push( s );
                else if (priority( s ) <= priority( exp.peek() )) {// ���ջ���������������ô�ͺ�ջ��������Ƚ����ȼ�
                    rPN.add( exp.pop() );// ��ǰ��������ȼ�С��ջ����������Ͱ�ջ��������������뵽��׺���ʽrPN�б���
                    exp.push( s );// Ȼ��ѹ�뵱ǰ�����

                } else
                    exp.push( s );

            } else if (s.equals( ")" )) {// �����ǰ�ַ����ǡ�)����ô�ͽ�ջ���ַ�������add��List rPN�У�ֱ��ջ���ַ���Ϊ"("
                while (!exp.peek().equals( "(" )) {
                    rPN.add( exp.pop() );
                }
                exp.pop();// һ��Ҫ�ǵý�������(����ȥ������һ��С������ɼ���
            } else if (s.equals( "(" ))
                exp.push( s );// ����������ž�ֱ����ջ
            else {// ����������֣�ֱ�Ӽ���rPN��
                rPN.add( s );
            }
        }
        while (!exp.isEmpty()) {// ���ɨ����list�󣬽�ջexp�е��ַ������μ��뵽rPN��׺���ʽ�б���
            rPN.add( exp.pop() );
        }
        return rPN;
    }

    // ���ʽת��׺���ʽ(֧��С���㣬������С����)
    public List<String> getList(String expression) {
        List<String> exp=new ArrayList<String>();
        String merge="";
        int index=0;
        char ch, ch1=0;
        boolean flag=false;
        expression=expression.replace( " ", "" );
        while (index != expression.length()) {
            ch=expression.substring( index, index + 1 ).charAt( 0 );
            merge+=ch;
            if (index < expression.length() - 1) {// ��ֹ�±�Խ��
                ch1=expression.substring( index + 1, index + 2 ).charAt( 0 );
                if (isOper( ch1 )) {
                    exp.add( merge );
                    merge="";
                    flag=false;
                } else if (isOper( ch )) {
                    if (flag == false && exp.size() > 0) {//-7.5*6/(-2+(-6.5- -5.22))
                        exp.add( merge );
                        merge="";
                        flag=false;
                    }
                }
                if (ch == '(' && ch1 == '-' || isOper( ch ) && ch1 == '-')
                    flag=true;
            }
            index++;
        }
        exp.add( merge );
        return exp;
    }

    public boolean isOper(String oper) {
        return oper.equals( "+" ) || oper.equals( "-" ) || oper.equals( "*" ) || oper.equals( "/" );
    }

    public boolean isOper(char oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/' || oper == '(' || oper == ')';
    }

    public int priority(String s) {
        if (s.equals( "+" ) || s.equals( "-" ))
            return 0;
        if (s.equals( "*" ) || s.equals( "/" ))
            return 1;
        return -1;
    }

    public double calc(double num1, double num2, int oper) {
        switch (oper) {
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                return num2 / num1;
            default:
                break;
        }
        return 0;
    }
}
