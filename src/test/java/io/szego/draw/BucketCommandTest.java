package io.szego.draw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Function;

import org.junit.Test;

import io.szego.draw.command.BucketCommand;

public class BucketCommandTest
{
    @Test
    public void testCreate()
    {
        BucketCommand   cmd = new BucketCommand();
        assertNotNull( cmd );
        
        assertEquals( "Bucket", cmd.getName() );
        assertEquals( BucketCommand.USAGE, cmd.getUsage() );
        assertEquals( 3, cmd.getArgCount() );
    }
    
    @Test
    public void testParse()
    {
        BucketCommand               cmd = new BucketCommand();
        Function<Canvas, Canvas>    f   = cmd.parse( "B", "1", "1", "*" );
        assertNotNull( f );
        
        Canvas  c = f.apply( new Canvas( 2, 2 ) );
        assertEquals( 
            "----\n" + 
            "|**|\n" +
            "|**|\n" +
            "----\n", 
            c.toString()
        );
    }    

    @Test
    public void testDodgeSpot()
    {
        Canvas                      c = new Canvas( 3, 3 );
        BucketCommand               cmd = new BucketCommand();
        Function<Canvas, Canvas>    f   = cmd.parse( "B", "1", "1", "." );
        
        c.setPixel( 2, 2, 'X' );
        c = f.apply( c );

        assertEquals( 
            "-----\n" + 
            "|...|\n" +
            "|.X.|\n" +
            "|...|\n" +
            "-----\n", 
            c.toString()
        );
    }    
}
