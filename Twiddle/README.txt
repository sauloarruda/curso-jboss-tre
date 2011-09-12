Usando o script twiddle.sh para acessar o servidor JMX

## Server Info
[bin]$ ./twiddle.sh -H serverinfo
Get information about the MBean server

usage: serverinfo [options]

options:
    -d, --domain    Get the default domain
    -c, --count     Get the MBean count
    -l, --list      List the MBeans
    --              Stop processing options

[bin]$ ./twiddle.sh --server=localhost serverinfo --count
508

[bin]$ ./twiddle.sh --server=localhost serverinfo --domain
jboss


## MBeans Query
[bin]$ ./twiddle.sh -H query
Query the server for a list of matching MBeans

usage: query [options] <query>
options:
    -c, --count    Display the matching MBean count
    --             Stop processing options
Examples:
 query all mbeans: query '*:*'
 query all mbeans in the jboss.j2ee domain: query 'jboss.j2ee:*'

[bin]$ ./twiddle.sh -s toki query 'jboss:service=invoker,*'
jboss:readonly=true,service=invoker,target=Naming,type=http
jboss:service=invoker,type=jrmp
jboss:service=invoker,type=local
jboss:service=invoker,type=pooled
jboss:service=invoker,type=http
jboss:service=invoker,target=Naming,type=http


## Get Attribute
[bin]$ ./twiddle.sh -H get
Get the values of one or more MBean attributes

usage: get [options] <name> [<attr>+]
  If no attribute names are given all readable attributes are retrieved
options:
    --noprefix    Do not display attribute name prefixes
    --            Stop processing options

[bin]$ ./twiddle.sh get jboss:service=invoker,type=jrmp RMIObjectPort StateString
RMIObjectPort=4444
StateString=Started

[bin]$ ./twiddle.sh get jboss:service=invoker,type=jrmp
ServerAddress=0.0.0.0
RMIClientSocketFactoryBean=null
StateString=Started
State=3
RMIServerSocketFactoryBean=org.jboss.net.sockets.DefaultSocketFactory@ad093076
EnableClassCaching=false
SecurityDomain=null
RMIServerSocketFactory=null
Backlog=200
RMIObjectPort=4444
Name=JRMPInvoker
RMIClientSocketFactory=null


## Bean Info
[bin]$ ./twiddle.sh -H info
Get the metadata for an MBean

usage: info <mbean-name>
  Use '*' to query for all attributes

[bin]$ ./twiddle.sh info jboss:service=invoker,type=jrmp
Description: Management Bean.
+++ Attributes:
 Name: Name
 Type: java.lang.String
 Access: r-
 Name: ServerAddress
 Type: java.lang.String
 Access: rw
 Name: RMIClientSocketFactory
 Type: java.lang.String
 Access: rw
 Name: StateString
 Type: java.lang.String
 Access: r-
 Name: Backlog
 Type: int
 Access: rw
 Name: State
 Type: int
 Access: r-
 Name: RMIServerSocketFactory
 Type: java.lang.String
 Access: rw
 Name: RMIServerSocketFactoryBean
 Type: java.rmi.server.RMIServerSocketFactory
 Access: rw
 Name: RMIObjectPort
 Type: int
 Access: rw
 Name: EnableClassCaching
 Type: boolean
 Access: rw
 Name: RMIClientSocketFactoryBean
 Type: java.rmi.server.RMIClientSocketFactory
 Access: rw
 Name: SecurityDomain
 Type: java.lang.String
 Access: rw
+++ Operations:
 void destroy()
 void stop()
 void create()
 void start()
 void jbossInternalLifecycle(java.lang.String p1)


## Invoke Operations
[bin]$ ./twiddle.sh -H invoke
Invoke an operation on an MBean

usage: invoke [options] <query> <operation> (<arg>)*

options:
    -q, --query-type[=<type>]    Treat object name as a query
    --                           Stop processing options

query type:
    f[irst]    Only invoke on the first matching name [default]
    a[ll]      Invoke on all matching names

