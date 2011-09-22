package br.eti.sauloarruda.jboss.mbean;

import java.util.HashMap;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import org.jboss.util.naming.NonSerializableFactory;
//import org.jboss.naming.NonSerializableFactory;

public class JNDIMap implements JNDIMapMBean
{
    private String jndiName;
    private HashMap<String, String> contextMap = new HashMap<String, String>();
    private boolean started;
    
    public String getJndiName()
    {
        return jndiName;
    }
    public void setJndiName(String jndiName) throws NamingException
    {
        String oldName = this.jndiName;
        this.jndiName = jndiName;
        if (started) {
            unbind(oldName);
            try {
                rebind();
            } catch(Exception e) {
                NamingException ne = new NamingException("Failedto update jndiName");
                ne.setRootCause(e);
                throw ne;
            }
        }
    }

    public void start() throws Exception
    {
        started = true;
        rebind();
    }
                
    public void stop()
    {
        started = false;
        unbind(jndiName);
    }
                
    public void add(String key, String value) {
        contextMap.put(key, value);
    }
    
    public String list() {
        StringBuilder values = new StringBuilder();
        for (String key : contextMap.keySet()) {
            values.append("[" + key + "=" + contextMap.get(key) + "]\n");
        }
        return values.toString();
    }
    
    private void rebind() throws NamingException
    {
        InitialContext rootCtx = new InitialContext();
        Name fullName = rootCtx.getNameParser("").parse(jndiName);
        System.out.println("fullName="+fullName);
        NonSerializableFactory.rebind(fullName, contextMap, true);
    }

    private void unbind(String jndiName)
    {
        try {
            InitialContext rootCtx = new InitialContext();
            rootCtx.unbind(jndiName);
            NonSerializableFactory.unbind(jndiName);
        } catch(NamingException e) {
            e.printStackTrace();
        }
    }
}