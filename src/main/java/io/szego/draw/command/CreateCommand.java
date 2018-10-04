package io.szego.draw.command;

import java.util.function.Function;

import io.szego.draw.Canvas;
import io.szego.draw.AbstractCommand;

/**
 * Command to create a new canvas of width w and height h.
 * 
 * <p>Example: C 20 4
 */
public class CreateCommand extends AbstractCommand
{
    /**
     * The usage string to display on parsing errors.
     */
    public static final String USAGE = "C[reate] w h";
    
    @Override
    public String getUsage()
    {
        return USAGE;
    }

    @Override
    public int getArgCount()
    {
        return 2;
    }

    /**
     * Helper that creates a function to execute a Create command. 
     *  
     * @param   width   the width of the canvas to create.
     * @param   height   the height of the canvas to create.
     * 
     * @return  a new {@link Function} to execute the Create command.
     */
    public static Function<Canvas,Canvas>   with( final int width, final int height )
    {
        return canvas -> new Canvas( width, height );        
    }
    
    @Override
    public Function<Canvas,Canvas>          parse( final String... args )
    {
        final int     width     = parseInt( args, 1 );
        final int     height    = parseInt( args, 2 );

        return with( width, height ); 
    }
}
