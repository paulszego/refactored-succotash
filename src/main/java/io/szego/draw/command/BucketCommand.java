package io.szego.draw.command;

import java.util.function.Function;

import io.szego.draw.Canvas;
import io.szego.draw.AbstractCommand;

/**
 * Command that fills the entire area connected to a pixel (x,y) with a "color" c. The behavior 
 * of this is the same as that of the "bucket fill" tool in paint programs.
 * 
 * <p>Example: B 10 3 o
 */
public class BucketCommand extends AbstractCommand
{
    /**
     * The usage string to display on parsing errors.
     */
    public static final String USAGE = "B[ucket] x y c";
    
    @Override
    public String getUsage()
    {
        return USAGE;
    }

    @Override
    public int getArgCount()
    {
        return 3;
    }
    
    @Override
    public Function<Canvas,Canvas> parse( final String... args )
    {
        final int       x       = parseInt( args, 1 );
        final int       y       = parseInt( args, 2 );
        final char      color   = args[3].charAt( 0 );  

        return BucketCommand.with( x, y, color ); 
    }    
    
    public static Function<Canvas,Canvas> with( final int x, final int y, final char color )
    { 
        return canvas -> fill( canvas, x, y, canvas.getPixel( x, y ), color );
    }
    
    /**
     * Bucket fill command - a four-way recursive (stack-based) implementation. 
     * Possibly the least efficient method known, but easy to understand.
     * 
     * @param   canvas  the canvas to draw onto.
     * @param   x       the starting x-coordinate of the fill.
     * @param   y       the starting y-coordinate of the fill.
     * @param   match   the color to match existing pixels with
     * @param   color   the color connected pixels will be changed to.
     * 
     * @return  the original canvas, <code>c</code>.
     * 
     * @throws  DrawException
     *              if the starting point (x,y) is not within the canvas.
     */
    private static Canvas fill( final Canvas    canvas, 
                                final int       x, 
                                final int       y, 
                                final char      match, 
                                final char      color )
    {
        //  If we're still on the canvas, and the pixel matches...
        if ( canvas.contains( x, y ) && canvas.getPixel( x, y ) == match )
        {
            canvas.setPixel( x, y, color );
            
            //  Recursively repeat for a pixel to the north, south, east and west.
            fill( canvas, x, y + 1, match, color );
            fill( canvas, x, y - 1, match, color );
            fill( canvas, x - 1, y, match, color );
            fill( canvas, x + 1, y, match, color );
        }        
        
        return canvas;
    }
}
