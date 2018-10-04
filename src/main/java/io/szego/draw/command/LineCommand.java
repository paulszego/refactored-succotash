package io.szego.draw.command;

import java.util.function.Function;

import io.szego.draw.Canvas;
import io.szego.draw.AbstractCommand;

/**
 * Draw a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are 
 * supported. Horizontal and vertical lines will be drawn using the 'x' character.
 * 
 * <p>Example: L 1 2 6 2
 */
public class LineCommand extends AbstractCommand
{    
    public static final char CHAR_LINE = 'x';
    
    /**
     * The usage string to display on parsing errors.
     */
    public static final String USAGE = "L[ine] x1 y1 x2 y2";
    
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

    
    public static Function<Canvas,Canvas>   with( final int x1, final int y1, final int x2, final int y2 )
    {
        if ( y1 != y2 && x1 != x2 )
        {
            throw new IllegalArgumentException( "Line is not horizontal or vertival" );
        }
        return canvas -> LineCommand.draw( canvas, x1, y1, x2, y2 );        
    }
    
    private static Canvas draw( final Canvas    canvas, 
                                final int       x1, 
                                final int       y1, 
                                final int       x2, 
                                final int       y2 )
    {
        if ( y1 == y2 )
        {
            //  Horizontal line
            for ( int x = x1; x <= x2; x++ )
            {
                canvas.setPixel( x, y1, CHAR_LINE );
            }
        }
        else  //  ( x1 == x2 )
        {
            //  Vertical line
            for ( int y = y1; y <= y2; y++ )
            {
                canvas.setPixel( x1, y, CHAR_LINE );
            }
        }

        return canvas;
    }

    @Override
    public Function<Canvas,Canvas>          parse( final String... args )
    {
        final int     x1  = parseInt( args, 1 );
        final int     y1  = parseInt( args, 2 );
        final int     x2  = parseInt( args, 3 );
        final int     y2  = parseInt( args, 4 );

        return LineCommand.with( x1, y1, x2, y2 ); 
    }    
}
