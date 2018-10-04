package io.szego.draw;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A description of a command that can be applied to a {@link Canvas}. Has the ability to parse
 * a command line, and produce an executable instance in the form of a {@link Function}.
 */
public abstract class AbstractCommand
{
    public static final Function<Canvas,Canvas> IDENTITY = UnaryOperator.<Canvas>identity();
    
    /**
     * Gets the name of this command. Defaults to the unqualified class name.
     * 
     * @return  the name of this command.
     */
    public String getName()
    {
        String  name = this.getClass().getSimpleName();
        
        //  Remove any "Command" suffix
        if ( name.endsWith( "Command" ) )
        {
            name = name.substring( 0, name.length() - 7 );
        }
        
        return name;
    }
    
    /**
     * Get the usage string to display on error.
     * 
     * @return  the usage string to display on error.
     */
    public abstract String  getUsage();
    
    /**
     * Get the expected number of arguments on a command line.
     * 
     * @return  the expected number of arguments on a command line.
     */
    public abstract int     getArgCount();
    
    /**
     * Checks for a name match and argument count before delegating to the <code>parse</code> 
     * method.
     * 
     * @param   args    command line arguments.
     * 
     * @return  <code>null</code> if this command doesn't match; else a {@link Function} to
     *          execute the command.
     *          
     * @throws  IllegalArgumentException
     *              if the number of arguments is wrong or cannot be parsed properly.
     */
    public Function<Canvas,Canvas> apply( final String... args )
    {
        final String name = args[0];        
        if ( !this.getClass().getSimpleName().startsWith( name ) )
        {
            return null;
        }
        
        if ( args.length != getArgCount() + 1 )
        {
            //  Wrong number of arguments.
            throw new IllegalArgumentException(
                "Wrong number of arguments, usage: " + getUsage() 
            );            
        }
        
        return parse( args );
    }
    
    /**
     * Attempt to parse the command line passed on.
     * 
     * @param   args    the command line arguments to parse.
     * 
     * @return  a {@link Function} instance if parsing was successful.
     */
    protected abstract Function<Canvas,Canvas> parse( final String... args );
    
    
    /**
     * Convenience function to parse an integer command line argument.
     * 
     * @param   args    the command line, tokenised into strings.
     * @param   index   the index into <code>args</code> of the value to parse.
     * 
     * @return  the integer value of the argument.
     * 
     * @throws  IllegalArgumentException
     *              if the argument cannot be parsed as an integer.
     */
    protected static int parseInt( final String[] args, final int index )
    {
        try
        {
            return Integer.parseInt( args[ index ] );            
        }
        catch ( NumberFormatException e )
        {
            throw new IllegalArgumentException( 
                "Expected integer at argument #" + index + " but got '" + args[ index ] + "'",
                e
            );
        }
    }
    
}
