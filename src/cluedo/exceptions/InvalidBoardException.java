package cluedo.exceptions;

@SuppressWarnings("serial")
public class InvalidBoardException extends Exception {
	public InvalidBoardException(){
        super();
    }

    public InvalidBoardException(String message){
        super(message);
    }
}
