
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;

public class UnitTest {


    public static List cacheAccesAdd(int chache, int bitAcdress,
                                     int blocks, List temp)
    {

        int offset = (int)(Math.log((double)blocks)/Math.log(2));
        int index = (int)(Math.log((double)chache/(double)blocks)/Math.log(2));
        int tag = bitAcdress - offset - index;
        temp.add(offset);
        temp.add(index);
        temp.add(tag);

        return temp;
    }

    public static List cacheAccesAdd(int chache, int bitAcdress,
                                     int blocks)
    {
        List<Integer> temp = new ArrayList<>();
        int offset = (int)(Math.log((double)blocks)/Math.log(2));
//        double offset = (Math.log((double)blocks));
        int index = (int)(Math.log((double)chache/(double)blocks)/Math.log(2));
        int tag = bitAcdress - offset - index;
        temp.add(offset);
        temp.add(index);
        temp.add(tag);

        return temp;
    }

    @Test
    public static void rightSize()
    {
        List temp;
        temp = new ArrayList<Integer>();
        temp = cacheAccesAdd(128,12,32,temp);
        Assert.assertEquals(5,temp.get(0));
        Assert.assertEquals(2,temp.get(1));
        Assert.assertEquals(5,temp.get(2));
    }

    @Test
    public  static void size()
    {
        List temp = new ArrayList<Integer>();

        //blocks in bytes
        //cache in bytes
        temp = cacheAccesAdd(128,12,32,temp);
        Assert.assertFalse(temp.isEmpty());
    }


}
