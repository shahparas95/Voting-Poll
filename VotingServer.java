/*
 * 
 * This is simplest form of Voting server which accepts votes from different client
 * which supports concurrency. only one client is served at once so there is no need for
 * mutual exclusion. This server will accepts input for votes and returns count values if 
 * asked. Since this was 30 minutes voting poll we will have to close socket manully. 
 */

import java.io.*;

public class VotingServer 
{
   public static void main(String[] args) 
   {
      //global variables  with default value
      int yesCount=0;
      int noCount=0;
      int dontCareCount=0;
      int serverPort = 7;    // default port
      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);       
      try 
      {

       MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort); 
         System.out.println("Echo server ready.");  
         while (true) // forever loop
         {  
             DatagramMessage request = 
             mySocket.receiveMessageAndSender();
             System.out.println("I want to vote");
             String message = request.getMessage( ).trim();
             System.out.println("Vote received: "+ message);
            
             // convert int to string
             String yesCount1 = Integer.toString(yesCount);
             String noCount1 = Integer.toString(noCount);
             String dontCareCount1 = Integer.toString(dontCareCount);
             String yesMessage = "You have voted Yes";
             String noMessage = "You have voted No";
             String dontCareMessage = "You don't care";
             // counters to keep track of votes.
            if ((message.trim()).equals ("1"))
            {
              yesCount = yesCount + 1;             
              mySocket.sendMessage(request.getAddress( ),
              request.getPort( ), yesMessage);
              
            }
             else if ((message.trim()).equals ("2"))
             {            
               noCount = noCount + 1;
               mySocket.sendMessage(request.getAddress( ),
               request.getPort( ), noMessage);
             }
             else if ((message.trim()).equals ("3"))
             {
               dontCareCount = dontCareCount + 1;
               mySocket.sendMessage(request.getAddress( ),
               request.getPort( ), dontCareMessage);
             }
             // returns yes count values 
             else if ((message.trim()).equals ("How many yes?"))
             {
              
               mySocket.sendMessage(request.getAddress( ),
               request.getPort( ), yesCount1);
             
             }
              // returns no count values
             else if ((message.trim()).equals ("How many no?"))
             {
             
               mySocket.sendMessage(request.getAddress( ),
               request.getPort( ), noCount1);
              
              }
              // returns dont care count values
             else if ((message.trim()).equals ("How many don't care?"))
             {
              
               mySocket.sendMessage(request.getAddress( ),
               request.getPort( ), dontCareCount1);
              
             }
             else
             {
                 String invalid = "Invalid input";
                 mySocket.sendMessage(request.getAddress( ),
                 request.getPort( ), invalid);
             }
     } //end while
    } // end try
     catch (Exception ex) 
     {
          ex.printStackTrace( );
     } // end catch
   } //end main
} // end class      
