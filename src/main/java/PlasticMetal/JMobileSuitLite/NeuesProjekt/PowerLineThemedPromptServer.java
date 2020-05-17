package PlasticMetal.JMobileSuitLite.NeuesProjekt;

import PlasticMetal.JMobileSuitLite.*;
import PlasticMetal.JMobileSuitLite.IO.*;

/**
 * a power line themed prompt server for mobile suit
 */
public class PowerLineThemedPromptServer extends CommonPromptServer

{


    /**
     * Initialize a prompt default.
     */
    public PowerLineThemedPromptServer()
    {
        super();
    }

    /**
     * Initialize a prompt with IO and color setting.
     *
     * @param io given io server
     */
    private PowerLineThemedPromptServer(IOServer io)
    {
        super(io, ColorSetting.getInstance());
    }

    /**
     * Initialize a prompt Server with given configuration
     *
     * @param configuration given configuration
     */
    public PowerLineThemedPromptServer(SuitConfiguration configuration)
    {
        super(configuration);
    }

    /**
     * Create a mobile suit configuration with power line theme
     */
    public static SuitConfiguration getPowerLineThemeConfiguration()
    {
        IOServer io = new IOServer();
        SuitConfiguration r = new CommonSuitConfiguration(BuildInCommandServer.class, io,
                new PowerLineThemedPromptServer(io), ColorSetting.getInstance());
        io.Prompt = r.Prompt();
        return r;
    }

    /**
     * a lightning ⚡ char
     */
    protected static final char Lightning = '⚡';

    /**
     * a right arrow  char
     */
    protected static final char RightArrow = '';

    /**
     * a right triangle  char
     */
    protected static final char RightTriangle = '';
    /**
     * a cross ⨯ char
     */
    protected static final char Cross = '⨯';

    @Override
    public void Print()
    {
        ConsoleColor tbColor = ColorSetting.SelectColor(LastTraceBack.Value > 0 ?
                OutputType.Prompt : (LastTraceBack == TraceBack.AllOk ? OutputType.AllOk : OutputType.Error), null, Color);

        ConsoleColor lastColor = LastTraceBack == TraceBack.Prompt ? Color.ListTitleColor : Color.InformationColor;

        IO.Write(' ' + LastInformation + ' ', Color.DefaultColor, lastColor);

        if (LastReturnValue != null && !LastReturnValue.equals(""))
        {
            IO.Write(String.valueOf(RightTriangle),
                    lastColor, Color.CustomInformationColor);
            IO.Write(" " + Lang.ReturnValue + " " + RightArrow + " " + LastReturnValue + " ",
                    Color.DefaultColor, Color.CustomInformationColor);
            lastColor = Color.CustomInformationColor;
        }

        String tbExpression = "";
        switch (LastTraceBack)
        {

            case Prompt:
                tbExpression = LastPromptInformation;
                break;
            case OnExit:
                tbExpression = "";
                break;
            case AllOk:
                tbExpression = Lang.AllOK;
                break;
            case InvalidCommand:
                tbExpression = Lang.InvalidCommand;
                break;
            case ObjectNotFound:
                tbExpression = Lang.ObjectNotFound;
                break;
            case MemberNotFound:
                tbExpression = Lang.MemberNotFound;
                break;
        }


        IO.Write(String.valueOf(RightTriangle),
                lastColor, tbColor);
        IO.Write(" " + tbExpression + " ", Color.DefaultColor, tbColor);
        IO.Write(RightTriangle + " ", tbColor);

    }
}
