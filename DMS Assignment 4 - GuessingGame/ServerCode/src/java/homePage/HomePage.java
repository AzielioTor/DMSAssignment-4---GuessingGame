/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homePage;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Aziel Shaw - 14847095
 */
@Path("home")
public class HomePage {
    
    private static GuessingGame game = new GuessingGame();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of homePage
     */
    public HomePage() {
    }

    /**
     * Retrieves representation of an instance of homePage.homePage
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        return  game.toString();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getData() {
        return game.getCount() + ";" + game.getRandomNumber()+ ";" + game.getGuess()+ ";" + game.getResponse();
    }
    
    
    
    
    /**
     * PUT method for updating or creating an instance of homePage
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String info) {
        System.out.println("restarted game");
        game.startGame();
        System.out.println("Actual answer: " + game.getRandomNumber());
    }
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public void submitData(String context) {
        String dataString = new String(context.getBytes());
        int dataInt = Integer.parseInt(dataString);
        
        game.guess(dataInt);
        
        System.out.println("Guess: " + dataString);
        System.out.println("Actual answer: " + game.getRandomNumber());
    }
    
    

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public int getCount() {
//        return  game.getCount();
//    }
//
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public int getRandomNumber() {
//        return  game.getRandomNumber();
//    }
//    
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public int getGuess() {
//        return  game.getGuess();
//    }
//    
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getResponse() {
//        return game.getResponse();
//    }

}
