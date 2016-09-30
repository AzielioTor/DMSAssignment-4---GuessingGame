/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmsass4client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import org.eclipse.persistence.sdo.helper.SDOHelperContext;

/**
 *
 * @author Aziel Shaw - 14847095
 */
public class DmsAss4Client extends JFrame {
    
    private static HomePage_JerseyClient client = new HomePage_JerseyClient();
    private String title = "Guessing Game";
    //GUI
    private JPanel panel = new JPanel();
    private JButton guessButton = new JButton("Guess");
    private JButton startAgain = new JButton("Start Over");
    private JLabel countField = new JLabel("Number of Guesses: ");
    private JTextArea informationTA = new JTextArea("Guess a number!");
    private JTextArea inputTA = new JTextArea("0");
    //game data
    private int count;
    private int randNum;
    private int guess;
    private String response;
    
    public DmsAss4Client() {
        super("Guessing Game");
        initGUI();
        initListeners();
        count = -1;
        randNum = -1;
        guess = -1;
        response = "";
    }
    
    private void initGUI() {
        this.setSize(320, 150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(title);
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        
        this.add(panel);
        panel.setLayout(null);
        panel.setSize(800, 600);
        panel.setBackground(Color.LIGHT_GRAY);
        
        guessButton.setLocation(60, 9);
        guessButton.setSize(70, 22);
        guessButton.setVisible(true);
        panel.add(guessButton);
        
        startAgain.setLocation(3, 41);
        startAgain.setSize(100, 22);
        startAgain.setVisible(true);
        panel.add(startAgain);
        
        countField.setLocation(3, 60);
        countField.setSize(200, 50);
        countField.setVisible(true);
        panel.add(countField);
        
        panel.add(informationTA);
        informationTA.setSize(150, 100);
        informationTA.setLocation(150, 10);
        informationTA.setEditable(false);
        
        panel.add(inputTA);
        inputTA.setSize(50, 20);
        inputTA.setLocation(5, 10);
        
        
        
//        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private void initListeners() {
        guessButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                client.submitData(inputTA.getText());
                getData();
                countField.setText("Number of Guesses: " + count);
                informationTA.setText(response);
            }
        });
        
        startAgain.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                client.putHtml("restart");
                getData();
                countField.setText("Number of Guesses: " + count);
                informationTA.setText(response);
            }
        });
    }
    
    public void getData() {
        String data = client.getData();
        String[] dataArray = data.split(";");
        count = Integer.parseInt(dataArray[0]);
        randNum = Integer.parseInt(dataArray[1]);
        guess = Integer.parseInt(dataArray[2]);
        response = dataArray[3];
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DmsAss4Client gui = new DmsAss4Client();
    }

    static class HomePage_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/DmsAssignment4/webresources";

        public HomePage_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("home");
        }

        public String getHtml() throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
        }

        public String getData() throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
        }
        
//        public int getCount() throws ClientErrorException {
//            WebTarget resource = webTarget;
//            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(int.class);
//        }
//        
//        public int getRandomNumber() throws ClientErrorException {
//            WebTarget resource = webTarget;
//            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(int.class);
//        }
//        
//        public int getGuess() throws ClientErrorException {
//            WebTarget resource = webTarget;
//            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(int.class);
//        }
//        
//        public String getResponse() throws ClientErrorException {
//            WebTarget resource = webTarget;
//            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
//        }

        public void putHtml(String requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.TEXT_HTML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_HTML));
            //System.out.println(requestEntity);
        }
        
        public void submitData(String requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
            //System.out.println(requestEntity);
        }

        public void close() {
            client.close();
        }
    }
    
}
