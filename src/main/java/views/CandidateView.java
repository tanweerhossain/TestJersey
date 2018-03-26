package views;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import utils.CandidateData;

@Path("/api")
public class CandidateView {
	private ArrayList<CandidateData> candidates;
	
	private void insertManyCandidates() {
		String name[] = { "Bhuvan", "Tanweer", "Heena" };
		String phone[] = { "9876543210", "1234567890", "7654321098" };
		String id[] = { "1", "2", "3" };
		
		for( int i = 0 ; i < name.length ; ++i )
			this.candidates.add( new CandidateData(name[i], phone[i], id[i]) );
	
	}
	
	public CandidateView() {
		try {
		    this.candidates = new ArrayList<CandidateData>();
		    this.insertManyCandidates();
    	} catch (Exception e) {
    		 e.printStackTrace();
    	}
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response post(String body) {
		Gson gson = new Gson();
		CandidateData object = (CandidateData) gson.fromJson(body, CandidateData.class);
		
		
		try {
			Boolean findingStatus = true;

			for(CandidateData data : this.candidates)
				if( data.getName().equalsIgnoreCase(object.getName().trim()) ) {
					findingStatus = false;
					return Response
							.status(200)
							.entity(gson.toJson(data))
							.build();
				}
			
			if( findingStatus )
				throw new Exception("data is not present in the DB.");
			
			return Response
					.status(200)
					.entity(gson.toJson(object))
					.build();			
		} catch (Exception e) {
			return Response
					.status(404)
					.entity(gson.toJson(new Status("404 " + e.getMessage())))
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String error404() {  
	  return "<h1>404 ERROR</h1>";  
	}  
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response get(@PathParam("id") final String id) {
		try {
			int candidateId = Integer.parseInt(id);
			
			if( (candidateId > 0) && (candidateId <= this.candidates.size()) ) {
				for(CandidateData data : this.candidates)
					if( Integer.parseInt(data.getId()) == candidateId ) {
						return Response
								.status(200)
								.entity("<h1>"+ data.getName() +" Details</h1><><><><><><><><><><><><><><><><br />Phone : " + data.getPhone())
								.build();
					}
			} else 
				return Response
						.status(404)
						.entity("<h1>ID DOESN'T MATCH<h2>")
						.build();
		} catch (Exception e) {
			return Response
					.status(404)
					.entity("<h1>ID DOESN'T MATCH<h2>")
					.build();
		}
		return Response
				.status(404)
				.entity("<h1>ID DOESN'T MATCH<h2>")
				.build();
	}  

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String delete(@PathParam("id") String id) {
		Gson gson = new Gson();
		
		return gson.toJson(new Status("Do you want to delete id " + id + " ?"));
	}
	
}
