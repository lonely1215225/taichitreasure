package edu.hunau.mybatis.sqlsession;

/**
 * �Զ���mybatis�к����ݿ⽻���õ�������
 * ���Դ���dao�ӿڵĴ������
 */
public interface SqlSession {
    /**
     * ���ݲ�������һ���������
     * @param daoInterfaceClass dao�Ľӿ��ֽ���
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * �ͷ���Դ
     */
    void close();
}
