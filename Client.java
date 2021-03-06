import java.io.*;

public class Client
{
   static final String endMessage = ".";    
   public static void main(String[] args) 
   {
   InputStreamReader is = new InputStreamReader(System.in);
   BufferedReader br = new BufferedReader(is);
   try {
         String hostName = "localhost";       
         String portNum = "7";
         EchoClientHelper01 helper = new EchoClientHelper01(hostName, portNum);
         boolean done = false;
         String message, vote;
         while (!done) 
         {
            System.out.println(" Vote (1) Yes, (2) No, or (3) don't care. " 
            + " \n or a single peroid to quit. ");
            message = br.readLine( );
            if ((message.trim()).equals (endMessage))
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
