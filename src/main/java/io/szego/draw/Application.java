package io.szego.draw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;


public final class Application
{
    private Application()
    {
        super();
    }
    
    /**
     * Command-line driver, processes stdin until EOF or failure.
     * 
     * @param   args    command line arguments.
     * 
     * @throws  IOException
     *              if any errors reading stdin. 
     */
    public static void main( final String... args ) throws IOException
    {
        final Registry          reg    = new Registry();
        final Reader            stdin  = new InputStreamReader( System.in, StandardCharsets.UTF_8 );
        final BufferedReader    buf    = new BufferedReader( stdin ); 
        
        String                  cmd;
        Canvas                  canvas  = null;
    
        System.out.println( "Ready..." );
        while ( ( cmd = buf.readLine() ) != null )
        {
            final Function<Canvas,Canvas> function = reg.parse(  cmd );
            try
            {
                canvas = function.apply( canvas );
                System.out.println( canvas.toString() );
            }
            catch ( DrawException e )
            {
                System.err.println( e.getLocalizedMessage() );                
                return;
            }            
        }
        System.out.println( "Done." );
    }
    
}
