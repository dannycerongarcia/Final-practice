import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Main {

    @SomeAnnotation
    public String car = "";


    @SomeAnnotation
    public void methodWithAnno(){}

    public static void main(String[] args){

//-----------------------------------part 1. units testing
        //in the UnitTest class file
        UnitTest unitTest;

//-----------------------------------part 2 design pattern
            //Adapter
        SocketObjAdapter sockAdap = new SocketObjAdapter();
        System.out.println("no adapter "+sockAdap.get120Volt().getVolt()+" Adapter test: "+sockAdap.get12Volt().getVolt());
            //Facade

            //Decorator
        System.out.println("--------------------------------------Decorator--------------------------------------");
        Decoration object = new BasicPrint();
        object.basicPrint(42);
        System.out.println("\n\n\n");
        object = new secondPrint(object);
        object.basicPrint(43);
        System.out.println("\n\n\n");
        object= new LastPrint(object);
        object.basicPrint(44);
        System.out.println("\n\n\n");
            //Iterator

            //Observer.Observer(in the observer folder)

//-----------------------------------part 3 Reflection/Annotations
        System.out.println("--------------------------------------Reflection/Annotations--------------------------------------");

        Field[] fields = Main.class.getFields();
        Method[] methods = Main.class.getMethods();

        for (Field field : fields) {
            String data = field.getName();
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == SomeAnnotation.class) {
                    data += " -HAS MY ANNOTATION";
                }
            }
            System.out.println(data);
        }

        for (Method method : methods) {
            String data = method.getName();
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == SomeAnnotation.class) {
                    data += " -HAS MY ANNOTATION";
                }
            }
            System.out.println(data);
        }
//        Annotation can also be created like this out side of the class
//        @interface MyAnnotation{
//
//        }
//-----------------------------------part 4 Stream
        System.out.println("--------------------------------------Stream--------------------------------------");

        List<Integer> temp = UnitTest.cacheAccesAdd(128,12,32);
        temp.stream()
                .map(n->n)
                .forEach(n->{
                    System.out.println(n);
                });

        //set cannot have repeated values.
        //that's why there are only 2 values in a set.
        Set<Integer> set = temp.stream()
                .collect(Collectors.toSet());
        System.out.println(set);

//-----------------------------------part 5 Compiler Basics------------------------

        }


}//end of main

interface Decoration{
    void basicPrint(int temp);
    void basecPrint2(String temp);
}

class BasicPrint implements Decoration
{

    public void basicPrint(int temp) {
        System.out.println("BasicPrint");
        System.out.println(temp);
        System.out.println("BasicPrint end");
    }

    public void basecPrint2(String temp) {
    }
}

class secondPrint implements Decoration
{
    Decoration DecObject;
    secondPrint(Decoration temp)
    {
        DecObject = temp;
    }
    public void basicPrint(int temp) {
        System.out.println("SecondPrint");
        DecObject.basicPrint(temp);
        System.out.println("SecondPrint end");
    }

    public void basecPrint2(String temp) {
    }
}

class LastPrint extends secondPrint
{

    LastPrint(Decoration temp) {
        super(temp);
    }

    public void basicPrint(int temp) {
        System.out.println("LastPrint extend second print");
        super.basicPrint(temp);
        System.out.println("LastPrint end");
    }
}

//-----------------------------------------------------------------------------adaptor classes--------------------------
class Volt{
    private int volts;

    public Volt(int v)
    {
        this.volts=v;
    }

    public int getVolt(){
        return volts;
    }
    public void setVolts(){
        this.volts= volts;
    }
}
class Socket{
    public Volt getVolt(){
        return new Volt(120);
    }
}


interface SocketAdapter{
    public Volt get120Volt();
    public Volt get12Volt();
    public Volt get3Volt();

}

class SocketObjAdapter implements SocketAdapter
{
    Socket sock = new Socket();
    @Override
    public Volt get120Volt() {
        return sock.getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt v = sock.getVolt();
        return VoltConverter(v,10);
    }

    @Override
    public Volt get3Volt() {
        Volt v = sock.getVolt();
        return VoltConverter(v,40);
    }

    private Volt VoltConverter(Volt v, int i)
    {
        return new Volt(v.getVolt()/i);
    }
}
