package edu.hunau.mybatis.cfg;

/**
 * ���ڷ�װִ�е�Sql���ͽ�����͵�ȫ�޶�����
 */
public class Mapper {
    private String queryString;
    private String resultType;

    @Override
    public String toString( ) {
        return "Mapper{" +
                "queryString='" + queryString + '\'' +
                ", resultType='" + resultType + '\'' +
                '}';
    }

    public String getQueryString( ) {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString=queryString;
    }

    public String getResultType( ) {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType=resultType;
    }
}
