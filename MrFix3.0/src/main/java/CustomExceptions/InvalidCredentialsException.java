package CustomExceptions;

public class InvalidCredentialsException extends RuntimeException {
   public InvalidCredentialsException(String msg) {
	   super(msg);
   }
}
