package io.szego.draw;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import io.szego.draw.command.BucketCommand;
import io.szego.draw.command.CreateCommand;
import io.szego.draw.command.LineCommand;
import io.szego.draw.command.QuitCommand;
import io.szego.draw.command.RectCommand;


/**
 * Contains the set of known {@link AbstractCommand}'s. Also contains a convenience method to
 * locate a parser and use it to parse a command line.  
 * 
 * @author paul
 */
public class Registry
{
    /**
     * The complete set of command descriptions.
     */
    private final Set<AbstractCommand>      commands;
    
    public Registry()
    {
        super();
        
        //  We could do this with Spring CDI, but you get the idea...
        commands = new HashSet<>();        
        commands.add( new CreateCommand() );
        commands.add( new LineCommand() );
        commands.add( new RectCommand() );
        commands.add( new BucketCommand() );
        commands.add( new QuitCommand() );
    }

    /**
     * 
     * @param line
     * @return
     */
    public Function<Canvas,Canvas> parse( final String line )
    {        
        if ( line.trim().isEmpty() )
        {
            //  Empty command line, ignore it.
            return AbstractCommand.IDENTITY;            
        }        
        final String[]  args = line.trim().split( "\\p{javaWhitespace}+" );
        
        return commands.stream()
            .map( command -> command.apply( args ) )
            .filter( Objects::nonNull )
            .findFirst()
            .orElseThrow( 
                () -> new IllegalArgumentException( "Unrecognized Command: " + args[0] ) 
            );
    }
    
}
