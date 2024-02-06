package CustomExceptions;

public class DataAlreadyExists extends RuntimeException{
   public DataAlreadyExists(String message){
   super(message);
   }
}
