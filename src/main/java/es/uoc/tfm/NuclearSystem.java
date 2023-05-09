package es.uoc.tfm;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;

import es.uoc.tfm.beans.*;


@Path("/nuclear")
public class NuclearSystem {

    @Inject
    JsonWebToken jwt;                                                        // (1)

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/operate")
    @RolesAllowed({"head_of_plant", "operator"})                            // (2)
    public String temperature(@Context SecurityContext ctx) {               // (3)
        return "Hello " + ctx.getUserPrincipal().getName();                 // (4)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info")
    public Info info(){
        return new Info("Vandellos I", 3);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/classified")
    @RolesAllowed({"head_of_plant"})                                        // (5)
    public String classified(@Context SecurityContext ctx) {   
       
        return "Classified Info: " + ctx.getUserPrincipal().toString();
    }
}