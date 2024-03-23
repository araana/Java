
public class InvalidPasswordException extends RuntimeException
{
    /**
     * Sets up this exception with an appropriate message.
     * @param collection the name of the collection
     */
    public InvalidPasswordException()
    {
        super("The entered password is invalid");
    }

}
