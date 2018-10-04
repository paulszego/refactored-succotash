package io.szego.draw.command;

import java.util.function.Function;

import io.szego.draw.Canvas;
import io.szego.draw.AbstractCommand;
import io.szego.draw.DrawException;

/**
 * Command to quit the program.
 * 
 * <p>Example: Q
 */
public class QuitCommand extends AbstractCommand
{
    /**
     * The usage string to display on parsing errors.
     */
    public static final String USAGE = "Q[uit]";
    
    @Override
    public String getUsage()
    {
        return USAGE;
    }

    @Override
    public int getArgCount()
    {
        return 0;
    }
    
    
    @Override
    public Function<Canvas,Canvas> parse( final String... args )
    {        
        return canvas ->
        {
            throw new DrawException( "Exit!" );
        };
    }
}
