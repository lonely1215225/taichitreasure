package edu.hunau.stack;

public class CalculatorDemo {
	public static void main(String[] args) {
		calculator("70-20  *  5 -6");
	}

//ջ��Ӧ�ã���׺���ʽ����(��֧��С���ź�С������)
	static int calculator(String expression) {
		ArrayStack numStack = new ArrayStack(10);// ��ջ
		ArrayStack operStack = new ArrayStack(10);// �����ջ
		int num1 = 0;// ��һ��pop
		int num2 = 0;// �ڶ���pop
		int oper = 0;// ������
		char ch = ' ';// ��������
		int index = 0;// ����������ɨ����ʽ
		String keepNum = "";// ����ƴ�Ӷ�λ��
		expression=expression.replace(" ", "");
		while (true) {
			ch = expression.substring(index, index + 1).charAt(0);// �����ʽ�зֳɵ����ַ�
			if (operStack.isOper(ch)) {// �ǲ�������
				if (!operStack.isEmpty()) {// ������ջ��Ϊ��
					if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {// ��ɨ�赽��ch�������������ջջ�����Ž������ȼ��Ƚ�
						num1 = numStack.pop();// �����ǰɨ�赽����������ȼ�С��ջ�����������pop��������������
						num2 = numStack.pop();
						oper = operStack.pop();
						numStack.push(numStack.calc(num1, num2, oper));// �����Ľ��ѹ����ջ
						operStack.push(ch);
					} else {
						operStack.push(ch);// �����ǰɨ�赽��ch��������ȼ�����ջ�����������ֱ�ӽ�chѹ�������ջ
					}
				} else {
					operStack.push(ch);// ������ջΪ�գ���ֱ�ӽ�������ջ
				}
			} else {
				keepNum += ch;// ������ǲ��������ͽ�����ƴ��
				if (index < expression.length() - 1) {// ��ֹindexɨ����ʽԽ��
					if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {// �жϵ�ǰindexɨ�����һ���Ƿ�Ϊ����,��������־�ֱ��index++���̶����ƴ�Ӽ����ж���һ���ַ�ch
						numStack.push(Integer.parseInt(keepNum));// �����һ���ַ��ǲ���������ô�ͽ�ƴ�Ӻ����������ջ
						keepNum = "";// �˴�ƴ����ɣ����
					}
				} else {
					numStack.push(ch - 48);// ���һ���ַ���������֣���ô��ֱ������ջ
				}
			}
			index++;// ɨ����һ���ַ�
			if (index >= expression.length())// ɨ������==�ַ�������ʱ����û���ַ��ˣ������˳�ѭ��
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