[bin]$ ./twiddle.sh invoke jboss:service=JNDIView list true
<h1>java: Namespace</h1>
<pre>
  +- XAConnectionFactory (class: org.jboss.mq.SpyXAConnectionFactory)
  +- DefaultDS (class: javax.sql.DataSource)
  +- SecurityProxyFactory (class: org.jboss.security.SubjectSecurityProxyFactory)
  +- DefaultJMSProvider (class: org.jboss.jms.jndi.JNDIProviderAdapter)
  +- comp (class: javax.naming.Context)
  +- JmsXA (class: org.jboss.resource.adapter.jms.JmsConnectionFactoryImpl)
  +- ConnectionFactory (class: org.jboss.mq.SpyConnectionFactory)
  +- jaas (class: javax.naming.Context)
  |   +- JmsXARealm (class: org.jboss.security.plugins.SecurityDomainContext)
  |   +- jbossmq (class: org.jboss.security.plugins.SecurityDomainContext)
  |   +- HsqlDbRealm (class: org.jboss.security.plugins.SecurityDomainContext)
  +- timedCacheFactory (class: javax.naming.Context)
Failed to lookup: timedCacheFactory, errmsg=null
  +- TransactionPropagationContextExporter (class: org.jboss.tm.TransactionPropagationContext
Factory)
  +- StdJMSPool (class: org.jboss.jms.asf.StdServerSessionPoolFactory)
  +- Mail (class: javax.mail.Session)
  +- TransactionPropagationContextImporter (class: org.jboss.tm.TransactionPropagationContext
Importer)
  +- TransactionManager (class: org.jboss.tm.TxManager)
</pre>
<h1>Global JNDI Namespace</h1>
<pre>
  +- XAConnectionFactory (class: org.jboss.mq.SpyXAConnectionFactory)
  +- UIL2ConnectionFactory[link -> ConnectionFactory] (class: javax.naming.LinkRef)
  +- UserTransactionSessionFactory (proxy: $Proxy11 implements interface org.jboss.tm.usertx.
interfaces.UserTransactionSessionFactory)
  +- HTTPConnectionFactory (class: org.jboss.mq.SpyConnectionFactory)
  +- console (class: org.jnp.interfaces.NamingContext)
  |   +- PluginManager (proxy: $Proxy36 implements interface org.jboss.console.manager.Plugin
ManagerMBean)
  +- UIL2XAConnectionFactory[link -> XAConnectionFactory] (class: javax.naming.LinkRef)
  +- UUIDKeyGeneratorFactory (class: org.jboss.ejb.plugins.keygenerator.uuid.UUIDKeyGenerator
Factory)
  +- HTTPXAConnectionFactory (class: org.jboss.mq.SpyXAConnectionFactory)
  +- topic (class: org.jnp.interfaces.NamingContext)
  |   +- testDurableTopic (class: org.jboss.mq.SpyTopic)
  |   +- testTopic (class: org.jboss.mq.SpyTopic)
  |   +- securedTopic (class: org.jboss.mq.SpyTopic)
  +- queue (class: org.jnp.interfaces.NamingContext)
  |   +- A (class: org.jboss.mq.SpyQueue)
  |   +- testQueue (class: org.jboss.mq.SpyQueue)
  |   +- ex (class: org.jboss.mq.SpyQueue)
  |   +- DLQ (class: org.jboss.mq.SpyQueue)
  |   +- D (class: org.jboss.mq.SpyQueue)
  |   +- C (class: org.jboss.mq.SpyQueue)
  |   +- B (class: org.jboss.mq.SpyQueue)
  +- ConnectionFactory (class: org.jboss.mq.SpyConnectionFactory)
  +- UserTransaction (class: org.jboss.tm.usertx.client.ClientUserTransaction)
  +- jmx (class: org.jnp.interfaces.NamingContext)
  |   +- invoker (class: org.jnp.interfaces.NamingContext)
  |   |   +- RMIAdaptor (proxy: $Proxy35 implements interface org.jboss.jmx.adaptor.rmi.RMIAd
aptor,interface org.jboss.jmx.adaptor.rmi.RMIAdaptorExt)
  |   +- rmi (class: org.jnp.interfaces.NamingContext)
  |   |   +- RMIAdaptor[link -> jmx/invoker/RMIAdaptor] (class: javax.naming.LinkRef)
  +- HiLoKeyGeneratorFactory (class: org.jboss.ejb.plugins.keygenerator.hilo.HiLoKeyGenerator
Factory)
  +- UILXAConnectionFactory[link -> XAConnectionFactory] (class: javax.naming.LinkRef)
  +- UILConnectionFactory[link -> ConnectionFactory] (class: javax.naming.LinkRef)
</pre>