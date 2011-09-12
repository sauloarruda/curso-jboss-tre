package br.eti.sauloarruda.jboss.mbean;

import javax.naming.NamingException;

public interface JNDIMapMBean {
    public String getJndiName();
    public void setJndiName(String jndiName) throws NamingException;
    public void start() throws Exception;
    public void stop() throws Exception;    
    public void add(String key, String value);
    public String list();
}
