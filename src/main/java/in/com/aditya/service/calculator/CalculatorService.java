package in.com.aditya.service.calculator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



/**
 * 
 * How does REST services work?
 * REST services work over HTTP protocol, so they are as simple as HTML requesting/submitting, ok?
 * HTTP works basically by having an address and paths under it, as you already know from HTML requests...
 * Every time you request some HTML, your request normally arrives to the server as text/plain request, and it answers normally as text/html.
 * No difference... REST is a protocol that works over HTTP protocol just like HTML is mark-up language that works over same HTTML protocol. (Great!)
 * But... REST can be much more sophisticated, because it allows you to receive/response many information through a well specified way.
 * So the REST APIs like jersey (we're using it) and rest-easy are just implementations of the JAX-RS (JEE) specification that makes programmer's life easier.
 * You could just open a socket and wait requests there and interpret the whole HTTP protocol, but since we already have these APIs, it's just a matter of learning the Annotations
 * and the dispatcher structure (web.xml).
 */

/** 
 * Example resource class hosted at the URI path "/make"
 * 
 * http://localhost:8080/calculator/myresorce (GET)
 * 
 */
@Path("/make")
public class CalculatorService {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    //@Path("/plus/{a}/{b}")
    @Path("/plus/{a}/{b}")
    @Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    // Since we're creating a plus service, we need at least two parameters, a and b, then y = a+b, and we response y.
    // Then now we have some options and within them a decision to make...
    // We can receive data through some different ways, one of them is through queryString (those ?param1=x&param2=y&param3=z and so on)... ?a=10 ...
    // query strings, param1 is a query string parameter, OK?
    // Through path parameters... Which are represented this way: /make/plus/{a}/{b}, so a should be replaced with the value from the path, same with b...
    // If the user hits /make/plus/10/20, then our service will receive a=10 and b=20.
    public Response plus(/*@PathParam(value="a")*/ @PathParam(value="a") int a, @PathParam(value="b") int b) {
    	// OK makes sure that my answer has a 200 HTTP response, together with my "Hello World.." string.
    	// take a read about design patterns: Builders.
        return Response.ok(Long.toString(Long.valueOf(a+b))).build();
    }
    
    @GET
    @Path("/divide/{a}/{b}")
    @Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public Response divide(@PathParam(value="a") int a, @PathParam(value="b") int b) {
        if(b == 0) {
//        	return Response.ok("Shit happened...").build();
//        	return Response.serverError().build();
        	return Response.status(Status.BAD_REQUEST).build();
        }
    	return Response.ok(Double.toString(Double.valueOf(a/(b+0.0)))).build();
    }
}