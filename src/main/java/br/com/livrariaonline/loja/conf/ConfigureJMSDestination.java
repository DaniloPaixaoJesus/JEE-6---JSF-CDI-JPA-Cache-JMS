package br.com.livrariaonline.loja.conf;

import javax.ejb.Singleton;
import javax.jms.JMSDestinationDefinition;

/**
 * class responseble for define a topic java:/jms/topics/CarrinhoComprasTopico
 * apache activemq is inside jee application server tomcat is the http server
 * used by application server
 * 
 * @author danilo
 *
 */
@JMSDestinationDefinition(name = "java:/jms/topics/CarrinhoComprasTopico", interfaceName = "javax.jms.Topic")
@Singleton
public class ConfigureJMSDestination {

}
