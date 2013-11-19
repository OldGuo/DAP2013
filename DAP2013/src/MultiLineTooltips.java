import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MultiLineTooltips
{
    private static int DIALOG_TOOLTIP_MAX_SIZE = 100;
    private static final int SPACE_BUFFER = 10;

    public static String splitToolTip(String tip)
    {
        return splitToolTip(tip,DIALOG_TOOLTIP_MAX_SIZE);
    }
    public static String splitToolTip(String tip,int length)
    {
    	//length = max tooltip size
        if(tip.length()<=length + SPACE_BUFFER)
        {
            return tip;
        }

        List<String>  parts = new ArrayList<String>();

        int maxLength = 0;
        String overLong = tip.substring(0, length + SPACE_BUFFER);
        int lastSpace = overLong.lastIndexOf(' ');
        if(lastSpace >= length)
        {
            parts.add(tip.substring(0,lastSpace));
            maxLength = lastSpace;
        }
        else
        {
            parts.add(tip.substring(0,length));
            maxLength = length;
        }

        while(maxLength < tip.length())
        {
            if(maxLength + length < tip.length())
            {
                parts.add(tip.substring(maxLength, maxLength + length));
                maxLength+=maxLength+length;
            }
            else
            {
                parts.add(tip.substring(maxLength));
                break;
            }
        }
        StringBuilder  sb = new StringBuilder("<html>");
        for(int i=0;i<parts.size() - 1;i++)
        {
        	System.out.println(parts.get(i) + "|");
            sb.append(parts.get(i)+"<br>");
        }
        sb.append(parts.get(parts.size() - 1));
        sb.append(("</html>"));
        return sb.toString();
    }
}