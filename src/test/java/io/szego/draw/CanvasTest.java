package io.szego.draw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CanvasTest
{
    @Test
    public void testCtor()
    {
        Canvas  c = new Canvas( 5, 6 );

        assertNotNull( c );
        assertEquals( 5, c.getWidth()  );
        assertEquals( 6, c.getHeight()  );
        
        assertFalse( c.contains( 0, 0 ) );
        assertFalse( c.contains( 0, 1 ) );
        assertFalse( c.contains( 1, 0 ) );
        
        assertFalse( c.contains( 6, 7 ) );
        assertFalse( c.contains( 5, 7 ) );
        assertFalse( c.contains( 6, 6 ) );  
        
        assertEquals( Canvas.CHAR_NULL, c.getPixel( 0, 0 ) );
        assertEquals( Canvas.CHAR_EMPTY, c.getPixel( 1, 1 ) );

//        assertTrue( c.isPixelEqual( 0, 0, Canvas.CHAR_NULL ) );
//        assertTrue( c.isPixelEqual( 1, 1, Canvas.CHAR_EMPTY ) );
//
//        assertFalse( c.isPixelEqual( 0, 0, Canvas.CHAR_EMPTY ) );
//        assertFalse( c.isPixelEqual( 1, 1, Canvas.CHAR_NULL ) );
        
        for ( int x = 1; x <= c.getWidth(); x++ )
        {
            for ( int y = 1; y <= c.getHeight(); y++ )
            {
                assertEquals( Canvas.CHAR_EMPTY, c.getPixel( x, y ) );
            }
        }
    }
    
    @Test( expected = IllegalArgumentException.class )
    public void testCtorWidthLow()
    {
        new Canvas( 0, 1 );
    }
    @Test( expected = IllegalArgumentException.class )
    public void testCtorWidthHi()
    {
        new Canvas( Canvas.MAX_WIDTH + 1, 1 );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testCtorHeightLow()
    {
        new Canvas( 1, 0 );
    }
    @Test( expected = IllegalArgumentException.class )
    public void testCtorHeightHi()
    {
        new Canvas( 1, Canvas.MAX_HEIGHT+ 1 );
    }
    
    @Test
    public void testSetPixel()
    {
        Canvas  c = new Canvas( 1, 1 );
        assertEquals( 
            "---\n| |\n---\n", 
            c.toString() 
        );

        c.setPixel( 0, 0, '*' );
        assertEquals( 
            "---\n| |\n---\n", 
            c.toString() 
        );

        c.setPixel( 1, 1, '*' );
        assertEquals( 
            "---\n|*|\n---\n", 
            c.toString() 
        );        
    }
    @Test
    public void testEquality()
    {
        EqualsVerifier
            .forClass( Canvas.class )
            .withIgnoredFields( "header" )
            .verify();
    }

    @Test
    public void testToString()
    {
        Canvas  c = new Canvas( 1, 1 );
        assertEquals( 
            "---\n| |\n---\n", 
            c.toString() 
        );
    }
}
