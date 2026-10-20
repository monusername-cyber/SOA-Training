
package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/list")
public class UniteEnsRestApi {

    private UniteEnseignementBusiness helper =
            new UniteEnseignementBusiness();

    // READ : recuperer la liste des UE
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListeUE() {
        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }

    // CREATE : ajouter une UE
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUe(UniteEnseignement ue) {

        if (helper.addUniteEnseignement(ue)) {
            return Response.status(201)
                    .entity("Success")
                    .build();
        } else {
            return Response.status(400)
                    .entity("Erreur")
                    .build();
        }
    }

    // UPDATE : modifier une UE par son code
    @Path("/update/{code}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUe(
            @PathParam("code") int code,
            UniteEnseignement ue) {

        if (helper.updateUniteEnseignement(code, ue)) {
            return Response.status(200)
                    .entity("UE modifiee avec succes")
                    .build();
        } else {
            return Response.status(404)
                    .entity("UE introuvable")
                    .build();
        }
    }

    // DELETE : supprimer une UE par son code
    @Path("/delete/{code}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUe(
            @PathParam("code") int code) {

        if (helper.deleteUniteEnseignement(code)) {
            return Response.status(200)
                    .entity("UE supprimee avec succes")
                    .build();
        } else {
            return Response.status(404)
                    .entity("UE introuvable")
                    .build();
        }
    }
}
