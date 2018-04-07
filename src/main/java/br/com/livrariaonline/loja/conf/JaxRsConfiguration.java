package br.com.livrariaonline.loja.conf;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * class set up URI REST service
 * all service begin with /services
 * RESTful Root Resource Class
 * define the URL mapping for the application.
 * @author danilo
 *
 */
@ApplicationPath("/services")
public class JaxRsConfiguration extends Application {

}
