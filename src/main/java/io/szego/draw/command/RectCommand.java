package io.szego.draw.command;

import java.util.function.Function;

import io.szego.draw.Canvas;
import io.szego.draw.AbstractCommand;


/**
 * Draw a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). 
 * Horizontal and vertical lines will be drawn using the 'x' character.
 * 
 * <p>Example: L 1 2 6 2
 */
public class RectCommand extends AbstractCommand
{
    /**
     * The usage string to display on parsing errors.
     */
    public static final String USAGE = "R[ect] x1 y1 x2 y2";
    
    @Override
    public String getUsage()
    {
        return USAGE;
    }

    @Override
    public int getArgCount()
    {
        return 4;
    }
    
    @Override
    public Function<Canvas,Canvas> parse( final String... args )
    {
        final int     x1  = parseInt( args, 1 );
        final int     y1  = parseInt( args, 2 );
        final int     x2  = parseInt( args, 3 );
        final int     y2  = parseInt( args, 4 );

        return RectCommand.with( x1, y1, x2, y2 );
    }
    
    public static Function<Canvas,Canvas>   with( final int x1, 
                                                  final int y1, 
                                                  final int x2, 
                                                  final int y2 )
    {
        return        LineCommand.with( x1, y1, x2, y1 )
            .andThen( LineCommand.with( x1, y2, x2, y2 ) )
            .andThen( LineCommand.with( x1, y1, x1, y2 ) )
            .andThen( LineCommand.with( x2, y1, x2, y2 ) );                
    }
    
}
