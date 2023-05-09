package es.uoc.tfm;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;

import es.uoc.tfm.beans.*;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.Random;


@Path("/nuclear")
public class NuclearSystem {

    @Inject
    JsonWebToken jwt;
    int power;

    @Timed( name = "operationTimer", 
            description = "Tiempo de duración de la operación ", 
            unit = MetricUnits.MILLISECONDS)                                                            //(1)
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/operate")
    @RolesAllowed({ "head_of_plant", "operator" })
    public String operate(@Context SecurityContext ctx) {

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Random rand = new Random();
        power = rand.nextInt(1000);

        return "Hello " + ctx.getUserPrincipal().getName() + "- Generated power: " + power;
    }

    @Gauge(unit = "MW", name = "megawatios", absolute = true)                                          //(2)                                 
    public int power() {
        return power;
    }

    @Counted(unit = MetricUnits.NONE, name = "accesoClasificado", absolute = true, 
            displayName = "Acceso clasificado", description = "Accesos a información clasificada",      //(3)
            tags = {"accessosClasificados=items" })
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/classified")
    @RolesAllowed({ "head_of_plant" })
    public String classified(@Context SecurityContext ctx) {

        return "Classified Info: " + ctx.getUserPrincipal().toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/info")
    public Info info() {
        return new Info("Vandellos I", 3);
    }
}