package edu.hunau.httpserver.core;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestObject implements ServletRequest {
    public Map<String, String[]> parameterMap=new HashMap<>();

    public RequestObject(String requestURI) {
        /**
         * /zzy/user?
         * /zzy/user?username=
         * /zzy/user?username=zhangsan
         * /zzy/user?username=zhangsan&gender=1
         * /zzy/user?username=zhangsan&gender=1&interest=food&interest=bambooFlute
         */
        if (requestURI.contains( "?" )) {
            String[] uriAndData=requestURI.split( "[?]" );
            if (uriAndData.length > 1) {
                String datas=uriAndData[1];
                if (datas.contains( "&" )) {
                    String[] namesAndValues=datas.split( "&" );
                    for (String nameAndValue : namesAndValues) {
                        String[] nameValue=nameAndValue.split( "=" );
                        if (parameterMap.containsKey( nameValue[0] )) {
                            String[] values=parameterMap.get( nameValue[0] );
                            String[] newValues=new String[values.length + 1];
                            //参数说明：1、原数组 2、从原数组的第i个位置开始复制 3、新数组 4、复制到新数组的第几个位置开始 5、复制长度
                            System.arraycopy( values, 0, newValues, 0, values.length );
                            if (nameValue.length > 1) {
                                newValues[newValues.length - 1]=nameValue[1];
                            } else {
                                newValues[newValues.length - 1]="";
                            }
                            parameterMap.put( nameValue[0], newValues );
                        } else {
                            if (nameValue.length > 1) {
                                parameterMap.put( nameValue[0], new String[]{nameValue[1]} );
                            } else {
                                parameterMap.put( nameValue[0], new String[]{""} );
                            }
                        }
                    }
                } else {
                    String[] nameAndValue=datas.split( "=" );
                    if (nameAndValue.length > 1) {
                        parameterMap.put( nameAndValue[0], new String[]{nameAndValue[1]} );
                    } else {
                        parameterMap.put( nameAndValue[0], new String[]{""} );
                    }
                }
            }
        }
    }

    /**
     * @param key
     * @return
     */
    public String getParameterValue(String key) {
        String[] values=parameterMap.get( key );
        return (values != null && values.length != 0) ? values[0] : null;
    }

    public String[] getParameterValues(String key) {
        return parameterMap.get( key );
    }
}
