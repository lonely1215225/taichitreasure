package edu.hunau.stack;

public class CalculatorDemo {
	public static void main(String[] args) {
		calculator("70-20  *  5 -6");
	}

//栈的应用：中缀表达式计算(不支持小括号和小数运算)
	static int calculator(String expression) {
		ArrayStack numStack = new ArrayStack(10);// 数栈
		ArrayStack operStack = new ArrayStack(10);// 运算符栈
		int num1 = 0;// 第一次pop
		int num2 = 0;// 第二次pop
		int oper = 0;// 操作符
		char ch = ' ';// 操作符号
		int index = 0;// 索引，用于扫描表达式
		String keepNum = "";// 用于拼接多位数
		expression=expression.replace(" ", "");
		while (true) {
			ch = expression.substring(index, index + 1).charAt(0);// 将表达式切分成单个字符
			if (operStack.isOper(ch)) {// 是操作符号
				if (!operStack.isEmpty()) {// 操作符栈不为空
					if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {// 将扫描到的ch操作符与操作符栈栈顶符号进行优先级比较
						num1 = numStack.pop();// 如果当前扫描到的运算符优先级小于栈顶运算符，就pop两个数进行运算
						num2 = numStack.pop();
						oper = operStack.pop();
						numStack.push(numStack.calc(num1, num2, oper));// 计算后的结果压入数栈
						operStack.push(ch);
					} else {
						operStack.push(ch);// 如果当前扫描到的ch运算符优先级大于栈顶运算符，就直接将ch压入操作符栈
					}
				} else {
					operStack.push(ch);// 操作符栈为空，就直接将符号入栈
				}
			} else {
				keepNum += ch;// 如果不是操作符，就将数字拼接
				if (index < expression.length() - 1) {// 防止index扫描表达式越界
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {// 判断当前index扫描的下一个是否为数字,如果是数字就直接index++，继而完成拼接继续判断下一个字符ch
						numStack.push(Integer.parseInt(keepNum));// 如果下一个字符是操作符，那么就将拼接后的数字入数栈
						keepNum = "";// 此次拼接完成，清空
					}
				} else {
					numStack.push(ch - 48);// 最后一个字符如果是数字，那么就直接入数栈
				}
			}
			index++;// 扫描下一个字符
			if (index >= expression.length())// 扫描索引==字符串长度时，就没有字符了，所以退出循环
				break;
		}
		while (true) {
			if (operStack.isEmpty())
				break;
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			numStack.push(numStack.calc(num1, num2, oper));
		}
		System.out.printf("%s = %d", expression, numStack.peek());
		return numStack.pop();
	}
}