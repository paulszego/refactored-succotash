package io.szego.draw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Function;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.szego.draw.command.QuitCommand;

public class QuitCommandTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    @Test
    public void testCreate()
    {
        QuitCommand     cmd = new QuitCommand();
        assertNotNull( cmd );
        
        assertEquals( "Quit", cmd.getName() );
        assertEquals( QuitCommand.USAGE, cmd.getUsage() );
        assertEquals( 0, cmd.getArgCount() );
    }
    
    @Test
    public void testParse()
    {
        QuitCommand                 cmd = new QuitCommand();
        Function<Canvas, Canvas>    f   = cmd.parse( "Q" );
        assertNotNull( f );
        
        thrown.expect( RuntimeException.class );
        thrown.expectMessage( "Exit!" );        
        f.apply( null );
    }    
}
