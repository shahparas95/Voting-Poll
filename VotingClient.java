/*
 * This is simple client program with default port and hostname which connects to VotingServer
 * You may vote by simply entering 1,2 or 3 and server will respond you once your input is 
 * accepted. You may also ask server for voting count.1
 * 
 */




import java.io.*;

public class VotingClient
{
   static final String endMessage = ".";    
   public static void main(String[] args) 
   {
   InputStreamReader is = new InputStreamReader(System.in);
   BufferedReader br = new BufferedReader(is);
   try {
         String hostName = "localhost";  //default     
         String portNum = "7";           //default
         EchoClientHelper1 helper = new EchoClientHelper1(hostName, portNum);
         boolean done = false;
         String message, vote;
         while (!done) // forever loop
         {
            System.out.println(" Vote (1) Yes, (2) No, or (3) don't care. " 
            + " \n If you like to know count Enter How many yes? , How many no or How many dont care?\n or a single peroid to quit. ");
            message = br.readLine( );
            if ((message.trim()).equals (endMessage)) //to end loop
            {
                 done = true;
                 helper.done( );
            }
            else {
                 vote = helper.getEcho( message);
                 System.out.println(vote);
                 } 
          } // end while
      } // end try  
      catch (Exception ex) 
      {
         ex.printStackTrace( );
      } // end catch
   } //end main
} // end class      
