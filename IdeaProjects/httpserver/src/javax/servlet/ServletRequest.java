package javax.servlet;

public interface ServletRequest {

    String getParameterValue(String key);
    String[] getParameterValues(String key);
}
