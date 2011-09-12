package br.eti.sauloarruda.jboss.jmx;

import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import org.jboss.jmx.adaptor.rmi.RMIAdaptor;

public class RMI {

    public static void main(String[] args) throws Exception {
        InitialContext ic = new InitialContext();
        RMIAdaptor server = (RMIAdaptor) ic.lookup("jmx/invoker/RMIAdaptor");
        
        // Get the MBeanInfo for the JNDIView MBean
        ObjectName name = new ObjectName("jboss:service=JNDIView");
        MBeanInfo  info = server.getMBeanInfo(name);
        System.out.println("JNDIView Class: " + info.getClassName());

        MBeanOperationInfo[] opInfo = info.getOperations();
        System.out.println("JNDIView Operations: ");
        for(int o = 0; o < opInfo.length; o ++) {
            MBeanOperationInfo op = opInfo[o];

            String returnType = op.getReturnType();
            String opName     = op.getName();
            System.out.print(" + " + returnType + " " + opName + "(");

            MBeanParameterInfo[] params = op.getSignature();
            for(int p = 0; p < params.length; p++)  {
                MBeanParameterInfo paramInfo = params[p];

                String pname = paramInfo.getName();
                String type  = paramInfo.getType();

                if (pname.equals(type)) {
                    System.out.print(type);
                } else {
                    System.out.print(type + " " + name);
                }

                if (p < params.length-1) {
                    System.out.print(','); 
                }
            }
            System.out.println(")");
        }
        
        // Invoke the list(boolean) op
        String[] sig    = {"boolean"};
        Object[] opArgs = {Boolean.TRUE};
        Object   result = server.invoke(name, "list", opArgs, sig);

        System.out.println("JNDIView.list(true) output:\n"+result);
    }
}
