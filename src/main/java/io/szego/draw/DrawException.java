package io.szego.draw;

public class DrawException extends RuntimeException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DrawException()
    {
        super();
    }

    public DrawException( final String message )
    {
        super( message );
    }

    public DrawException( final Throwable cause )
    {
        super( cause );
    }

    public DrawException( final String message, final Throwable cause )
    {
        super( message, cause );
    }

}
