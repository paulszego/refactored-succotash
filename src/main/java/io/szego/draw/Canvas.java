package io.szego.draw;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A fixed size plane of pixels that we can draw upon. The <code>width</code> and 
 * <code>height</code> are fixed at construction. The "color" of a pixel is represented as a
 * simple char.
 * 
 * <p>Coordinates in the API are expressed in the form (x,y), and are 1-based. 
 * 
 * <p>Commands to get and set pixels out of the bounds of the canvas are silently ignored: setting
 * a pixel has no effect; getting a pixel returns CHAR_NULL.
 */
public final class Canvas
{
    //  The character used to display horizontal lines in String output.
    public static final char    CHAR_HORZ   = '-';
    //  The character used to display vertical lines in String output.
    public static final char    CHAR_VERT   = '|';
    
    //  The initial value of every pixel.
    public static final char    CHAR_EMPTY  = ' ';
    
    //  The value returned for a pixel at an invalid coordinate. 
    public static final char    CHAR_NULL   = '\0';    

    //  Arbitrary maximum dimensions, for sanity.
    public static final int     MAX_WIDTH   = 100;
    public static final int     MAX_HEIGHT  = 100;
    
    
    /**
     * The width of the canvas, i.e. number of pixels across.
     */
    private final int           width;
    
    /**
     * The height of the canvas, i.e. number of pixels top to bottom.
     */
    private final int           height;
    
    /**
     * The content of the canvas. Uses the Java convention of [row][column] element addressing.
     */
    private final char[][]      pixels;

    /**
     * A convenience value, the pre-computed "header" line used for printing the contents.
     */
    private final char[]        header;

    
    /**
     * Create a new empty canvas of the specified size. Dimensions must be at least 1, and cannot
     * be greater that the maxmimums ({@link #MAX_WIDTH}, {@link #MAX_HEIGHT}).
     * 
     * @param   width   the width of the canvas, in pixels.
     * @param   height  the height of the canvas, in pixels.
     * 
     * @throws  IllegalArgumentException
     *              if either dimension is less than 1 or greater than the maximum.
     */
    public Canvas( final int width, final int height )
    {
        if ( width < 1 || width > MAX_WIDTH )
        {
            throw new IllegalArgumentException( "width" );
        }
        if ( height < 1 || height > MAX_HEIGHT )
        {
            throw new IllegalArgumentException( "height" );
        }
        
        this.width  = width;
        this.height = height;        
        
        this.pixels = new char[ height ][ width ];
        clear();

        this.header = new char[ width+2 ];        
        Arrays.fill( header, CHAR_HORZ );        
    }

    /**
     * Set all of the pixels to empty.
     */
    public void clear()
    {
        Stream.of( pixels ).forEach( row -> Arrays.fill( row, CHAR_EMPTY ) );
    }
    
    /**
     * Get the width of the canvas.
     * 
     * @return  the width of the canvas.
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Get the height of the canvas.
     * 
     * @return  the height of the canvas.
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Determine if a point is within the bounds of the canvas. Note the coordinates are 1-based.
     * 
     * @param   x   the x-coordinate of the point to check.
     * @param   y   the y-coordinate of the point to check.
     * 
     * @return  <code>true</code> if both the x and y coordinates are within bounds.
     */
    public boolean contains( final int x, final int y )
    {
        return ( x > 0 && x <= width && y > 0 && y <= height );
    }

    /**
     * Get the value of a pixel.
     * 
     * @param   x   the x-coordinate of the pixel value to get.
     * @param   y   the y-coordinate of the pixel value to get.
     * 
     * @return  the value of the pixel at that coordinate, or <code>CHAR_NULL</code> if the point
     *          is out of bounds.
     */
    public char getPixel( final int x, final int y )
    {
        if ( !contains( x, y ) )
        {
            return CHAR_NULL;
        }
        return pixels[y - 1][x - 1];
    }
    
    /**
     * Set the value of a pixel. If the coordinate is out of bounds this call has no effect.
     * 
     * @param   x       the x-coordinate of the pixel value to set.
     * @param   y       the y-coordinate of the pixel value to set.
     * @param   color   the value ( "color" ) to set the pixel to.
     */
    public void setPixel( final int x, final int y, final char color )
    {
        if ( contains( x, y ) )
        {
            pixels[y - 1][x - 1] = color;
        }
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash( width, height, Arrays.deepHashCode( pixels ) );
    }

    @Override
    public boolean equals( final Object obj )
    {        
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null || getClass() != obj.getClass() )
        {
            return false;
        }
        final Canvas other = (Canvas)obj;
        
        return Objects.equals( width, other.width )
            && Objects.equals( height, other.height )
            && Objects.deepEquals( pixels, other.pixels );
    }

    @Override
    public String toString()
    {
        final StringBuilder bld = new StringBuilder( ( width + 3 ) * ( height + 2 ) );

        bld.append( header );
        bld.append( System.lineSeparator() );
        for ( final char[] row: pixels )
        {
            bld.append( CHAR_VERT );
            bld.append( row );
            bld.append( CHAR_VERT );
            bld.append( System.lineSeparator() );
        }
        bld.append( header );
        bld.append( System.lineSeparator() );
        
        return bld.toString();
    }

}
